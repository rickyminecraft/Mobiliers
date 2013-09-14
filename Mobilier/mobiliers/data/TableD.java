package mobiliers.data;

import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class TableD
{
	/*
	 * Type definitions
	 */
	public final static byte	TYPE_NORMAL = 0,
								TYPE_GLASS_1 = 1;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data & 0xf;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TECarpentersBlock TE, int type)
	{
		int temp = BlockProperties.getData(TE) & 0xfff0;
		temp |= type;
		
		BlockProperties.setData(TE, temp);
	}
}
