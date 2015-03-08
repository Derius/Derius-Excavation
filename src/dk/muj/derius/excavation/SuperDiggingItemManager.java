package dk.muj.derius.excavation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.inventory.SpecialItemManager;

public class SuperDiggingItemManager implements SpecialItemManager
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static SuperDiggingItemManager i = new SuperDiggingItemManager();
	public static SuperDiggingItemManager get() { return i; }
	private SuperDiggingItemManager() {}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public ItemStack toSpecial(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		if (this.matches(item)) return item;
		if (this.isSpecial(item)) return item;
		// Meta
		ItemMeta meta = item.getItemMeta();
		
		// Set enchant
		int lvl = this.calcBuff(item);
		meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
		
		// Set lore
		List<String> lore = this.getLore(meta);
		lore.add(SuperDigging.ACTIVATED_LORE_TAG);
		meta.setLore(lore);
		
		// Apply
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public ItemStack toNormal(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		if ( ! this.isSpecial(item)) return item;
		
		int lvl = this.calcDebuff(item);
		
		ItemMeta meta = item.getItemMeta();
		
		List<String> lore = this.getLore(meta);
		lore.remove(SuperDigging.ACTIVATED_LORE_TAG);
		meta.setLore(lore);
		
		meta.removeEnchant(Enchantment.DIG_SPEED);
		if (lvl > 0) meta.addEnchant(Enchantment.DIG_SPEED, lvl, true);
		
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public boolean isSpecial(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		if ( ! matches(item)) return false;
		
		ItemMeta meta = item.getItemMeta();
		List<String> lore = this.getLore(meta);
		
		return lore.contains(SuperDigging.ACTIVATED_LORE_TAG);
	}

	@Override
	public boolean matches(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		return MUtil.isSpade(item);
	}
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //

	private List<String> getLore(ItemMeta meta)
	{
		return meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
	}
	
	private int calcBuff(ItemStack item)
	{
		int lvlBefore = item.getEnchantmentLevel(Enchantment.DIG_SPEED);
		if (lvlBefore < 0) lvlBefore = 0;
		int lvl = lvlBefore + ExcavationSkill.getEfficiencyBuff();
		
		return lvl;
	}
	
	private int calcDebuff(ItemStack item)
	{
		int lvlBefore = item.getEnchantmentLevel(Enchantment.DIG_SPEED);
		int lvl = lvlBefore - ExcavationSkill.getEfficiencyBuff();
		if (lvl < 0) lvl = 0;
		
		return lvl;
	}
	
}