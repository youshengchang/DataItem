package com.youshengchang.plainolnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.youshengchang.plainolnotes.data.NoteItem;

/**
 * Created by youshengchang on 3/8/15.
 */
public class NoteEditorActivity extends Activity {

    private NoteItem note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Intent intent = this.getIntent();
        note = new NoteItem();
        note.setKey(intent.getStringExtra("key"));
        note.setText(intent.getStringExtra("text"));
        EditText et = (EditText)findViewById(R.id.noteText);
        et.setText(note.getText());
        et.setSelection(note.getText().length());

    }

    private void saveAndFinish(){

        EditText et = (EditText)findViewById(R.id.noteText);
        String editText = et.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("key", note.getKey());
        intent.putExtra("text", editText);
        setResult(RESULT_OK, intent);
        finish();
    }
}
