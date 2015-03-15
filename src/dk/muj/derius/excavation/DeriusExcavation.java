package dk.muj.derius.excavation;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.excavation.reward.RewardMixin;
import dk.muj.derius.excavation.reward.RewardMixinDefault;

public final class DeriusExcavation extends MassivePlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static DeriusExcavation i;
	public static DeriusExcavation get() { return i; }
	private DeriusExcavation() { i = this; }
	
	// -------------------------------------------- //
	// REWARD MIXIN
	// -------------------------------------------- //
	
	private static RewardMixin rewardMixin = RewardMixinDefault.get();
	public static RewardMixin getRewardMixin() { return rewardMixin; }
	public static void setRewardMixin(RewardMixin val) { rewardMixin = val; }
	
	// -------------------------------------------- //
	// OVERRIDE: PLUGIN
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! this.preEnable()) return;
	
		ExcavationSkill.get().register();
		SuperDigging.get().register();
		CarefulDigging.get().register();
		
		DeriusAPI.registerPreparableTools(MUtil.SPADE_MATERIALS);
		DeriusAPI.getBlockMixin().addBlockTypesToListenFor(ExcavationSkill.getExpGain().keySet());
		
		this.postEnable();
	}
	
}
