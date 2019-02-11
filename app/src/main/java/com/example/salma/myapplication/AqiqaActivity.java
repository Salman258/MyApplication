package com.example.salma.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AqiqaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqiqa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] arrayAqiqaPay = new String[] {"Bank", "Cash"};

        Spinner AqiqaPay = (Spinner)findViewById(R.id.spinner_aqiqa_pay);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayAqiqaPay);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AqiqaPay.setAdapter(adapter);


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
