package dk.muj.derius.excavation;

import org.bukkit.enchantments.Enchantment;

import dk.muj.derius.api.inventory.SpecialItemManagerDefault;

public class SuperDiggingItemManager extends SpecialItemManagerDefault
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
