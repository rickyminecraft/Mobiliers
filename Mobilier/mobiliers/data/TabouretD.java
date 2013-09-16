package mobiliers.data;

import carpentersblocks.tileentity.TECarpentersBlock;
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
	public final static void setType(TECarpentersBlock TE, int type)
	{
		int temp = BlockProperties.getData(TE) & 0xfffC;
		temp |= type;
		
		BlockProperties.setData(TE, temp);
	}
}
