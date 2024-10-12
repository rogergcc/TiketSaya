package com.codepolitan.tiketsaya.sample;

/**
 * Created on octubre.
 * year 2024 .
 */
import com.google.firebase.database.DataSnapshot;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonBuilder {
    public JSONObject buildJson(DataSnapshot dataSnapshot) throws JSONException {
        JSONObject json = new JSONObject();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            if (child.hasChildren()) {
                json.put(child.getKey(), buildJson(child));
            } else {
                json.put(child.getKey(), child.getValue());
            }
        }
        return json;
    }
}