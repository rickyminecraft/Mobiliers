package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class EffetsD
{
	/*
	 * Type definitions
	 */
	public final static byte 	FOG = 0,
								BRUME = 1,
								SMOKE = 2;
							//EFFET_0 = 3;
//								BANC_Y_NEG = 4,
//								BANC_Y_POS = 5;
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
		int data = BlockProperties.getMetadata(TE) & 0xfff7;
		data |= type <<3;
		
		BlockProperties.setMetadata(TE, data);
	}
	
	/**
	 * Returns data.
	 */
	public final static int getEffet(int data)
	{
		return data & 0x7;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setEffet(TEBase TE, int Effet)
	{
		int data = BlockProperties.getMetadata(TE) & 0xfff8;
		data |= Effet;
		
		BlockProperties.setMetadata(TE, data);
	}
}
