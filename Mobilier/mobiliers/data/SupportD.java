package mobiliers.data;

import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class SupportD
{
	public final static byte SUPPORT_X_NEG = 0,
							SUPPORT_X_POS = 1,
							SUPPORT_Z_NEG = 2,
							SUPPORT_Z_POS = 3;
	
	public final static byte SUPPORT_HAUT = 0,
							SUPPORT_BAS = 1;
	
	/**
	 * Returns state.
	 */
	public final static int getPosition(int data)
	{
		int data1 = data & 0x8;
		return data1 >> 3;
	}

	/**
	 * Sets state.
	 */
	public final static void setPosition(TEBase TE, int state)
	{
		int data1 = BlockProperties.getMetadata(TE) & 0xfff7;
		data1 |= state << 3;

		int data = BlockProperties.getMetadata(TE);

		if (getPosition(data) != state)
			BlockProperties.setMetadata(TE, data1);
	}
}
