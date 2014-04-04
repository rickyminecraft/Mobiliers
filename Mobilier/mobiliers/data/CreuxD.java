package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class CreuxD
{
	/*
	 * Type definitions
	 */
	public final static byte 	CREUX_X_NEG = 0,
								CREUX_X_POS = 1,
								CREUX_Z_NEG = 2,
								CREUX_Z_POS = 3,
								CREUX_Y_NEG = 4,
								CREUX_Y_POS = 5;
	// xpos south
	// xneg north
	// zpos west
	// zneg east
	// ypos top
	// yneg bottom
	
	/*
	 * Type definitions
	 */
	public final static byte	BANC = 0,
								BORD = 1;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data >>3;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TEBase TE, int type)
	{
		int temp = BlockProperties.getMetadata(TE) & 0xfff7;
		temp |= type <<3;
		
		BlockProperties.setMetadata(TE, temp);
	}
	
	/**
	 * Returns data.
	 */
	public final static int getRotation(int data)
	{
		return data & 0x7;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setRotation(TEBase TE, int Rotation)
	{
		int temp = BlockProperties.getMetadata(TE) & 0xfff8;
		temp |= Rotation;
		
		BlockProperties.setMetadata(TE, temp);
	}
}
