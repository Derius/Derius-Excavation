package dk.muj.derius.excavation;

import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;

public class TreasureDigging extends AbilityAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static TreasureDigging i = new TreasureDigging();
	public static TreasureDigging get() { return i; }
	private TreasureDigging() { }

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
		return "derius:excavation:treasure";
	}

	@Override
	public String getLvlDescriptionMsg(int lvl)
	{
		return null;
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		return null;
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{

	}

}
