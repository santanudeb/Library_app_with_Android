package com.code.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.website_layout);

        // up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // making WebsiteActivity reusable by taking url from where it has been called
        Intent intent = getIntent();
        if (null != intent){
            String url = intent.getStringExtra("url");
            webView = (WebView)findViewById(R.id.webView);
            webView.loadUrl(url);

            // to open the website within the app not by other browsers
            webView.setWebViewClient(new WebViewClient());

            // enable JS if needed
            webView.getSettings().setJavaScriptEnabled(true);
        }
    }

    // up back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}