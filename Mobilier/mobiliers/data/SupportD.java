package mobiliers.data;

import carpentersblocks.tileentity.TECarpentersBlock;
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
		int temp = data & 0x8;
		return temp >> 3;
	}

	/**
	 * Sets state.
	 */
	public final static void setPosition(TECarpentersBlock TE, int state)
	{
		int temp = BlockProperties.getData(TE) & 0xfff7;
		temp |= state << 3;

		int data = BlockProperties.getData(TE);

		if (getPosition(data) != state)
			BlockProperties.setData(TE, temp);
	}
}
