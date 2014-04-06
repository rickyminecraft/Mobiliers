package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class ArmoireD 
{
	/*
	 * Type definitions
	 */
	public final static byte	HAUT = 0,
								BAS = 1;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data >>5;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TEBase TE, int type)
	{
		int temp = BlockProperties.getMetadata(TE) & 0xffbf;
		temp |= type <<5;
		
		BlockProperties.setMetadata(TE, temp);
	}
}
