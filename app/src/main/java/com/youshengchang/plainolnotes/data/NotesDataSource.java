package com.youshengchang.plainolnotes.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by youshengchang on 3/8/15.
 */
public class NotesDataSource {

    private static final String PREFKEY = "notes";
    private SharedPreferences notesPref;

    public NotesDataSource(Context context){
        notesPref = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);

    }

    public List<NoteItem> findAll(){

        Map<String, ?> notesMap = notesPref.getAll();
        SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());

        List<NoteItem> noteList = new ArrayList<NoteItem>();

//        if(keys.isEmpty()){
//            Log.d("NOTES", "noteList is empty");
//            NoteItem note = NoteItem.getNew();
//            noteList.add(note);
//            Log.d("NOTES", note.getKey());
//            return noteList;
//        }
        Log.d("NOTES", "The noteList is not empty.");
        for(String key: keys){
            NoteItem note = new NoteItem();
            note.setKey(key);
            note.setText((String)notesMap.get(key));

            noteList.add(note);
        }

        return noteList;
    }

    public boolean update(NoteItem note){

        SharedPreferences.Editor editor = notesPref.edit();
        editor.putString(note.getKey(),note.getText());
        editor.commit();
        return true;
    }

    public boolean remove(NoteItem note){

        if(notesPref.contains(note.getKey())){
            SharedPreferences.Editor editor = notesPref.edit();
            editor.remove(note.getKey());
            editor.commit();
        }
        return true;


    }

}
