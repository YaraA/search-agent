import java.util.EnumSet;


public class HelpR2D2 extends SearchProb {
	
	public HelpR2D2(EnumSet operators, HelpR2D2State initialState) {
		super(operators, initialState);
		
	}

	@Override
	public State transition(State state, Operator operator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean goalTest(State state) {
		/*
		 * Check that the teleportal is activated and the agent is on its cell.
		 */
		Grid g = ((HelpR2D2State) state).getGrid();
		if(g.isActivated() && 
				g.getAgentLocation().equals(g.getTeleportalPosition()))
			return true;
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
