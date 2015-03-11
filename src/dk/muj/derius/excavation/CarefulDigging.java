package dk.muj.derius.excavation;

import java.util.OptionalDouble;

import org.bukkit.event.player.PlayerItemDamageEvent;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;
import dk.muj.derius.api.util.AbilityUtil;
import dk.muj.derius.api.util.LevelUtil;

public class CarefulDigging extends AbilityAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static CarefulDigging i = new CarefulDigging();
	public static CarefulDigging get() { return i; }
	private CarefulDigging()
	{
		this.setDesc("Extends shovel durability");
		
		this.setName("Careful Digging");
		
		this.setType(AbilityType.PASSIVE);
		this.setCooldownMillis(-1);
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
	public String getLvlDescriptionMsg(int lvl)
	{
		OptionalDouble optMultiplier = LevelUtil.getLevelSettingFloat(ExcavationSkill.getDurabilityMultiplier(), lvl);
		if ( ! optMultiplier.isPresent()) return "<i>No change";
		double multiplier = optMultiplier.getAsDouble();
		return String.format("<i>Shovel durability multiplied by <h>%.2f", multiplier);
	}
	
	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		if ( ! (other instanceof PlayerItemDamageEvent))return AbilityUtil.CANCEL;
		PlayerItemDamageEvent event = (PlayerItemDamageEvent) other;
		
		
		int level = dplayer.getLvl(this.getSkill());
		OptionalDouble optMultiplier = LevelUtil.getLevelSettingFloat(ExcavationSkill.getDurabilityMultiplier(), level);
		if ( ! optMultiplier.isPresent()) return AbilityUtil.CANCEL;
		double multiplier = optMultiplier.getAsDouble();
		
		// EXAMPLES: 1 / 3 = 0.333 so if multiplier is 3 in every third case damage occurs
		// EXAMPLES: 1 / 0.333 = 3 so if multiplier is lower than 1. Damage will be multiplied.
		int damage = (int) MUtil.probabilityRound(1D / multiplier);
		event.setDamage(damage);
		
		return damage; // Return is unused.
	}
	
	@Override
	public void onDeactivate(DPlayer p, Object other)
	{
		
	}
}
