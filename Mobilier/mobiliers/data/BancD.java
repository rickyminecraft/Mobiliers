package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class BancD
{
	/*
	 * Type definitions
	 */
	public final static byte 	BANC_X_NEG = 0,
								BANC_X_POS = 1,
								BANC_Z_NEG = 2,
								BANC_Z_POS = 3;
	// xpos south
	// xneg north
	// zpos west
	// zneg east
	// ypos top
	// yneg bottom
	
	/*
	 * Type definitions
	 */
	public final static byte	BANC_1 = 0,
								BANC_2 = 1,
								BANC_3 = 2,
								BANC_4 = 3;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data >>2 & 0x3; //xxxxoxxx le o est le bit qui nous interesse -> xxxxooxx
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TEBase TE, int type)
	{
		int data = BlockProperties.getMetadata(TE) & 0xFFF3; //11110111 -> 11110011
		data |= type <<2;
		
		BlockProperties.setMetadata(TE, data);
	}
	
	/**
	 * Returns data.
	 */
	public final static int getRotation(int data)
	{
		return data & 0x3; //xxxxxooo -> xxxxxxoo
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setRotation(TEBase TE, int Rotation)
	{
		int data = BlockProperties.getMetadata(TE) & 0xFFFC; //11111000 -> 11111100
		data |= Rotation;
		
		BlockProperties.setMetadata(TE, data);
	}
}
