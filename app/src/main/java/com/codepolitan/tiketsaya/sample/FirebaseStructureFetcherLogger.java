package com.codepolitan.tiketsaya.sample;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created on octubre.
 * year 2024 .
 */
public class FirebaseStructureFetcherLogger {

    private static final String TAG = "FirebaseLogger";

    public void fetchDatabaseStructure() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                printStructure(dataSnapshot, 0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error fetching database structure: " + databaseError.getMessage());
            }
        });
    }

    private void printStructure(DataSnapshot dataSnapshot, int level) {
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            printIndent(level);
            Log.d(TAG, child.getKey() + ": " + getValueString(child));
            if (child.hasChildren()) {
                printStructure(child, level + 1);
            }
        }
    }

    private void printIndent(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        Log.d(TAG, indent.toString());
    }

    private String getValueString(DataSnapshot dataSnapshot) {
        Object value = dataSnapshot.getValue();
        if (value instanceof String || value instanceof Number || value instanceof Boolean) {
            return value.toString();
        } else {
            return "";
        }
    }

//    public static void main(String[] args) {
//        FirebaseStructureFetcherLogger fetcher = new FirebaseStructureFetcherLogger();
//        fetcher.fetchDatabaseStructure();
//    }
}
