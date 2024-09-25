
/**
 * checks to see if a character is a plus sign
 */
public class PlusInputVerifier implements InputVerifier 
{
/**
 *  @param c character
 *  @return true if it is a "+" sign
 */
	@Override
	public boolean meetsCriteria(char c)
	{
		// 43 is the ASCII value for a plus sign
	    if (c ==  43)
	    {
	      return true;
	    }   
	    return false;    
	}
}
