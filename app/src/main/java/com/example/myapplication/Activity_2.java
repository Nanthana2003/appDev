package com.example.myapplication;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;

import com.example.myapplication.views.CameraPreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Activity_2 extends AppCompatActivity {
    private Camera mCamera;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private CameraPreview mPreview;
    public Camera.PictureCallback mPicture;
    public static final int MEDIA_TYPE_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
            Log.w("camera", "permission obtained");
            mCamera = getCameraInstance();

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        } else {
            // Permission is already granted, proceed with camera operation
            Log.w("camera", "permission already there");
            mCamera = getCameraInstance();

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.w("path", imagePath);
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }


    public void checkCameraHardware() {
        Context context = getApplicationContext();
        Log.w("cam", "entered");
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            Log.w("camera", "working");
//            return true;

        } else {
            // no camera on this device
            Log.w("camera", "not detected");
//            return false;

        }
//
    }

    public void takepic(View view){
//        mPicture = new Camera.PictureCallback() {
//
//            @Override
//            public void onPictureTaken(byte[] data, Camera camera) {
//
//                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//
//                if (pictureFile == null){
//                    Log.d("picture", "Error creating media file, check storage permissions");
//                    return;
//                }
//
//
//                try {
//                    FileOutputStream fos = new FileOutputStream(pictureFile);
//                    fos.write(data);
//                    Log.w("test", "here");
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    Log.d("picture", "File not found: " + e.getMessage());
//                } catch (IOException e) {
//                    Log.d("picture", "Error accessing file: " + e.getMessage());
//                }
//            }
//        };
        mCamera.takePicture(null, null, new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

                if (pictureFile == null){
                    Log.d("picture", "Error creating media file, check storage permissions");
                    return;
                }


                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    Log.w("test", "here");
                    fos.close();
                } catch (FileNotFoundException e) {
                    Log.d("picture", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("picture", "Error accessing file: " + e.getMessage());
                }
                Log.w("picture", "taken successfully");
            }
        });




    }

    /** A safe way to get an instance of the Camera object. */



        public static Camera getCameraInstance(){
            Camera c = null;
            try {
                c = Camera.open(); // attempt to get a Camera instance
            }
            catch (Exception e){
                // Camera is not available (in use or does not exist)
                Log.w("app:","error in opening camera " + e.getMessage());
            }
            return c; // returns null if camera is unavailable
        }


}

/** A basic Camera preview class */

