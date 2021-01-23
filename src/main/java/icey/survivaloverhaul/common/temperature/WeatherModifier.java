package icey.survivaloverhaul.common.temperature;

import icey.survivaloverhaul.Main;
import icey.survivaloverhaul.api.temperature.ModifierBase;
import icey.survivaloverhaul.config.Config;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.RainType;

public class WeatherModifier extends ModifierBase
{
	public WeatherModifier()
	{
		super();
		this.setRegistryName(Main.MOD_ID, "weather");
	}
	
	// TODO: Try and get this also working with serene seasons
	@Override
	public float getWorldInfluence(World world, BlockPos pos)
	{
		if (world.isRainingAt(pos) && world.canSeeSky(pos))
		{
			Biome biome = world.getBiome(pos);
			
			if (biome.getPrecipitation() == RainType.SNOW)
			{
				return (float) Config.BakedConfigValues.snowTemperatureModifier;
			}
			else if (biome.getPrecipitation() == RainType.RAIN)
			{
				return (float) Config.BakedConfigValues.rainTemperatureModifier;
			}
		}
		
		return 0.0f;
	}
}