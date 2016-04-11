package psych.android.aml.fling;

import java.util.Random;
import java.util.Vector;

import android.util.Log;

public class GetBinTrial {
	static int randomBinNumber=0;
	String[] currentRandomTarget;
	Records recobj;
	int bins;
	int i,total;
	int trials, ret = 0;
	int numberOfTrialsLeft;
	private int currentBlockNumber=0;
	private Random generator;
	private Vector randomBin;
	int count=0;
	boolean flag=false;
	//int ActualTarget[];
	int[] ActualTarget=new int[300];
	FetchList fetch = new FetchList();

	public String[] updateTarget() {
		/*fetch = new FetchList();
		fetch.Fetching();
		total=fetch.contacts.size();
		for(int i=0;i<=fetch.contacts.size();i++)
		Log.e(Integer.toString(i), fetch.contacts.get(i).toString());
		*/
		currentRandomTarget = generateNextTarget();
		
		return currentRandomTarget;
	}

	public int getTargetid(String randomTarget) {
		fetch.Fetching();
		for ( i = 0; i <fetch.contacts.size(); i++)
			if (fetch.contacts.elementAt(i).equals(randomTarget))
				ret = i + 1;
		return ret;

	}

	private String[] generateNextTarget() {
		int totalNumberOfContacts = 150; // to be parameterized later
		int numberOfContactsInEachBin = 10; // to be made configurable
		int numberOfBins = 0;
		FetchList fetch = new FetchList();
		generator = new Random();
		fetch.Fetching();
		
		// If the total number of contacts can be equally distributed among the
		// bins
		if ((totalNumberOfContacts % numberOfContactsInEachBin) == 0)
			numberOfBins = totalNumberOfContacts / numberOfContactsInEachBin;
		// If Not
		else
			numberOfBins = (totalNumberOfContacts / numberOfContactsInEachBin) + 1;

	
		// Apply first level of randomization (select a random bin from the
		// total number of available bins)
		while(flag==false){
			randomBinNumber = generator.nextInt(numberOfBins ); // because
			// generator.nextInt()
			// generates
			// from 0

			Log.e("random",Integer.toString(randomBinNumber));
			if(List.visited[randomBinNumber]==1){
				flag=false;
			}
			else
			{
				flag=true;
				List.visited[randomBinNumber]=1;
			}
			}
			flag=false;
			System.out.println("Random Bin Generated is  "+ randomBinNumber);
			
		
		// Populate the selected random bin with 10 items accordingly
		randomBin = new Vector();
		for (int i = (randomBinNumber * numberOfContactsInEachBin) ; i < (randomBinNumber * numberOfContactsInEachBin) + numberOfContactsInEachBin+1 ; i++) {
			if(i!=0 || i!=150){
			randomBin.addElement(fetch.contacts.elementAt(i));
			}
		}

		// Apply second level of randomization (select a random item from this
		// bin). This is final random contact name returned.
		int randomNumber = generator.nextInt(randomBin.size() - 1);
		//String randomElement = (String) randomBin.elementAt(generator.nextInt(randomBin.size() - 1));
		String randomElement = (String) randomBin.elementAt(randomNumber);
		// gives the id of the element in the entire queue of names
		int randomElementId = randomBinNumber*numberOfContactsInEachBin + randomNumber - 4;
		
		//to fix the bug when the chosen song is 154
		while (randomElementId >= 150) {
		randomNumber = generator.nextInt(randomBin.size() - 1);
		//String randomElement = (String) randomBin.elementAt(generator.nextInt(randomBin.size() - 1));
		randomElement = (String) randomBin.elementAt(randomNumber);
		// gives the id of the element in the entire queue of names
		randomElementId = randomBinNumber*numberOfContactsInEachBin + randomNumber - 4;
		}
		
		String[] randomElementAndId = {randomElement, Integer.toString(randomElementId)};
		return randomElementAndId;
	}

}
