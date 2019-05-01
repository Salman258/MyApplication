package com.example.salma.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SadqaActivity extends AppCompatActivity {


    private String donationType;
    private int quantity;
    private String name;
    private double amountPayable;
    private double amount;
    private double percentPay;
    private double amountMultiply;
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
    private TextView message;
    private Button btnDonate;
    private TextView amountValue;
    private ProgressBar pB;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadqa);
        sadqaPay    = (Spinner)findViewById(R.id.spinner_sadqa_pay);
        sadqaType   = (Spinner)findViewById(R.id.spinner_sadqa_type);
        np          = (NumberPicker)findViewById(R.id.sadqaQuantity);
        txtName     = (EditText)findViewById(R.id.txtName);
        txtemail    = (EditText)findViewById(R.id.txtEmail);
        txtphone    = (EditText)findViewById(R.id.txtPhone);
        txtremarks  = (EditText)findViewById(R.id.txtRemark);
        toolbar     = (Toolbar) findViewById(R.id.toolbar);
        btnDonate   = (Button)findViewById(R.id.btnDonate);
        amountValue = (TextView)findViewById(R.id.amountVal);
        message     = (TextView)findViewById(R.id.txtMessage);
        AndroidNetworking.initialize(getApplicationContext());

        btnDonate.setTextColor(Color.WHITE);
        btnDonate.setBackgroundColor(Color.BLUE);

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
                    collapsingToolbarLayout.setTitle("Sadqah");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        String[] arraySadqaPay = new String[] {"Bank", "Cash"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySadqaPay);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sadqaPay.setAdapter(adapter);

        if(isNetworkConnectionAvailable()){

        }
        else{

        }


        String[] arraySadqaType = new String[] {"Goat 5000", "Goat 5500","Goat 6000","Goat 7000","Goat 8000","Goat 9000","Goat 10,000","Goat 12,000","Goat 15,000",
                                                "Cow 30,000","Cow 35,000","Cow 40,000","Cow 45,000","Cow 50,000",
                                                "Camel 50,000","Camel 55,000","Camel 60,000","Hen 300"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySadqaType);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        sadqaType.setAdapter(adapter2);


        np.setMaxValue(40);
        np.setValue(0);


        np.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                quantity =np.getValue();
                amount = onSelectedItem(sadqaType.getSelectedItem().toString());
                amountMultiply = amount * quantity;
                percentPay = amountMultiply * 3 / 100;
                amountPayable = amountMultiply + percentPay;
                amountValue.setText(String.valueOf(amountPayable));
                amountValue.setTextColor(Color.RED);


            }
        });

        sadqaType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quantity =np.getValue();
                amount = onSelectedItem(sadqaType.getSelectedItem().toString());
                amountMultiply = amount * quantity;
                percentPay = amountMultiply * 3 / 100;
                amountPayable = amountMultiply + percentPay;
                amountValue.setText(String.valueOf(amountPayable));
                amountValue.setTextColor(Color.RED);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNetworkConnectionAvailable()) {

                    donationType = sadqaType.getSelectedItem().toString();
                    name = txtName.getText().toString();
                    phoneNumber = txtphone.getText().toString();
                    email = txtemail.getText().toString();
                    remarks = txtremarks.getText().toString();
                    formtype = "3";
                    quantity = np.getValue();
                    amount = onSelectedItem(sadqaType.getSelectedItem().toString());
                    amountMultiply = amount * quantity;
                    percentPay = amountMultiply * 3 / 100;
                    amountPayable = amountMultiply + percentPay;
                    amountValue.setText(String.valueOf(amountPayable));
                    amountValue.setTextColor(Color.RED);

                    if (!(name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty())) {
                        if (email.contains("@") && email.contains(".")) {

                            AndroidNetworking.post("http://saylaniwelfare.net/Saylani/Registration.php")
                                    .setTag("donations")
                                    .setPriority(Priority.HIGH)
                                    .addBodyParameter("form_type", "4")
                                    .addBodyParameter("donation_type", donationType)
                                    .addBodyParameter("OrderID", phoneNumber)
                                    .addBodyParameter("OrderName", String.valueOf(onSelectedItem(sadqaType.getSelectedItem().toString())))
                                    .addBodyParameter("OrderDisplay", sadqaType.getSelectedItem().toString())
                                    .addBodyParameter("quantity", String.valueOf(quantity))
                                    .addBodyParameter("Amount", String.valueOf(amountPayable))
                                    .addBodyParameter("Name", name)
                                    .addBodyParameter("OrderInfo", txtemail.getText().toString())
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

                            message.setText("");
                            Log.e("Donnation type", donationType);
                            Log.e("quantity", String.valueOf(quantity));
                            Log.e("name", name);
                            Log.e("phone Number", phoneNumber);
                            Log.e("email", email);
                            Log.e("remarks", remarks);
                            Log.e("Amount", String.valueOf(amountPayable));
                        } else {
                            message.setTextColor(Color.RED);
                            message.setText("Please Enter a valid email");
                        }
                    } else {
                        message.setTextColor(Color.RED);
                        message.setText(R.string.message_string);
                    }

                }else{
                    checkNetworkConnection();
                }

            }
        });
    }

    public int onSelectedItem(String transaction){
       int  amount = 0;

        switch (transaction){
            case "Goat 5000":
                amount = 5000;
                break;
            case "Goat 5500":
                amount =5500;
                break;
            case "Goat 6000":
                amount = 6000;
                break;
            case "Goat 7000":
                amount =7000;
                break;
            case "Goat 8000":
                amount =8000;
                break;
            case "Goat 9000":
                amount = 9000;
                break;
            case "Goat 10,000":
                amount = 10000;
                break;
            case "Goat 12,000":
                amount = 12000;
                break;
            case "Goat 15,000":
                amount = 15000;
                break;
            case "Cow 30,000":
                amount = 30000;
                break;
            case "Cow 35,000":
                amount = 35000;
                break;
            case "Cow 40,000":
                amount = 40000;
                break;
            case "Cow 45,000":
                amount = 45000;
                break;
            case "Cow 50,000":
                amount = 50000;
                break;
            case "Camel 50,000":
                amount = 50000;
                break;
            case "Camel 55,000":
                amount = 55000;
                break;
            case "Camel 60,000":
                amount = 60000;
                break;
            case "Hen 300":
                amount = 300;
                break;
        }

        return amount;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
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
}
