package com.honmichael.esutputme.examboardutils;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.honmichael.esutputme.ExamBoaredActivity;
import com.honmichael.esutputme.R;
import com.honmichael.esutputme.adapters.ExamNumberAdapter;
import com.honmichael.esutputme.database.QuestionEntry;
import com.honmichael.esutputme.utils.LocalQuestions;

import java.util.ArrayList;

public class FirstFragment extends Fragment implements ExamNumberAdapter.OnClickAction, RadioButton.OnClickListener, RadioGroup.OnCheckedChangeListener {


    private static final String TAG = "DepartmentsFragment";


    private View view;
    private TextView q_no;
    private TextView questions;
    private RadioButton opA;
    private RadioButton opB;
    private RadioButton opC;
    private RadioButton opD;
    private RadioGroup optionsGroup;
    private FloatingActionButton prevButton;
    private FloatingActionButton forwardButton;
    private MaterialButton submitButton;


    private QuestionEntry mQuestionEntry;
    private int mCurrentQuestionNum = 0;

    private ArrayList<QuestionEntry> mAnsweredQuestionEntries;


    //widgets
    private RecyclerView mRecyclerView;
    private ArrayList<QuestionEntry> mMatches = new ArrayList<>();

    //vars
    private LinearLayoutManager mLinearLayoutManager;
    private ExamNumberAdapter mRecyclerViewAdapter;
    private int score = 0;
    private int mExamSze;


    private Boolean isResultMood;
    private int img_correct = R.drawable.ic_correct;
    private int img_wrong = R.drawable.ic_wrong;


    public FirstFragment() {
        // Required empty public constructor
    }


    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = getLayoutInflater().inflate(R.layout.fragment_first, container, false);
        isResultMood = false;


        prevButton = view.findViewById(R.id.fab_prev);
        forwardButton = view.findViewById(R.id.fab_forward);
        submitButton = view.findViewById(R.id.fab_submit);
        prevButton.setVisibility(View.GONE);


        q_no = view.findViewById(R.id.q_numbers);
        questions = view.findViewById(R.id.questionEntry);


        opA = view.findViewById(R.id.optionA);
        opB = view.findViewById(R.id.optionB);
        opC = view.findViewById(R.id.optionC);
        opD = view.findViewById(R.id.optionD);
        optionsGroup = view.findViewById(R.id.options);

        if (isResultMood) {
            setUpResultMode();
        }


        ExamBoaredActivity activity = (ExamBoaredActivity) getActivity();

        mAnsweredQuestionEntries = new ArrayList<>();

