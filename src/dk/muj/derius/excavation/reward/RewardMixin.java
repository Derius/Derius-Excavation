package dk.muj.derius.excavation.reward;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.DPlayer;

public interface RewardMixin
{
	public List<ItemStack> getRewards(DPlayer dplayer, Block block);
	
}
