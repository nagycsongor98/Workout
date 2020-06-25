package com.nagycsongor.workout.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nagycsongor.workout.DetailsPage.DetailsActivity;
import com.nagycsongor.workout.Models.Workout;
import com.nagycsongor.workout.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WorkoutsListAdapter extends RecyclerView.Adapter<WorkoutsListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Workout> data;
    private StorageReference storageReference;

    public WorkoutsListAdapter(Context context, ArrayList<Workout> data) {
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public WorkoutsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.workout_list_item, parent, false);
        WorkoutsListAdapter.MyViewHolder viewHolder = new WorkoutsListAdapter.MyViewHolder(view);
        viewHolder.itemView.setOnClickListener(v -> {
            MyViewHolder holder = (MyViewHolder) v.getTag(R.id.viewHolder);
            int position = holder.getAdapterPosition();
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(DetailsActivity.KEY,data.get(position));
            v.getContext().startActivity(intent);

        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsListAdapter.MyViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.date.setText(data.get(position).getDate());
        String url = data.get(position).getImageURL();
        if (url != null) {
            try {
                File localFile = File.createTempFile("images", "jpg");
                storageReference.child(url).getFile(localFile)
                        .addOnSuccessListener(taskSnapshot ->
                                Glide.with(context)
                                        .load(localFile)
                                        .into(holder.imageView));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        holder.itemView.setTag(R.id.viewHolder, holder);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            date = itemView.findViewById(R.id.dateTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
