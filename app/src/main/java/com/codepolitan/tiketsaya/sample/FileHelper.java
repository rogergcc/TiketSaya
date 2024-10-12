package com.codepolitan.tiketsaya.sample;

/**
 * Created on octubre.
 * year 2024 .
 */
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    private final Context context;

    public FileHelper(Context context) {
        this.context = context;
    }

    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public void saveJsonToFile(JSONObject json, String fileName) throws IOException, JSONException {
        if (isExternalStorageWritable()) {
            File file = new File(context.getExternalFilesDir(null), fileName);
            if (file.exists()) {
                Log.d("TEST_LOGGER", "File already exists: " + fileName);
                Log.d("TEST_LOGGER", "Route: " + file.getAbsolutePath());
                return;
            }
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(json.toString(4)); // Indent with 4 spaces for readability
                Log.d("TEST_LOGGER", "File saved: " + file.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("External storage is not writable");
        }
    }
}