        findMatches();


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPrev();
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSubmit(view);
            }
        });

        //optionsGroup.setOnCheckedChangeListener(this);
        return view;
    }


    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.rcView);
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewAdapter = new ExamNumberAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerViewAdapter.addAll(mMatches);

        mQuestionEntry = mMatches.get(0);
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        //mRecyclerView.addItemDecoration(dividerItemDecoration);
    }


    private void findMatches() {
        LocalQuestions questions = new LocalQuestions(getContext());
        if (mMatches != null) {
            mMatches.clear();
        }
        for (QuestionEntry model : questions.QuestionsEntryArrayList()) {
            mMatches.add(model);
        }
        if (mRecyclerViewAdapter == null) {
            initRecyclerView(view);
            setUpQuestionView();
        }
    }


    @SuppressLint("RestrictedApi")
    private void setUpQuestionView() {


        if (mCurrentQuestionNum > 0) {
            prevButton.setVisibility(View.VISIBLE);
        } else {
            prevButton.setVisibility(View.GONE);
        }
        if (mCurrentQuestionNum < mMatches.size() - 1) {
            forwardButton.setVisibility(View.VISIBLE);
        } else {
            forwardButton.setVisibility(View.GONE);

        }

        if (isResultMood) {
            setUpResultMode();
        }

        q_no.setText("QuestionEntry " + String.valueOf(mCurrentQuestionNum + 1) + "/" + String.valueOf(mMatches.size()));
        Spanned htmlAsSpanned = Html.fromHtml(mQuestionEntry.getQuestion());
        questions.setText(htmlAsSpanned);


      /*  if(mAnsweredQuestionEntries.contains(mQuestionEntry)){


        }else{
            optionsGroup.clearCheck();
        }*/


        opA.setText(mQuestionEntry.getOptionA());
        opB.setText(mQuestionEntry.getOptionB());
        opC.setText(mQuestionEntry.getOptionC());
        opD.setText(mQuestionEntry.getOptionD());

        opA.setOnClickListener(this);
        opB.setOnClickListener(this);
        opC.setOnClickListener(this);
        opD.setOnClickListener(this);

        try {
            if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionA())) {
                opA.setChecked(true);
            } else if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionB())) {
                opB.setChecked(true);
            } else if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionC())) {
                opC.setChecked(true);
            } else if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionD())) {
                opD.setChecked(true);
            } else {
                optionsGroup.clearCheck();
            }
        } catch (Exception e) {
            optionsGroup.clearCheck();

        }


    }

    private void goNext() {

        if (mCurrentQuestionNum < mMatches.size() - 1) {
            mCurrentQuestionNum++;

        } else {
            mCurrentQuestionNum = mMatches.size();
        }
        mRecyclerView.scrollToPosition(mCurrentQuestionNum);
        mQuestionEntry = mMatches.get(mCurrentQuestionNum);
        mRecyclerViewAdapter.addAnAnswedQuestionToList(mQuestionEntry, mCurrentQuestionNum);
        setUpQuestionView();
    }

    private void goPrev() {

        if (mCurrentQuestionNum > 0) {
            mCurrentQuestionNum--;
        } else {
            mCurrentQuestionNum = 0;
        }
        mRecyclerView.scrollToPosition(mCurrentQuestionNum);
        mQuestionEntry = mMatches.get(mCurrentQuestionNum);
        mRecyclerViewAdapter.addAnAnswedQuestionToList(mQuestionEntry, mCurrentQuestionNum);
        setUpQuestionView();

    }


    @Override
    public void onClickAction(QuestionEntry currentQuestionEntry, int position) {
        if (currentQuestionEntry != null) {
            mQuestionEntry = currentQuestionEntry;
            mCurrentQuestionNum = position;

            setUpQuestionView();
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

        QuestionEntry answeredQuestionEntry = mQuestionEntry;

        /*




        switch (checkId){
            case R.id.optionA:
                answeredQuestionEntry.setAnswerChosen(answeredQuestionEntry.getOptionA());
                break;

            case R.id.optionB:
                answeredQuestionEntry.setAnswerChosen(answeredQuestionEntry.getOptionB());
                break;

            case R.id.optionC:
                answeredQuestionEntry.setAnswerChosen(answeredQuestionEntry.getOptionC());
                break;

            case R.id.optionD:
                answeredQuestionEntry.setAnswerChosen(answeredQuestionEntry.getOptionD());
                break;
        }


        if(checkId != -1){
            //mRecyclerViewAdapter.addAnAnswedQuestionToList(mQuestionEntry);
            if(mAnsweredQuestionEntries.contains(answeredQuestionEntry)){
                mAnsweredQuestionEntries.remove(answeredQuestionEntry);
                mAnsweredQuestionEntries.add(answeredQuestionEntry);
            }else {
                mAnsweredQuestionEntries.add(answeredQuestionEntry);
            }

        }*/

    }

    @Override
    public void onClick(View view) {
        int checkId = optionsGroup.getCheckedRadioButtonId();

        // QuestionEntry answeredQuestion = mQuestionEntry;

        switch (checkId) {
            case R.id.optionA:
                //answeredQuestion.setAnswerChosen(answeredQuestion.getOptionA());
                mQuestionEntry.setAnswerChosen(mQuestionEntry.getOptionA());
                break;

            case R.id.optionB:
                mQuestionEntry.setAnswerChosen(mQuestionEntry.getOptionB());
                break;

            case R.id.optionC:
                mQuestionEntry.setAnswerChosen(mQuestionEntry.getOptionC());
                break;

            case R.id.optionD:
                mQuestionEntry.setAnswerChosen(mQuestionEntry.getOptionD());
                break;

            default:

        }
        mRecyclerViewAdapter.addAnAnswedQuestionToList(mQuestionEntry, mCurrentQuestionNum);
    }


    public void clickSubmit(final View view) {

    }


    public void setUpResultMode() {



        opA.setEnabled(false);
        opB.setEnabled(false);
        opC.setEnabled(false);
        opD.setEnabled(false);

        opA.setBackgroundResource(R.drawable.radiobuttonselector);
        opB.setBackgroundResource(R.drawable.radiobuttonselector);
        opC.setBackgroundResource(R.drawable.radiobuttonselector);
        opD.setBackgroundResource(R.drawable.radiobuttonselector);

        opA.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        opB.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        opC.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        opD.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


        try {
            /*for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) optionsGroup.getChildAt(i);
                String radioText = radioButton.getText().toString();
                if (radioText.equals(mQuestionEntry.getAnswerChosen()))
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_wrong, 0);

            }*/


            if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionA())) {

                opA.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_wrong, 0);
            } else if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionB())) {

                opB.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_wrong, 0);

            } else if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionC())) {
                opC.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_wrong, 0);
            } else if (mQuestionEntry.getAnswerChosen().equals(mQuestionEntry.getOptionD())) {
                opD.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_wrong, 0);
            } else {
                optionsGroup.clearCheck();
            }




        } catch (Exception e) {
            optionsGroup.clearCheck();

        }

        if (mQuestionEntry.getCorrectAnswer().equals(mQuestionEntry.getOptionA())) {
            opA.setBackgroundResource(R.drawable.radiobuttonselector_correct);
            opA.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);

        } else if (mQuestionEntry.getCorrectAnswer().equals(mQuestionEntry.getOptionB())) {
            opB.setBackgroundResource(R.drawable.radiobuttonselector_correct);
            opB.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);

        } else if (mQuestionEntry.getCorrectAnswer().equals(mQuestionEntry.getOptionC())) {
            opC.setBackgroundResource(R.drawable.radiobuttonselector_correct);
            opC.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);
        } else if (mQuestionEntry.getCorrectAnswer().equals(mQuestionEntry.getOptionD())) {
            opD.setBackgroundResource(R.drawable.radiobuttonselector_correct);
            opD.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);
        } else {
            optionsGroup.clearCheck();
        }

