package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class FenetreD
{
	public final static byte 	FENETRE_X = 0,
								FENETRE_Z = 1;
	
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
