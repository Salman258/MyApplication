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

public class SadqaActivity extends AppCompatActivity {

    private String transactionType;
    private String donationType;
    private int quantity;
    private String name;
    private double amount;
    private String email;
    private String phoneNumber;
    private String remarks;
    private String formtype;

    private Spinner sadqaPay;
    private Spinner sadqaType;
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
        setContentView(R.layout.activity_sadqa);
        sadqaPay = (Spinner)findViewById(R.id.spinner_sadqa_pay);
        sadqaType = (Spinner)findViewById(R.id.spinner_sadqa_type);
        np = (NumberPicker)findViewById(R.id.sadqaQuantity);
        txtName = (EditText)findViewById(R.id.txtName);
        txtemail =(EditText)findViewById(R.id.txtEmail);
        txtphone = (EditText)findViewById(R.id.txtPhone);
        txtremarks =(EditText)findViewById(R.id.txtRemark);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnDonate = (Button)findViewById(R.id.btnDonate);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] arraySadqaPay = new String[] {"Bank", "Cash"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySadqaPay);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sadqaPay.setAdapter(adapter);


        String[] arraySadqaType = new String[] {"Goat(5000)", "Goat(5500)","Goat(6000)","Goat(7000)","Goat(8000)","Goat(9000)","Goat(10,000)","Goat(12,000)","Goat(15,000)",
                                                "Cow(30,000)","Cow(35,000)","Cow(40,000)","Cow(45,000)","Cow(50,000)",
                                                "Camel(50,000)","Camel(55,000)","Camel(60,000)","Hen(300)"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySadqaType);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sadqaType.setAdapter(adapter2);

        np.setMinValue(1);
        np.setMaxValue(1000);


        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionType = sadqaPay.getSelectedItem().toString();
                donationType = sadqaType.getSelectedItem().toString();
                quantity = np.getValue();
                name =txtName.getText().toString();
                phoneNumber = txtphone.getText().toString();
                email = txtemail.getText().toString();
                remarks = txtremarks.getText().toString();
                formtype = "3";

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
