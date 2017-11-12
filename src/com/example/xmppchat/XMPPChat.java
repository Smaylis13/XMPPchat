package com.example.xmppchat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.jivesoftware.smack.*;

import java.util.ArrayList;

public class XMPPChat extends Activity implements Runnable,OnItemClickListener{

	private ProgressDialog dialog;
	private XMPPConnection connection;
	private ArrayList<String> users = new ArrayList<String>();
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg)	{
		/* code pour mettre à jour la listview avec l'adapter */
			if ( msg.what == 0)
				listView.setAdapter(adapter);
		}
		};
	public final boolean sendEmptyMessage (int what) {
		return false;
	}
	private ArrayAdapter<String> adapter;
	private ListView listView;
		
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users.add("toto");


		listView = (ListView) findViewById(R.id.list_v);
		listView.setOnItemClickListener(this);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, users);///////////////////////////////////
		
		
        dialog = ProgressDialog.show(this,"Connexion","Veuillez patienter...",true);
        Thread t = new Thread(this);
        t.start();

    }

    
    
    
    
    
    
    
    
    
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String HOST = "74.125.133.125";
		int PORT = 5222;
		
		String SERVICE = "gmail.com";
		String USERNAME = "doodleutln@gmail.com";
		String PASSWORD = "etudiant";
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*=====================================SLEEP==================*/
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        /*=================================== CONNEXION ********************* */
		ConnectionConfiguration connConfig = new ConnectionConfiguration(HOST, PORT, SERVICE);
		connection = new XMPPConnection(connConfig);
		try {
			connection.connect();
			connection.login(USERNAME,PASSWORD );
			Log.i("MyTAG", "User : " + connection.getUser());
		} catch (XMPPException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
			/*=========================== CONTACTS ===========================*/
		Roster listecontacts = connection.getRoster();
		for( RosterEntry cnt : listecontacts.getEntries() ){
			users.add(cnt.getUser());
			Log.i("TAG_CONTACT", "Nom : "+cnt.getName()+" User : "+cnt.getUser());
		}
		mHandler.sendEmptyMessage(0);
		dialog.dismiss();
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent lIntent = new Intent(this, ChatWithUser.class);
		lIntent.putExtra("USER", users.get(position));
		startActivity(lIntent);
	}
}
