package com.demo.aevicedemo.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.demo.aevicedemo.R;
import com.demo.aevicedemo.utils.Utils;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.cameraview)
    SurfaceView cameraView;
    @BindView(R.id.ivClose)
    CircleImageView ivClose;
    @BindView(R.id.btnTake)
    CircleImageView btnTake;

    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    String detectedString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("CameraActivity", "Detector dependencies are not yet available");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(CameraActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if (items.size() != 0) {
                        //new Handler().post(() -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < items.size(); i++) {
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                if (i != items.size() - 1) {
                                    stringBuilder.append("\n");
                                }
                            }
                            detectedString = stringBuilder.toString();
                            Log.w("CameraActivity", "Detector: " + stringBuilder.toString());
                        //});
                    }
                }
            });
        }

        btnTake.setOnClickListener(view -> cameraSource.takePicture(() -> {

        }, bytes -> {
            try {

//                outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));
//                outStream.write(bytes);
//                outStream.close();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                ByteArrayOutputStream blob = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 30 /* Ignored for PNGs */, blob);
//                byte[] bitmapdata = blob.toByteArray();
//                Log.d("PHIMAI", "onPictureTaken - wrote bytes: " + bitmapdata.length);
//                String encoded = Base64.encodeToString(bitmapdata, Base64.DEFAULT);
//                Log.d("PHIMAI", "onPictureTaken - wrote bytes: " + encoded);
                String filePath = Utils.saveToInternalStorage(getApplicationContext(),bitmap);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", detectedString);
                returnIntent.putExtra("image", filePath);
                setResult(Activity.RESULT_OK,returnIntent);

                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "Picture Saved", Toast.LENGTH_SHORT).show();
        }));

        ivClose.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case RequestCameraPermissionID:
            {
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }




}
