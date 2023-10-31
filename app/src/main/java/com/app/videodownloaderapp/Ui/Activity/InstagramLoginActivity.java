package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.R;

public class InstagramLoginActivity extends AppCompatActivity {

    private Context context;
    private SwipeRefreshLayout RefreshLayout;
    private WebView WebInstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_login);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        WebInstView = (WebView) findViewById(R.id.WebInstView);
        RefreshLayout = (SwipeRefreshLayout) findViewById(R.id.RefreshLayout);
    }

    private void VideoInitListerns() {
    }

    private void VideoInitActions() {
        WebInstView.getSettings().setJavaScriptEnabled(true);
        WebInstView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        WebInstView.getSettings().setSupportMultipleWindows(true);
        Login();
        RefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public final void onRefresh() {
                Login();
            }
        });
        WebInstView.setWebViewClient(new WebViewClient() {
            public final void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (str.contains("https://www.instagram.com/accounts/onetap/") || str.equals("https://www.instagram.com/")) {
                    Toast.makeText(context, getResources().getString(R.string.toast_now_paste_link_again), Toast.LENGTH_SHORT).show();
                    String cookie = CookieManager.getInstance().getCookie(str);
                    SharedPreferences.Editor edit = getSharedPreferences("cookie", 0).edit();
                    edit.putString("instagram", cookie);
                    edit.apply();
                    WebInstView.destroy();
                    if (cookie != null) {
                        finish();
                    }
                }
            }
        });
    }

    public final void Login() {
        WebInstView.getSettings().setJavaScriptEnabled(true);
        WebInstView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        WebInstView.getSettings().setSupportMultipleWindows(true);
        WebInstView.clearCache(true);
        WebInstView.setWebChromeClient(new WebChromeClient());
        WebInstView.loadUrl("https://www.instagram.com/accounts/login/");
        WebInstView.setWebChromeClient(new WebChromeClient() {
            public final void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    RefreshLayout.setRefreshing(false);
                } else {
                    RefreshLayout.setRefreshing(true);
                }
            }
        });
    }

}