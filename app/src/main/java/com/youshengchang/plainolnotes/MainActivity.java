package com.youshengchang.plainolnotes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.youshengchang.plainolnotes.data.NoteItem;
import com.youshengchang.plainolnotes.data.NotesDataSource;

import java.util.List;

public class MainActivity extends ListActivity {

    private NotesDataSource dataSource;

    List<NoteItem> noteList;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.youshengchang.plainolnotes.R.layout.activity_main);

        dataSource = new NotesDataSource(this);

        List<NoteItem> notes = dataSource.findAll();
        NoteItem note = notes.get(0);
        note.setText("Updated");
        dataSource.update(note);



        refreshDisplay();

	}

    private void refreshDisplay() {

        noteList = dataSource.findAll();

        ArrayAdapter<NoteItem> adapter = new ArrayAdapter<NoteItem>(this, R.layout.list_item_layout,noteList);
        setListAdapter(adapter);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.youshengchang.plainolnotes.R.menu.main, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_create){
            createNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNote() {

        NoteItem note = NoteItem.getNew();
        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", note.getText());
        startActivityForResult(intent, 1001);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        NoteItem note = noteList.get(position);
        Intent intent = new Intent(this, NoteEditorActivity.class);
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", note.getText());
        startActivityForResult(intent, 1001);

    }
}
