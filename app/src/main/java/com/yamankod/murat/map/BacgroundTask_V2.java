package com.yamankod.murat.map;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by murat on 19.04.2016.
 */
public class BacgroundTask_V2 extends AsyncTask<String ,Void,String> {

    Context ctx;
    private  String TAG ="background";
    private HttpClient httpClient;
    private HttpPost httpPost;
    private List<NameValuePair> nameValuePair;

    JSONObject jsonObject;
    private  ProgressDialog dialog ;

   public BacgroundTask_V2(Context ctx){
       this.ctx=ctx;
       dialog = new ProgressDialog(ctx);
    }


    @Override
    protected String doInBackground(String... params) {

        String brm =params[0];
        String knm=params[1];
        String msj =params[2];
        String rsm=params[3];

        //("2",konum,msj,rsm)

        // replace with your url
        String reg_url ="http://yamankod.com/durumum_acil/service.php";

        //Date & Time
        Calendar c = Calendar.getInstance();
        jsonObject = new JSONObject();


        try {


             httpClient = new DefaultHttpClient();
             httpPost = new HttpPost(reg_url);

            jsonObject.put("birim", brm);
            jsonObject.put("konum", knm);
            jsonObject.put("mesaj", msj);
            jsonObject.put("resim", rsm);
            jsonObject.put("tarih", c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE)+"-"+c.get((Calendar.HOUR_OF_DAY))+"-"+c.get(Calendar.MINUTE));

            nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("parametre", jsonObject.toString()));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "makePostRequest: Jsonn"+jsonObject.toString());
        Log.d(TAG, "makePostRequest  Birim: "+brm+"--konum:"+knm+" -- Mesaj :" +msj+"--resim :"+reg_url+"--Tarih :");

        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }
        //making POST request.
        try {

            HttpResponse response = httpClient.execute(httpPost);
            Log.d("Http Post Response:", response.toString());


        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        String message ="gonderiliyor";
        this.dialog.setMessage(message);

    }

    @Override
    protected void onPostExecute(String results) {
        this.dialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }




}
