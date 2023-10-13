package com.example.harveyticker;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;

import java.util.LinkedList;


public class CustomViewModel extends ViewModel {
    private MutableLiveData<LinkedList<String>> tickers;
    private MutableLiveData<String> current;


    public MutableLiveData<LinkedList<String>> getTickers() {
        if (tickers == null) {
            tickers = new MutableLiveData<LinkedList<String>>();
            loadTickers();
        }
        return tickers;
    }


    private void loadTickers() {
        LinkedList<String> tickerslist = tickers.getValue();

        tickerslist.add("BAC");
        tickerslist.add("AAPL");
        tickerslist.add("DIS");

        tickers.setValue(tickerslist);

    }

    public void addTicker(String newTick){
        LinkedList<String> tickerlist2 = tickers.getValue();
        tickerlist2.add(newTick);
        tickers.setValue(tickerlist2);

    }


    public MutableLiveData<String> getCurrent(){
        if(current == null){
            current = new MutableLiveData<>();
            current.setValue("BAC");
        }
        return current;
    }

    public void setCurrent(String newCurrent){
        current.setValue(newCurrent);

    }


}