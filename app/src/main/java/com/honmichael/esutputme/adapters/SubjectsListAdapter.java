package com.honmichael.esutputme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.honmichael.esutputme.R;
import com.honmichael.esutputme.model.Subjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flawless on 11/13/2018.
 */

public class SubjectsListAdapter extends RecyclerView.Adapter<SubjectsListAdapter.RecyclerViewHolder>{


    Context context;
    LayoutInflater inflater;
    ArrayList<Subjects> mSubjects, selectedSubjects;




    public interface OnClickAction {
        public void onClickAction(int position);
    }

    final private OnClickAction mItemClickListener;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv,tvStatus;
        CheckBox mCheckBox;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.subject_name);
            tvStatus = (TextView) itemView.findViewById(R.id.status);
            mCheckBox = (CheckBox)itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


        }
    }

    public SubjectsListAdapter(Context context, OnClickAction itemClickListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        mItemClickListener = itemClickListener;
        this.selectedSubjects = new ArrayList<>();
        this.mSubjects = new ArrayList<>();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.single_subject, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final Subjects subject = mSubjects.get(position);
        holder.tv.setText(subject.getName());
        holder.tv.setTag(holder);
        holder.tvStatus.setText(subject.getCompulsory().toString());
        holder.mCheckBox.setChecked(selectedSubjects.contains(subject));


        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


                if (selectedSubjects.contains(subject)) {
                    selectedSubjects.remove(subject);
                    unhighlightView(holder);
                    holder.mCheckBox.setChecked(false);

                } else {
                    if(getSelected().size() <4){
                    selectedSubjects.add(subject);
                    highlightView(holder);
                    holder.mCheckBox.setChecked(true);

                }else{
                        Toast.makeText(context, "You can't select more than 4 subjects", Toast.LENGTH_SHORT).show();
                        holder.mCheckBox.setChecked(false);
                    }

            }
                int selectedSize =selectedSubjects.size();
                mItemClickListener.onClickAction(selectedSize);
            }
        });

        if (selectedSubjects.contains(subject))
            highlightView(holder);
        else
            unhighlightView(holder);
    }

    public void addAll(ArrayList<Subjects> items) {
        this.mSubjects = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    private void highlightView(RecyclerViewHolder holder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrePrimary));
    }

    private void unhighlightView(RecyclerViewHolder holder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
    }



    public ArrayList<Subjects> getSelected() {
        return selectedSubjects;
    }



}
