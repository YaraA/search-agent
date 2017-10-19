import java.util.EnumSet;


public class HelpR2D2 extends SearchProb {

	public HelpR2D2(EnumSet<Operator> operators, HelpR2D2State initialState) {
		super(operators, initialState);
	}

	@Override
	public State transition(State state, Operator op) throws Exception {
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
		 * 1. Invalid cell position
		 */
		if(!g.liesInGrid(nextCell))
			return null;
		/*
		 * 2. Valid cell position cases
		 */
		CellType nextType = newGrid.getCellType(nextCell);
		switch(nextType){
		/*
		 * Next cell does not contain a rock.
		 */
		case BLANK:
		case TELEPORTAL:
		case PAD: return smoothTransition(newState, nextCell); 
		case OBSTACLE: return null;
		/*
		 * Next cell contains a rock.
		 */
		case ROCK:
		case ROCKONTELEPORTAL:
		case ROCKONPAD: return roughTransition(newState, nextCell, op);
		}
		return newState;
	}
	public State smoothTransition(HelpR2D2State s, Position newPos){
		/*
		 * Updates the agent location in case the new cell is BLANK or PAD.
		 */
		s.getGrid().setAgentLocation(newPos);
		return s;
	}

	public State roughTransition(HelpR2D2State s, Position rockPos, Operator op) throws Exception{
		Grid g = s.getGrid();
		Position adjacentCell = Grid.nextCell(rockPos, op);

		/*
		 * Check the adjacent cell to the rock/rock on pad.
		 */
		if(!g.liesInGrid(adjacentCell))
		{  
			/*
			 * If the adjacent cell is invalid (rock next to an edge), 
			 * the state should be null.
			 */
			return null;
		}

		CellType adjType = g.getCellType(adjacentCell);
		switch(adjType){
		/*
		 * 1. The rock is not movable.
		 */
		case OBSTACLE:
		case ROCK:
		case ROCKONTELEPORTAL:
		case ROCKONPAD: return null;
		/*
		 * 2. The rock is movable.
		 */
		case BLANK:
		case TELEPORTAL: 
		case PAD: g.moveRock(rockPos, adjacentCell);
		}
		return s;
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
		/*
		 * Check if the node is the root.
		 */
		if(node.getParent() == null)
			return 0;
		int stepCost = 1;
		/*
		 * Give different values for each operator (only for testing Uniform-Cost)
		 */
//		switch (node.getOperator()) {
//		case UP: stepCost = 10;
//		break;
//		case DOWN: stepCost = 2;
//		break;
//		case LEFT: stepCost = 3;
//		break;
//		case RIGHT: stepCost = 4;
//		break;
//		}

		return stepCost + node.getParent().getPathCost();
	}
}
