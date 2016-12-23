package com.yamankod.murat.map;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.Arrays;
import java.util.List;

public class Tab_SendSmsAndLocation extends Fragment {
    private static String TAG ="_Tab1";

    View view;
    LocationService locationService;
    android.telephony.SmsManager sms;

    private String numara="05452284808";
    private WebService bacgroundTask_v2;
    private String konum;
    private String konumSms;
    private  String msj ;
    private String birim;

    private String amblns ="1";
    private String pls ="2";
    private String yngn ="4";
    private String gz ="3";
    private String ylyrdm ="5";
    private String elktrk ="6";
    private String jandarma ="7";
    private String ormanYng ="8";
    private String belediye="9";
    private String trafikPolisi= "10";

    private Button btnItfaiye, btgaz, btnPolice, btnAmbulance, btnYolYardim, btElektrik;
    private Button btnjandarma ,btnOrmanYangini,btnBldye,btnTraficPolisi;

    SharedPreferences sp;
    SharedPreferences preferences_kisibilgi ;
    public  static  final String myidd ="Prefss";

    Bundle bundle;
    Vibrator vibrat;

    private String rsm ="murad";
    private String kisi_bilgileri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_tab1, container, false);

        setupVariable();
        getPermissionLocation();

     //   kisi_bilgileri =sp.getString("personInfo","girilmedi");
        Log.d(TAG, "kisi_bilgileri tab_sendSMS "+kisi_bilgileri);

       kisi_bilgileri = preferences_kisibilgi.getString("kisi_bilgileri", "kisi_yok");
        Log.d(TAG, "kisi_bilgileri  tamSMS : "+kisi_bilgileri);


        konum=  String.valueOf(locationService.getLatitude()) + "," + String.valueOf(locationService.getLongitude());
        konumSms= "Konum : \n  maps.google.com/?q=" + String.valueOf(locationService.getLatitude()) + "," + String.valueOf(locationService.getLongitude());
        msj ="AcilDurumBilgisi";

        btnClick();

        return view;
    }


    private void setupVariable() {
        preferences_kisibilgi = this.getActivity().getSharedPreferences(myidd, 0);
       // preferences_kisibilgi = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        vibrat = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        locationService = new LocationService(getActivity());
        sms = android.telephony.SmsManager.getDefault();

        btElektrik = (Button)view.findViewById(R.id.elektrik);
        btnAmbulance = (Button)view. findViewById(R.id.ambulance);
        btnPolice = (Button)view. findViewById(R.id.police);
        btnItfaiye = (Button)view. findViewById(R.id.fire);
        btgaz = (Button)view. findViewById(R.id.gas);
        btnYolYardim = (Button)view. findViewById(R.id.road);


        btnjandarma = (Button)view. findViewById(R.id.soldier);
        btnOrmanYangini = (Button)view. findViewById(R.id.orman);
        btnBldye = (Button)view. findViewById(R.id.belediye);
        btnTraficPolisi = (Button)view. findViewById(R.id.traffict);

        bacgroundTask_v2=new WebService(getActivity());

        bundle= new Bundle();
        //shared preferences
       // sp = getSharedPreferences(Prefs, Context.MODE_PRIVATE);
       // editor = sp.edit();
    }


    private void btnClick() {

        btnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] items = new String[]{ "Durum Bildir","Image + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

  new AlertDialog.Builder(getActivity()).setTitle("Polis için işlem Seçin")
        .setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        try {
                            vibrat.vibrate(100);
                            birim = pls;
                            Log.d(TAG, "makePostRequest  Birim: 2 " + "konum:" + konum + " -- Mesaj :" + msj + "--resim :" + "XYZWERSD" + "--Tarih :" + "21:21:21");
                            Toast.makeText(getActivity(), "Gonderildi", Toast.LENGTH_SHORT).show();
                            new WebService(getActivity()).execute(birim, konum, msj, rsm,kisi_bilgileri);
                            break;
                        } catch (Exception e) {
                        }
                        break;

                    case 1:

                        try {
                            vibrat.vibrate(100);
                            birim = pls;
                            bundle.putString("birim", birim);
                            bundle.putString("konum",konum);
                            bundle.putString("mesaj",msj);
                            Intent i = new Intent(getActivity(), Camera.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        } catch (Exception e) {
                        }
                        break;
                }
            }
        }).show();
            }
        });


        btnAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Ambulans için işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                        vibrat.vibrate(100);
                                        birim = amblns;
                                        Toast.makeText(getActivity(), "Gonderildi", Toast.LENGTH_SHORT).show();
                                        new WebService(getActivity()).execute(birim, konum, msj, rsm,kisi_bilgileri);
                                            Log.d(TAG, "kisi_bilgileri tamSendSMS EXECUTE : "+kisi_bilgileri);
                                    }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(100);
                                            birim = amblns;
                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);
                                            startActivity(i);
                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();
            }
        });

        btnItfaiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Yangın için işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(150);
                                            birim =yngn;
                                            Toast.makeText(getActivity()," durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim,konum,msj,rsm);
                                        }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = yngn;
                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle); startActivity(i);
                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();

            }
        });


        btgaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Yangın için işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(100);
                                            birim =gz;
                                            // sms.sendTextMessage(numara, null, msj + "\n" + "\n" + konumSms + "\n", null, null);
                                            Toast.makeText(getActivity(), " durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim,konum,msj,rsm);

                                        }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = gz;

                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);   startActivity(i);
                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();
            }
        });

        btnYolYardim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Yol Yardim için işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(100);
                                            birim = ylyrdm;
                                            Toast.makeText(getActivity()," durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim,konum,msj,rsm);
                                        }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = ylyrdm;
                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);startActivity(i);
                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();
            }
        });


        btElektrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Elektrik Ariza için işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(100);
                                            birim = elktrk;
                                            Toast.makeText(getActivity()," durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim,konum,msj,rsm);
                                        }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = elktrk;

                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);
                                            startActivity(i);

                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();
            }
        });


        /*news**/
        btnjandarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Jandarma için işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(100);
                                            birim = jandarma;
                                            // sms.sendTextMessage(numara, null, msj + "\n" + "\n" + konumSms + "\n", null, null);
                                            Toast.makeText(getActivity()," durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim,konum,msj,rsm);
                                        }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = jandarma;

                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);
                                            startActivity(i);

                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();
            }
        });

        btnOrmanYangini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[]{"Durum Bildir", "Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{R.drawable.sms_oi, R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Orman Yangını işlem Seçin !!!")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(100);
                                            birim = ormanYng;
                                            // sms.sendTextMessage(numara, null, msj + "\n" + "\n" + konumSms + "\n", null, null);
                                            Toast.makeText(getActivity(), " durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim, konum, msj, rsm);
                                        } catch (Exception e) {
                                        }
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = ormanYng;

                                            bundle.putString("birim", birim);
                                            bundle.putString("konum", konum);
                                            bundle.putString("mesaj", msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);
                                            startActivity(i);

                                        } catch (Exception e) {
                                        }
                                        break;
                                }
                            }
                        }).show();
            }
        });

        btnBldye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Belediye için işlem Seçin !!!")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(100);
                                            birim = belediye;
                                            // sms.sendTextMessage(numara, null, msj + "\n" + "\n" + konumSms + "\n", null, null);
                                            Toast.makeText(getActivity()," durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim,konum,msj,rsm);
                                        }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = belediye;

                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);
                                            startActivity(i);

                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();
            }
        });

        btnTraficPolisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[]{ "Durum Bildir","Resim + Durum Bildir"};
                final Integer[] icons = new Integer[]{ R.drawable.sms_oi,R.drawable.camera_oi_k};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Trafk Polisi için işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            vibrat.vibrate(100);
                                            birim = trafikPolisi;
                                            // sms.sendTextMessage(numara, null, msj + "\n" + "\n" + konumSms + "\n", null, null);
                                            Toast.makeText(getActivity()," durum bilginiz iletildi", Toast.LENGTH_LONG).show();
                                            new WebService(getActivity()).execute(birim,konum,msj,rsm);
                                        }catch (Exception e){}
                                        break;
                                    case 1:
                                        try {
                                            vibrat.vibrate(150);
                                            birim = trafikPolisi;

                                            bundle.putString("birim",birim);
                                            bundle.putString("konum",konum);
                                            bundle.putString("mesaj",msj);
                                            Intent i = new Intent(getActivity(), Camera.class);
                                            i.putExtras(bundle);
                                            startActivity(i);

                                        } catch (Exception e) {}
                                        break;
                                }
                            }
                        }).show();
            }
        });

        //yaşlı yakınıma haber ver

        if (!locationService.canGetLocation) {
            locationService.showSettingsAlert();
        }
        locationService.stopUsingGPS();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cikis) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //-----------MarsMallow Permission---------------------------------------------

    final static int PERMISSIONS_REQUEST_CODE = 1;


    public void getPermissionLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            }
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {}
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    //location finder
    public String konumBul(){

           konum= "Konum : \n  maps.google.com/?q," + String.valueOf(locationService.getLatitude()) + "," +
                String.valueOf(locationService.getLongitude());

        return  konum;
    }



    /**iconlar için class*/
    public class ArrayAdapterWithIcon extends ArrayAdapter<String> {

        private List<Integer> images;

        public ArrayAdapterWithIcon(Context context, List<String> items, List<Integer> images) {
            super(context, android.R.layout.select_dialog_item, items);
            this.images = images;
        }

        public ArrayAdapterWithIcon(Context context, String[] items, Integer[] images) {
            super(context, android.R.layout.select_dialog_item, items);
            this.images = Arrays.asList(images);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setCompoundDrawablesWithIntrinsicBounds(images.get(position), 0, 0, 0);
            textView.setCompoundDrawablePadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getContext().getResources().getDisplayMetrics()));
            return view;
        }

    }





}