package com.example.androidexamplelistcontact;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ExampleListContacts extends ListActivity {

	private ArrayAdapter<String> adapter;
	
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
       // setContentView(R.layout.activity_example_list_contacts);
   
        //Uri for search contacts
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        
        //Recover Contacts using Cursor
        Cursor c = getContentResolver().query(uri, null, null, null, null);
        
        //Uses ArrayAdapter, to present Array of String on Screen
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        
        //Course the elements of Cursor, that are contact of the agenda
        while (c.moveToNext()){
        	
        	adapter.add(c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
        }
        //
        setListAdapter(adapter);
        c.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
    	super.onListItemClick(l, v, position, id);
    	
    	// When users click in the Item to list
    	String name = (String) adapter.getItem(position);
    	Toast.makeText(this, "Contact Selected: " + name, Toast.LENGTH_SHORT).show();
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.example_list_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}