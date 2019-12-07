package z1855326.cs.niu.edu.antidepression.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.view.activities.AntiDepression;

public class Post extends Fragment {

    private TextView timestamp;
    private TextView subject;
    private TextView description;
    private String buttonText = "Comments";
    private String titleText = "Post Details";

    public static Post newInstance() {
        return new Post();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //mapping view to xml layout
        final View view = inflater.inflate(R.layout.fragment_post, container, false);
        this.timestamp = (TextView)view.findViewById(R.id.post_timestamp);
        this.subject = (TextView)view.findViewById(R.id.post_subject);
        this.description = (TextView)view.findViewById(R.id.post_description);

        final String position = getArguments().getString("Position");
        String timestamp = getArguments().getString("Timestamp");
        String subject = getArguments().getString("Subject");
        String description = getArguments().getString("Description");

        this.timestamp.setText(timestamp);
        this.subject.setText(subject);
        this.description.setText(description);
        // setting header values
        ((AntiDepression) getActivity()).button.setText(buttonText);
        ((AntiDepression) getActivity()).title.setText(titleText);

        ((AntiDepression) getActivity()).button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AntiDepression) getActivity()).gotoCommentFragement(position);
            }
        });

        return view;
    }

}