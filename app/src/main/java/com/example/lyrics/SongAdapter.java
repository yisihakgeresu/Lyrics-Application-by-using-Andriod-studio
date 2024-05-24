package com.example.lyrics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.viewHolder> {
    Context context;

    ArrayList<SongModel> list;
    public SongAdapter(Context context,ArrayList<SongModel> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view= LayoutInflater.from(context).inflate(R.layout.item_subject,parent,false
);
        return new viewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
SongModel model=list.get(position);
holder.songName.setText(model.getSongName());

        Glide.with(context)
                .load(R.drawable.tesfeye_img) // Replace with your image source
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imageView);

// Load the second image
//        Glide.with(context)
//                .load(R.drawable.tesfeye_img) // Replace with your image source
//                .apply(RequestOptions.circleCropTransform())
//                .into(holder.imageView2);


holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,PdfViewActivity.class);
        intent.putExtra("name",model.getSongName());
//        intent.putExtra("name2",model.getSongName());
        intent.putExtra("position",position);
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<SongModel> filteredList) {
    }

    public void updateData(ArrayList<SongModel> list) {
        this.list = list;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView songName;
        ImageView imageView, imageView2;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            songName=itemView.findViewById(R.id.song1);
             imageView = itemView.findViewById(R.id.imageView);
            imageView2 = itemView.findViewById(R.id.imageView2);


        }

    }
}
