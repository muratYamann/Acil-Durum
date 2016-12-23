package com.yamankod.murat.map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by murat on 09.04.2016.
 */
public class Login extends AppCompatActivity {

    private  static        String TAG="kisiKAyit";
    private  static  final String NAMESPACE_TC ="http://tckimlik.nvi.gov.tr/WS";
    private  static  final String SERVİCEURL_TC ="https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx";
    private  static  final String METHODNAME_TC ="TCKimlikNoDogrula";
    private  static  final String SOAPACTION_TC="http://tckimlik.nvi.gov.tr/WS/TCKimlikNoDogrula";


    public  static  final String myidd ="Prefss";
    public  static  final String myid ="Prefs";
    public static final int actMode = Activity.MODE_PRIVATE;

    SharedPreferences preferences_kisi_bilgi ;
    SharedPreferences.Editor editor_kisi_bilgi;


    private Boolean response;
    String personInfo ="";
    private EditText userName;
    private EditText lastName;
    private EditText userID;
    private EditText userAge;
    private EditText Adres;
    private TextView tSonuc ;
    private String donen="";
    private Button btnLogin;


    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Login.this.getWindow().setStatusBarColor(Color.parseColor("#b21212"));

        setContentView(R.layout.login);
        setupVariable();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    String login ="yes";
                    String user = userName.getText().toString().trim();
                    String surname = lastName.getText().toString().trim();
                    long ID = Long.valueOf(userID.getText().toString().trim());
                    int Age = Integer.valueOf(userAge.getText().toString().trim());
                    String adress = Adres.getText().toString();
                    Log.d(TAG, "OnClick");

                    String convertStringID = String.valueOf(ID);
                    String convertStringAge = String.valueOf(Age);

                    String uID =userID.getText().toString().trim();
                    int uid =uID.length();
                    String yas = String.valueOf(Age);


                    if( ( uid==11 ) &&  ( !user.isEmpty() ) && ( !surname.isEmpty() )  && ( yas.length()==4 ) ) {

                        MyAsyncTask task = new MyAsyncTask();
                        task.execute();

                        personInfo = user+","+surname+","+yas+","+adress;
                        editor_kisi_bilgi.putString("kisi_bilgileri",personInfo);
                        editor_kisi_bilgi.commit();
                        Log.d(TAG, "kisi_bilgileri login: "+personInfo);


                        sp= getSharedPreferences(myid, actMode);
                        editor = sp.edit();

                        editor.putString("username", user);
                        editor.putString("surname", surname);
                        editor.putString("userıd", convertStringID);
                        editor.putString("personInfo",personInfo);
                        editor.putString("login", login);
                        editor.putInt("count", 1);

                        editor.commit();

                    }
                    else {
                        Toast.makeText(Login.this, "Tc niz 11 karaketerden küçük olamaz ", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e){}


            }
        });

    }
    public void setupVariable(){

        userName = (EditText)findViewById(R.id.username);
        lastName = (EditText)findViewById(R.id.lastname);
        userID =(EditText)findViewById(R.id.userID);
        userAge=(EditText)findViewById(R.id.age);
        Adres = (EditText)findViewById(R.id.etAdres);
        tSonuc=(TextView)findViewById(R.id.tvResponse);
        btnLogin =(Button)findViewById(R.id.btnlogin);

        preferences_kisi_bilgi =getSharedPreferences(myidd,0);
        editor_kisi_bilgi = preferences_kisi_bilgi.edit();


    }
    public class MyAsyncTask extends AsyncTask<String, Void, String> {


        private final ProgressDialog dialog = new ProgressDialog(Login.this);
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Kontrol ediliyor...");
            this.dialog.show();
        }
        @Override
        protected void onPostExecute(String sonuc) {
            try {
                 donen=sonuc;
                if (sonuc != null) {
                    Log.d(TAG, "onPostExecute: Sonuc "+sonuc);
                    if (sonuc.equals("true")){
                        Toast.makeText(getApplicationContext(),"Giriş Başarılı", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "donen: "+donen.toString());

                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Boyle bir Kayit yok  \n veya \n bilgileri yanlış girdiniz  . Bilgilerinizi Dogru giriniz.", Toast.LENGTH_SHORT).show();}
                    tSonuc.setText(sonuc);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Result Found is ==  " + sonuc + "", Toast.LENGTH_LONG).show();
                }
                if (this.dialog.isShowing()) {
                    this.dialog.dismiss();
                }
                super.onPostExecute(sonuc);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "tekrar Deneyin", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            String son = "";
            son= ControlUserID();
            return son;
        }
    };

    public String ControlUserID() {

        Log.d(TAG, "ControlUserID: ");
        String responseValue = "";
        SoapObject request = new SoapObject(NAMESPACE_TC, METHODNAME_TC);
        request.addProperty("TCKimlikNo", Long.valueOf(userID.getText().toString()));
        request.addProperty("Ad",userName.getText().toString().toUpperCase().trim());
        request.addProperty("Soyad",lastName.getText().toString().toUpperCase().trim());
        request.addProperty("DogumYili",Integer.valueOf(userAge.getText().toString().trim()));

        Log.d(TAG,request.toString());

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(request);
        HttpTransportSE ahtSE = new HttpTransportSE(SERVİCEURL_TC);
        try {
            ahtSE.call(SOAPACTION_TC, soapEnvelope);
        } catch (IOException e) {

            e.printStackTrace();

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        try {
            responseValue = ""+(SoapPrimitive) soapEnvelope.getResponse();
            Log.d(TAG,responseValue);
        } catch (SoapFault e) {
            e.printStackTrace();
        }
        return responseValue;
    }
}
