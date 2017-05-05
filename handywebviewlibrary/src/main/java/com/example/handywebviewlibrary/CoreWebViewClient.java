package com.example.handywebviewlibrary;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhangzhiqiang on 2017/5/5.
 */

public class CoreWebViewClient extends WebViewClient {

    private boolean isError = false;
    private View mErrorView;

    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);


    }

    @TargetApi(android.os.Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
        // Redirect to deprecated method, so you can use it in all SDK versions
        onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d("zzq", "onPageStarted");
    }


    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    protected void showErrorPage(WebView webView) {

        ViewGroup webParentView = (ViewGroup) webView.getParent();

        initErrorPage(webView);

        while (webParentView.getChildCount() > 1) {
            webParentView.removeViewAt(0);
        }

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webParentView.addView(mErrorView, 0, lp);
        isError = true;
    }

    protected void hideErrorPage(WebView webView) {
        ViewGroup webParentView = (ViewGroup) webView.getParent();

        isError = false;
        while (webParentView.getChildCount() > 1) {
            webParentView.removeViewAt(0);
        }
    }


    protected void initErrorPage(final WebView webView) {
//        if (mErrorView == null) {
//            mErrorView = View.inflate(this, R.layout.online_error, null);
//            Button button = (Button) mErrorView.findViewById(R.id.online_error_btn_retry);
//            button.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    webView.reload();
//                }
//            });
//            mErrorView.setOnClickListener(null);
//        }
    }

}
