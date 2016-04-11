package psych.android.aml.fling;

import com.ngohung.view.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class FirstActivity extends Activity {
	/** Called when the activity is first created. */
	Records RecObj;
	Spinner SpinSontype, spinvisual, spininttype;
	EditText PartID, block, trial;
	String[] arrSonType = { "TTS", "Spindex+TTS", "Spearcon+TTS",
			"Spindex+Spearcon+TTS", "No Sound", "Pr-TTS", "Pr-Spindex+TTS",
			"Pr-Spearcon+TTS", "Pr-Spindex+Spearcon+TTS", "Pr-No Sound" };
	String[] arrvisuals = { "On", "Off" };
	String[] arrinter = { "Select if visuals are off", "Names Off",
			"Labels off", "Screen off" };
	int Type, vispos, inttype;
	String partID;
	String Blocks, Trials;
	CheckBox mute;
	boolean mute_sound;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Records RecObj = new Records();

		SpinSontype = (Spinner) findViewById(R.id.SonType);
		spinvisual = (Spinner) findViewById(R.id.Visual);
		spininttype = (Spinner) findViewById(R.id.Interaction);
		mute = (CheckBox) findViewById(R.id.mute);

		PartID = (EditText) findViewById(R.id.partID);
		block = (EditText) findViewById(R.id.blocks);
		trial = (EditText) findViewById(R.id.trials);

		ArrayAdapter AdaptSonType = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, arrSonType);
		AdaptSonType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpinSontype.setAdapter(AdaptSonType);

		ArrayAdapter AdaptVisual = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, arrvisuals);
		AdaptVisual
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinvisual.setAdapter(AdaptVisual);

		ArrayAdapter AdaptIntType = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, arrinter);
		AdaptIntType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spininttype.setAdapter(AdaptIntType);

		SpinSontype.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {

				Type = parent.getSelectedItemPosition();
				Log.e("Sontype", Integer.toString(Type));

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mute_sound=isChecked;

			}
		});

		spinvisual.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {

				vispos = parent.getSelectedItemPosition();
				Log.e("vispos", Integer.toString(vispos));

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		spininttype.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {

				inttype = parent.getSelectedItemPosition();
				Log.e("inttype", Integer.toString(inttype));

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		final Button button = (Button) findViewById(R.id.OK);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*
				 * partID= PartID.getText().toString(); Blocks=
				 * block.getText().toString();
				 * Trials=trial.getText().toString();
				 */
				/*
				 * System.out.println(PartID.getText().toString()); System.out
				 * .println(Integer.parseInt(block.getText().toString()));
				 * System.out
				 * .println(Integer.parseInt(trial.getText().toString()));
				 */

				RecObj.typeofVisuals = inttype;

				RecObj.setParticipantID(PartID.getText().toString());
				RecObj.setNumberOfBlocks(Integer.parseInt(block.getText()
						.toString()));
				RecObj.setNumberOfTrials(Integer.parseInt(trial.getText()
						.toString()));

				if (Type == 0)
					RecObj.setSonificationType("tts");
				else if (Type == 1)
					RecObj.setSonificationType("spdxtts");
				else if (Type == 2)
					RecObj.setSonificationType("spctts");
				else if (Type == 3)
					RecObj.setSonificationType("spdxspctts");
				else if (Type == 4)
					RecObj.setSonificationType("nosound");
				else if (Type == 5)
					RecObj.setSonificationType("practise-tts");
				else if (Type == 6)
					RecObj.setSonificationType("practise-spdxtts");
				else if (Type == 7)
					RecObj.setSonificationType("practise-spctts");
				else if (Type == 8)
					RecObj.setSonificationType("practise-spdxspctts");
				else
					RecObj.setSonificationType("practise-nosound");

				if (vispos == 0)
					RecObj.setVisualMode(true);
				else if (vispos == 1)
					RecObj.setVisualMode(false);
				RecObj.setMute(mute_sound);

				Log.e("Checking SOnType", RecObj.getSonificationType());

				Intent i = new Intent(FirstActivity.this, List.class);
				startActivity(i);
			}
		});

	}

}