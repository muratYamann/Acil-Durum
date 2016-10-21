package com.yamankod.murat.map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends Activity {

    private Intent intentmain;
    private Intent intetLogin;

    public  static  final String myId ="Prefs";
    public  static  final int actMode =Activity.MODE_PRIVATE;

    private static final String TAG = "_Splash";
    private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

//        SplashActivity.this.getWindow().setStatusBarColor(Color.parseColor("#b21212"));

        setContentView(R.layout.activity_splash);
        startHandler();

    }
    public void startHandler(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                intetLogin  =new Intent(getApplicationContext(),Login.class);
                intentmain =new Intent(getApplicationContext(),MainActivity.class);

                SharedPreferences myPrefs = getSharedPreferences(myId, actMode);

                int kulaniciLogin = myPrefs.getInt("count", 0);
                String kLogin =myPrefs.getString("login","false");

                Log.d(TAG, "Splash: "+kLogin);

                 if (kulaniciLogin==0){
                    startActivity(intetLogin);
                }else {
                    startActivity(intentmain);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}