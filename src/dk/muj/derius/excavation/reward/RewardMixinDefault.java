package dk.muj.derius.excavation.reward;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.DPlayer;
import dk.muj.derius.excavation.ExcavationSkill;

public class RewardMixinDefault implements RewardMixin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static RewardMixinDefault i = new RewardMixinDefault();
	public static RewardMixinDefault get() { return i; }
	public RewardMixinDefault() {}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<ItemStack> getRewards(DPlayer dplayer, Block block)
	{
		List<ItemStack> ret = new ArrayList<ItemStack>();
		
		Material type = block.getType();
		
		for (Reward reward : ExcavationSkill.getRewards())
		{
			if ( ! reward.getBlocksToGetFrom().contains(type)) continue;
			if (reward.getMinimumLevel() > dplayer.getLvl(ExcavationSkill.get())) continue;
			if (MUtil.probabilityRound(reward.getChance()) == 0) continue;
			
			ret.add(reward.getItem());
		}
		
		return null;
	}
	
}
