package com.codepolitan.tiketsaya.sample;

/**
 * Created on octubre.
 * year 2024 .
 */

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseService {
    public interface DataCallback {
        void onDataReceived(DataSnapshot dataSnapshot);
        void onError(DatabaseError databaseError);
    }

    public void fetchDatabaseStructure(DataCallback callback) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onDataReceived(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError);
            }
        });
    }
}