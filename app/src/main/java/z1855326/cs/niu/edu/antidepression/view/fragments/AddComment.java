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
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.controller.CommentService;
import z1855326.cs.niu.edu.antidepression.controller.PostService;
import z1855326.cs.niu.edu.antidepression.service.Callback;
import z1855326.cs.niu.edu.antidepression.view.activities.AntiDepression;


public class AddComment extends Fragment {
    public static AddComment newInstance() {
        return new AddComment();
    }
    private String buttonText = "";
    private String titleText = "Add Comment";
    private EditText comment;
    private Button submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //mapping view to xml layout
        View view = inflater.inflate(R.layout.fragment_add_comment, container, false);
        // setting header values
        ((AntiDepression) getActivity()).button.setText(buttonText);
        ((AntiDepression) getActivity()).title.setText(titleText);
        //getting data from fragments
        final String value = getArguments().getString("Position");
        final int position = Integer.valueOf(value);

        comment = view.findViewById(R.id.addComment_comment);
        submit = view.findViewById(R.id.addComment_submit);

        comment.setText("");

        //submit function to add a post
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if the editText values are empty
                if(comment.getText().toString().equalsIgnoreCase("")) {
                    String messag = "";
                    if(comment.getText().toString().equalsIgnoreCase("")) {
                        messag = "Enter Comment";
                    }
                    Toast toast = Toast.makeText(getContext(),
                            messag,
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    CommentService.getInstance().comment = comment.getText().toString();
                    CommentService.getInstance().postId = PostService.getInstance().getPosts().get(position).getId();
                    CommentService.getInstance().AddComment(getContext(), new Callback() {
                        @Override
                        public void onComplete(boolean result, JSONObject jsonObject) {
                            String message = null;
                            if(result) { // success scenario
                                message = "Success";
                                Fragment fragment = Feed.newInstance();
                                getFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                            else { //fail scenario
                                message = "Failed";
                            }
                            Toast toast = Toast.makeText(getContext(),
                                    message,
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            }
        });
        return view;
    }

}