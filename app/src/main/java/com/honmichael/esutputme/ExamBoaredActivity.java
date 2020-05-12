package com.honmichael.esutputme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;
import com.honmichael.esutputme.database.QuestionEntry;
import com.honmichael.esutputme.examboardutils.FirstFragment;
import com.honmichael.esutputme.examboardutils.SecondFragment;
import com.honmichael.esutputme.examboardutils.ThirdFragment;
import com.honmichael.esutputme.examboardutils.FourthFragment;
import com.honmichael.esutputme.examboardutils.MyPagerAdapter;
import com.honmichael.esutputme.examboardutils.SubmitFragment;
import com.honmichael.esutputme.model.FragmentTag;
import com.levitnudi.legacytableview.LegacyTableView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.levitnudi.legacytableview.LegacyTableView.LEVICI;

public class ExamBoaredActivity extends AppCompatActivity implements IExamBoardActivty {


    private static final String TAG = "ExamboardActivity";
    private static final int FIRST_FRAGMENT = 0;
    private static final int SECOND_FRAGMENT = 1;
    private static final int THIRD_FRAGMENT = 2;
    private static final int FOURTH_FRAGMENT = 3;
    private static final int SUBMIT_FRAGMENT = 4;


    private int mFirstExamSize = 12;

    //widgets
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    //fragments
    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;
    private ThirdFragment mThirdFragment;
    private FourthFragment mFourthFragment;
    private SubmitFragment mSubmitFragment;

    private String[] subjects;
    private ArrayList<String> passedSubjects;
    private ArrayList<Integer> scorelist;
    private ArrayList<String> tableResult;


    private ArrayList<FragmentTag> mFragments = new ArrayList<>();

    Dialog resultDialog;
    ImageView resultDialogClose;
    PieChart mPiechart;

    Boolean isResultMood;

