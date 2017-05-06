package com.example.admin.handywebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.handywebviewlibrary.CoreWebViewClient;
import com.example.handywebviewlibrary.ProgressWebView;

public class MainActivity extends AppCompatActivity {
    private final String url = "https://huayiquan.com.cn/index.php?s=/home/article/content2/user_mobile/15142555427/aid/858/mobile/17774934497.html";
    private final String errorUrl = "https://code.google.com/p/android/issues/detail?id=968";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ProgressWebView coreWebView = (ProgressWebView) findViewById(R.id.core_web_view);
        coreWebView.setWebViewClient(new CoreWebViewClient());

        coreWebView.loadUrl(errorUrl);

    }
}
