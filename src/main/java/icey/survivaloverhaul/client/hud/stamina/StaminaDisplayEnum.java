package icey.survivaloverhaul.client.hud.stamina;

public enum StaminaDisplayEnum
{
	ABOVE_ARMOR("above_armor"),
	BAR("bar"),
	NONE("none");
	
	private String displayType;
	
	private StaminaDisplayEnum(String displayType)
	{
		this.displayType = displayType;
	}
	
	public String getDisplayType()
	{
		return displayType;
	}
}
