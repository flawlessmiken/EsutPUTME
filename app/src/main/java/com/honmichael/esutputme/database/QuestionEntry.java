package com.honmichael.esutputme.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "questionstable")
public class QuestionEntry implements Parcelable  {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String question;
    private String questionImage;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String answerChosen;
    private String subject;

    @Ignore
    public QuestionEntry(int id, String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String answerChosen) {
        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.answerChosen = answerChosen;
    }

    public QuestionEntry(int id, String question, String mQuestionImage, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String answerChosen, String mSubject) {
        this.id = id;
        this.question = question;
        this.questionImage = mQuestionImage;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.answerChosen = answerChosen;
        this.subject = mSubject;
    }


    public QuestionEntry() {
    }

    @Ignore
    public QuestionEntry(String question, String mQuestionImage, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String answerChosen, String mSubject) {
        this.question = question;
        this.questionImage = mQuestionImage;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.answerChosen = answerChosen;
        this.subject = mSubject;
    }

    @Ignore
    public QuestionEntry(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;

    }


    protected QuestionEntry(Parcel in) {
        id = in.readInt();
        question = in.readString();
        questionImage = in.readString();
        optionA = in.readString();
        optionB = in.readString();
        optionC = in.readString();
        optionD = in.readString();
        correctAnswer = in.readString();
        answerChosen = in.readString();
        subject = in.readString();
    }

    public static final Creator<QuestionEntry> CREATOR = new Creator<QuestionEntry>() {
        @Override
        public QuestionEntry createFromParcel(Parcel in) {
            return new QuestionEntry(in);
        }

        @Override
        public QuestionEntry[] newArray(int size) {
            return new QuestionEntry[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAnswerChosen() {
        return answerChosen;
    }

    public void setAnswerChosen(String answerChosen) {
        this.answerChosen = answerChosen;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeString(questionImage);
        parcel.writeString(optionA);
        parcel.writeString(optionB);
        parcel.writeString(optionC);
        parcel.writeString(optionD);
        parcel.writeString(correctAnswer);
        parcel.writeString(answerChosen);
        parcel.writeString(subject);
    }
}
