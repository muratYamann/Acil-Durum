package com.yamankod.murat.map;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
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

public class Tab_Call extends Fragment {

    View view;
    LocationService locationService;
    private Button btnItfaiye, btgaz, btnPolice, btnAmbulance, btnYolYardim, btElektrik;

    private Button btnjandarma_call ,btnOrmanYangini_call,btnBldye_call,btnTraficPolisi_call,btnDiger_call;

     Vibrator vibrat;
     private   String numara;

    //"zabıta", "SaglıkDanışmanı","Cenaze Hizmetleri","Sahil Güvenlik","SuArıza fatura Bilgileri","izmit ulaşım"};//1

    String toastArama ,zabita,SaglikDanismani,Cenaze,Sahil,SuArıza,izmitUlasim ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    view=inflater.inflate(R.layout.activity_tab2, container, false);
        numara ="05452284808";

        setupVariable();
        getPermissionCallPhone();
        getPermissionToInternet();
        getPermissionLocation();

        toastArama =getResources().getString(R.string.calling);
        btnClick();


        return view;
    }

    private void btnClick() {


        btnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numara="155";
                vibrat.vibrate(100);
                Intent ambulance = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(ambulance);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });

        btnAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrat.vibrate(100);
                numara="112";
                Intent ambulance = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(ambulance);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });

        btnItfaiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrat.vibrate(100);
                numara="110";
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(i);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });


        btgaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrat.vibrate(100);
                numara ="187";
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(i);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });

        btnYolYardim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrat.vibrate(100);

                Toast.makeText(getActivity(), "Yol yardim biriminizi acil durum birimini ekle girişin den ekleyiniz . ", Toast.LENGTH_LONG).show();
            }
        });


        btElektrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numara="186";
                vibrat.vibrate(100);
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(i);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });


        btnOrmanYangini_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numara="177";
                vibrat.vibrate(100);
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(i);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });

        btnBldye_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numara="441141";
                vibrat.vibrate(100);
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(i);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });


        btnjandarma_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrat.vibrate(100);
                numara ="156";
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(i);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });

        btnTraficPolisi_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrat.vibrate(100);
                numara ="154";
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numara));
                startActivity(i);
                Toast.makeText(getActivity(), toastArama, Toast.LENGTH_LONG).show();
            }
        });


        btnDiger_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] items = new String[]{"zabıta", "Saglık Danışmanı","Cenaze Hizmetleri","Sahil Güvenlik","Su Arıza fatura Bilgileri","izmit ulaşım"};//153,184,188,158,185,4441141
                final Integer[] icons = new Integer[]{R.drawable.call_, R.drawable.call_,R.drawable.call_, R.drawable.call_,R.drawable.call_, R.drawable.call_};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("Arama Yapılacak Numara  Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        try {
                                            String zabita = "153";
                                            vibrat.vibrate(100);
                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + zabita));
                                            startActivity(i);

                                            break;
                                        } catch (Exception e) {
                                        }
                                        break;

                                    case 1:

                                        try {
                                            String saglik = "184";
                                            vibrat.vibrate(100);
                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + saglik));
                                            startActivity(i);

                                        } catch (Exception e) {
                                        }
                                        break;
                                    case 2:

                                        try {
                                            String Cenaze = "188";
                                            vibrat.vibrate(100);
                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Cenaze));
                                            startActivity(i);

                                        } catch (Exception e) {
                                        }
                                        break;
                                    case 3:

                                        try {
                                            String suArıza = "158";
                                            vibrat.vibrate(100);
                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + suArıza));
                                            startActivity(i);

                                        } catch (Exception e) {
                                        }
                                        break;
                                    case 4:

                                        try {
                                            String sahil = "185";
                                            vibrat.vibrate(100);
                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sahil));
                                            startActivity(i);

                                        } catch (Exception e) {
                                        }
                                        break;
                                    case 5:

                                        try {
                                            String izmit = "4441141";
                                            vibrat.vibrate(100);
                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + izmit));
                                            startActivity(i);

                                        } catch (Exception e) {
                                        }
                                        break;
                                }
                            }
                        }).show();

            }
        });








    }

    private void setupVariable() {

        vibrat = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        locationService = new LocationService(getActivity());

        btElektrik = (Button)view.findViewById(R.id.call_elektrik);
        btnAmbulance = (Button)view. findViewById(R.id.call_ambulance);
        btnPolice = (Button)view. findViewById(R.id.call_police);
        btnItfaiye = (Button)view. findViewById(R.id.call_fire);
        btgaz = (Button)view. findViewById(R.id.call_gas);
        btnYolYardim = (Button)view. findViewById(R.id.call_road);

        btnjandarma_call = (Button)view. findViewById(R.id.call_soldier);
        btnOrmanYangini_call = (Button)view. findViewById(R.id.call_orman);
        btnBldye_call = (Button)view. findViewById(R.id.call_belediye);
        btnTraficPolisi_call = (Button)view. findViewById(R.id.call_traffict);
        btnDiger_call =(Button)view.findViewById(R.id.call_diger);

        /*
        toastArama = getResources().getString(R.string.calling);
        zabita = getResources().getString(R.string.calingDepartmenCenaze);
        SaglikDanismani = getResources().getString(R.string.);
        toastArama = getResources().getString(R.string.calling);
        toastArama = getResources().getString(R.string.calling);
        toastArama = getResources().getString(R.string.calling);
        toastArama = getResources().getString(R.string.calling);
         */

        //String toastArama ,zabita,SaglikDanismani,Cenaze,Sahil,SuArıza,izmitUlasim ;

        // sp = getSharedPreferences(Prefs, Context.MODE_PRIVATE);
       //   editor = sp.edit();


    }

    //-----------MarsMallow Permission---------------------------------------------

    final static int PERMISSIONS_REQUEST_CODE = 1;

    public void getPermissionCallPhone() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.CALL_PHONE)) {
            }
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSIONS_REQUEST_CODE);
        }
    }

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

    public void getPermissionToInternet() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.INTERNET)) {
            }
            requestPermissions(new String[]{Manifest.permission.INTERNET},
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
            } else {
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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