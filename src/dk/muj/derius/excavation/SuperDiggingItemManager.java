package dk.muj.derius.excavation;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.inventory.SpecialItemManagerEnchant;

public class SuperDiggingItemManager extends SpecialItemManagerEnchant
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
	public Collection<Material> getToolTypes()
	{
		return MUtil.SPADE_MATERIALS;
	}
	
	@Override
	public String getLoreTag()
	{
		return SuperDigging.ACTIVATED_LORE_TAG;
	}
	
	@Override
	public Enchantment getEnchantment()
	{
		return Enchantment.DIG_SPEED;
	}
	
	@Override
	public int getBuff()
	{
		return ExcavationSkill.getEfficiencyBuff();
	}
	
}
