package com.demo.aevicedemo.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.demo.aevicedemo.R;
import com.demo.aevicedemo.utils.TimePickerListener;
import com.demo.aevicedemo.utils.Utils;
import com.demo.aevicedemo.views.adapters.TimeAdapter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMedicationActivity extends AppCompatActivity {
    @BindView(R.id.etDate)
    EditText etDate;
    @BindView(R.id.btnAddPhoto)
    Button btnAddPhoto;
    @BindView(R.id.vInfoMedication)
    LinearLayout vInfoMedication;
    @BindView(R.id.ivMedication)
    ImageView ivMedication;
    @BindView(R.id.etMedicine)
    EditText etMedicine;
    @BindView(R.id.tvDose)
    TextView tvDose;
    @BindView(R.id.ivPlusDose)
    ImageView ivPlusDose;
    @BindView(R.id.ivMinusDose)
    ImageView ivMinusDose;
    @BindView(R.id.tvFrequent)
    TextView tvFrequent;
    @BindView(R.id.ivPlusFrequent)
    ImageView ivPlusFrequent;
    @BindView(R.id.ivMinusFrequent)
    ImageView ivMinusFrequent;
    @BindView(R.id.tvBefore)
    TextView tvBefore;
    @BindView(R.id.tvAfter)
    TextView tvAfter;
    @BindView(R.id.vSelectTime)
    LinearLayout vSelectTime;
    @BindView(R.id.rvTime)
    RecyclerView rvTime;
    @BindView(R.id.btnSave)
    Button btnSave;

    DatePickerDialog picker;

    static int RESULT_CAMERA = 1;
    int currentDose = 2;
    int currentFrequent = 2;
    String[] times = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);
        ButterKnife.bind(this);

        btnAddPhoto.setOnClickListener(view -> {
            Intent intent = new Intent(AddMedicationActivity.this, CameraActivity.class);
            startActivityForResult(intent, RESULT_CAMERA);
        });

        etDate.setInputType(InputType.TYPE_NULL);
        etDate.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus) {
                showDatePicker();
            } else {
                if(picker != null) {
                    picker.hide();
                }
            }
        });
        etDate.setOnClickListener(view -> showDatePicker());

        tvDose.setText(String.format("%d", currentDose));
        tvFrequent.setText(String.format("%d", currentFrequent));
        ivPlusDose.setOnClickListener(view -> processDose(1));
        ivMinusDose.setOnClickListener(view -> processDose(-1));
        ivPlusFrequent.setOnClickListener(view -> processFrequent(1));
        ivMinusFrequent.setOnClickListener(view -> processFrequent(-1));

        vSelectTime.setOnClickListener(view -> showTimePicker());

        Utils.underlineTextview(tvBefore);
        tvBefore.setOnClickListener(view -> {
            Utils.underlineTextview(tvBefore);
            tvAfter.setText("After");
        });
        tvAfter.setOnClickListener(view -> {
            Utils.underlineTextview(tvAfter);
            tvBefore.setText("Before");
        });
    }
    void processDose(int add) {
        currentDose += add;
        if (currentDose == 0) {
            currentDose = 1;
        }
        ivMinusDose.setAlpha(currentDose == 1 ? 0.5f : 1.0f);
        tvDose.setText(""+currentDose);
    }
    void processFrequent(int add) {
        currentFrequent += add;
        if (currentFrequent == 0) {
            currentFrequent = 1;
        }
        ivMinusFrequent.setAlpha(currentFrequent == 1 ? 0.5f : 1.0f);
        tvFrequent.setText(""+currentFrequent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CAMERA) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                etMedicine.setText(result);
                vInfoMedication.setVisibility(View.VISIBLE);

                String img = data.getStringExtra("image");
//                byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Glide.with(this).load(img).signature(new ObjectKey(System.currentTimeMillis())).into(ivMedication);
            }
        }
    }

    void showDatePicker() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(AddMedicationActivity.this,
                (view1, year1, monthOfYear, dayOfMonth) -> etDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
        picker.getDatePicker().setMinDate(System.currentTimeMillis());
        picker.show();
    }
    void showTimePicker() {
        Utils.showTimePicker(this, times, item -> {
            times = item;
            rvTime.setAdapter(new TimeAdapter(AddMedicationActivity.this, times));
            rvTime.setLayoutManager(new GridLayoutManager(AddMedicationActivity.this , 4));
        });

    }
}