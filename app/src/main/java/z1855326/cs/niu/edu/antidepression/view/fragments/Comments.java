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

import org.json.JSONObject;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.adapters.CommentAdapter;
import z1855326.cs.niu.edu.antidepression.adapters.FeedAdapter;
import z1855326.cs.niu.edu.antidepression.controller.CommentService;
import z1855326.cs.niu.edu.antidepression.controller.PostService;
import z1855326.cs.niu.edu.antidepression.service.Callback;
import z1855326.cs.niu.edu.antidepression.view.activities.AntiDepression;

public class Comments extends Fragment {

    private ProgressBar progressBar;
    private Button button;
    private String buttonText = "Add";
    private String titleText = "Comments";

    public static Comments newInstance() { return new Comments(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        progressBar = view.findViewById(R.id.progressBar_comments);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView_comments);
        recyclerView.setHasFixedSize(true);
        final String value = getArguments().getString("Position");
        final int position = Integer.valueOf(value);
        ((AntiDepression) getActivity()).button.setText(buttonText);
        ((AntiDepression) getActivity()).title.setText(titleText);
        CommentService.getInstance().postId = PostService.getInstance().getPosts().get(position).getId();
        CommentService.getInstance().fetchComments(getContext(), new Callback(){
            @Override
            public void onComplete(boolean result, JSONObject jsonObject) {
                if(result) {
                    progressBar.setVisibility(View.GONE);
                    CommentAdapter adapter;
                    adapter = new CommentAdapter(CommentService.getInstance().getComments(), getActivity());
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
                ((AntiDepression) getActivity()).gotoAddCommentFragement(value);
            }
        });

        return view;
    }

}