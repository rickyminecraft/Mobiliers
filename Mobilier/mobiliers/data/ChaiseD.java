package mobiliers.data;

import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class ChaiseD
{
	public final static byte	CHAISE_X_NEG = 0,
								CHAISE_X_POS = 1,
								CHAISE_Z_NEG = 2,
								CHAISE_Z_POS = 3;
	
	/*
	 * Type definitions
	 */
	public final static byte	TYPE_1 = 0,
								TYPE_2 = 1,
								TYPE_3 = 2,
								TYPE_4 = 3;
	
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