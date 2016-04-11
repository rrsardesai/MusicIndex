package psych.android.aml.fling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.ngohung.example.models.ExampleContactItem;
import com.ngohung.view.ExampleContactListView;
import com.ngohung.view.R;
import com.ngohung.widget.ContactItemInterface;
import com.ngohung.widget.ContactListAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class List extends Activity implements ListView.OnScrollListener,
		Runnable {

	public static final long Time = System.currentTimeMillis();
	static StringBuilder track;
	public int LastPlayed = 0;
	public boolean upvalue = true;
	public boolean downvalue = false;
	public int counter = 0;
	long currentTime2;
	public boolean amilli = false;
	int posToSet = 0;
	public int scrolled;
	String middle;
	private MarkAdapter m_adapter; // Class Defined below.
	private Runnable view_marks;
	static int posToPlay = 0;
	static int target[];
	static int count = 0;
	static int visited[];
	public boolean selectionFlag = false;
	public UpdateClass classObject;
	static int TrialCount = 1, BlockCount = 1;
	static int Blocks, Trials;
	static int ActualTarget[];
	static int count2 = 0;
	static boolean set;
	public ProgressDialog m_ProgressDialog = null;
	public long progressStartTime;
	public int state;
	public boolean selection = false;
	public static int NumberOfItemsOnScreen = 10;
	Scroller scrolling;
	public ExampleContactListView lv;
	static ArrayList<String> mark_ids = null;
	static ArrayList<ContactItemInterface> mark_names = null;
	static ArrayList<ContactItemInterface> blanks = null;
	static MediaPlayer mp = new MediaPlayer();
	static MediaPlayer mp2 = new MediaPlayer();
	static int refresh = 0;
	private ExecutionTimer timer;
	int ind = 0;
	String[] random;

	int touchArray[];
	int factor = 1000 * 10;
	TextView tv;
	FetchList fetchlist;
	GetBinTrial GBTObj = new GetBinTrial();
	TextView Bottom;
	Records recd;
	public ExecutionTimer timer2;
	public int coun = 0;
	List instance;
	public String action = null;
	public int coun2 = 0;
	public boolean condition = false;
	boolean condition_scroll = false;
	float[] scrolls;
	public ExecutionTimer timer3;
	float[] touch_scroll;
	int[] first;
	public boolean progresschecker = false;
	int countern;
	String firstitem;
	String File;
	Integer[] conditionArray;
	int conditionArrayIndex;
	Boolean values;
	long startTime, currentTime, diff, last_thread_time;
	int pot = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// setContentView(R.layout.list);
		fetchlist = new FetchList();
		recd = new Records();
		target = new int[150];
		BlockCount = 1;
		TrialCount = 1;
		ActualTarget = new int[150];
		track = new StringBuilder();
		timer = new ExecutionTimer();
		touchArray = new int[10000];
		classObject = new UpdateClass(currentTime2);
		timer2 = new ExecutionTimer();
		scrolls = new float[100];
		touch_scroll = new float[100];
		timer3 = new ExecutionTimer();
		first = new int[50];
		scrolling = new Scroller(getBaseContext());
		instance = this;
		action = new String();
		visited = new int[100];
		conditionArrayIndex = 0;

		// setting the layout to be full screen

		// count = 0;

		refresh++;
		setContentView(R.layout.list);

		tv = (TextView) findViewById(R.id.status);

		instance = this;
		fetchlist.Fetching();

		if (recd.getSonificationType() == "tts") {
			File = "silenttts.wav";
			conditionArray = new Integer[] { 9, 45, 94, 66, 145, 20, 80, 104,
					36, 125, 110, 27, 141, 83, 49, 96, 10, 73, 137, 38, 109,
					122, 69, 121, 22, 81, 118, 40, 48 };
		}

		else if (recd.getSonificationType() == "spdxtts") {
			File = "silentspdxtts.wav";
			conditionArray = new Integer[] { 15, 119, 75, 123, 53, 76, 23, 44,
					130, 114, 87, 47, 30, 135, 105, 28, 43, 133, 74, 92, 64,
					54, 99, 98, 138, 8, 67, 9 };
		}

		else if (recd.getSonificationType() == "spctts") {
			File = "silentspctts.wav";
			conditionArray = new Integer[] { 7, 63, 132, 58, 101, 59, 65, 108,
					24, 124, 97, 51, 72, 26, 142, 85, 113, 31, 29, 136, 35, 12,
					115, 95, 37, 86, 127, 79 };
		}

		else if (recd.getSonificationType() == "spdxspctts") {
			File = "silentspdxspctts.wav";
			conditionArray = new Integer[] { 6, 88, 46, 144, 120, 33, 17, 82,
					129, 116, 34, 70, 143, 111, 11, 61, 55, 103, 21, 139, 62,
					102, 42, 128, 117, 84, 18, 57, 100 };
		}

		else if (recd.getSonificationType() == "nosound") {
			File = "nosound.wav";
			conditionArray = new Integer[] { 14, 50, 93, 134, 77, 19, 106, 140,
					56, 89, 60, 126, 78, 91, 5, 41, 68, 131, 112, 16, 52, 71,
					90, 107, 32, 13, 39, 25 };
		}

		else if (recd.getSonificationType() == "practise-tts") {
			File = "silenttts.wav";
			conditionArray = new Integer[] { 128, 60, 144, 122, 81, 68, 79,
					141, 131, 24, 107, 101, 115, 114, 43, 4, 85, 140, 72, 90,
					10, 15, 26, 32, 39, 93, 51, 48, 47 };
		}

		else if (recd.getSonificationType() == "practise-spdxtts") {
			File = "silentspdxtts.wav";
			conditionArray = new Integer[] { 12, 63, 22, 56, 49, 11, 98, 84,
					30, 132, 145, 50, 34, 40, 76, 100, 19, 121, 5, 109, 73, 92,
					105, 66, 133, 138, 87, 18, 118 };
		}

		else if (recd.getSonificationType() == "practise-spctts") {
			File = "silentspctts.wav";
			conditionArray = new Integer[] { 117, 65, 62, 58, 17, 7, 2, 38, 52,
					104, 25, 125, 124, 99, 108, 135, 111, 16, 97, 77, 143, 126,
					64, 42, 78, 46, 83, 35 };
		}

		else if (recd.getSonificationType() == "practise-spdxspctts") {
			File = "silentspdxspctts.wav";
			conditionArray = new Integer[] { 106, 112, 139, 29, 27, 94, 57,
					120, 82, 9, 80, 36, 134, 59, 23, 54, 127, 33, 69, 136, 86,
					119, 13, 6, 96, 37, 74, 91, 71 };
		}

		if (recd.getSonificationType() == "practise-nosound") {
			File = "nosound.wav";
			conditionArray = new Integer[] { 116, 142, 31, 14, 123, 41, 70, 21,
					137, 61, 44, 8, 110, 130, 28, 129, 53, 89, 102, 95, 103,
					88, 45, 75, 55, 113, 20, 67 };
		}

		lv = (ExampleContactListView) findViewById(R.id.list);
		lv.setFastScrollEnabled(true);
		mp = new MediaPlayer();
		try {
			mp.setDataSource("//sdcard//TTS//" + File);
			mp.prepare();
			if(Records.mute)
			mp.setVolume(0.0f, 0.0f);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mp2 = new MediaPlayer();
		try {
			mp2.setDataSource("//sdcard//TTS//silenttts.wav");
			mp2.prepare();
			if(Records.mute)
			mp2.setVolume(0.0f, 0.0f);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Log.e("Checking", Records.sonificationType);
		Bottom = (TextView) findViewById(R.id.status2);

		Blocks = Records.numberOfBlocks;
		Trials = Records.numberOfTrials;

		// lv = getListView();

		lv.setVerticalFadingEdgeEnabled(false);
		lv.setScrollContainer(true);
		// getListView().setFocusableInTouchMode(true);

		lv.setOnScrollListener(this);

		ReportWriter.SaveDataToFile("Participant ID" + "," + "Block" + ","
				+ "Trial" + "," + "Sonification Type" + "," + "Visuals" + ","
				+ "Bin Number" + "," + "Target to be Hit" + ","
				+ "Target actually Hit" + "," + "Missed By" + ","
				+ "Correct Selection" + "," + "," + "Start Time" + ","
				+ "End Time" + "," + "Time Taken" + "\n");
		ReportWriter.SaveDataToFile2("Part ID" + "," + "Block" + "," + "Trial"
				+ "," + "Target Hit" + "," + "Actual Target" + ","
				+ "Path Followed" + "," + "FlingTime" + "," + "TouchTime" + ","
				+ "Action Performed" + "," + "\n");

		lv.setFastScrollEnabled(false);
		try {
			UpdateLabel();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		timer.start();
		lv.setVerticalScrollBarEnabled(false);

		mark_ids = new ArrayList<String>();
		mark_names = new ArrayList<ContactItemInterface>();
		blanks = new ArrayList<ContactItemInterface>();

		for (int id = 0; id < fetchlist.contacts.size(); id++) {
			mark_ids.add(Integer.toString(id));
			mark_names.add(new ExampleContactItem(fetchlist.contacts.get(id)
					.toString()));
			blanks.add(new ExampleContactItem("   "));
			// Log.e("PAth to each file",
			// fetchlist.contacts.get(id).toString());

		}

		// Setting Adapter for List Display
		if (Records.visualMode == true) {
			this.m_adapter = new MarkAdapter(this, R.layout.row, mark_names);
		} else {
			lv.setItemsCanFocus(false);
			this.m_adapter = new MarkAdapter(this, R.layout.row, blanks);
			// lv.setVisibility(View.INVISIBLE);
			if (Records.typeofVisuals == 2) {
				lv.setDivider(null);
				lv.setScrollContainer(false);
			} else if (Records.typeofVisuals == 3) {

				lv.setDivider(null);
				lv.setFocusable(false);
				lv.setVerticalScrollBarEnabled(false);
			}

		}
		lv.setAdapter(this.m_adapter);

		lv.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View view, MotionEvent arg0) {
				System.out.println("posToPlay  " + posToPlay);
				if (posToPlay >= 149) {
					mp.seekTo(149 * factor);
				}

				return false;

			}
		});

		mp.setOnSeekCompleteListener(new OnSeekCompleteListener() {

			public void onSeekComplete(MediaPlayer mp) {

				downvalue = false;
				Thread t = new Thread(instance);

				while (t.isAlive()) {

				}

				t.start();
				mp.start();
				System.out.println(t.getState());

				while (selectionFlag) {
					// currentTime2 = System.currentTimeMillis();

					// System.out.println("Classobject ki DownValue "+classObject.downvalue);

					if (downvalue == true) {
						try {

							// This puts the thread to sleep for a length of
							// time
							// between 15-45 seconds.
							Random r = new Random();
							int temp1 = r.nextInt(45000 - 15000) + 15000;
							try {
								t.sleep((long) temp1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							UpdateLabel();
							m_ProgressDialog.dismiss();
							downvalue = false;

						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						selectionFlag = false;
					}

				}

			}

		});

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				currentTime2 = System.currentTimeMillis();

				m_ProgressDialog = ProgressDialog.show(List.this,
						"Please wait ", "", true, false);

				tv = (TextView) findViewById(R.id.status);
				tv.setText("    ");
				selectionFlag = true;
				upvalue = true;
				amilli = true;
				timer.end();
				timer2.end();
				lv.setSelection(0);

				// mp.seekTo((posToSet)*factor);
				mp.seekTo((position - 4) * factor);
				// mp.seekTo((position)*factor);

				target[count] = position - 4;
				System.out.println("Target[count]=" + target[count] + "count="
						+ count);
				// Log.e("Clicked", Integer.toString(position));

				int loop = 0;

				for (int in = 0; in <= countern; in++) {
					track.append(first[in]);
					track.append(" ");

				}

				while (loop <= coun) {
					if (touch_scroll[loop] == 0) {
						// action.equals("SELECT");
						action = "SELECT";
					}
					// else if(scrolls[loop]-touch_scroll[loop]<1000){
					if (touchArray[coun] == 1) {
						action = "FLING";
					}
					if (touchArray[coun] == 2) {
						action = "TOUCH";
					}
					// System.out.println(action);
					ReportWriter.SaveDataToFile2(Records.participantID + ","
							+ BlockCount + "," + TrialCount + ","
							+ target[count] + "," + ActualTarget[count] + ","
							+ track + "," + scrolls[loop] + ","
							+ touch_scroll[loop] + "," + action + "," + "\n");
					/*
					 * System.out.println("Scrolls..."+scrolls[loop]);
					 * System.out
					 * .println("Touch Scroll ...."+touch_scroll[loop]);
					 * System.out.println("Path FOllowed is ...."+track);
					 */
					loop++;
				}

				condition = false;
				coun = 0;
				coun2 = 0;
				condition_scroll = false;
				// lv.setSelection(0);

				if (ActualTarget[count] == target[count])
					set = true;
				else
					set = false;

				System.out.println("target to be hit array");
				for (int r = 0; r < 8; r++)
					System.out.println("Array actualtarget " + r + "="
							+ ActualTarget[r]);

				System.out.println("refresh = " + refresh + " Array count= "
						+ count);

				boolean check = ReportWriter
						.SaveDataToFile(Records.participantID + ","
								+ BlockCount + "," + TrialCount + ","
								+ Records.sonificationType + ","
								+ Boolean.toString((Records.visualMode)) + ","
								+ (GetBinTrial.randomBinNumber + 1) + ","
								+ ActualTarget[count] + "," + target[count]
								+ "," + dif(ActualTarget[count], target[count])
								+ "," + Boolean.toString(set) + "," + track
								+ "," + timer.getStart() + "," + timer.getEnd()
								+ "," + timer.duration() + "\n");

				// Log.e("Written to File", Boolean.toString(check));
				timer.reset();
				timer.start();
				track.delete(0, track.capacity());
				countern = 0;
				first[0] = 0;
				if (TrialCount == Trials) {
					BlockCount++;

					if (BlockCount <= Blocks) {
						TrialCount = 1;
						/*
						 * Toast toast1 = Toast.makeText(getBaseContext(),
						 * "Now Starting Block " + BlockCount, 3);
						 * toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						 * toast1.show();
						 */
						new AlertDialog.Builder(List.this)
								.setMessage(
										"Block "
												+ (BlockCount - 1)
												+ " is over, Now Starting Block "
												+ BlockCount)
								.setPositiveButton("OK", null).show();
						for (int i = 0; i <= 20; i++) {
							visited[i] = 0;
							System.out.println("leaking at selection Block");
						}

					}

				} else
					TrialCount++;

				if (BlockCount > Blocks) {

					Intent i = new Intent(List.this, EndScreen.class);
					startActivity(i);
					System.exit(0);

				}
				// Commented out for Thom's experiment
				// Bottom.setText("Block::: " + (BlockCount) + "  Trials::: "+
				// TrialCount);
				count++;

			}
		});
	}

	// @Override
	// public void onListItemClick(ListView parent, View v, int position, long
	// id) {
	//
	// {
	// currentTime2= System.currentTimeMillis();
	//
	// m_ProgressDialog= ProgressDialog.show(List.this,"Please wait ",
	// "",true,false);
	//
	//
	// tv = (TextView) findViewById(R.id.status);
	// tv.setText("    ");
	// selectionFlag=true;
	// upvalue=true;
	// amilli=true;
	// timer.end();
	// timer2.end();
	// lv.setSelection(0);
	//
	//
	// //mp.seekTo((posToSet)*factor);
	// mp.seekTo((position-4)*factor);
	// //mp.seekTo((position)*factor);
	//
	//
	//
	//
	// target[count] = position -4;
	// System.out.println("Target[count]="+target[count]+"count="+count);
	// // Log.e("Clicked", Integer.toString(position));
	//
	//
	// int loop=0;
	//
	// for (int in = 0; in <= countern; in++) {
	// track.append(first[in]);
	// track.append(" ");
	//
	// }
	//
	// while(loop<=coun ){
	// if(touch_scroll[loop]==0){
	// //action.equals("SELECT");
	// action="SELECT";
	// }
	// //else if(scrolls[loop]-touch_scroll[loop]<1000){
	// if(touchArray[coun]==1){
	// action="FLING";
	// }
	// if(touchArray[coun]==2){
	// action="TOUCH";
	// }
	// //System.out.println(action);
	// ReportWriter.SaveDataToFile2(Records.participantID
	// +","+BlockCount+","+TrialCount+","+target[count]+","+ActualTarget[count]+","+track+","+scrolls[loop]+","+
	// touch_scroll[loop]+","+action+","+"\n");
	// /* System.out.println("Scrolls..."+scrolls[loop]);
	// System.out.println("Touch Scroll ...."+touch_scroll[loop]);
	// System.out.println("Path FOllowed is ...."+track);*/
	// loop++;
	// }
	//
	//
	// condition = false;
	// coun = 0;
	// coun2 = 0;
	// condition_scroll = false;
	// // lv.setSelection(0);
	//
	// if (ActualTarget[count] == target[count])
	// set = true;
	// else
	// set = false;
	//
	// System.out.println("target to be hit array");
	// for (int r=0; r<8; r++)
	// System.out.println("Array actualtarget " + r + "=" + ActualTarget[r]);
	//
	//
	//
	// System.out.println("refresh = "+refresh+" Array count= "+ count);
	//
	// boolean check = ReportWriter.SaveDataToFile(Records.participantID
	// + "," + BlockCount + "," + TrialCount + ","
	// + Records.sonificationType + ","
	// + Boolean.toString((Records.visualMode)) + ","
	// + (GetBinTrial.randomBinNumber + 1) + ","
	// + ActualTarget[count] + "," + target[count] + ","
	// + dif(ActualTarget[count], target[count]) + ","
	// + Boolean.toString(set) + "," + track + ","
	// + timer.getStart() + "," + timer.getEnd() + ","
	// + timer.duration() + "\n");
	//
	//
	//
	// //Log.e("Written to File", Boolean.toString(check));
	// timer.reset();
	// timer.start();
	// track.delete(0, track.capacity());
	// countern=0;
	// first[0]=0;
	// if (TrialCount == Trials) {
	// BlockCount++;
	//
	// if (BlockCount <= Blocks) {
	// TrialCount = 1;
	// /*Toast toast1 = Toast.makeText(getBaseContext(),
	// "Now Starting Block " + BlockCount, 3);
	// toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	// toast1.show();*/
	// new AlertDialog.Builder(this)
	// .setMessage("Block "+(BlockCount-1)+" is over, Now Starting Block "+BlockCount)
	// .setPositiveButton("OK", null).show();
	// for(int i=0;i<=20;i++){
	// visited[i]=0;
	// System.out.println("leaking at selection Block");
	// }
	//
	// }
	//
	// } else
	// TrialCount++;
	//
	// if (BlockCount > Blocks) {
	//
	// Intent i = new Intent(List.this, EndScreen.class);
	// startActivity(i);
	// System.exit(0);
	//
	// }
	// //Commented out for Thom's experiment
	// //Bottom.setText("Block::: " + (BlockCount) + "  Trials::: "+
	// TrialCount);
	// count++;
	// }
	//
	//
	//
	//
	// }

	private int dif(int i, int j) {
		if (i > j)
			return i - j;
		else
			return j - i;

	}

	public class MarkAdapter extends ContactListAdapter {
		// private ArrayList<ContactItemInterface> items;

		public MarkAdapter(Context context, int textViewResourceId,
				ArrayList<ContactItemInterface> items) { // @ If smthng fails
															// check here.
			super(context, textViewResourceId, items);
			// this.items = items;

		}

		// To Generate a custom view.
		// @Override
		// public View getView(int position, View convertView, ViewGroup parent)
		// {
		// View v = convertView;
		// if (v == null) {
		// LayoutInflater vi = (LayoutInflater)
		// getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// v = vi.inflate(R.layout.row, null);
		//
		// }
		//
		// /*
		// * if(position==7||position==15||position==23||position==30){
		// * scrolling.forceFinished(true); lv.setSelection(position-7); }
		// */
		// // maxY)
		// String o = items.get();
		// //Log.e("post", Integer.toString(position));
		//
		// if (position > recd.p) {
		// posToPlay = position - NumberOfItemsOnScreen;
		// if (posToPlay < 0) {
		// posToPlay = 0;
		// scrolled = 1;
		// }
		// }
		//
		// if (position < recd.p) {
		// posToPlay = position ;
		// scrolled = 1;
		// }
		// if (posToPlay >= 0) {
		// if (scrolled == 1) {
		// // mp[LastPlayed].pause();
		//
		// mp.setVolume(0, 0);
		//
		//
		// }
		//
		// mp.setVolume(100, 100);
		// if(amilli==true){
		// if(posToPlay!=0){
		// //mp.seekTo(posToPlay*factor);
		// amilli=false;
		// }
		// }
		// else{
		// // mp.seekTo(posToPlay*factor);
		// }
		//
		// }
		//
		// // lv.setSelectionAfterHeaderView(); lv.setSelector(Color.BLUE);
		//
		//
		// lv.setSelection(posToPlay);
		// // lv.setFastScrollEnabled(false);
		// lv.bringChildToFront(lv.findViewById(posToPlay));
		// LastPlayed = posToPlay;
		// // Log.e("to Play", Integer.toString(posToPlay));
		// recd.p = position;
		// if (o != null) {
		// TextView tt = (TextView) v.findViewById(R.id.toptext);
		// if (tt != null) {
		// tt.setText(o);
		// }
		// }
		// return v;
		// }

		public void populateDataForRow(View parentView,
				ContactItemInterface item, int position) {
			// default just draw the item only
			View infoView = parentView.findViewById(R.id.infoRowContainer);

			/*
			 * if(position==7||position==15||position==23||position==30){
			 * scrolling.forceFinished(true); lv.setSelection(position-7); }
			 */
			// maxY)
			String o = item.getItemForIndex();
			// Log.e("post", Integer.toString(position));

			if (position > recd.p) {
				posToPlay = position - NumberOfItemsOnScreen;
				if (posToPlay < 0) {
					posToPlay = 0;
					scrolled = 1;
				}
			}

			if (position < recd.p) {
				posToPlay = position;
				scrolled = 1;
			}
			if (posToPlay >= 0) {
				if (scrolled == 1) {
					// mp[LastPlayed].pause();

					mp.setVolume(0, 0);

				}
				if (!Records.mute)
					mp.setVolume(100, 100);
				if (amilli == true) {
					if (posToPlay != 0) {
						// mp.seekTo(posToPlay*factor);
						amilli = false;
					}
				} else {
					// mp.seekTo(posToPlay*factor);
				}

			}

			// lv.setSelectionAfterHeaderView(); lv.setSelector(Color.BLUE);

			lv.setSelection(posToPlay);
			// lv.setFastScrollEnabled(false);
			lv.bringChildToFront(lv.findViewById(posToPlay));
			LastPlayed = posToPlay;
			// Log.e("to Play", Integer.toString(posToPlay));
			recd.p = position;
			if (o != null) {
				TextView tt = (TextView) infoView.findViewById(R.id.toptext);
				if (tt != null) {
					tt.setText(o);
				}
			}

		}
		// mark_ids.size();

	}

	public void UpdateLabel() throws IllegalStateException, IOException {
		tv = (TextView) findViewById(R.id.status);

		// to get a random song
		// random = GBTObj.updateTarget();

		// to get from fixed list based on the condition
		random = updateTargetFromList();
		// Log.e("Random to Click", random);
		if (refresh > 1) {
			count2--;
			refresh = 1;
		}
		// ActualTarget[count2] = GBTObj.getTargetid(random[0]);
		ActualTarget[count2] = Integer.parseInt(random[1]);
		System.out.println("Actual target =" + ActualTarget[count2] + "-"
				+ random[0] + "-" + random[1]);
		System.out
				.println("random[0]=" + random[0] + " random[1]=" + random[1]);
		System.out.println("seek to value=" + Integer.valueOf(random[1])
				* 10000);
		cntr_aCounter.start();

		mp2.seekTo(Integer.valueOf(random[1]) * 10000);

		/*
		 * mp2.setDataSource(path); mp2.prepare(); mp2.start();
		 */
		count2++;

		tv.setText(random[0]);
		mp2.start();

		cntr_aCounter.start();

	}

	// Waits for 3 seconds for mp2 to and then stop
	CountDownTimer cntr_aCounter = new CountDownTimer(3000, 1000) {
		public void onTick(long millisUntilFinished) {
			// System.out.println("temp1: " + temp1 + " milis till done: " +
			// millisUntilFinished);
		}

		public void onFinish() {
			// code fire after finish
			// setTvText(random[0]);
			mp2.pause();
		}
	};

	public String[] updateTargetFromList() {

		Vector randomBin = new Vector();
		FetchList fetch = new FetchList();
		fetch.Fetching();
		for (int i = 0; i < 150; i++) {
			randomBin.addElement(fetch.contacts.elementAt(i));
		}
		System.out.println("randombin = " + randomBin.elementAt(123));
		int randomElementId = conditionArray[conditionArrayIndex++];
		if (conditionArrayIndex > (conditionArray.length - 1)) {
			conditionArrayIndex = 0;
		}
		int randomNumber = randomElementId + 4;
		String randomElement = (String) randomBin.elementAt(randomNumber);
		String[] randomElementAndId = { randomElement,
				Integer.toString(randomElementId) };
		// System.out.println("grandom[0]="+random[0]+" grandom[1]="+random[1]);
		return randomElementAndId;
	}

	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		posToSet = firstVisibleItem;

		if (!selectionFlag) {
			if (state == OnScrollListener.SCROLL_STATE_IDLE) {

				mp.seekTo((posToSet) * factor);

			} else {
				mp.seekTo((posToSet + 1) * factor);
			}
		}
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		state = scrollState;
		// view.setSelection(view.getFirstVisiblePosition());
		/*
		 * scrolling.fling(0,0, 0, 3,0,0,0,3);
		 * if(scrolling.getStartY()+8==scrolling.getCurrY()){
		 * scrolling.forceFinished(true); scrolling.abortAnimation(); }
		 */
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:
			lv.setSelection(posToSet);
			// mp.seekTo(posToSet+1);
			first[countern] = lv.getFirstVisiblePosition();
			countern++;
			if (condition = true) {
				timer2.end();
				scrolls[coun] = timer2.duration();
				timer2.reset();
				condition = false;
				// Log.e("DIFFERENCE IN TIME OF SCROLLS", Float
				// .toString(scrolls[coun]));

			}
			if (condition_scroll = true) {
				timer3.end();
				touch_scroll[coun] = timer3.duration();
				timer3.reset();
				condition_scroll = false;
				// Log.e("Difference in time touch scroll", Float
				// .toString(touch_scroll[coun]));
			}
			coun++;
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			touchArray[pot] = 1;
			pot++;
			condition = true;

			timer2.start();
			// Log.e("Working type", "fling");
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			lv.setSelection(posToSet);
			// mp.seekTo(getListView().getFirstVisiblePosition()+1);
			touchArray[pot] = 2;
			pot++;
			condition_scroll = true;

			timer3.start();
			// Log.e("Working type", "touch");
			lv.setSelection(posToSet);
			break;

		}

	}

	public void run() {

		startTime = System.currentTimeMillis();
		this.last_thread_time = startTime;
		long my_thread_time = startTime;

		/*
		 * while (upvalue == true) { diff = System.currentTimeMillis() -
		 * currentTime2; System.out.println("difference in time is "+ diff); if
		 * (diff > 8000) {
		 * 
		 * downvalue=true; upvalue=false;
		 * 
		 * }
		 * 
		 * }
		 */

		values = false;
		// System.out.println("Thread called");
		// System.out.println(last_thread_time);
		// System.out.println(my_thread_time);
		while (values != true && this.last_thread_time == my_thread_time) {

			currentTime = System.currentTimeMillis();

			diff = (startTime + 10000) - currentTime;
			if (diff <= 7000) {
				System.out.println("Volume set to zero!!");
				downvalue = true;
				mp.pause();
				values = true;
				break;
			}

		}
	}
}
