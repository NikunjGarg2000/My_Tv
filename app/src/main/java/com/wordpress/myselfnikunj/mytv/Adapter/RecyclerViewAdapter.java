package com.wordpress.myselfnikunj.mytv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.wordpress.myselfnikunj.mytv.ExoPlayerActivity;
import com.wordpress.myselfnikunj.mytv.Model.LiveChannelModel;
import com.wordpress.myselfnikunj.mytv.R;
import com.wordpress.myselfnikunj.mytv.RecyclerClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<LiveChannelModel> liveChannelModelList;

    public RecyclerViewAdapter(Context context, List<LiveChannelModel> liveChannelModelList) {
        this.context = context;
        this.liveChannelModelList = liveChannelModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(liveChannelModelList.get(position).getImageUrl())
                .into(holder.channelImage);
        holder.channelName.setText(liveChannelModelList.get(position).getName());
        holder.setListener(new RecyclerClickListener() {
            @Override
            public void onItemClickListener(View view, int pos) {
                Intent intent = new Intent(context, ExoPlayerActivity.class);
                intent.putExtra("link",liveChannelModelList.get(pos).getChannelLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return liveChannelModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Unbinder unbinder;

        @BindView(R.id.channelName)
        TextView channelName;
        @BindView(R.id.channelImage)
        CircularImageView channelImage;

        RecyclerClickListener listener;

        public void setListener(RecyclerClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClickListener(view, getAdapterPosition());
        }
    }
}
