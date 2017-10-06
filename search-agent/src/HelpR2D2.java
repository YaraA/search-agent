import java.util.EnumSet;


public class HelpR2D2 extends SearchProb {
	
	private static EnumSet<Operator> operators; 
	public HelpR2D2(HelpR2D2State initialState) {
		super(operators, initialState);
	}
	
	

	public EnumSet<Operator> getOperators() {
		return operators;
	}



	public static void setOperators(EnumSet<Operator> operators) {
		HelpR2D2.operators = operators;
	}



	@Override
	public State transition(State state, Operator operator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean goalTest(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int pathCost(Node node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EnumSet<Operator> allowedOperators(Node node) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
