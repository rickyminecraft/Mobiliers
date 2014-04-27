package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class TabouretD
{
	/*
	 * Type definitions
	 */
	public final static byte	TYPE_1 = 0,
								TYPE_2 = 1;
	
	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TEBase TE, int type)
	{
		int data = BlockProperties.getMetadata(TE) & 0xfffC;
		data |= type;
		
		BlockProperties.setMetadata(TE, data);
	}
}
