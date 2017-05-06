package com.example.handywebviewlibrary;

import android.annotation.TargetApi;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.view.View.VISIBLE;

/**
 * Created by zhangzhiqiang on 2017/5/5.
 */

public class CoreWebViewClient extends WebViewClient {

    private boolean isError = false;
    private ViewStub errorViewStub;

    private int errorLayout = R.layout.error_layout;

    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        Log.e("zzq", "onReceivedError");

        if (errorViewStub == null) {
            errorViewStub = new ViewStub(view.getContext());
//            errorViewStub.setInflatedId(errorLayout);
            errorViewStub.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            errorViewStub.setLayoutInflater(LayoutInflater.from(webView.getContext()));
            errorViewStub.setLayoutResource(errorLayout);
            errorViewStub.setClickable(true);
            view.addView(errorViewStub);

//            View errorView = mErrorViewStub.inflate();
//            errorView.findViewById(R.id.error_layout).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    webView.reload();
//                }
//            });
        } else {
            errorViewStub.setVisibility(VISIBLE);
        }

//        showErrorPage(view);
    }

    @TargetApi(android.os.Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
        // Redirect to deprecated method, so you can use it in all SDK versions
        onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());

    }

//    /**
//     * @param errorLayout 可以自定义一个用来展示的错误页面
//     */
//    protected void setErrorViewLayout(@LayoutRes int errorLayout) {
//        this.errorLayout = errorLayout;
//    }


    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */

    public void showErrorPage(WebView webView) {

        if (errorViewStub == null) {
            errorViewStub = new ViewStub(webView.getContext());
//            errorViewStub.setInflatedId(errorLayout);
            errorViewStub.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            errorViewStub.setLayoutInflater(LayoutInflater.from(webView.getContext()));
            errorViewStub.setLayoutResource(errorLayout);
            errorViewStub.setClickable(true);
            webView.addView(errorViewStub);

//            View errorView = mErrorViewStub.inflate();
//            errorView.findViewById(R.id.error_layout).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    webView.reload();
//                }
//            });
        } else {
            errorViewStub.setVisibility(VISIBLE);
        }
        isError = true;

//
//        if (errorViewStub == null) {
//            errorViewStub = new ViewStub(webView.getContext());
//            errorViewStub.setLayoutResource(errorLayout);
//            errorViewStub.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            errorViewStub.setClickable(true);
//            webView.addView(errorViewStub);
//        } else {
//            errorViewStub.setVisibility(VISIBLE);
//        }


    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

}
