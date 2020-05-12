package com.honmichael.esutputme.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.honmichael.esutputme.R;
import com.honmichael.esutputme.adapters.DepartmentsListAdapter;
import com.honmichael.esutputme.utils.ItemPlates;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentsFragment extends Fragment {


    private static final String TAG = "DepartmentsFragment";




    //widgets
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<String> mMatches = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private DepartmentsListAdapter mRecyclerViewAdapter;


    public DepartmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = getLayoutInflater().inflate(R.layout.fragment_department_list,container,false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        findMatches();
        return view;
    }

    private void findMatches() {
        ItemPlates plates = new ItemPlates();
        if (mMatches != null) {
            mMatches.clear();
        }
        for (String department : plates.departments) {
            mMatches.add(department);
        }
        if (mRecyclerViewAdapter == null) {
            initRecyclerView();
        }

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewAdapter = new DepartmentsListAdapter(getActivity(), mMatches);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

}
