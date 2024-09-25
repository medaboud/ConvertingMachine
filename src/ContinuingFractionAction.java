
/**  
 * continue working through a fraction until it terminates
 */
public class ContinuingFractionAction implements Action
{
	/**
	 *  @param x 
	 *  @param c
	 */
	@Override
	public InterimResult execute(InterimResult x, char c) 
	{
	  x.setV(x.getV() + (x.getP() * (c - '0')));
	  x.setP(x.getP() / 10);
	  return x;
	}

}
