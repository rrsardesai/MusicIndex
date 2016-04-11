package psych.android.aml.fling;

//Class to manage track time of user response
public class ExecutionTimer {
	private long start;
	private long end;
	
	public ExecutionTimer(){
		reset();
	}
	
	public void start(){
		start = System.currentTimeMillis();
	}
	
	public void end(){
		end = System.currentTimeMillis();
	}
	
	public long duration(){
		return (end - start);
	}
	
	public void reset(){
		start = 0;
		end = 0;
	}
	
	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
	
}
