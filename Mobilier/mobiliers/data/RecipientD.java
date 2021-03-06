package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class RecipientD
{
	/*
	 * Type definitions
	 */
	public final static byte CENTRER = 0, 
							RECIPIENT_X_NEG = 1, 
							RECIPIENT_X_POS = 2, 
							RECIPIENT_Z_NEG = 3, 
							RECIPIENT_Z_POS = 4;

	/*
	 * State (on/off).
	 */
	public final static byte STATE_EMPTY = 0, STATE_FULL = 1;

	/**
	 * Returns state.
	 */
	public final static int getState(int data)
	{
		int data1 = data & 0x8;
		return data1 >> 3;
	}

	/**
	 * Sets state.
	 */
	public final static void setState(TEBase TE, int state)
	{
		int data1 = BlockProperties.getMetadata(TE) & 0xfff7;
		data1 |= state << 3;

		int data = BlockProperties.getMetadata(TE);

		if (getState(data) != state)
			BlockProperties.setMetadata(TE, data1);
	}
}
