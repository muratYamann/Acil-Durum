package com.yamankod.murat.map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class DepartmanAdd extends Activity {

    private  static  String TAG="_DepartmanAdd";


    private int departmanCount;
    String[]newDepartmen ;
    Bundle bundleGet;
    Intent intent;


    public static final String myid = "1";
    public static final int actMode = Activity.MODE_PRIVATE;


    private Button btnBirimSave;

    private EditText birimAdi;
    private EditText birimFonk;
    private EditText birimMsj;
    private EditText birimNumara;

    SharedPreferences preferences ;
    SharedPreferences.Editor editor;

    private String birimisim;
    private String birimislev;
    private String birimmesaj;
    private String birimnumra;

    private String bundleBirim;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departmanadd);

        SetupVariable();
        OnClickListener();



    }

    public void SetupVariable(){

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        //bundle getData fileName
         intent= getIntent();
         bundleGet = intent.getExtras();

        birimAdi = (EditText)findViewById(R.id.departmanName);
        birimFonk = (EditText)findViewById(R.id.departmanFonksiyon);
        birimMsj =(EditText)findViewById(R.id.departmanMessage);
        birimNumara=(EditText)findViewById(R.id.departmanNumber);

        btnBirimSave = (Button) findViewById(R.id.btnSave);


    }


    public void OnClickListener() {
        Log.d(TAG, "OnClick");


        btnBirimSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundleBirim = bundleGet.getString("fullname");

                    String adi = birimAdi.getText().toString().trim();
                    String fonksiyonu = birimFonk.getText().toString().trim();
                    String mesaj = birimMsj.getText().toString().trim();
                    String numara = birimNumara.getText().toString().trim();


                if(adi != null && !mesaj.isEmpty() && !numara.isEmpty() ){

                        String data = adi + ";" + fonksiyonu + ";" + mesaj + ";" + numara;
                        editor.putString(bundleBirim, data);
                        editor.commit();

                        Toast.makeText(DepartmanAdd.this, "Kayit Başarılı", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(DepartmanAdd.this, "Boş girilen yerleri doldurunuz ", Toast.LENGTH_SHORT).show();
                    }


             /*
                SharedPreferences preferences = getSharedPreferences(myid, actMode);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", user);
                editor.putString("surname", surname);
                editor.putString("userID", convertStringID);
                editor.putString("userAge", convertStringID);
                editor.commit();
                */


            }
        });
    }





}