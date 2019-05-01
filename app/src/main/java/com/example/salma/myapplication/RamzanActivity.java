package com.example.salma.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.StringRes;
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
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.w3c.dom.Text;

public class RamzanActivity extends AppCompatActivity {

    private Spinner ramzanDonationCountry;
    private Spinner ramzanDonationType;
    private NumberPicker np;
    private EditText ramzanDonorName;
    private EditText ramzanDonorEmail;
    private EditText ramzanDonorPhone;
    private EditText ramzanDonorRemarks;
    private TextView ramzanDonationMessage;
    private Button ramzanBtnDonate;
    private TextView ramzanDonationAmountPayable;
    private ImageView ramzanDonationImage;

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

    String[] ramzanDonationUs;
    String[] ramzanDonationUk ;
    String[] ramzanDonationUae;
    String[] ramzanDonationPk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramzan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ramzanDonationCountry =(Spinner)findViewById(R.id.spinner_ramzan_country);
        ramzanDonationType =(Spinner)findViewById(R.id.spinner_ramzan_donation);
        np=(NumberPicker)findViewById(R.id.ramzan_donation_quantity);
        ramzanDonorName =(EditText)findViewById(R.id.ramzan_donor_name);
        ramzanDonorEmail=(EditText)findViewById(R.id.ramzan_donor_email);
        ramzanDonorPhone =(EditText)findViewById(R.id.ramzan_donor_phone);
        ramzanDonorRemarks = (EditText)findViewById(R.id.ramzan_donor_remark);
        ramzanDonationMessage = (TextView)findViewById(R.id.ramzan_donation_message);
        ramzanBtnDonate = (Button)findViewById(R.id.ramzan_btn_donate);
        ramzanDonationAmountPayable = (TextView)findViewById(R.id.ramzan_donation_value);
        ramzanDonationImage =(ImageView)findViewById(R.id.image_ramzan_donation);

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
                    collapsingToolbarLayout.setTitle("Ramzan Donation");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        String[] arrayType = new String[] { "Pakistan" , "United States" , "United Kingdom" , "United Arab Emirates"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ramzanDonationCountry.setAdapter(adapter);

        ramzanDonationUs = new String[] {"Sehri $1" , "Iftar $2" , "Rashan $50" , "Kafara $50" , "Fidya $1" , "Fitra $1"};

        ramzanDonationUk = new String[] {"Sehri £0.5" , "Iftar £1" , "Rashan £45" , "Kafara £120" , "Fidya £1" , "Fitra £3"};

        ramzanDonationUae = new String[] {"Sehri 2.5 AED" , "Iftar 5 AED" , "Rashan 180 AED" , "Kafara 180 AED" , "Fidya 3 AED" , "Fitra 3 AED"};

        ramzanDonationPk = new String[] {"Sehri PKR 70" , "Iftar PKR 150" , "Rashan PKR 6000" , "Kafara PKR 6000" , "Fidya PKR 100" , "Fitra PKR 100"};

        ramzanDonationCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = ramzanDonationCountry.getSelectedItem().toString();

                if(type == "United States"){

                    ArrayAdapter<String> adapterUS = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ramzanDonationUs);
                    adapterUS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ramzanDonationType.setAdapter(adapterUS);
                    ramzanDonationImage.setImageResource(R.drawable.usa_ramzan);
                }
                else if(type == "United Kingdom"){
                    ArrayAdapter<String> adapterUK = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ramzanDonationUk);
                    adapterUK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ramzanDonationType.setAdapter(adapterUK);
                    ramzanDonationImage.setImageResource(R.drawable.uk_ramzan);
                }
                else if(type == "United Arab Emirates"){
                    ArrayAdapter<String> adapterUAE = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ramzanDonationUae);
                    adapterUAE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ramzanDonationType.setAdapter(adapterUAE);
                    ramzanDonationImage.setImageResource(R.drawable.uae_ramzan);
                }
                else if(type == "Pakistan"){
                    ArrayAdapter<String> adapterPK = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ramzanDonationPk);
                    adapterPK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ramzanDonationType.setAdapter(adapterPK);
                    ramzanDonationImage.setImageResource(R.drawable.pak_ramzan);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        np.setMaxValue(40);
        np.setValue(0);


        np.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                quantity =np.getValue();
                amount = onSelectedItem(ramzanDonationType.getSelectedItem().toString());
                amountMultiply = amount * quantity;
                percentPay = amountMultiply * 3 / 100;
                amountPayable = amountMultiply + percentPay;
                ramzanDonationAmountPayable.setText(String.valueOf(amountPayable));
                ramzanDonationAmountPayable.setTextColor(Color.RED);


            }
        });

        ramzanDonationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quantity =np.getValue();
                amount = onSelectedItem(ramzanDonationType.getSelectedItem().toString());
                amountMultiply = amount * quantity;
                percentPay = amountMultiply * 3 / 100;
                amountPayable = amountMultiply + percentPay;
                ramzanDonationAmountPayable.setText(String.valueOf(amountPayable));
                ramzanDonationAmountPayable.setTextColor(Color.RED);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ramzanBtnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNetworkConnectionAvailable()){
                    donationType = ramzanDonationType.getSelectedItem().toString();
                    quantity = np.getValue();
                    name = ramzanDonorName.getText().toString();
                    email = ramzanDonorEmail.getText().toString();
                    phoneNumber = ramzanDonorPhone.getText().toString();
                    remarks = ramzanDonorRemarks.getText().toString();
                    formtype = "4";
                    amount = onSelectedItem(ramzanDonationType.getSelectedItem().toString());
                    amountMultiply = amount * quantity;
                    percentPay = amountMultiply * 3 / 100;
                    amountPayable =amountMultiply + percentPay;
                    ramzanDonationAmountPayable.setTextColor(Color.RED);
                    ramzanDonationAmountPayable.setText(String.valueOf(amountPayable));

                    if (!(name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty())) {
                        if (email.contains("@") && email.contains(".")) {

                            AndroidNetworking.post("http://saylaniwelfare.net/Saylani/Registration.php")
                                    .setTag("donations")
                                    .setPriority(Priority.HIGH)
                                    .addBodyParameter("form_type", "4")
                                    .addBodyParameter("donation_type", donationType)
                                    .addBodyParameter("OrderID", phoneNumber)
                                    .addBodyParameter("OrderName", String.valueOf(onSelectedItem(ramzanDonationType.getSelectedItem().toString())))
                                    .addBodyParameter("OrderDisplay", ramzanDonationType.getSelectedItem().toString())
                                    .addBodyParameter("quantity", String.valueOf(quantity))
                                    .addBodyParameter("Amount", String.valueOf(amountPayable))
                                    .addBodyParameter("Name", name)
                                    .addBodyParameter("OrderInfo", email)
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

                        }else{
                            ramzanDonationMessage.setTextColor(Color.RED);
                            ramzanDonationMessage.setText("Please enter a valid email");
                        }
                    }else{
                        ramzanDonationMessage.setTextColor(Color.RED);
                        ramzanDonationMessage.setText("Please fill in all the required details");
                    }

                }
                else{
                    checkNetworkConnection();
                }
            }
        });

    }

    public double onSelectedItem(String donation){

        double money = 0;

        switch(donation) {
            case "Sehri $1":
                money = 145;
                break;
            case "Iftar $2":
                money = 290;
                break;
            case "Rashan $50":
                money = 7287;
                break;
            case "Kafara $50":
                money = 7287;
                break;
            case "Fidya $1":
                money = 145;
                break;
            case "Fitra $1":
                money = 145;
                break;
            case "Sehri £0.5":
                money = 94;
                break;
            case "Iftar £1":
                money = 190;
                break;
            case "Rashan £45":
                money = 8608;
                break;
            case "Kafara £120":
                money = 22958;
                break;
            case "Fidya £1":
                money = 190;
                break;
            case "Fitra £3":
                money = 571;
                break;
            case "Sehri 2.5 AED":
                money = 98;
                break;
            case "Iftar 5 AED":
                money = 197;
                break;
            case "Rashan 180 AED":
                money = 7137;
                break;
            case "Kafara 180 AED":
                money = 7137;
                break;
            case "Fidya 3 AED":
                money = 118;
                break;
            case "Fitra 3 AED":
                money = 118;
                break;
            case "Sehri PKR 70":
                money = 70;
                break;
            case "Iftar PKR 150":
                money = 150;
                break;
            case "Rashan PKR 6000":
                money = 6000;
                break;
            case "Kafara PKR 6000":
                money = 6000;
                break;
            case "Fidya PKR 100":
                money = 100;
                break;
            case "Fitra PKR 100":
                money = 100;
                break;
        }

        return money;
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
