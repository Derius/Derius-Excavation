package dk.muj.derius.excavation;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
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
	// LISTENER
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void blockBreakThingy(BlockBreakEvent event)
	{	
		// Get objects
		Player player = event.getPlayer();
		DPlayer dplayer = DeriusAPI.getDPlayer(player);
		Block block = event.getBlock();
		
		// Checks
		if ( ! MUtil.isSpade(event)) return;
		if (DeriusAPI.isBlockPlacedByPlayer(block)) return;

		// Rewards
		List<ItemStack> items = DeriusExcavation.getRewardMixin().getRewards(dplayer, block);
		if (AbilityUtil.activateAbility(dplayer, TreasureDigging.get(), items, VerboseLevel.ALWAYS) != AbilityUtil.CANCEL)
		{
			items.forEach(item -> block.getWorld().dropItemNaturally(block.getLocation(), item));
		}
		
		return;
	}

}
