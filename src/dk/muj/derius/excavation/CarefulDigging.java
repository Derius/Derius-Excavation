package dk.muj.derius.excavation;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.ability.AbilityDurabilityMultiplier;
import dk.muj.derius.api.skill.Skill;

public class CarefulDigging extends AbilityDurabilityMultiplier
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static CarefulDigging i = new CarefulDigging();
	public static CarefulDigging get() { return i; }
	private CarefulDigging()
	{
		this.setName("Careful Digging");
	}
	
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
		return "derius:excavation:careful";
	}
	
	@Override
	public Collection<Material> getToolTypes()
	{
		return MUtil.SPADE_MATERIALS;
	}
	
	@Override
	public Map<Integer, Double> getDurabilityMultiplier()
	{
		return ExcavationSkill.getDurabilityMultiplier();
	}
	
	@Override
	public String getToolName()
	{
		return "Shovel";
	}

}
