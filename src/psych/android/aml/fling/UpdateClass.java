package psych.android.aml.fling;

import java.io.IOException;

public class UpdateClass implements Runnable {
	boolean upvalue = true;
	public static boolean down2= false;
	List object;
	
	long diff;
	long time;
	public UpdateClass(long currentTime2) {
		// TODO Auto-generated constructor stub
		object= new List();
		time=currentTime2;
	}

	
	public void run() {
		// TODO Auto-generated method stub
	//	System.out.println("Thread running");
		while (upvalue == true) {
			 diff = System.currentTimeMillis() - time;
			//System.out.println("difference in time is "+ diff);
			 if (diff > 120000) {
			System.out.println("other timer working");
			down2=true;
			//object.stopProgress();
			break;
				
			}

		}

	}

}