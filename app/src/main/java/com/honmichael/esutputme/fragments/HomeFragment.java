package com.honmichael.esutputme.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honmichael.esutputme.R;
import com.honmichael.esutputme.adapters.MainRecyclerViewAdapter;
import com.honmichael.esutputme.model.HomeItemModel;
import com.honmichael.esutputme.utils.ItemPlates;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "HomeFragment";

    //constants
    private static final int NUM_COLUMNS = 2;

    //widgets
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    //vars
    private ArrayList<HomeItemModel> mMatches = new ArrayList<>();
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private MainRecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "onCreateView: stated");
        mRecyclerView = view.findViewById(R.id.recycler_view);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        findMatches();
        return view;
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

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), mMatches);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onRefresh() {
         findMatches();
         onItemsLoadComplete();
    }

    private  void onItemsLoadComplete(){
        mRecyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
