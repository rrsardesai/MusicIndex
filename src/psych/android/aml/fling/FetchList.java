package psych.android.aml.fling;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

import android.util.Log;

public class FetchList {

	public Records Rec;

	public int NumOfItems = 150;
	public String audioFilesRootPath = "/sdcard/TTS/";
	public String SubPath;
	private boolean readNamesFromFile=true;
	private String nameListSource= "/sdcard/names.txt";
	// public Vector listElements = new Vector();
	public Vector contacts;
	public Vector audioFiles;
	public Vector scrollbarAudioFiles = new Vector();

	public void Fetching() {
		contacts = new Vector();
		audioFiles = new Vector();
		Records rec = new Records();
		// position=0;
		if (rec.getSonificationType().equals("tts")) {
			SubPath = "/tts/";
		} 
		
		else if (rec.getSonificationType().equals("spdxtts")) {
			this.SubPath = "/spdxtts/";
		}

		else if (rec.getSonificationType().equals("spctts")) {
			SubPath = "/spctts/";
		} 
		
		else if (rec.getSonificationType().equals("spdxspctts")) {
			SubPath = "/spdxspctts/";
		}
		
		else if (rec.getSonificationType().equals("nosound")) {
			SubPath = "/spdxspctts/";
		}
		else if (rec.getSonificationType().equals("practise-tts")) {
			SubPath = "/tts/";
		} 
		
		else if (rec.getSonificationType().equals("practise-spdxtts")) {
			this.SubPath = "/spdxtts/";
		}

		else if (rec.getSonificationType().equals("practise-spctts")) {
			SubPath = "/spctts/";
		} 
		
		else if (rec.getSonificationType().equals("practise-spdxspctts")) {
			SubPath = "/spdxspctts/";
		}
		
		else if (rec.getSonificationType().equals("practise-nosound")) {
			SubPath = "/spdxspctts/";
		}
		Log.e("sontype", SubPath);

		prependContacts();
		try {
			if(readNamesFromFile){
				populateContactListFromFile();				
			}
			else{
				populateContactsListFromList(audioFilesRootPath );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void prependContacts(){		
		for(int id=0;id<4;id++){
			contacts.addElement("  ");
		}	
	}
	
	private void populateContactListFromFile() throws IOException{
		File f= new File(nameListSource);
		FileInputStream fstream = new FileInputStream(f);
	    // Get the object of DataInputStream
	    DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;
	    //Read File Line By Line
	    int count = 0;
	    while ((strLine = br.readLine()) != null && count < NumOfItems )   {	    
	      addToContacts(strLine);
	      count++;
	    }
		prependContacts();
	}
	
	public void populateContactsListFromList(String audioFilesDirectory)
			throws IOException {
		// FileConnection fc =
		// (FileConnection)Connector.open(audioFilesDirectory,Connector.READ_WRITE);
		File f = new File(audioFilesDirectory);
		File[] fileNames = f.listFiles();		
		
		Log.e("File names is ", fileNames.toString());
		int i = 0;
		
		while (i < NumOfItems) {
			if (audioFilesDirectory.endsWith("scrollbarSounds/")) {
				String sound = (String) fileNames[i].toString();
				scrollbarAudioFiles.addElement(sound);
				i++;
			} else {				
				addToContacts(fileNames[i].toString());				
				i++;
			}

		}
		prependContacts();
		System.out.println("Contacts are "+contacts);
	}
	
	private void addToContacts(String contact){
		Log.e("contact from file is ", contact);
		String person;
		if(!readNamesFromFile){
			person = contact.substring(0, contact.length() - 4);			
		}
		else{
			person= contact;
		}
		String[] tokens = split(person, "_");
		System.out.println("token is "+ tokens[1]);
		contacts.addElement(tokens[1]);
		//s.e("Name to be Displayed", tokens[1]);
		audioFiles.addElement(contact);
		
	}

	public static String[] split(String inString, String delimeter) {
		String[] returnArray = new String[0];
		try {
			Vector vec = new Vector();
			int indexA = 0;
			int indexB = inString.indexOf(delimeter);

			while (indexB != -1) {
				if (indexB > indexA)
					vec.addElement(new String(inString
							.substring(indexA, indexB)));
				indexA = indexB + delimeter.length();
				indexB = inString.indexOf(delimeter, indexA);
			}
			vec.addElement(new String(inString.substring(indexA, inString
					.length())));
			returnArray = new String[vec.size()];
			for (int i = 0; i < vec.size(); i++) {
				returnArray[i] = vec.elementAt(i).toString();
			}
		} catch (Exception e) {

		}
		return returnArray;
	}

}
