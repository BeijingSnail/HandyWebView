package com.example.handywebviewlibrary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by zhanghziqiagn on 2017/5/4.
 */

public class CoreWebView extends WebView {

    private static final String TAG = CoreWebView.class.getSimpleName();

    public CoreWebView(Context context) {
        super(context);
        init();
    }

    public CoreWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoreWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CoreWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        try {
            WebSettings settings = getSettings();
            //添加JavaScript支持
            settings.setJavaScriptEnabled(true);
            //临时、简单数据的缓存，cookie的拓展
            settings.setDomStorageEnabled(true);
            //设置自适应屏幕，两者合用
            settings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
            settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

            settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
            settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            settings.setDefaultTextEncodingName("utf-8");//设置编码格式

            setWebViewClient(new CoreWebViewClient());
//            setWebChromeClient(new CoreChromeClient());

            //解决在5.0以上不显示htts图片的问题
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }

            //解决3.0到4.2期间webview的挂马风险，4.2以上Google已修复
            if (Build.VERSION_CODES.JELLY_BEAN_MR1 > Build.VERSION.SDK_INT && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                removeJavascriptInterface("searchBoxJavaBridge_");
            }

            // 5.0以上同步cookie的操作已经可以自动同步、但前提是我们必须开启第三方cookie的支持。
            CookieManager.getInstance().setAcceptCookie(true);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            }
        } catch (Exception e) {
            throw new RuntimeException(TAG + "init error");
        }
    }

}
