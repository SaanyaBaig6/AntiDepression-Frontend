package z1855326.cs.niu.edu.antidepression.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.adapters.FeedAdapter;
import z1855326.cs.niu.edu.antidepression.controller.PostService;
import z1855326.cs.niu.edu.antidepression.service.Callback;
import z1855326.cs.niu.edu.antidepression.view.activities.AntiDepression;

public class Feed extends Fragment {

    private ProgressBar progressBar;
    private String buttonText = "Add Post";
    private String titleText = "Feed";

    public static Feed newInstance() {
        return new Feed();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        progressBar = view.findViewById(R.id.progressBar_feed);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView_feed);
        recyclerView.setHasFixedSize(true);
        ((AntiDepression) getActivity()).button.setText(buttonText);
        ((AntiDepression) getActivity()).title.setText(titleText);
        PostService.getInstance().fetchFeed(getContext(), new Callback(){
            @Override
            public void onComplete(boolean result, JSONObject jsonObject) {
            if(result) {
                progressBar.setVisibility(View.GONE);
                FeedAdapter adapter;
                adapter = new FeedAdapter(PostService.getInstance().getPosts(), getActivity());
                recyclerView.setAdapter(adapter);
            }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ((AntiDepression) getActivity()).button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AntiDepression) getActivity()).gotoAddPostFragement();
            }
        });

        return view;
    }

}