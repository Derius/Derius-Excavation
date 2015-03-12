package dk.muj.derius.excavation.reward;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Reward
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private ItemStack item;
	public ItemStack getItem() { return item; }
	public void setItem(ItemStack item) { this.item = item; }
	
	private int minimumLevel;
	public int getMinimumLevel() { return minimumLevel; }
	public void setMinimumLevel(int minimumLevel) { this.minimumLevel = minimumLevel; }
	
	private int maximumLevel;
	public int getMaximumLevel() { return maximumLevel; }
	public void setmMaximumLevel(int maximumLevel) { this.maximumLevel = maximumLevel; }
	
	// Chance is between 0.0 and 1.0
	private double chance;
	public double getChance() { return chance; }
	public void setChance(double chance) { this.chance = chance; }
	
	private int expGain = 0;
	public int getExpGain() { return expGain; }
	public void setExpGain(int expGain) { this.expGain = expGain; }
	
	private List<Material> blocksToGetFrom;
	public List<Material> getBlocksToGetFrom() { return blocksToGetFrom; }
	public void setBlocksToGetFrom(List<Material> blocksToGetFrom) { this.blocksToGetFrom = blocksToGetFrom; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	// GSON
	public Reward()
	{
		this.item = null;
		this.minimumLevel = 0;
		this.maximumLevel = 0;
		this.chance = 0.0;
		this.blocksToGetFrom = new ArrayList<>();
	}
	
	public Reward(ItemStack item, int minimumLevel, int maximumLevel, double chance, List<Material> blocksToGetFrom)
	{
		this.item = item;
		this.minimumLevel = minimumLevel;
		this.chance = chance;
		this.blocksToGetFrom = blocksToGetFrom;
	}
	
}
