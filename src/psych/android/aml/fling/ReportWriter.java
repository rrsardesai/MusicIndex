package psych.android.aml.fling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportWriter {
	static boolean SaveDataToFile(String data) {

		try {
			File file = new File("/sdcard/FlingExperimentResults " + " Data "
					+ Records.participantID +".csv");

			if (!file.exists())
				file.createNewFile();

			FileWriter fstream = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(data);
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	static boolean SaveDataToFile2(String data) {

		try {
			File file = new File("/sdcard/" + "FlingExpTiming " + Records.participantID
					+ ".csv");

			if (!file.exists())
				file.createNewFile();

			FileWriter fstream = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(data);
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
