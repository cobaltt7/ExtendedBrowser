package com.paulsreid.coding.extendedbrowser;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputURL = findViewById(R.id.inputURL);
        Button searchButton = findViewById(R.id.go);
        WebView webView = findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();
        settings.setAllowContentAccess(true); // allow retrieving content
        settings.setAllowFileAccess(true); // allow file:/// urls
        settings.setBlockNetworkImage(false); // disallow loading images over the network
        settings.setBlockNetworkLoads(false); // disallow loading pages over the network
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true); // enable zooming
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setDefaultTextEncodingName("utf-8");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        load("https://google.com");

        searchButton.setOnClickListener(v -> load(inputURL.getText().toString()));
    }

    public void load(String url) {
        WebView webView = findViewById(R.id.webView);
       if(url.contains(":")) {
           webView.loadUrl(url);
       } else {
           webView.loadUrl("https://google.com/search/?q="+ url);
       }
    }
}