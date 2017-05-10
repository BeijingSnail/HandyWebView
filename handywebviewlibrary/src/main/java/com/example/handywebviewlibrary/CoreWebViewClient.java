package com.example.handywebviewlibrary;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by zhangzhiqiang on 2017/5/5.
 */

public class CoreWebViewClient extends WebViewClient {

    /**
     * 本加载结束后是否是错误页面
     */
    private boolean isError;

    /**
     * 上一次加载结束后是否是错误页面
     */
    private boolean lastStatus;

    /**
     * 当前状态是否处于页面加载中
     */
    private boolean isLoading;

    private ViewStub errorViewStub;

    private int errorLayout = R.layout.error_layout;

    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        showErrorPage(view);
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

    public void showErrorPage(final WebView webView) {
        if (errorViewStub == null) {
            errorViewStub = new ViewStub(webView.getContext());
            errorViewStub.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            errorViewStub.setLayoutResource(errorLayout);
            errorViewStub.setClickable(true);
            webView.addView(errorViewStub, 0);
            View view = errorViewStub.inflate();
            view.findViewById(R.id.reload).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isLoading) {
                        webView.reload();
                    }
                }
            });
        } else {
            errorViewStub.setVisibility(VISIBLE);
        }
        isError = true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (lastStatus && errorViewStub != null) {
            errorViewStub.setVisibility(VISIBLE);
        }
        isError = false;
        isLoading = true;
        
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (!isError && errorViewStub != null) {
            errorViewStub.setVisibility(GONE);
        }
        lastStatus = isError;
        isLoading = false;
    }


}
