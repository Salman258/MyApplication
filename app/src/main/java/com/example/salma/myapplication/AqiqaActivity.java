package com.example.salma.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class AqiqaActivity extends AppCompatActivity {

    private String transactionType;
    private String donationType;
    private int quantity;
    private String name;
    private double amount;
    private String email;
    private String phoneNumber;
    private String remarks;
    private String formtype;

    private Spinner aqiqaPay;
    private Spinner aqiqaType;
    private NumberPicker np;
    private EditText txtName;
    private EditText txtemail;
    private EditText txtphone;
    private EditText txtremarks;
    private Button btnDonate;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqiqa);
        aqiqaType = (Spinner)findViewById(R.id.spinner_aqiqa_type);
        aqiqaPay = (Spinner)findViewById(R.id.spinner_aqiqa_pay);
        np = (NumberPicker)findViewById(R.id.aqiqaQuantity);
        txtName = (EditText)findViewById(R.id.txtName2);
        txtemail =(EditText)findViewById(R.id.txtEmail2);
        txtphone = (EditText)findViewById(R.id.txtPhone2);
        txtremarks =(EditText)findViewById(R.id.txtRemark2);
        btnDonate = (Button)findViewById(R.id.btnDonate2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] arrayAqiqaPay = new String[] {"Bank", "Cash"};
        aqiqaPay = (Spinner)findViewById(R.id.spinner_aqiqa_pay);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayAqiqaPay);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aqiqaPay.setAdapter(adapter);


        String[] arrayAqiqaType = new String[] {"Goat(8000)", "Goat(9000)","Goat(10,000)","Goat(12,000)","Goat(15,000)"
                                                ,"Cow(40,000)","Cow(45,000)","Cow(50,000)"
                                                ,"Camel(50,000)","Camel(55,000)","Camel(60,000)"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayAqiqaType);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aqiqaType.setAdapter(adapter2);

        np.setMinValue(1);
        np.setMaxValue(1000);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionType = aqiqaPay.getSelectedItem().toString();
                donationType = aqiqaPay.getSelectedItem().toString();
                quantity = np.getValue();
                name =txtName.getText().toString();
                phoneNumber = txtphone.getText().toString();
                email = txtemail.getText().toString();
                remarks = txtremarks.getText().toString();
                formtype = "2";

                Log.e("transactionType",transactionType);
                Log.e("Donnation type",donationType);
                Log.e("quantity",String.valueOf(quantity));
                Log.e("name",name);
                Log.e("phone Number",phoneNumber);
                Log.e("email",email);
                Log.e("remarks",remarks);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