    LegacyTableView legacyTableView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_boared);

        resultDialog = new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);


        passedSubjects = new ArrayList<>();

        isResultMood = false;

        Intent intent = getIntent();
        if (intent.hasExtra("KEY")) {
            subjects = intent.getStringArrayExtra("KEY");
            passedSubjects.addAll(Arrays.asList(subjects));
            passedSubjects.add("Submit");
        }

        initializeResultDialogue();

        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        mTabLayout = findViewById(R.id.tabs_bottom);


        FragmentsArray();
        setupViewPager();
        setUpTablayout();

    }


    private void setUpTablayout() {

        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.darkGrey), ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    private void FragmentsArray() {

        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();
        mFourthFragment = new FourthFragment();
        mSubmitFragment = new SubmitFragment();


        switch (passedSubjects.size()) {
            case 2:
                mFragments.add(new FragmentTag(mFirstFragment, passedSubjects.get(0)));
                mFragments.add(new FragmentTag(mSubmitFragment, passedSubjects.get(1)));
                break;
            case 3:
                mFragments.add(new FragmentTag(mFirstFragment, passedSubjects.get(0)));
                mFragments.add(new FragmentTag(mSecondFragment, passedSubjects.get(1)));
                mFragments.add(new FragmentTag(mSubmitFragment, passedSubjects.get(2)));
                break;
            case 4:
                mFragments.add(new FragmentTag(mFirstFragment, passedSubjects.get(0)));
                mFragments.add(new FragmentTag(mSecondFragment, passedSubjects.get(1)));
                mFragments.add(new FragmentTag(mThirdFragment, passedSubjects.get(2)));
                mFragments.add(new FragmentTag(mSubmitFragment, passedSubjects.get(3)));
                break;
            case 5:
                mFragments.add(new FragmentTag(mFirstFragment, passedSubjects.get(0)));
                mFragments.add(new FragmentTag(mSecondFragment, passedSubjects.get(1)));
                mFragments.add(new FragmentTag(mThirdFragment, passedSubjects.get(2)));
                mFragments.add(new FragmentTag(mFourthFragment, passedSubjects.get(3)));
                mFragments.add(new FragmentTag(mSubmitFragment, passedSubjects.get(4)));
                break;
            default:

                break;
        }

    }


    private void setupViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        if (mFragments != null) {
            for (int i = 0; i < mFragments.size(); i++) {
                adapter.addFragment(mFragments.get(i).getFragment());
            }
            mViewPager.setAdapter(adapter);

            mViewPager.setOffscreenPageLimit(4);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_bottom);
            tabLayout.setupWithViewPager(mViewPager);
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);


            for (int i = 0; i < mFragments.size(); i++) {
                tabLayout.getTabAt(i).setText(String.valueOf(mFragments.get(i).getTag()));
            }

            //tabLayout.getTabAt(FIRST_FRAGMENT).setText(getString(R.string.tag_fragment_first));
        }
        /*mFirstFragment = new ThirdFragment();
        mSecondFragment = new ThirdFragment();
        mThirdFragment = new ThirdFragment();
        mFourthFragment = new FourthFragment();


        adapter.addFragment(mFirstFragment);
        adapter.addFragment(mSecondFragment);
        adapter.addFragment(mThirdFragment);
        adapter.addFragment(mFourthFragment);

        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_bottom);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(FIRST_FRAGMENT).setText(getString(R.string.tag_fragment_first));
        tabLayout.getTabAt(SECOND_FRAGMENT).setText(getString(R.string.tag_fragment_second));
        tabLayout.getTabAt(THIRD_FRAGMENT).setText(getString(R.string.tag_fragment_third));
        tabLayout.getTabAt(FOURTH_FRAGMENT).setText(getString(R.string.tag_fragment_fourth));*/

    }


    @Override
    public void swithBtwQuestions(int Position) {

    }

    @Override
    public void onSubmitButtonClicked() {
        //int size = passedSubjects.size();
        processResult();
        showResultDialog();
        /*switch (size){
            case 2:

                ArrayList<QuestionEntry> firstFragmentResults = mFirstFragment.getFirstFragmentResults();
                int scoreFromFirstFragment = getScorePercentageFromPassedArray(firstFragmentResults);
                //Toast.makeText(this, "You have successful submitted goodluck ! "+ scoreFromFirstFragment, Toast.LENGTH_SHORT).show();
                break;

            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }*/
        // Toast.makeText(this, "You have successful submitted goodluck ! "+ , Toast.LENGTH_SHORT).show();
    }


    public int getScorePercentageFromPassedArray(ArrayList<QuestionEntry> questionEntryArrayList) {
        // An array of QuestionEntry is passed into this method an it determines how many answers were correct

        int score = 0;
        int size = 0;
        int percentageScore = 0;


        if (questionEntryArrayList != null) {
            for (int i = 0; i < questionEntryArrayList.size(); i++) {
                QuestionEntry questionEntry = questionEntryArrayList.get(i);
                if (questionEntry.getAnswerChosen() != null)
                    if (questionEntry.getAnswerChosen().equals(questionEntry.getCorrectAnswer())) {
                        score++;
                    }
                size++;
            }

            tableResult.add(String.valueOf(score));
            tableResult.add(String.valueOf(size-score));

            percentageScore = score * 100 / size;


        }


        return percentageScore;
    }


    public void processResult() {


        scorelist = new ArrayList<>();
        tableResult = new ArrayList<>();
        for (int i = 0; i < mFragments.size() - 1; i++) {
            mFragments.get(i).getFragment();

            ArrayList<QuestionEntry> resultFromFragment = getResultsFromFragment(mFragments.get(i).getFragment());

            tableResult.add(mFragments.get(i).getTag());
            tableResult.add(String.valueOf(resultFromFragment.size()));
            scorelist.add(getScorePercentageFromPassedArray(resultFromFragment));

        }

    }


    public ArrayList<QuestionEntry> getResultsFromFragment(Fragment fragment) {

        // checks what fragment was passed and getResult From the passed fragment
        assert fragment.getTag() != null;
        if (fragment.getTag().equals(mFirstFragment.getTag())) {
            return mFirstFragment.getFirstFragmentResults();
        } else if (fragment.getTag().equals(mSecondFragment.getTag())) {
            return mSecondFragment.getSecondFragmentResults();
        } else if (fragment.getTag().equals(mThirdFragment.getTag())) {
            return mThirdFragment.getThirdFragmentResults();
        } else if (fragment.getTag().equals(mFourthFragment.getTag())) {
            return mFourthFragment.getFourthFragmentResults();
        }
        return null;
    }





    public void showResultDialog() {

        String [] data = new String[tableResult.size()];
        for (int i = 0; i <tableResult.size() ; i++) {
            data[i]= tableResult.get(i);
        }
        //set table contents as string arrays
        LegacyTableView.insertLegacyContent(data);
        legacyTableView.setContent(LegacyTableView.readLegacyContent());
        legacyTableView.setTheme(LEVICI);

        //remember to build your table as the last step
        legacyTableView.build();


        ArrayList<PieEntry> yValue = new ArrayList<>();

        assert scorelist != null;
        for (int i = 0; i < scorelist.size(); i++) {
            yValue.add(new PieEntry(scorelist.get(i), passedSubjects.get(i)));
        }

        mPiechart.animateY(1000, Easing.EaseInCirc);


        PieDataSet dataSet = new PieDataSet(yValue, "Result");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(10f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData dataP = new PieData(dataSet);
        dataP.setValueTextSize(10f);

        dataP.setValueTextColor(Color.BLACK);


        mPiechart.setData(dataP);

        resultDialog.show();

    }



    public Boolean tellAdapterCurrentMood(){
        return isResultMood;
    }




    public void setUpTableWithData(Dialog view){

    }

    private void initializeResultDialogue(){
        resultDialog.setContentView(R.layout.dialogueview);
        //resultDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        resultDialogClose = (ImageView) resultDialog.findViewById(R.id.close_btn);


        resultDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);

                try {
                    mFirstFragment.setResultMoodEnabled(true);
                    mSecondFragment.setResultMoodEnabled(true);
                    mThirdFragment.setResultMoodEnabled(true);
                    mFourthFragment.setResultMoodEnabled(true);
                }catch (Exception e){

                }

                resultDialog.dismiss();
            }
        });


        mPiechart = (PieChart) resultDialog.findViewById(R.id.pie_chart);
        mPiechart.setUsePercentValues(true);
        mPiechart.getDescription().setEnabled(false);
        mPiechart.setExtraOffsets(5, 10, 5, 5);

        mPiechart.setDragDecelerationFrictionCoef(0.95f);
        mPiechart.setDrawHoleEnabled(true);
        mPiechart.setHoleColor(Color.WHITE);
        mPiechart.setTransparentCircleRadius(61f);

        //setUpTableWithData(resultDialog);

        LegacyTableView.insertLegacyTitle("Subject", "Total Number", "Score", "Failed");
        legacyTableView = (LegacyTableView)resultDialog.findViewById(R.id.legacy_table_view);
        legacyTableView.setTitle(LegacyTableView.readLegacyTitle());

        //if you want a smaller table, change the padding setting
        //legacyTableView.setTablePadding(7);
        //to enable users to zoom in and out:
        legacyTableView.setZoomEnabled(false);
        legacyTableView.setShowZoomControls(false);

    }




}
