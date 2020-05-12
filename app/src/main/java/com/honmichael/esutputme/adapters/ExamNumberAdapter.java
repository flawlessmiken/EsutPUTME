package com.honmichael.esutputme.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.honmichael.esutputme.IExamBoardActivty;
import com.honmichael.esutputme.R;
import com.honmichael.esutputme.database.QuestionEntry;

import java.util.ArrayList;


public class ExamNumberAdapter extends RecyclerView.Adapter<ExamNumberAdapter.ViewHolder> {

    private static final String TAG = "ExamNuberAdapter";

    private Context mContext;

    private IExamBoardActivty mInterface1;

    ArrayList<QuestionEntry> mQuestionEntries, selectedQuestionEntry;

    private static final int VIEW_NORMAL = 0;
    private static final int VIEW_SELECTED = 1;

    private Boolean mIsResultMood;


    public ExamNumberAdapter(Context context, OnClickAction itemClickListener) {
        mContext = context;

        mItemClickListener = itemClickListener;
        this.selectedQuestionEntry = new ArrayList<>();
        this.mQuestionEntries = new ArrayList<>();
        mIsResultMood = false;
    }

    public interface OnClickAction {
        public void onClickAction(QuestionEntry currentQuestionEntry, int position);
    }

    final private ExamNumberAdapter.OnClickAction mItemClickListener;


    @Override
    public ExamNumberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        int layoutId;
        switch (viewType) {

            case VIEW_NORMAL: {
                layoutId = R.layout.layout_exam_number_feed;
                break;
            }

            case VIEW_SELECTED: {
                layoutId = R.layout.layout_exam_number_feed_bold;
                break;

            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ExamNumberAdapter.ViewHolder holder = new ExamNumberAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ExamNumberAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");




        if(mQuestionEntries.get(position).getAnswerChosen() != null){
            if(mIsResultMood && mQuestionEntries.get(position).getAnswerChosen().equals(mQuestionEntries.get(position).getCorrectAnswer())){
                highlightViewGreen(holder);
            }else highlightView(holder);
        }else{
            unhighlightView(holder);
        }

        holder.exam_number.setText(String.valueOf(position + 1));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*if (selectedQuestionEntry.contains(mQuestionEntries.get(position))) {
                    selectedQuestionEntry.remove(mQuestionEntries.get(position));
                    unhighlightView(holder);

                } else {
                        selectedQuestionEntry.add(mQuestionEntries.get(position));
                        highlightView(holder);
                }

                int selectedSize =selectedQuestionEntry.size();
                */
                selectedQuestionEntry.clear();
                selectedQuestionEntry.add(mQuestionEntries.get(position));

                mItemClickListener.onClickAction(mQuestionEntries.get(position), mQuestionEntries.indexOf(mQuestionEntries.get(position)));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemViewType(int position) {

        if(selectedQuestionEntry.contains(mQuestionEntries.get(position))){
            return VIEW_SELECTED;
        }else {
            return VIEW_NORMAL;
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mInterface1 = (IExamBoardActivty) mContext;

    }

    @Override
    public int getItemCount() {
        return mQuestionEntries.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView exam_number;

        RelativeLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            exam_number = itemView.findViewById(R.id.tv_exam_number);

        }
    }


    public void addAll(ArrayList<QuestionEntry> items) {
        this.mQuestionEntries = items;
        notifyDataSetChanged();
    }

    public void addAnAnswedQuestionToList(QuestionEntry questionEntry, int itemposition) {

        if(questionEntry != null){
            selectedQuestionEntry.clear();
            selectedQuestionEntry.add(mQuestionEntries.get(itemposition));
        mQuestionEntries.set(itemposition, questionEntry);
        notifyDataSetChanged();
        }
        /*if(!selectedQuestionEntry.contains(questionEntry)){
            this.selectedQuestionEntry.add(questionEntry);

        }*/




    }





    private void highlightView(ViewHolder holder) {

            holder.exam_number.setBackground(ContextCompat.getDrawable(mContext,R.drawable.curved_circle_stroke_two));

    }

    private void unhighlightView(ViewHolder holder) {
        holder.exam_number.setBackground(ContextCompat.getDrawable(mContext, R.drawable.curved_circle_stroke));
    }

    private void highlightViewGreen(ViewHolder holder) {

        holder.exam_number.setBackground(ContextCompat.getDrawable(mContext,R.drawable.curved_circle_stroke_two_green));

    }



    public ArrayList<QuestionEntry> getResult() {
        return mQuestionEntries;
    }

    public void isResultMoodActivated(Boolean mood) {
        mIsResultMood = mood;
        notifyDataSetChanged();

    }
}
