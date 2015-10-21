package com.example.appxone.morsecode;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by APPXONE on 10/15/2015.
 */

public class onItemselected  implements AdapterView.OnItemSelectedListener {

    public  static String item="International(ITU)";
   public  static int position=0;
    continentals cont=new continentals();
    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {

        item=parent.getItemAtPosition(pos).toString();
        position=pos;




    }

    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    }
}
