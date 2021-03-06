package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class BuffetD 
{
	/*
	 * Type definitions
	 */
	public final static byte	GAUCHE = 0,
								DROITE = 1;

	/**
	 * Returns data.
	 */
	public final static int getType(int data)
	{
		return data >>5;
	}
	
	/**
	 * Sets data (vanilla, picket, etc).
	 */
	public final static void setType(TEBase TE, int type)
	{
		int data = BlockProperties.getMetadata(TE) & 0xffbf;
		data |= type <<5;
		
		BlockProperties.setMetadata(TE, data);
	}
}
