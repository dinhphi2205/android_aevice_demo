package com.demo.aevicedemo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.EditText;
import android.widget.TextView;
import com.demo.aevicedemo.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static void underlineTextview(TextView tv) {
        SpannableString content = new SpannableString(tv.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv.setText(content);
    }

    public static void showTimePicker(Context context, String[] items, TimePickerListener listener) {
        ArrayList<String> result = new ArrayList<>(Arrays.asList(items));
        String[] dataTime = context.getResources().getStringArray(R.array.times);
        boolean[] checked = new boolean[dataTime.length];
        for (int i = 0; i < dataTime.length; i++) {
            checked[i] = Arrays.asList(items).contains(dataTime[i]);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMultiChoiceItems(R.array.times, checked, (DialogInterface.OnMultiChoiceClickListener) (dialogInterface, i, b) -> {
            if (result.contains(dataTime[i]) && !b) {
                result.remove(dataTime[i]);
            } if (!result.contains(dataTime[i]) && b) {
                result.add(dataTime[i]);
            }
        }).setPositiveButton("OK", (dialogInterface, i) -> {
            Collections.sort(result);
            String[] selected = new String[result.size()];
            selected = result.toArray(selected);
            listener.onSelected(selected);
        }).setTitle("Choose time:");
        builder.create().show();
    }

    public static String saveToInternalStorage(Context context, Bitmap bitmap){
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("temp", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory,"1.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
    }

    public static String currentDate(String format) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String currentMiliseconToHHmm() {
        long millis = System.currentTimeMillis();
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1));
    }

    public static void showDialogInputText(Context context, OnSymptonsInput listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("   ");
// Set up the input
        final EditText input = new EditText(context);
        input.setHint("Please input other symptoms");
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        input.requestFocus();

// Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> listener.onInput(input.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}

