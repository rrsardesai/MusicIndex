package psych.android.aml.fling;

import com.ngohung.view.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EndScreen extends Activity{
	
	EndScreen end;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.norow);
	
		final ActivityManager act = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );;
		final String pkg = "psych.android.aml.fling";
         
		Button b1= (Button) findViewById(R.id.Button01);
		
		b1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				act.restartPackage(pkg);
			//	android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		

}
}