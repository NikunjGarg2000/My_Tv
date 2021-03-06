package com.wordpress.myselfnikunj.mytv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wordpress.myselfnikunj.mytv.Adapter.RecyclerViewAdapter;
import com.wordpress.myselfnikunj.mytv.Model.LiveChannelModel;

import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment {

    private LiveChannelModel liveChannelModel;
    DatabaseReference databaseReference;

    private List<LiveChannelModel> hindiLiveChannelModelList = new ArrayList<>();
    private RecyclerViewAdapter hindiRecyclerViewAdapter;

    ProgressBar progressBar;
    ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);
        Sprite wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.VISIBLE);

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        scrollView.setVisibility(View.INVISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("channels").child("Category").child("Music");

        hindiRecyclerViewAdapter = new RecyclerViewAdapter(getContext(), hindiLiveChannelModelList);
        RecyclerView hindiRecyclerView = (RecyclerView) view.findViewById(R.id.hindiRecyclerView);
        hindiRecyclerView.setHasFixedSize(true);
        hindiRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        hindiRecyclerView.setAdapter(hindiRecyclerViewAdapter);

        fetchDataForHindi();

        return view;
    }

    public void fetchDataForHindi() {
        databaseReference.child("Hindi_Music").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LiveChannelModel model = snapshot.getValue(LiveChannelModel.class);
                    String channelName = model.getName();
                    String channelImageUrl = model.getImageUrl();
                    String channelLink = model.getChannelLink();
                    liveChannelModel = new LiveChannelModel(getContext(), channelName, channelImageUrl, channelLink);
                    hindiLiveChannelModelList.add(model);
                }
                hindiRecyclerViewAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Error", databaseError.getMessage());
                Toast.makeText(getContext(), "Sorry, there is some error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}