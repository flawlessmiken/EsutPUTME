package com.honmichael.esutputme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.honmichael.esutputme.database.AppExecutors;
import com.honmichael.esutputme.database.QuestionEntry;

import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final Intent startAppProper = new Intent(FirstActivity.this, MainActivity.class);

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(startAppProper);
                                finish();
                            }
                        });
                    }
                },
                2600
        );
    }


    public void addQuestionsToDatabase() {
       /* for (int i = 0; i < studentNames.length; i++) {
            final QuestionEntry questionEntry = new QuestionEntry(studentNames[i], studentReg[i],
                    false, false, false, false,false);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.mBookDao().addStudent(bookEntry);
                }
            });
        }*/
    }
}
