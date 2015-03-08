package dk.muj.derius.excavation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.util.AbilityUtil;

public class ExcavationEngine extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static ExcavationEngine i = new ExcavationEngine();
	public static ExcavationEngine get() { return i; }
	private ExcavationEngine() { }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return DeriusExcavation.get();
	}

	// -------------------------------------------- //
	// EVENT
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void blockBreakThingy(BlockBreakEvent event)
	{
		// Just in case
		if (event == null) return;
		
		// Get objects
		Player player = event.getPlayer();
		DPlayer dplayer = DeriusAPI.getDPlayer(player);
		ItemStack inHand = player.getItemInHand();
		Block block = event.getBlock();
		Material type = block.getType();
		
		// Checks
		if ( ! MUtil.isSpade(inHand)) return;
		if (DeriusAPI.isBlockPlacedByPlayer(block)) return;
		
		// Super Digging
		Optional<Material> optPrepared = dplayer.getPreparedTool();
		// If this block type can activate super mining. They have prepared a toll & it is a pickaxe.
		if (ExcavationSkill.getSuperDiggingBlocks().contains(type) && optPrepared.isPresent() && MUtil.SPADE_MATERIALS.contains(optPrepared.get()))
		{
			AbilityUtil.activateAbility(dplayer, SuperDigging.get(), null, VerboseLevel.LOW);
		}
		
		// Rewards
		List<ItemStack> items = DeriusExcavation.getRewardMixin().getRewards(dplayer, block);
		if (AbilityUtil.activateAbility(dplayer, TreasureDigging.get(), items, VerboseLevel.ALWAYS) != AbilityUtil.CANCEL)
		{
			items.forEach(item -> block.getWorld().dropItemNaturally(block.getLocation(), item));
		}
		
		// Exp gain
		Map<Material, Integer> expGain = ExcavationSkill.getExpGain();
		if (expGain.containsKey(type))
		{
			dplayer.addExp(ExcavationSkill.get(), expGain.get(type));
		}
		
		return;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void changeDurability(PlayerItemDamageEvent event)
	{
		ItemStack item = event.getItem();
		if ( ! MUtil.isPickaxe(item)) return;
		DPlayer dplayer = DeriusAPI.getDPlayer(event.getPlayer());
		AbilityUtil.activateAbility(dplayer, CarefulDigging.get(), event, VerboseLevel.ALWAYS);
		
	}
}
