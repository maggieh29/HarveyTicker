package com.example.harveyticker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;

import java.util.LinkedList;


public class WebFragment extends Fragment {

    WebView webView;
    CustomViewModel sharedModel;
    String current;

    //observer
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(String current) {
           current = sharedModel.getCurrent().getValue().toString();
           if (current == "Home"){
               webView.loadUrl("https://seekingalpha.com/");
           }else {
               changeWeb(current);
           }
        }
    };

    public WebFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedModel = new ViewModelProvider(getActivity()).get(CustomViewModel.class);
        //observer
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v =  inflater.inflate(R.layout.fragment_web, container, false);

      webView = v.findViewById(R.id.webV);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        webView.loadUrl("https://seekingalpha.com/");

      return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedModel.getCurrent().observe(getViewLifecycleOwner(), observer);
    }


    public void changeWeb(String current){
        webView.loadUrl("https://seekingalpha.com/symbol/" + current);
    }
}