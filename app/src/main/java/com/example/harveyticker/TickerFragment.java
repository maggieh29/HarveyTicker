package com.example.harveyticker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TickerFragment extends Fragment {


    private CustomViewModel sharedModel;
    ListView listView;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        sharedModel = new CustomViewModel(getActivity()).get(ViewModel.class);
    }

    Observer<LinkedList<String>> observer = new Observer<LinkedList<String>>() {
        @Override
        public void onChanged(LinkedList<String> pets) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, sharedModel.getTickers().getValue());
            listView.setAdapter(adapter);
        }
    };


    public TickerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_ticker, container, false);

        listView = v.findViewById(R.id.listV);

        return v;
    }
}