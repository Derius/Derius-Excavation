package dk.muj.derius.excavation;

import java.util.Collection;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.ability.AbilitySpecialItem;
import dk.muj.derius.api.inventory.SpecialItemManager;
import dk.muj.derius.api.skill.Skill;

public class SuperDigging extends AbilitySpecialItem
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static SuperDigging i = new SuperDigging();
	public static SuperDigging get() { return i; }
	
	public SuperDigging()
	{
		this.setDesc("Digs faster");
		
		this.setName("Super Digging");
	}
	
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static final String ACTIVATED_LORE_TAG = Txt.parse("<lime>SuperDigging Shovel");
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Skill getSkill()
	{
		return ExcavationSkill.get();
	}

	@Override
	public String getId()
	{
		return "derius:excavation:superdigging";
	}

	@Override
	public SpecialItemManager getSpecialItemManager()
	{
		return SuperDiggingItemManager.get();
	}

	@Override
	public Collection<Material> getToolTypes()
	{
		return MUtil.SPADE_MATERIALS;
	}

	@Override
	public Collection<Material> getBlockTypes()
	{
		return ExcavationSkill.getSuperDiggingBlocks();
	}

}
