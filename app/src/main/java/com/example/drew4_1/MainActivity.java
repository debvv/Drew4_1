package com.example.drew4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void MyClick(View v)
    { try {
        Intent myIntentA1A2 = new Intent(MainActivity.this,Draw1.class);
        startActivityForResult(myIntentA1A2,1);
    } catch (Exception e) {
        Toast.makeText(getBaseContext(),
                e.getMessage(),
                Toast.LENGTH_LONG).show();
    }
    }
}