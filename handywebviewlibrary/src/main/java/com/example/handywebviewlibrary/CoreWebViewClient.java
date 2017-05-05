package com.example.handywebviewlibrary;

import android.annotation.TargetApi;
import android.support.annotation.LayoutRes;
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

    private int errorLayout;

    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        showErrorPage(view);
    }

    @TargetApi(android.os.Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
        // Redirect to deprecated method, so you can use it in all SDK versions
        onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());

    }

    /**
     * @param errorLayout 可以自定义一个用来展示的错误页面
     */
    protected void setErrorViewLayout(@LayoutRes int errorLayout) {
        this.errorLayout = errorLayout;
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
        if (errorLayout == 0) {
            mErrorView = View.inflate(webView.getContext(), R.layout.error_layout, null);
        } else {
            mErrorView = View.inflate(webView.getContext(), errorLayout, null);
        }

        try {
            View reloadView = mErrorView.findViewById(R.id.reload);
            reloadView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    webView.reload();
                }
            });
            mErrorView.setOnClickListener(null);

        } catch (Exception e) {
            throw new RuntimeException("click view id is not 'reload'");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        hideErrorPage(view);
    }
}
