import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Merlin
 *
 */
public class ConvertingMachineTest
{

	private ConvertingMachine m;

	/**
	 * A negative number with multiple digits in both the integer and fraction portions
	 * @throws NumberFormatException 
	 */
	@Test
	public void fullNegative() throws NumberFormatException
	{
		assertEquals(-42.16, m.parse("-42.16"), 0.001);
	}

	/**
	 * A positive number with multiple digits in both the integer and fraction portions
	 * @throws NumberFormatException 
	 */
	@Test
	public void fullPositive() throws NumberFormatException
	{
		assertEquals(42.16, m.parse("42.16"), 0.001);
	}

	/**
	 * if it isn't a number, we should see the exception
	 * @throws NumberFormatException 
	 */
	@Test(expected = NumberFormatException.class)
	public void illegalCharacter() throws NumberFormatException
	{
		m.parse("a");
	}
	
	/**
	 * if there are illegal characters in the integer portion, we should see the exception
	 * @throws NumberFormatException 
	 */
	@Test(expected = NumberFormatException.class)
	public void illegalCharacterInInteger() throws NumberFormatException
	{
		m.parse("4-");
	}

	/**
	 * if there are illegal characters in the decimal portion, we should see the exception
	 * @throws NumberFormatException 
	 */
	@Test(expected = NumberFormatException.class)
	public void illegalCharacterInDecimal() throws NumberFormatException
	{
		m.parse("4.+");
	}
	/**
	 * integers with multiple digits 
	 * @throws NumberFormatException 
	 */
	@Test
	public void multipleDigitInteger() throws NumberFormatException
	{
		assertEquals(42, m.parse("42"), 0.001);
	}

	/**
	 * Negative integer with multiple digits
	 * @throws NumberFormatException 
	 */
	@Test
	public void negativeInteger() throws NumberFormatException
	{
		assertEquals(-42, m.parse("-42"), 0.001);
	}

	/**
	 * We can have a negative sign followed by the decimal point (no integer portion)
	 * @throws NumberFormatException 
	 */
	@Test
	public void negativeNoInteger() throws NumberFormatException
	{
		assertEquals(-0.16, m.parse("-.16"), 0.001);
	}

	/**
	 * Numbers can start with the decimal point
	 * @throws NumberFormatException 
	 */
	@Test
	public void noInteger() throws NumberFormatException
	{
		assertEquals(0.16, m.parse(".16"), 0.001);
	}

	/**
	 * Just create the machine
	 */
	@Before
	public void setUp()
	{
		m = new ConvertingMachine();
	}

	/**
	 * Zero is legal
	 * @throws NumberFormatException 
	 */
	@Test
	public void zero() throws NumberFormatException
	{
		assertEquals(0, m.parse("0"), 0.001);
	}
}
