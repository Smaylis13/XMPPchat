package com.example.xmppchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChatWithUser extends Activity{
	EditText edit;
	String user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		user = (String) getIntent().getExtras().get("USER");
		edit = (EditText) findViewById(R.id.edit);
		
	}
	
	public void sendMessage(View view){
	/*	Message msg = new Message(user, Message.Type.chat);
		msg.setBody(edit.getText().toString());
		XMPPLogic.getInstance().getConnection().sendPacket(msg);
	*/}
}
