package com.example.drew4_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivityDR extends
        Activity implements AdapterView.OnItemSelectedListener {

    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dr);
        Spinner spinner = (Spinner)
                findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

//Creating the ArrayAdapter instance having the list of Countries
        ArrayAdapter adapt1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Cases);
        adapt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(adapt1);
        spinner.setSelection(0);
        choice = Cases[0];
    }

    public void MyClick(View v)
    {
        Intent myIntentA1A2 = new Intent(MainActivityDR.this,Draw2.class);
        myIntentA1A2.putExtra("choice",choice);
        startActivityForResult(myIntentA1A2,1);
    }

    String[] Cases = {"Initial", "Horizontal", "Vertical", "Vertical+Horizontal", "Rotate 30", "Rotate 90", "Rotate 180"};

    @Override
    public void
    onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        choice = arg0.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
