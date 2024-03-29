public class DepthLimit {
	/*
	 * Stores needed info about the depth and the progress of the ID search strategy.
	 */
	private int depthLimit;
	private boolean incDepth;

	public DepthLimit() {
		depthLimit = 0;
		incDepth = false;
	}
	public int getDepthLimit() {
		return depthLimit;
	}
	public void setDepthLimit(int depthLimit) {
		this.depthLimit = depthLimit;
	}
	public void incDepthLimit() {
		 depthLimit++;
	}
	public boolean isIncDepth() {
		return incDepth;
	}
	public void setIncDepth(boolean incDepth) {
		this.incDepth = incDepth;
	}
}
