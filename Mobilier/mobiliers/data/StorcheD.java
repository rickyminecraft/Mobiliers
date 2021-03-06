package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class StorcheD
{
	/*
	 * Type definitions
	 */
	public final static byte 	STORCHE_X_NEG = 0,
								STORCHE_X_POS = 1,
								STORCHE_Z_NEG = 2,
								STORCHE_Z_POS = 3;
	// xpos south
	// xneg north
	// zpos west
	// zneg east
	// ypos top
	// yneg bottom
	
	/*
	 * Type definitions
	 */
	public final static byte	ETEINT = 0,
								ALLUME = 1;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data >>3 & 0x3;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TEBase TE, int type)
	{
		int data = BlockProperties.getMetadata(TE) & 0xfff3;
		data |= type <<3;
		
		BlockProperties.setMetadata(TE, data);
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
		int data = BlockProperties.getMetadata(TE) & 0xfffc;
		data |= Rotation;
		
		BlockProperties.setMetadata(TE, data);
	}
}
