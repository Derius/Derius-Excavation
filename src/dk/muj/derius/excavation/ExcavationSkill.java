package dk.muj.derius.excavation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.xlib.gson.reflect.TypeToken;

import dk.muj.derius.api.skill.SkillAbstract;
import dk.muj.derius.excavation.reward.Reward;

public class ExcavationSkill extends SkillAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ExcavationSkill i = new ExcavationSkill();
	public static ExcavationSkill get() { return i; }
	
	public ExcavationSkill()
	{		
		this.setName("Excavation");
		this.setDesc("Makes you able to dig up treasures");
		this.setIcon(Material.DIAMOND_SPADE);
		this.addEarnExpDescs("Dig up the ground");
		
		Map<Material, Integer> expMap = MUtil.map(
				Material.DIRT, 10,
				Material.SAND, 10,
				Material.SOUL_SAND, 50,
				Material.CLAY, 50,
				Material.LAPIS_ORE, 50,
				Material.GRAVEL, 10,
				Material.GRASS, 25,
				Material.MYCEL, 50,
				Material.SNOW, 10,
				Material.SNOW_BLOCK, 10
				);
			
		this.writeConfig(Const.JSON_EXP_GAIN, expMap, new TypeToken<Map<Material, Integer>>(){});
		this.writeConfig(Const.JSON_SUPER_DIGGING_BLOCKS, expMap.keySet(), new TypeToken<Set<Material>>(){});
		this.writeConfig(Const.JSON_EFFICIENCY_BUFF, 5);
		
		this.writeConfig(Const.JSON_CAREFUL_DIGGING, MUtil.map(
				0, 0.5,
				1000, 1.5,
				2000, 3.0), new TypeToken<Map<Integer, Double>>(){});
		
		this.writeConfig(Const.JSON_REWARDS, MUtil.list(
				new Reward(new ItemStack(Material.GLOWSTONE_DUST), 1, 25, 0.01, MUtil.list(Material.SAND, Material.DIRT))
				), new TypeToken<List<Reward>>(){});
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public String getId() 
	{
		return "derius:excavation";
	}
	

	@Override
	public Plugin getPlugin()
	{
		return DeriusExcavation.get();
	}
	
	// -------------------------------------------- //
	// CONFIG
	// -------------------------------------------- //
	
	public static int getEfficiencyBuff()
	{
		return get().readConfig(Const.JSON_EFFICIENCY_BUFF, Integer.TYPE);
	}
	
	public static Map<Material, Integer> getExpGain()
	{
		return get().readConfig(Const.JSON_EXP_GAIN, new TypeToken<Map<Material, Integer>>(){});
	}

	public static Set<Material> getSuperDiggingBlocks()
	{
		return get().readConfig(Const.JSON_SUPER_DIGGING_BLOCKS, new TypeToken<Set<Material>>(){});
	}
	
	public static Map<Integer, Double> getDurabilityMultiplier()
	{
		return get().readConfig(Const.JSON_SUPER_DIGGING_BLOCKS, new TypeToken<Map<Integer, Double>>(){});
	}
	
	public static List<Reward> getRewards()
	{
		return get().readConfig(Const.JSON_REWARDS, new TypeToken<List<Reward>>(){});
	}

}
