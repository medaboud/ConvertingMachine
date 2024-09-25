import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestInputVerifier 
{

  /*
   * test two digits, a symbol, and a letter
   * only the two digits should return true
   */
  @Test
  public void testDigitInputVerifier() 
  {
    DigitInputVerifier verifier = new DigitInputVerifier();
    assertTrue(verifier.meetsCriteria('0'));
    assertTrue(verifier.meetsCriteria('9'));
    assertFalse(verifier.meetsCriteria('&'));
    assertFalse(verifier.meetsCriteria('a'));
  }

  /*
   * test minus, a number, and a letter
   * only minus should return true
   */
  @Test
  public void testMinusInputVerifier() 
  {
    MinusInputVerifier verifier = new MinusInputVerifier();
    assertTrue(verifier.meetsCriteria('-'));
    assertFalse(verifier.meetsCriteria('1'));
    assertFalse(verifier.meetsCriteria('a'));
  }
  
  /*
   * test period, a number, and a letter
   * only period should return true
   */
  @Test
  public void testPeriodInputVerifier() 
  {
    PeriodInputVerifier verifier = new PeriodInputVerifier();
    assertTrue(verifier.meetsCriteria('.'));
    assertFalse(verifier.meetsCriteria('1'));
    assertFalse(verifier.meetsCriteria('a'));
  }
  
  /*
   * test plus, a number, and a letter
   * only plus should return true
   */
  @Test
  public void testPlusInputVerifier() 
  {
    PlusInputVerifier verifier = new PlusInputVerifier();
    assertTrue(verifier.meetsCriteria('+'));
    assertFalse(verifier.meetsCriteria('1'));
    assertFalse(verifier.meetsCriteria('a'));
  }
}
