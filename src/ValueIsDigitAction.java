
public class ValueIsDigitAction implements Action 
{
	/**
	 *  @param x 
	 *  @param c
	 */
	@Override
	public InterimResult execute(InterimResult x, char c) 
	{
		// TODO Auto-generated method stub
		x.setV(c - '0');
		return x;
	}

}
