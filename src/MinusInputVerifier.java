
/**
 * checks to see if a character is a hyphen (minus symbol)
 */
public class MinusInputVerifier implements InputVerifier 
{

/**
 *  @param c character
 *  @return true if it is a "-" sign
 */
  @Override
  public boolean meetsCriteria(char c) 
  {
    // 45 is the ASCII value for a hyphen
    if (c ==  45)
    {
      return true;
    }
    return false;
  }
}
