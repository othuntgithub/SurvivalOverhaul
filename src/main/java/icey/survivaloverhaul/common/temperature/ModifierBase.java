package icey.survivaloverhaul.common.temperature;

import icey.survivaloverhaul.api.temperature.ITemperatureModifier;
import icey.survivaloverhaul.api.temperature.TemperatureEnum;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public abstract class ModifierBase implements ITemperatureModifier
{
	
	
	/**
	 * Unique World Modifiers
	 * 
	 * Altitude
	 * Biome
	 * Default
	 * Season // requires serene seasons
	 * Snow
	 * Time
	 * Humidity
	 * --
	 * Proximity World Modifiers
	 * 
	 * Blocks
	 * Tile Entities
	 * --
	 * Unique Player Modifiers
	 * 
	 * Armor
	 * Sprinting
	 * Temporary
	 */
	
	private final String name;
	protected final float defaultTemperature;
	
	protected ModifierBase(String name)
	{
		this.name = name;
		this.defaultTemperature = (TemperatureEnum.NORMAL.getUpperBound() + TemperatureEnum.COLD.getUpperBound()) / 2;
	}
	
	@Override
	public float getPlayerInfluence(PlayerEntity player) { return 0.0f; }
	
	@Override
	public float getWorldInfluence(World world, BlockPos pos) { return 0.0f; }
	
	@Override
	public String getName() { return name; }
	
	protected float applyUndergroundEffect(float temperature, World world, BlockPos pos)
	{
		// Code ripped and modified from 
		// https://github.com/Charles445/SimpleDifficulty/blob/v0.3.4/src/main/java/com/charles445/simpledifficulty/temperature/ModifierBase.java
		
		//Y 64 - 256 is always unchanged temperature
		if (pos.getY() > 64)
		{
			return temperature;
		}
		
		// If we're in a dimension that has a ceiling,
		// then just return the default value.
		if(!true|| world.getDimensionType().getHasCeiling())
		{
			return temperature;
		}
		
		// Charles445's comments in this part of the code say
		// that there's probably an easier way to do this
		// that takes into account distance inside of a cave,
		// but fucked if I know
		if(world.canSeeSky(pos) || world.canSeeSky(pos.up()))
		{
			return temperature;
		}
		
		int cutoff = 48;
		
		if (pos.getY() <= cutoff || cutoff == 64)
		{
			return 0.0f;
		}
		
		return temperature * (float)(pos.getY() - cutoff) / (64.0f - cutoff);
	}
	
	protected float getTempForBiome(Biome biome)
	{
		// Get the biome's temperature, clamp it between 0 and 1.35,
		// and then normalize it to a value between 0 and 1.
		return MathHelper.clamp(biome.getTemperature(), 0.0f, 1.35f)/ 1.35f;
	}
	
	protected float normalizeToPosNeg(float value)
	{
		return (value * 2.0f) - 1.0f;
	}
}
