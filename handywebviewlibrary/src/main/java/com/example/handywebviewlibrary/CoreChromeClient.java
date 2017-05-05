package com.example.handywebviewlibrary;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by zhangzhiqiang on 2017/5/5.
 */

public class CoreChromeClient extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);

    }

}
