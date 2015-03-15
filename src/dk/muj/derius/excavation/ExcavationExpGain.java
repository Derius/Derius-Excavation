package dk.muj.derius.excavation;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.BlockBreakExpGain;
import dk.muj.derius.api.skill.Skill;

public class ExcavationExpGain implements BlockBreakExpGain
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static ExcavationExpGain i = new ExcavationExpGain();
	public static ExcavationExpGain get() { return i; }
	private ExcavationExpGain() { }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Map<Material, Integer> getBlockTypes()
	{
		return ExcavationSkill.getExpGain();
	}
	
	@Override
	public Collection<Material> getToolTypes()
	{
		return MUtil.SPADE_MATERIALS;
	}
	
	@Override
	public Skill getSkill()
	{
		return ExcavationSkill.get();
	}

}
