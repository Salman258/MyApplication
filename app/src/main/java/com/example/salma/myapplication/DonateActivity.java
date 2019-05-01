package com.example.salma.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.w3c.dom.Text;

public class DonateActivity extends AppCompatActivity {

    private String transactionType;
    private String donationType;
    private int quantity;
    private String name;
    private double amountPayable;
    private double amount;
    private double percentPay;
    private String email;
    private String phoneNumber;
    private String remarks;
    private String stringAmount;

    private Spinner donationPay;
    private Spinner donateType;
    private EditText donorName;
    private EditText donoremail;
    private EditText donorphone;
    private EditText donorremarks;
    private TextView Errormessage;
    private Button btnDonation;
    private TextView donationAmountValue;
    private EditText textAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        donationPay = (Spinner)findViewById(R.id.spinner_donation_payment);
        donateType =(Spinner)findViewById(R.id.spinner_donation_type);
        donorName =(EditText)findViewById(R.id.txtNameDonor);
        donoremail =(EditText)findViewById(R.id.txtEmailDonor);
        donorphone =(EditText)findViewById(R.id.txtPhoneDonor);
        donorremarks =(EditText)findViewById(R.id.txtRemarkDonor);
        Errormessage =(TextView)findViewById(R.id.txtMessageDonation);
        btnDonation =(Button)findViewById(R.id.btnDonate3);
        donationAmountValue =(TextView)findViewById(R.id.donationAmountVal);
        textAmount =(EditText)findViewById(R.id.txtAmountDonation);
        AndroidNetworking.initialize(getApplicationContext());

        btnDonation.setTextColor(Color.WHITE);
        btnDonation.setBackgroundColor(Color.BLUE);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Donations");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        String[] donationPaymentArray = new String[] {"Bank", "Cash"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, donationPaymentArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donationPay.setAdapter(adapter);


        String[] donationTypeArray = new String[] {"Donation", "Marriage","Zakaat","Food","Education","Water Well","Monthly Rashan","Thar Fund","Flood Victim",
                "Fitra (oversees)","Saaf Pani","Heat Stroke","Syrian Crisis","Masjid Construction", "Medical Project"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, donationTypeArray);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donateType.setAdapter(adapter2);

        textAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Errormessage.setText("");
                stringAmount = textAmount.getText().toString();
                amountPayable = ParseDouble(stringAmount);
                donationAmountValue.setText(String.valueOf(amountPayable));
                donationAmountValue.setTextColor(Color.RED);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btnDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNetworkConnectionAvailable()) {

                    transactionType = donationPay.getSelectedItem().toString();
                    donationType = donateType.getSelectedItem().toString();
                    name = donorName.getText().toString();
                    phoneNumber = donorphone.getText().toString();
                    email = donoremail.getText().toString();
                    remarks = donorremarks.getText().toString();
                    quantity = 0;
                    stringAmount = textAmount.getText().toString();
                    amountPayable = ParseDouble(stringAmount);
                    donationAmountValue.setText(String.valueOf(amountPayable));
                    donationAmountValue.setTextColor(Color.RED);


                    if (!(stringAmount.isEmpty() || name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty())) {
                        if (email.contains("@") && email.contains(".")) {

                            Log.e("transactionType", transactionType);
                            Log.e("Donnation type", donationType);
                            Log.e("quantity", String.valueOf(quantity));
                            Log.e("name", name);
                            Log.e("phone Number", phoneNumber);
                            Log.e("email", email);
                            Log.e("remarks", remarks);
                            Log.e("Amount", String.valueOf(amountPayable));

                            AndroidNetworking.post("http://saylaniwelfare.net/Saylani/Registration.php")
                                    .setTag("donations")
                                    .setPriority(Priority.HIGH)
                                    .addBodyParameter("form_type", "4")
                                    .addBodyParameter("OrderID", phoneNumber)
                                    .addBodyParameter("donation_type", donationType)
                                    .addBodyParameter("OrderName", donationType)
                                    .addBodyParameter("OrderDisplay", donateType.getSelectedItem().toString())
                                    .addBodyParameter("quantity", String.valueOf(quantity))
                                    .addBodyParameter("Amount", String.valueOf(amountPayable))
                                    .addBodyParameter("Name", name)
                                    .addBodyParameter("OrderInfo", donoremail.getText().toString())
                                    .addBodyParameter("remarks", remarks)
                                    .build()
                                    .getAsString(new StringRequestListener() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.e("Respnse", response);

                                            Intent i = new Intent(getApplicationContext(), Webview.class);
                                            i.putExtra("Response", response);
                                            startActivity(i);
                                        }

                                        @Override
                                        public void onError(ANError anError) {

                                        }
                                    });
                        } else {
                            Errormessage.setTextColor(Color.RED);
                            Errormessage.setText("Please enter a valid email");
                        }
                    } else {
                        Errormessage.setTextColor(Color.RED);
                        Errormessage.setText("Please fill in all the details");
                    }
                }else{
                    checkNetworkConnection();
                }

            }
        });
    }

    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            return true;
        }
        else{
            Log.d("Network","Not Connected");
            return false;
        }
    }

    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {

                return -1;
            }
        }
        else return 0;
    }
}
