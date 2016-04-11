package psych.android.aml.fling;

import android.app.Activity;

public class Records {
	public static String participantID;
	public static String sonificationType;
	public static boolean visualMode;
	static int numberOfBlocks;
	public static int numberOfTrials;
	public  static int p=0;
	public static int typeofVisuals;
	public static boolean mute;


	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public String getSonificationType() {
		return sonificationType;
	}

	public void setSonificationType(String sonificationtype) {
		sonificationType = sonificationtype;
	}

	public boolean isVisualMode() {
		return visualMode;
	}

	public void setVisualMode(boolean visualmode) {
		visualMode = visualmode;
	}

	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public void setNumberOfBlocks(int numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}

	public int getNumberOfTrials() {
		return numberOfTrials;
	}

	public void setNumberOfTrials(int numberOfTrials) {
		this.numberOfTrials = numberOfTrials;
	}

	public String getParticipantID() {
		return participantID;
	}

	public void setParticipantID(String ParticipantID) {
		participantID = ParticipantID;
	}

}
