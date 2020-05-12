package com.honmichael.esutputme.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honmichael.esutputme.R;
import com.honmichael.esutputme.adapters.MainRecyclerViewAdapter;
import com.honmichael.esutputme.model.HomeItemModel;
import com.honmichael.esutputme.utils.ItemPlates;

import java.util.ArrayList;


public class MiddleFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    //constants
    private static final int NUM_COLUMNS = 2;

    //widgets
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<HomeItemModel> mMatches = new ArrayList<>();
    //private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;
    private MainRecyclerViewAdapter mRecyclerViewAdapter;


    public MiddleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_middle,container,false);

        mRecyclerView = view.findViewById(R.id.recycler_view);

        findMatches();
        return  view;
    }

    private void findMatches() {
        ItemPlates plates = new ItemPlates();
        if (mMatches != null) {
            mMatches.clear();
        }
        for (HomeItemModel model : plates.Models) {
            mMatches.add(model);
        }
        if (mRecyclerViewAdapter == null) {
            initRecyclerView();
        }
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), mMatches);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }


}
