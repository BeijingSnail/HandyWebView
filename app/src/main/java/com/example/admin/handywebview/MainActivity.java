package com.example.admin.handywebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.handywebviewlibrary.CoreWebView;

public class MainActivity extends AppCompatActivity {
    private final String url = "https://huayiquan.com.cn/index.php?s=/home/article/content2/user_mobile/15142555427/aid/858/mobile/17774934497.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoreWebView coreWebView = (CoreWebView) findViewById(R.id.core_web_view);

        coreWebView.loadUrl(url);

    }
}
