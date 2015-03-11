package dk.muj.derius.excavation;

import java.util.LinkedHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.ability.AbilityAbstract;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqCooldownIsExpired;
import dk.muj.derius.api.skill.Skill;
import dk.muj.derius.api.util.AbilityUtil;

public class SuperDigging extends AbilityAbstract
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
		
		this.setType(AbilityType.ACTIVE);
		
		this.addActivateRequirements(ReqCooldownIsExpired.get());
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
	public String getLvlDescriptionMsg(int lvl)
	{
		int millis = this.getDurationMillis(lvl);
		
		LinkedHashMap<TimeUnit, Long> unitcounts = TimeDiffUtil.limit(TimeDiffUtil.unitcounts(millis, TimeUnit.getAllButMillis()), 3);
		
		String entry = Txt.parse("<v>%1$d <k>%3$s");
		String comma = TimeDiffUtil.FORMAT_COMMA_VERBOOSE;
		String and = TimeDiffUtil.FORMAT_AND_VERBOOSE;
		String durationDesc = TimeDiffUtil.formated(unitcounts, entry, comma, and, "<yellow>");
		
		return "<i>Lasts " + durationDesc;
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		if ( ! dplayer.isPlayer()) return AbilityUtil.CANCEL;
		Player player = dplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		if (inHand == null || inHand.getType() == Material.AIR) return AbilityUtil.CANCEL;
		
		SuperDiggingItemManager.get().toSpecial(inHand);
		
		player.updateInventory();
		return player.getItemInHand();
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		if ( ! dplayer.isPlayer()) return;
		
		SuperDiggingItemManager.get().clearInventory(dplayer.getPlayer().getInventory());
		
		dplayer.getPlayer().updateInventory();
		
		return;
	}

}
