/**
 * A finite state machine that parses a string containing a real number. Will
 * throw NumberFormatExcaption if the string doesn't not contain a legal
 * representation of a real number. Note: we are not dealing with scientific
 * notation
 * 
 * @author Merlin
 *
 */
public class ConvertingMachine
{

	private final Edge[] machine =
	{
			new Edge(State.START, new DigitInputVerifier(),
					new ValueIsDigitAction(), State.INTEGER),
			new Edge(State.START, new MinusInputVerifier(), new NegateAction(),
					State.INTEGER),
			new Edge(State.START, new PlusInputVerifier(), new NoAction(),
					State.INTEGER),
			new Edge(State.START, new PeriodInputVerifier(),
					new StartFraction(), State.DECIMAL),
			new Edge(State.INTEGER, new DigitInputVerifier(),
					new ContinuingIntegerAction(), State.INTEGER),
			new Edge(State.INTEGER, new PeriodInputVerifier(),
					new StartFraction(), State.DECIMAL),
			new Edge(State.DECIMAL, new DigitInputVerifier(),
					new ContinuingFractionAction(), State.DECIMAL)

	};

	/**
	 * 
	 * @param text the string to be converted
	 * @return the number after conversion
	 * @throws NumberFormatException 
	 */
	public double parse(String text) throws NumberFormatException 
	{
		int counter = 0;
		State currentState = State.START;
		InterimResult result = new InterimResult(0, 1, 0);
		char[] ch = text.toCharArray();
		Edge edge = null;
	
		while(counter < text.length())
		{

     		edge = searchForEdge(currentState, ch[counter]);


			result = edge.action.execute(result, ch[counter]);
			currentState = edge.nextState;
			
			counter++;
		}
		
		return result.getV() * result.getS();
	}

	private Edge searchForEdge(State currentState, char ch) throws NumberFormatException
	{
		int counter = 0;
		Edge edge = machine[counter]; 
		
		while((edge.currentState != currentState
				|| !edge.inputVerifier.meetsCriteria(ch))
				&& counter < 7)
		{
			counter++;
			if(counter < 7)
			{
				edge = machine[counter];	
			}
			else
			{			
				throw new NumberFormatException();
			}
	
		}
		
		return edge;
	}

	private class Edge
	{
		State currentState;
		InputVerifier inputVerifier;
		Action action;
		State nextState;

		Edge(State currenState, InputVerifier inpuVerifier,
				Action theAction, State nexState)
		{
			this.currentState = currenState;
			this.inputVerifier = inpuVerifier;
			this.action = theAction;
			this.nextState = nexState;
		}
	}

	private enum State
	{
		START, INTEGER, DECIMAL, END
	}
}
