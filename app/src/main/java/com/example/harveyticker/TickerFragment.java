package com.example.harveyticker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.LinkedList;

public class TickerFragment extends Fragment {


    private CustomViewModel sharedModel;
    ListView listView;


    Observer<LinkedList<String>> observer = new Observer<LinkedList<String>>() {
        @Override
        public void onChanged(LinkedList<String> pets) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, sharedModel.getTickers().getValue());
            listView.setAdapter(adapter);
        }
    };


   AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           String selected = listView.getItemAtPosition(i).toString();
           //Toast.makeText(getActivity(), selected, Toast.LENGTH_LONG).show();
           sharedModel.setCurrent(selected);


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
        sharedModel = new ViewModelProvider(getActivity()).get(CustomViewModel.class);

        listView = v.findViewById(R.id.listV);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, sharedModel.getTickers().getValue());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(listener);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //sharedModel =  new ViewModelProvider(getActivity()).get(CustomViewModel.class);
        sharedModel.getTickers().observe(getViewLifecycleOwner(), observer);
    }


}