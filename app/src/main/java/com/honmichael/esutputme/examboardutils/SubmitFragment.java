package com.honmichael.esutputme.examboardutils;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.honmichael.esutputme.IExamBoardActivty;
import com.honmichael.esutputme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitFragment extends Fragment {

    private MaterialButton submitButton;
    private IExamBoardActivty mInterface1;


    public SubmitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getLayoutInflater().inflate(R.layout.fragment_submit, container, false);
        mInterface1 = (IExamBoardActivty) getContext();

        submitButton = view.findViewById(R.id.fab_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSubmit(view);
            }
        });
        return view;
    }

    public void clickSubmit(final View view) {
        AlertDialog.Builder alertConfirm = new AlertDialog.Builder(getContext());
        alertConfirm.setTitle("Confirm Submission");
        alertConfirm.setMessage("Do you want to submit?");
        alertConfirm.setCancelable(true);
        alertConfirm.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertConfirm.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //submit(view);
                mInterface1.onSubmitButtonClicked();
            }
        });
        AlertDialog dialog = alertConfirm.create();
        dialog.show();
    }

}
