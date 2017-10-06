import java.util.EnumSet;


public class HelpR2D2 extends SearchProb {

	public HelpR2D2(EnumSet<Operator> operators, HelpR2D2State initialState) {
		super(operators, initialState);
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
