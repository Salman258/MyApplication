package com.example.salma.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Webview extends AppCompatActivity {

    private String response;
    private WebView web;
    private String PageURL;
    private Button btnToApp;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        web = (WebView)findViewById(R.id.simpleWebView);
        btnToApp =(Button)findViewById(R.id.btn2App);
        pb =(ProgressBar)findViewById(R.id.pB);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnToApp.setTextColor(Color.WHITE);
        btnToApp.setBackgroundColor(Color.BLUE);

        pb.setVisibility(View.VISIBLE);

        btnToApp.setVisibility(View.GONE);

        btnToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        response = getIntent().getStringExtra("Response");

        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadDataWithBaseURL(null, response, "text/html", "utf-8", null);

    }

    public class WebViewClient extends android.webkit.WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            PageURL = view.getUrl();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            PageURL = view.getUrl();
            pb.setVisibility(View.GONE);

            if(PageURL.contains("Finalization")){
                btnToApp.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}

