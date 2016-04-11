package com.ngohung.example.models;

import com.ngohung.widget.ContactItemInterface;

public class ExampleContactItem implements ContactItemInterface{

	private String songname;
	
	public ExampleContactItem(String song_name) {
		super();
		this.songname = song_name;
	}

	// index the list by nickname
	@Override
	public String getItemForIndex() {
		return songname;
	}

	public String getNickName() {
		return songname;
	}

	public void setNickName(String nickName) {
		this.songname = nickName;
	}


}
