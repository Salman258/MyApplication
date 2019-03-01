package com.example.salma.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SadqaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadqa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String[] arraySadqaPay = new String[] {"Bank", "Cash"};

        Spinner SadqaPay = (Spinner)findViewById(R.id.spinner_sadqa_pay);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySadqaPay);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SadqaPay.setAdapter(adapter);


        String[] arraySadqaType = new String[] {"Goat(5000)", "Goat(5500)","Goat(6000)","Goat(7000)","Goat(8000)","Goat(9000)","Goat(10,000)","Goat(12,000)","Goat(15,000)",
                                                "Cow(30,000)","Cow(35,000)","Cow(40,000)","Cow(45,000)","Cow(50,000)",
                                                "Camel(50,000)","Camel(55,000)","Camel(60,000)","Hen(300)"};

        Spinner SadqaType = (Spinner)findViewById(R.id.spinner_sadqa_type);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySadqaType);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SadqaType.setAdapter(adapter2);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
