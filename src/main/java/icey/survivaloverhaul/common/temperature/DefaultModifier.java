package icey.survivaloverhaul.common.temperature;

import icey.survivaloverhaul.Main;
import icey.survivaloverhaul.api.temperature.ModifierBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DefaultModifier extends ModifierBase
{
	public DefaultModifier()
	{
		super();
		this.setRegistryName(Main.MOD_ID, "default");
	}
	
	@Override
	public float getWorldInfluence(World world, BlockPos pos)
	{
		return defaultTemperature;
	}
}