/*
        try {

            for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) optionsGroup.getChildAt(i);
                String radioText = radioButton.getText().toString();


                if(mQuestionEntry.getCorrectAnswer().equals(radioText)){
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);
                }else if(mQuestionEntry.getAnswerChosen().equals(radioText) && mQuestionEntry.getCorrectAnswer() != radioText){
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_wrong, 0);
                }else  if(mQuestionEntry.getAnswerChosen() != null) {
                    if(mQuestionEntry.getCorrectAnswer().equals(radioText)){
                        radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);
                    }else radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }else  radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


                /*if (mQuestionEntry.getAnswerChosen().equals(radioText)) {
                    if(radioText.equals(mQuestionEntry.getCorrectAnswer())) {
                        radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);
                    }else radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_wrong, 0);
                }else if (mQuestionEntry.getAnswerChosen() == null) {
                    if(radioText.equals(mQuestionEntry.getCorrectAnswer())){
                        radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, img_correct, 0);
                    }else   radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }else   radioButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }catch (Exception e){}




            */
    }


    public void setResultMoodEnabled(Boolean mood) {
        isResultMood = mood;
        mRecyclerViewAdapter.isResultMoodActivated(mood);
        setUpResultMode();
    }



    public ArrayList<QuestionEntry> getFirstFragmentResults() {
        ArrayList<QuestionEntry> result = mRecyclerViewAdapter.getResult();
        return result;
    }
}
