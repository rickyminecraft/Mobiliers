package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class QuartD
{
	/*
	 * rotation definitions
	 */
	public final static byte	XPOS = 0,
								XPOSCENTRE = 1,
								XNEG = 2,
								ZNEGCENTRE = 3,
								ZNEG = 4,
								XNEGCENTRE = 5,
								ZPOS = 6,
								ZPOSCENTRE = 7,	
								XPOSLARGE = 8,
								XNEGLARGE = 9,
								ZNEGLARGE = 10,
								ZPOSLARGE = 11,
								CENTRE = 12;


	/*
	 * Type definitions
	 */
	public final static byte	PLEIN = 0,
								BAS = 1,
								MILIEU = 2,
								HAUT = 3;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data >>4;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TEBase TE, int type)
	{
		int data = BlockProperties.getMetadata(TE) & 0xff0f;
		data |= type <<4;
		
		BlockProperties.setMetadata(TE, data);
	}
	
	/**
	 * Returns data.
	 */
	public final static int getRotation(int data)
	{
		return data & 0xF;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setRotation(TEBase TE, int Rotation)
	{
		int data = BlockProperties.getMetadata(TE) & 0xfff0;
		data |= Rotation;
		
		BlockProperties.setMetadata(TE, data);
	}
}
