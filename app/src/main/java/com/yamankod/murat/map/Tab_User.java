package com.yamankod.murat.map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tab_User extends Fragment   {

    Context mContext;

    public static String TAG="_UserEmergency";
    SharedPreferences preferences ;


    String birimMesaj ;
    String birimAdi;
    String birimIslev;


    String m1="";
    String m2="";
    String m3="";
    String m4="";
    String m5="";
    String m6="";
    String m7="";
    String m8="";
    String m9="";
    String m10="";

    String birimAdi1;
    String birimAdi2;
    String birimAdi3;
    String birimAdi4;
    String birimAdi5;
    String birimAdi6;
    String birimAdi7;
    String birimAdi8;

    String br1;
    String br2;
    String br3;
    String br4;
    String br5;
    String br6;
    String br7;
    String br8;





    private String gonderilecekNumara;
    private String konum;
    private  String Sendmsj ;
    Bundle bundleBirimAdi;

    android.telephony.SmsManager sms;
    LocationService locationService ;

    String msj="";
    View view;
    Button birim1,birim2,birim3,birim4,birim5,birim6,birim7,birim8,birim9,birim10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_tab3, container, false);

        setupVariable();
        konum= "Konum : \n  maps.google.com/?q=" + String.valueOf(locationService.getLatitude()) + "," +
                String.valueOf(locationService.getLongitude());

        butonName();

        onClick();


        return view;
    }
    public  void setupVariable(){

        birim1 =(Button)view.findViewById(R.id.brm1);
        birim2 =(Button)view.findViewById(R.id.brm2);
        birim3 =(Button)view.findViewById(R.id.brm3);
        birim4 =(Button)view.findViewById(R.id.brm4);
        birim5 =(Button)view.findViewById(R.id.brm5);
        birim6 =(Button)view.findViewById(R.id.brm6);
      //  birim7 =(Button)view.findViewById(R.id.brm7);
      //  birim8 =(Button)view.findViewById(R.id.brm8);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        locationService = new LocationService(getActivity());
        sms = android.telephony.SmsManager.getDefault();


        mContext =getActivity();
        bundleBirimAdi = new Bundle();

    }

    public void  butonName(){

         br1 = preferences.getString("birim1", "Boş");
        String[]data1 =br1.split(";");
         birimAdi1= data1[0].toString();
         birim1.setText("1."+birimAdi1.toString());

         br2 = preferences.getString("birim2", "Boş");
        String[]data2 =br2.split(";");
        birimAdi2= data2[0].toString();
        birim2.setText("2."+birimAdi2.toString());

         br3 = preferences.getString("birim3", "Boş");
        String[]data3 =br3.split(";");
        birimAdi3= data3[0].toString();
        birim3.setText("3."+birimAdi3.toString());

         br4 = preferences.getString("birim4", "Boş");
        String[]data4 =br4.split(";");
         birimAdi4= data4[0].toString();
        birim4.setText(birimAdi4.toString());

         br5 = preferences.getString("birim5", "Boş");
        String[]data5 =br5.split(";");
        birimAdi5= data5[0].toString();
        birim5.setText(birimAdi5.toString());

        br6 = preferences.getString("birim6", "Boş");
        String[]data6 =br6.split(";");
        birimAdi6= data6[0].toString();
        birim6.setText(birimAdi6.toString());
/*
        br7 = preferences.getString("birim7", "Boş");
        String[]data7 =br7.split(";");
        birimAdi7= data7[0].toString();
        birim7.setText(birimAdi7.toString());

        br8 = preferences.getString("birim8", "Boş");
        String[]data8 =br8.split(";");
        birimAdi8= data8[0].toString();
        birim8.setText(birimAdi8.toString());*/
    }


    public  void  onClick(){

        Log.d(TAG, "onClick: ");
        birim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] items = new String[]{"Ara", "SMS", "Acil birimini Ekle"};
                final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi, R.drawable.add_oi};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        if (!br1.equals("Boş")) {

                                            String myStr = preferences.getString("birim1", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();

                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi  Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 1:

                                        if (!br1.equals("Boş")) {

                                            String myStr = preferences.getString("birim1", "a");

                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();

                                            sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                        } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;

                                    case 2:
                                        try {
                                            bundleBirimAdi.putString("fullname", "birim1");
                                            Intent is = new Intent(getActivity(), DepartmanAdd.class);
                                            is.putExtras(bundleBirimAdi);
                                            startActivity(is);
                                        } catch (Exception e) {
                                        }
                                        break;
                                }
                            }
                        }).show();}});



        birim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              final String[] items = new String[]{"Ara", "SMS", "Acil birimini Ekle"};
            final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi, R.drawable.add_oi};
            ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

            new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                    .setAdapter(adapter, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                           switch (item) {
                                case 0:

                                    if (!br2.equals("Boş")) {

                                        String myStr = preferences.getString("birim2", "a");
                                        String[] data = myStr.split(";");
                                        birimAdi = data[0].toString();
                                        birimIslev = data[1].toString();
                                        birimMesaj = data[2].toString();
                                        gonderilecekNumara = data[3].toString();

                                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                        startActivity(i);
                                     } else {
                                        Toast.makeText(getActivity(), "Birim girilmedi  Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                case 1:

                                    if (!br2.equals("Boş")) {

                                        String myStr = preferences.getString("birim2", "a");

                                        String[] data = myStr.split(";");
                                        birimAdi = data[0].toString();
                                        birimIslev = data[1].toString();
                                        birimMesaj = data[2].toString();
                                        gonderilecekNumara = data[3].toString();

                                        sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                     } else {
                                        Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                    }
                                    break;

                                case 2:
                                    try {
                                        bundleBirimAdi.putString("fullname", "birim2");
                                        Intent is = new Intent(getActivity(), DepartmanAdd.class);
                                        is.putExtras(bundleBirimAdi);
                                        startActivity(is);
                                    } catch (Exception e) {
                                    }
                                    break;
                            }
                        }
                    }).show();}});



        birim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] items = new String[]{"Ara", "SMS", "Acil birimini Ekle"};
                final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi, R.drawable.add_oi};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:

                                        if (!br3.equals("Boş")) {
                                            String myStr = preferences.getString("birim3", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();

                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 1:

                                        if (!br3.equals("Boş")) {

                                            String myStr = preferences.getString("birim3", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();
                                            sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                        } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 2:
                                        try {
                                            bundleBirimAdi.putString("fullname", "birim3");
                                            Intent is = new Intent(getActivity(), DepartmanAdd.class);
                                            is.putExtras(bundleBirimAdi);
                                            startActivity(is);
                                        } catch (Exception e) {
                                        }
                                        break;
                                }
                            }
                        }).show();}});
        birim4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = new String[]{"Ara", "SMS", "Acil birimini Ekle"};
                final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi, R.drawable.add_oi};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                 switch (item) {
                                    case 0:

                                        if (!br4.equals("Boş")) {

                                            String myStr = preferences.getString("birim4", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();

                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                            startActivity(i);
                                          } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 1:

                                        if (!br4.equals("Boş")) {

                                            String myStr = preferences.getString("birim4", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();
                                            sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                        } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 2:
                                        try {
                                            bundleBirimAdi.putString("fullname", "birim4");
                                            Intent is = new Intent(getActivity(), DepartmanAdd.class);
                                            is.putExtras(bundleBirimAdi);
                                            startActivity(is);
                                        } catch (Exception e) {
                                        }break;
                                }}}).show();}});

        birim5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String[] items = new String[]{"Ara", "SMS", "Acil birimini Ekle"};
                final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi, R.drawable.add_oi};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:

                                        if (!br5.equals("Boş")) {

                                            String myStr = preferences.getString("birim5", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();

                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                            startActivity(i);
                                           } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 1:

                                        if (!br5.equals("Boş")) {

                                            String myStr = preferences.getString("birim5", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();
                                            sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                            Toast.makeText(getActivity(), birimMesaj, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 2:
                                        try {
                                            bundleBirimAdi.putString("fullname", "birim5");
                                            Intent is = new Intent(getActivity(), DepartmanAdd.class);
                                            is.putExtras(bundleBirimAdi);
                                            startActivity(is);
                                        } catch (Exception e) {
                                        }break;
                                }}}).show();}});



        birim6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] items = new String[]{"Ara", "SMS", "Acil birimini Ekle"};
                final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi, R.drawable.add_oi};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                 switch (item) {
                                    case 0:

                                        if (!br6.equals("Boş")) {

                                            String myStr = preferences.getString("birim6", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();

                                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                            startActivity(i);
                                         } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 1:

                                        if (!br6.equals("Boş")) {

                                            String myStr = preferences.getString("birim6", "a");
                                            String[] data = myStr.split(";");
                                            birimAdi = data[0].toString();
                                            birimIslev = data[1].toString();
                                            birimMesaj = data[2].toString();
                                            gonderilecekNumara = data[3].toString();
                                            sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                        } else {
                                            Toast.makeText(getActivity(), "Birim girilmedi Lütfen Birim Giriniz.!!!", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 2:
                                        try {
                                            bundleBirimAdi.putString("fullname", "birim6");
                                            Intent is = new Intent(getActivity(), DepartmanAdd.class);
                                            is.putExtras(bundleBirimAdi);
                                            startActivity(is);
                                        } catch (Exception e) {
                                        }break;
                                }}}).show();}});



        /*
        birim7.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                try {

                    bundleBirimAdi.putString("fullname", "birim7");

                    Intent i = new Intent(getActivity(), DepartmanAdd.class);
                    i.putExtras(bundleBirimAdi);
                    startActivity(i);

                } catch (Exception e) {

                }
                return true;
            }
        });


        birim7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String myStr = preferences.getString("birim7", "a");

                String[] data = myStr.split(";");
                String birimAdi = data[0].toString();
                String birimIslev = data[1].toString();
                final String birimMesaj = data[2].toString();
                gonderilecekNumara = data[3].toString();

                Toast.makeText(getActivity(), birimAdi + birimIslev + birimMesaj + gonderilecekNumara, Toast.LENGTH_SHORT).show();


                final String[] items = new String[]{"Ara", "SMS"};
                final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                Toast.makeText(getActivity(), "call Selected: " + item, Toast.LENGTH_SHORT).show();
                                switch (item) {
                                    case 0:
                                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                        startActivity(i);
                                        Toast.makeText(getActivity(), "sms Selected: " + item, Toast.LENGTH_SHORT).show();
                                        break;

                                    case 1:
                                        sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                        Toast.makeText(getActivity(), birimMesaj, Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }).show();

            }

        });


        birim8.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                try {

                    bundleBirimAdi.putString("fullname", "birim8");

                    Intent i = new Intent(getActivity(), DepartmanAdd.class);
                    i.putExtras(bundleBirimAdi);
                    startActivity(i);

                } catch (Exception e) {

                }
                return true;
            }
        });
        birim8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String myStr = preferences.getString("birim8", "a");

                String[] data = myStr.split(";");
                String birimAdi = data[0].toString();
                String birimIslev = data[1].toString();
                final String birimMesaj = data[2].toString();
                gonderilecekNumara = data[3].toString();

                Toast.makeText(getActivity(), birimAdi + birimIslev + birimMesaj + gonderilecekNumara, Toast.LENGTH_SHORT).show();


                final String[] items = new String[]{"Ara", "SMS"};
                final Integer[] icons = new Integer[]{R.drawable.call_oi, R.drawable.sms_oi};
                ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);

                new AlertDialog.Builder(getActivity()).setTitle("işlem Seçin")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                Toast.makeText(getActivity(), "call Selected: " + item, Toast.LENGTH_SHORT).show();
                                switch (item) {
                                    case 0:
                                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + gonderilecekNumara));
                                        startActivity(i);
                                        Toast.makeText(getActivity(), "sms Selected: " + item, Toast.LENGTH_SHORT).show();
                                        break;

                                    case 1:
                                        sms.sendTextMessage(gonderilecekNumara, null, birimMesaj + "\n" + "\n" + konum + "\n", null, null);
                                        Toast.makeText(getActivity(), birimMesaj, Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }).show();

            }

        });
*/


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