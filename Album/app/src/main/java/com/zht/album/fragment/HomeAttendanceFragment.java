package com.zht.album.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zht.album.R;


/**
 * Created by masterzht on 2017/12/5.
 */

public class HomeAttendanceFragment extends Fragment {

    private View viewCourse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewCourse = inflater.inflate(R.layout.fragment_meitu,container, false);
        return viewCourse;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView;



        /*WebView webview = getActivity().findViewById(R.id.webview);
        //webview.loadUrl("http://m.meizitu.com/a/more_2.html");
        WebSettings webSettings = webview.getSettings();

        webSettings.setDomStorageEnabled(true);
        // 设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl("https://m.baidu.com");*/

        webView =getActivity().findViewById(R.id.webview);
        // 开启 localStorage
        webView.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        webView.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //使用自定义的WebViewClient
        webView.setWebViewClient(new WebViewClient()
        {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("https://m.baidu.com");

    }



}
