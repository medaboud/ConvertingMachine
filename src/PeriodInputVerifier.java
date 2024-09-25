
public class PeriodInputVerifier implements InputVerifier 
{
	/**
	 *  @param c character
	 *  @return true if it is a "+" sign
	 */
		@Override
	public boolean meetsCriteria(char c)
	{
	    // 46 is the ASCII value for a hyphen
	    if (c ==  46)
	    {
	      return true;
	    }
        return false;
	}

}
