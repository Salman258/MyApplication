package com.example.salma.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class TrackingActivity extends AppCompatActivity {

    EditText trackingID;
    Button   trackingButton;
    TextView trackingStatus;

    String trackID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        trackingID =(EditText)findViewById(R.id.trackingID);
        trackingButton =(Button) findViewById(R.id.trackButton);
        trackingStatus =(TextView)findViewById(R.id.trackingStatus);

        trackingStatus.setText("");

        trackingButton.setTextColor(Color.WHITE);
        trackingButton.setBackgroundColor(Color.BLUE);



        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        trackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Testing", "Testing");

                trackID = trackingID.getText().toString();
                Log.e("Data", trackID);

                AndroidNetworking.get("http://saylaniwelfare.net/addtransaction.php")
                        .setTag("Tracking")
                        .addQueryParameter("transactionId", trackID)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsString(new StringRequestListener() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("Response", response );
                                trackingStatus.setText(response);
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
