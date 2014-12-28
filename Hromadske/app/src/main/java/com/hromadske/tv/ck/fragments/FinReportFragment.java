package com.hromadske.tv.ck.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import static com.hromadske.tv.ck.utils.SystemUtils.EXTRA_ENTITY;
import static com.hromadske.tv.ck.utils.SystemUtils.IMAGELOADER;
import static com.hromadske.tv.ck.utils.SystemUtils.watchYoutubeVideo;

/**
 * Created by cheb on 28.12.2014.
 */
public class FinReportFragment extends Fragment {
    private static final String TAG = FinReportFragment.class.getSimpleName();
    private WebView webView;
    private ProgressBar progressBar;

    public FinReportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fin_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        webView = (WebView)view.findViewById(R.id.webview);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress < 99){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl("http://hromadske.cherkasy.ua/finansovi-zvity");
    }
}