package com.codepolitan.tiketsaya.sample;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created on octubre.
 * year 2024 .
 */
public class FirebaseStructureFetcher {
    private final FileHelper fileHelper;
    private final FirebaseService firebaseService;
    private final JsonBuilder jsonBuilder;

    public FirebaseStructureFetcher(FileHelper fileHelper, FirebaseService firebaseService, JsonBuilder jsonBuilder) {
        this.fileHelper = fileHelper;
        this.firebaseService = firebaseService;
        this.jsonBuilder = jsonBuilder;
    }

    public void fetchDatabaseStructure() {
        firebaseService.fetchDatabaseStructure(new FirebaseService.DataCallback() {
            @Override
            public void onDataReceived(DataSnapshot dataSnapshot) {
                printStructure(dataSnapshot, 0);
                JSONObject json;
                try {
                    json = jsonBuilder.buildJson(dataSnapshot);
                    fileHelper.saveJsonToFile(json, "database_structure.json");
                    System.out.println(json.toString());
                } catch (JSONException | IOException e) {
                    Log.e("TEST_LOGGER", "Error converting database structure to JSON: " + e.getMessage());
                    throw new RuntimeException(e);
                }
                System.out.println("---------------------------");

            }

            @Override
            public void onError(DatabaseError databaseError) {
                Log.e("TEST_LOGGER", "Error fetching database structure: " + databaseError.getMessage());
            }
        });
    }
    private void printStructure(DataSnapshot dataSnapshot, int level) {
//        for (DataSnapshot child : dataSnapshot.getChildren()) {
//            printIndent(level);
//            System.out.println(child.getKey());
//            printStructure(child, level + 1);
//        }

        for (DataSnapshot child : dataSnapshot.getChildren()) {
            printIndent(level);
            System.out.print(child.getKey() + ": ");
            if (child.getValue() instanceof String || child.getValue() instanceof Number || child.getValue() instanceof Boolean) {
                System.out.println(child.getValue());
            } else {
                System.out.println();
                printStructure(child, level + 1);
            }
        }
    }

    private void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }


}