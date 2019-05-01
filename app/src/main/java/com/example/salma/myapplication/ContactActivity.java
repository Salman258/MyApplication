package com.example.salma.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.media.Image;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class ContactActivity extends AppCompatActivity {

    private String Name;
    private String Email;
    private String phone;
    private String message;

    private EditText name;
    private EditText email;
    private EditText phoneNumber;
    private EditText msg;
    private Button btnSubmit;
    private TextView phoneSaylani;
    private TextView emailSaylani;
    private TextView addressSaylani;
    private TextView ErrorSaylani;
    private ImageView fb;
    private ImageView twitter;
    private ImageView yt;
    private ImageView insta;

    public static String FACEBOOK_URL = "https://www.facebook.com/saylaniwelfare";
    public static String FACEBOOK_PAGE_ID = "Saylani Welfare Trust";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        name = (EditText)findViewById(R.id.conName);
        email = (EditText)findViewById(R.id.conEmail);
        phoneNumber = (EditText)findViewById(R.id.conPhone);
        msg = (EditText) findViewById(R.id.conRemark);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        phoneSaylani =(TextView)findViewById(R.id.PhoneVal);
        emailSaylani = (TextView)findViewById(R.id.emailVal);
        addressSaylani = (TextView)findViewById(R.id.addressVal);
        ErrorSaylani = (TextView)findViewById(R.id.txtError);
        fb = (ImageView) findViewById(R.id.img_fb);
        twitter =(ImageView)findViewById(R.id.img_twitter);
        yt =(ImageView)findViewById(R.id.img_yt);
        insta =(ImageView)findViewById(R.id.img_insta);
        AndroidNetworking.initialize(getApplicationContext());

        btnSubmit.setTextColor(Color.WHITE);
        btnSubmit.setBackgroundColor(Color.BLUE);

        fb.setClickable(true);
        twitter.setClickable(true);
        yt.setClickable(true);
        insta.setClickable(true);

        phoneSaylani.setText("111-729-526");
        emailSaylani.setText("info@saylaniwelfare.com");
        addressSaylani.setText("A-25, Bahadurabad Chowrangi");

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
                    collapsingToolbarLayout.setTitle("Contact Us");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNetworkConnectionAvailable()) {
                    Name = name.getText().toString();
                    Email = email.getText().toString();
                    phone = phoneNumber.getText().toString();
                    message = msg.getText().toString();

                    Log.e("Name", Name);
                    Log.e("Email", Email);
                    Log.e("phone", phone);
                    Log.e("Message", message);

                    if (!(Name.isEmpty() || Email.isEmpty() || phone.isEmpty() || message.isEmpty())) {
                        if (Email.contains("@") && Email.contains(".")) {
                            AndroidNetworking.get("http://saylaniwelfare.net/add-contact-entry.php")
                                    .addQueryParameter("name", Name)
                                    .addQueryParameter("email", Email)
                                    .addQueryParameter("phone", phone)
                                    .addQueryParameter("msg", message)
                                    .setTag("Contact")
                                    .setPriority(Priority.HIGH)
                                    .build()
                                    .getAsString(new StringRequestListener() {
                                        @Override
                                        public void onResponse(String response) {
                                            name.getText().clear();
                                            email.getText().clear();
                                            phoneNumber.getText().clear();
                                            msg.getText().clear();
                                            if (response.contains("sucess")) {
                                                Log.d("Response",response);
                                                AlertDialog alertDialog = new AlertDialog.Builder(ContactActivity.this).create();
                                                alertDialog.setTitle("Thank You");
                                                alertDialog.setMessage("We have received your information and we will contact you soon ");
                                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                alertDialog.show();
                                                ErrorSaylani.setText("");
                                            }

                                        }

                                        @Override
                                        public void onError(ANError anError) {

                                        }
                                    });
                        } else {
                            ErrorSaylani.setTextColor(Color.RED);
                            ErrorSaylani.setText("Please enter a valid Email");
                        }
                    } else {
                        ErrorSaylani.setTextColor(Color.RED);
                        ErrorSaylani.setText("Please fill in all the fields");
                    }
                }else{
                    checkNetworkConnection();
                }
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Facebook",Toast.LENGTH_LONG).show();
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getApplicationContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"youtube",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/c/SaylaniWelfareTrustOfficialPage"));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Twitter",Toast.LENGTH_LONG).show();
                Intent intent = null;
                try{
                    getApplicationContext().getPackageManager().getPackageInfo("com.twitter.android",0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=716255382042230785"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // If no Twitter app found, open on browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/OfficialSwit"));
                }
                startActivity(intent);

            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Instagram",Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse("http://instagram.com/_u/saylaniwelfare");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/saylaniwelfare")));
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
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

