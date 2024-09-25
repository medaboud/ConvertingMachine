
public class NegateAction implements Action 
{

	/**
	 *  @param x 
	 *  @param c
	 */
	@Override
	public InterimResult execute(InterimResult x, char c) 
	{
		// TODO Auto-generated method stub
		x.setS(-1);
		return x;
	}

}
