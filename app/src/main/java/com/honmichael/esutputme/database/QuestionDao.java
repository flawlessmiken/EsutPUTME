package com.honmichael.esutputme.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {


    @Insert
    void addQuestion(QuestionEntry questionEntry);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateQuestionEntry(QuestionEntry questionEntry);

    @Query("SELECT * FROM questionstable WHERE subject = :mSubject ORDER BY subject")
    List<QuestionEntry> loadQuestionBySubject(String mSubject);
}
