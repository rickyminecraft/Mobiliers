package mobiliers.data;

import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class TableD
{
	/*
	 * rotation definitions
	 */
	public final static byte	ROTATE_0 = 0,
								ROTATE_1 = 1;
	
	/*
	 * Type definitions
	 */
	public final static byte	TYPE_NORMAL = 0,
								TYPE_GLASS = 1,
								TYPE_BAS = 2,
								TYPE_BAS_GLASS = 3;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data >>2;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TECarpentersBlock TE, int type)
	{
		int temp = BlockProperties.getData(TE) & 0xfff3;
		temp |= type <<2;
		
		BlockProperties.setData(TE, temp);
	}
	
	/**
	 * Returns data.
	 */
	public final static int getRotation(int data)
	{
		return data & 0x3;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setRotation(TECarpentersBlock TE, int Rotation)
	{
		int temp = BlockProperties.getData(TE) & 0xfffc;
		temp |= Rotation;
		
		BlockProperties.setData(TE, temp);
	}
}