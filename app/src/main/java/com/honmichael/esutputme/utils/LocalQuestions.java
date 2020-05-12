package com.honmichael.esutputme.utils;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.Spanned;

import com.honmichael.esutputme.R;
import com.honmichael.esutputme.database.QuestionEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalQuestions {

    Context mContext;

    public LocalQuestions(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<QuestionEntry> QuestionsEntryArrayList() {

        ArrayList<QuestionEntry> entries = new ArrayList<>();

        // get our html content
        String htmlAsString = mContext.getString(R.string.English_comprehension_2);
      //  Spanned htmlAsSpanned = Html.fromHtml(htmlAsString); // used by TextView

       /* Resources res = mContext.getResources();
        TypedArray ta = res.obtainTypedArray(R.array.arrayEnglish);
        int n = ta.length();
        String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                array[i] = res.getStringArray(id);

            } else {
                // something wrong with the XML
            }
        }
        ta.recycle();*/


      List<String> arrayList;

        Resources res = mContext.getResources();
        TypedArray ta = res.obtainTypedArray(R.array.arrayEnglish);
        int n = ta.length();
        String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                array[i] = res.getStringArray(id);


                try {
                    QuestionEntry questionEntryzzz =  new QuestionEntry();
                    questionEntryzzz.setQuestion(array[i][0]);
                    questionEntryzzz.setQuestionImage(array[i][1]);
                    questionEntryzzz.setOptionA(array[i][2]);
                    questionEntryzzz.setOptionB(array[i][3]);
                    questionEntryzzz.setOptionC(array[i][4]);
                    questionEntryzzz.setOptionD(array[i][5]);
                    questionEntryzzz.setCorrectAnswer(array[i][6]);
                    questionEntryzzz.setSubject(array[i][7]);
                    entries.add(questionEntryzzz);

                }catch (Exception e){}



                /*arrayList = Arrays.asList(array[i]);
                QuestionEntry questionEntryzzz = QuestionEntry.class.cast(arrayList);
                QuestionEntry questionEntryzz = Class.forName(QuestionEntry).
                entries.add(questionEntryzzz);*/

            } else {
                // something wrong with the XML
            }
        }
        ta.recycle();





        /*


        QuestionEntry mQuestionEntry = new QuestionEntry(htmlAsString,
                "(A) white man will send the people to prison",
                "(B) white man can kill all the people with his gun",
                "(C) people are only trying to run away from the white man",
                "(D) people must accept the white man.",
                "(C) people are only trying to run away from the white man");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);


        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);


        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("For where your ​treasure​ is, there will your also be?",
                "Mind", "Heart", "Body", "Spirit",
                "Heart");
        entries.add(mQuestionEntry);

        mQuestionEntry = new QuestionEntry("What is the first element of the periodic table",
                "hydrogen", "Helium", "litium", "Boron",
                "hydrogen");
        entries.add(mQuestionEntry);



        */
        return entries;
    }




/*
        entries.add(mQuestion);mQuestion = new  QuestionsEntry ( "",
                                                                         "","","", "",
                                                                         "","",_TESTAMENT,EASY,false);
        entries.add(mQuestion);


3).

4).

5).


7).

8).

9).
11). At which pool was the man with 38 years of sickness healed
    A. Siloam
    B. Bethesda
    C. Jordan
    D. Bethsaida
12). Which country did the Eunuch come from
    A. Egypt
    B. Jerusalem
    C. Syria
    D. Ethiopia
13) Which prophet prophesied about the dry bones
    A. Isaiah
    B. Jeremiah
    C. Lamentations
    D. Ezekiel
14). The rebuilding of the walls of Jerusalem was done by whom
    A. Sanballat
    B. Esther
    C. Nehemiah
    D. Tobias

15) How many daughters did Job have
    A.  1
    B.  3
    C.  4
    D.  2

    */
}
