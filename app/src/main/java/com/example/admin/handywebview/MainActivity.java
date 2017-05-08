package com.example.admin.handywebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.handywebviewlibrary.CoreWebViewClient;
import com.example.handywebviewlibrary.ProgressWebView;

public class MainActivity extends AppCompatActivity {
    private final String url = "https://www.baidu.com";
    private final String errorUrl = "https://www.sdd.com/search/";
//    private final String errorUrl = "https://code.google.com/p/android/issues/detail?id=968";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ProgressWebView coreWebView = (ProgressWebView) findViewById(R.id.core_web_view);
        coreWebView.setWebViewClient(new CoreWebViewClient());

        findViewById(R.id.load_normal_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coreWebView.loadUrl(url);
            }
        });

        findViewById(R.id.load_error_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coreWebView.loadUrl(errorUrl);
            }
        });

    }
}
