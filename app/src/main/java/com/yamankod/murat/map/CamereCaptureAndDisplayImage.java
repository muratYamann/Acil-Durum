package com.yamankod.murat.map;

/**
 * Created by murat on 01.05.2016.
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class CamereCaptureAndDisplayImage extends AppCompatActivity {

    private static String TAG ="_CaptureImage";
    private  String   encodedImageData="murad";
    private LocationService locationService;
    private String konum;


    private static final int CAMERA_REQUEST = 1888;
    ImageView mimageView;

    Intent intentBunle ;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_image_from_camera_and_display);

        locationService = new LocationService(getApplicationContext());
        konum=  String.valueOf(locationService.getLatitude()) + "," +
                String.valueOf(locationService.getLongitude());

        intentBunle = getIntent();
        bundle = intentBunle.getExtras();

        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        Button button = (Button) this.findViewById(R.id.take_image_from_camera);
    }

    public void takeImageFromCamera(View view) {

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {


            Bitmap mphoto = (Bitmap) data.getExtras().get("data");

            encodedImageData =getEncoded64ImageStringFromBitmap(mphoto);

            String brm = bundle.getString("birim");
            String msj =bundle.getString("mesaj");

            new BacgroundTask_V2(getApplicationContext()).execute(brm,konum, msj, encodedImageData);

            Intent i =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);


            mimageView.setImageBitmap(mphoto);




        }
    }


    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }

}