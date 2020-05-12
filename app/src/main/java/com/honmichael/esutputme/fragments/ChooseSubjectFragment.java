package com.honmichael.esutputme.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.honmichael.esutputme.ExamBoaredActivity;
import com.honmichael.esutputme.IMainActivity;
import com.honmichael.esutputme.R;
import com.honmichael.esutputme.adapters.MainRecyclerViewAdapter;
import com.honmichael.esutputme.adapters.SubjectsListAdapter;
import com.honmichael.esutputme.model.HomeItemModel;
import com.honmichael.esutputme.model.Subjects;
import com.honmichael.esutputme.utils.ItemPlates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class ChooseSubjectFragment extends Fragment implements SubjectsListAdapter.OnClickAction {

    Context activity;
    RecyclerView rv;
    SubjectsListAdapter adapter;
    ActionMode actionMode;
    Toolbar toolbar;

    private View view;

    private ArrayList<Subjects> mMatches = new ArrayList<>();
    ArrayList<Subjects> selectedForModification;
    private String openingTag;

    private IMainActivity mInterface;

    public ChooseSubjectFragment() {
        // Required empty public constructor
    }


    private void findMatches() {
        ItemPlates plates = new ItemPlates();
        if (mMatches != null) {
            mMatches.clear();
        }
        for (Subjects model : plates.subjects) {
            mMatches.add(model);
        }
        if (adapter == null) {
            init(view);
        }
    }


    private void initRecyclerView() {


    }




    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_add_selected:
                    alertDialog();
                    mode.finish();
                    //getActivity().onBackPressed();
                    return true;
                default:
                    toolbar.setVisibility(View.VISIBLE);
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_choose_subject, container, false);
        activity = view.getContext();

        mInterface = (IMainActivity) getActivity();

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        findMatches();
        return view;
    }

    private  void init(View v){
        LinearLayoutManager lm = new LinearLayoutManager(activity);
        adapter = new SubjectsListAdapter(activity, this);



        rv = v.findViewById(R.id.rcView);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);

        adapter.addAll(mMatches);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), lm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);


    }


    @Override
    public void onClickAction(int position) {

        if (actionMode == null) {
            actionMode = ((AppCompatActivity)getActivity()).startActionMode(actionModeCallback);
            actionMode.setTitle("Selected: " + position);
            toolbar.setVisibility(View.GONE);
        } else {
            if (position == 0) {
                toolbar.setVisibility(View.VISIBLE);
                actionMode.finish();

            } else {
                actionMode.setTitle("Selected: " + position);
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    private void alertDialog(){
        selectedForModification = adapter.getSelected();

         Intent openExamBoardActivity = new Intent(activity, ExamBoaredActivity.class);
        Bundle bundle = new Bundle();
        /*ArrayList<String> stringsarray = new ArrayList<>();

        String longsrring = "";
        if(selectedForModification!= null){
            for (int i = 0; i <selectedForModification.size() ; i++) {
                stringsarray.add(selectedForModification.get(i).getName());
                longsrring = longsrring + " /"+ selectedForModification.get(i).getName();
            }
            startActivity(openExamBoardActivity);
        }
        bundle.putStringArrayList("Key",stringsarray);

        Toast.makeText(activity, longsrring, Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();*/


        mInterface.inflateExamBoardActivity(selectedForModification);
    }

}
