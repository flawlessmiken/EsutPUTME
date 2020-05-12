package com.honmichael.esutputme.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.honmichael.esutputme.IMainActivity;
import com.honmichael.esutputme.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class DepartmentsListAdapter extends RecyclerView.Adapter<DepartmentsListAdapter.ViewHolder> {

    private static final String TAG = "ConnectionsAdapter";

    //vars
    private ArrayList<String> mDepartments = new ArrayList<>();
    private ArrayList<String> mFilteredUsers = new ArrayList<>();
    private Context mContext;
    private IMainActivity mInterface;
    private IMainActivity mInterface1;


    public DepartmentsListAdapter(Context context, ArrayList<String> departments) {
        mContext = context;
        mDepartments = departments;
        mFilteredUsers = departments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_department_feed, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final String department = mFilteredUsers.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(R.drawable.esutlogo)
                .apply(requestOptions)
                .into(holder.image);


       holder.name.setText(department);
        holder.position.setText(String.valueOf(position + 1));
      // holder.points.setText(user.getPoints());
        //holder.coin.setText(user.getPoints());


       /* holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + user.getName());

               mInterface.inflateChallengeRoom(user);
            }
        });*/
    }




    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mInterface1 = (IMainActivity) mContext;

    }

    @Override
    public int getItemCount() {
        return mFilteredUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

         CircleImageView image;
        TextView name;
        TextView points;
        TextView position;
        RelativeLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_user);
            name = itemView.findViewById(R.id.text_deptname);
            points = itemView.findViewById(R.id.tv_faculty);
            position = itemView.findViewById(R.id.text_position);

        }
    }
}