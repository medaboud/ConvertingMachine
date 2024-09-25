/**
 * checks to see if a character is an integer
 */
public class DigitInputVerifier implements InputVerifier 
{
/**
 *  @param c character
 *  @return true if it is a "-" sign
 */
  @Override
  public boolean meetsCriteria(char c) 
  {
    if (Character.isDigit(c))
    {
      return true;
    }
    return false;
  }
}
