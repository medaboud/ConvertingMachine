
public class ContinuingIntegerAction implements Action 
{

	/**
	 *  @param x 
	 *  @param c
	 */
	@Override
	public InterimResult execute(InterimResult x, char c) 
	{
		// TODO Auto-generated method stub
		x.setV(10 * x.getV() + c - '0');
		return x;
	}

}
