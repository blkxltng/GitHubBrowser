package com.blkxltng.githubbrowser.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.blkxltng.githubbrowser.Constants;
import com.blkxltng.githubbrowser.R;

public class WebViewFragment extends Fragment {

    private static final String TAG = "WebViewFragment";
    private String repoURL;

    private WebView webView;

    public static WebViewFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.ARG_REPO_URL, url);

        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(args);

        return webViewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repoURL = getArguments().getString(Constants.ARG_REPO_URL);
//        showBackButton();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        webView = view.findViewById(R.id.fragmentWebView_webView);
        webView.loadUrl(repoURL);
        return view;
    }

    public void showBackButton() {
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
