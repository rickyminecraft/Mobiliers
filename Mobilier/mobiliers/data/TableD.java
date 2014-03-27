package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
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
	public final static void setType(TEBase TE, int type)
	{
		int temp = BlockProperties.getMetadata(TE) & 0xfff3;
		temp |= type <<2;
		
		BlockProperties.setMetadata(TE, temp);
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
	public final static void setRotation(TEBase TE, int Rotation)
	{
		int temp = BlockProperties.getMetadata(TE) & 0xfffc;
		temp |= Rotation;
		
		BlockProperties.setMetadata(TE, temp);
	}
}
