package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class PupitreD
{
	/*
	 * Type definitions
	 */
	public final static byte 	PUPITRE_X_NEG = 0,
								PUPITRE_X_POS = 1,
								PUPITRE_Z_NEG = 2,
								PUPITRE_Z_POS = 3;
	// xpos south
	// xneg north
	// zpos west
	// zneg east
	// ypos top
	// yneg bottom
	
	/*
	 * Type definitions
	 */
	public final static byte	VIDE = 0,
								PLEIN = 1;

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
