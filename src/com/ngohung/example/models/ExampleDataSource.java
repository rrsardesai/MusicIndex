package com.ngohung.example.models;

import java.util.ArrayList;
import java.util.List;

import com.ngohung.widget.ContactItemInterface;

public class ExampleDataSource {

	public static List<ContactItemInterface> getSampleContactList(){
		 List<ContactItemInterface>  list = new  ArrayList<ContactItemInterface> ();
		
		 list.add(new ExampleContactItem("Lizbeth" ) );
		 list.add(new ExampleContactItem("Zachery" ) );
		 list.add(new ExampleContactItem("Vada" ) );
		 list.add(new ExampleContactItem("Essie" ) );
		 list.add(new ExampleContactItem("Gracia" ) );
		 list.add(new ExampleContactItem("Roselia") );
		 list.add(new ExampleContactItem("Venice" ) );
		 list.add(new ExampleContactItem("Lanita" ) );
		 list.add(new ExampleContactItem("Chana" ) );
		 list.add(new ExampleContactItem("Stella" ) );
		 
		 list.add(new ExampleContactItem("Pete" ) );
		 list.add(new ExampleContactItem("Dwain") );
		 list.add(new ExampleContactItem("Terisa" ) );
		 list.add(new ExampleContactItem("Delisa" ) );
		 list.add(new ExampleContactItem("Zada") );
		 list.add(new ExampleContactItem("Rosalie" ) );
		 list.add(new ExampleContactItem("Gladis") );
		 list.add(new ExampleContactItem("Branda" ) );
		 list.add(new ExampleContactItem("Tory" ) );
		 
		 
		 list.add(new ExampleContactItem("Gregorio" ) );
		 list.add(new ExampleContactItem("Jaclyn") );
		 list.add(new ExampleContactItem("Juana") );
		 list.add(new ExampleContactItem("Demetrius") );
		 list.add(new ExampleContactItem("Joline" ) );
		 list.add(new ExampleContactItem("Neida") );
		 
		 list.add(new ExampleContactItem("1111" ) );
		 list.add(new ExampleContactItem("7777") );
		 list.add(new ExampleContactItem("5555") );
		
		 
		 return list;
	}
	
	
}
