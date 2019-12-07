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
import z1855326.cs.niu.edu.antidepression.controller.PostService;
import z1855326.cs.niu.edu.antidepression.service.Callback;
import z1855326.cs.niu.edu.antidepression.view.activities.AntiDepression;

public class AddPost extends Fragment {
    public static AddPost newInstance() { return new AddPost(); }
    private String buttonText = "";
    private String titleText = "Add Post";
    private EditText subject;
    private EditText desc;
    private Button submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);
        ((AntiDepression) getActivity()).button.setText(buttonText);
        ((AntiDepression) getActivity()).title.setText(titleText);
        subject = view.findViewById(R.id.addPost_subject);
        desc = view.findViewById(R.id.addPost_description);
        submit = view.findViewById(R.id.addPost_submit);

        subject.setText("");
        desc.setText("");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subject.getText().toString().equalsIgnoreCase("") || desc.getText().toString().equalsIgnoreCase("")) {
                    String messag = "";
                    if(subject.getText().toString().equalsIgnoreCase("")) {
                        messag = "Enter Subject";
                    }
                    else if(desc.getText().toString().equalsIgnoreCase("")) {
                        messag = "Enter Description";
                    }
                    Toast toast = Toast.makeText(getContext(),
                            messag,
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    PostService.getInstance().subject = subject.getText().toString();
                    PostService.getInstance().desc = desc.getText().toString();
                    PostService.getInstance().AddPost(getContext(), new Callback() {
                        @Override
                        public void onComplete(boolean result, JSONObject jsonObject) {
                            String message = null;
                            if(result) {
                                message = "Success";
                                Fragment fragment = Feed.newInstance();
                                getFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                            else {
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