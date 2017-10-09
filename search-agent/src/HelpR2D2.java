import java.util.EnumSet;


public class HelpR2D2 extends SearchProb {

	public HelpR2D2(EnumSet operators, HelpR2D2State initialState) {
		super(operators, initialState);
	}

	@Override
	public State transition(State state, Operator op) {
		/*
		 * Given the current state and a given operator, computes the next State
		 * in case the operator is allowed, returns null otherwise.
		 */
		HelpR2D2State s = (HelpR2D2State) state;
		Grid g = s.getGrid();
		Position agent = s.getGrid().getAgentLocation();

		HelpR2D2State newState = s.clone();
		Grid newGrid = s.getGrid();
		Position nextCell = Grid.nextCell(agent, op);
		/*
		 * Check for UNALLOWED cases.
		 */
		/*
		 * 1. Invalid cell
		 */
		if(!g.liesInGrid(nextCell))
			return null;

		CellType nextType = newGrid.getCellType(nextCell);
		switch(nextType){
		case BLANK:
		case TELEPORTAL:
		case PAD: smoothTransition(newState, nextCell); break;
		case OBSTACLE: return null;
		case ROCK:
		case ROCKONPAD: roughTransition(newState, nextCell, op); break;
		}
		return newState;
	}
	public void smoothTransition(HelpR2D2State s, Position newPos){
		/*
		 * Updates the agent location in case the new cell is BLANK or PAD.
		 */
		s.getGrid().setAgentLocation(newPos);
	}

	public void roughTransition(HelpR2D2State s, Position newPos, Operator op){
		Grid g = s.getGrid();
		Position adjacentCell = Grid.nextCell(newPos, op);

		/*
		 * Check the adjacent cell to the rock/rock on pad.
		 */
		if(!g.liesInGrid(adjacentCell))
		{  
			/*
			 * If the adjacent cell is invalid (rock next to an edge), 
			 * the state should be null.
			 */
			s = null;
			return;
		}
		
		CellType adjType = g.getCellType(adjacentCell);
		switch(adjType){
		/*
		 * 1. The rock is not movable.
		 */
		case OBSTACLE:
		case ROCK:
		case ROCKONPAD: s = null; return;
		/*
		 * 2. The rock is movable.
		 */
		case BLANK:
		case TELEPORTAL: 
		case PAD: 
		}
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
