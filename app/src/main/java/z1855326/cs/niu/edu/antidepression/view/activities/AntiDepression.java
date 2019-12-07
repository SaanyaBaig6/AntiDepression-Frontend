package z1855326.cs.niu.edu.antidepression.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.model.Comment;
import z1855326.cs.niu.edu.antidepression.view.fragments.AddComment;
import z1855326.cs.niu.edu.antidepression.view.fragments.AddPost;
import z1855326.cs.niu.edu.antidepression.view.fragments.Comments;
import z1855326.cs.niu.edu.antidepression.view.fragments.Feed;
import z1855326.cs.niu.edu.antidepression.view.fragments.Post;

public class AntiDepression extends AppCompatActivity {

    private Fragment fragment;
    public static final String MAIN_FRAGMENT = "MAIN_FRAGMENT";
    public Button button;
    public TextView title;
    private String buttonText = "Comments";
    private String titleText = "Post Details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_depression);
        this.button = findViewById(R.id.button);
        this.button.setText(buttonText);
        this.title = findViewById(R.id.title);
        this.title.setText(titleText);

        fragment = getSupportFragmentManager().findFragmentByTag(MAIN_FRAGMENT);
        Feed feedFragment = Feed.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, feedFragment,
                MAIN_FRAGMENT).commit();

    }

    public void gotoPostFragement(int position, z1855326.cs.niu.edu.antidepression.model.Post post) {
        Fragment fragment = Post.newInstance();
        Bundle args = new Bundle();
        args.putString("Position", String.valueOf(position));
        args.putString("Timestamp", post.getTimestamp());
        args.putString("Subject", post.getSubject());
        args.putString("Description", post.getDesc());
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void gotoCommentFragement(String position) {
        Fragment fragment = Comments.newInstance();
        Bundle args = new Bundle();
        args.putString("Position", position);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void gotoAddPostFragement() {
        Fragment fragment = AddPost.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void gotoAddCommentFragement(String position) {
        Fragment fragment = AddComment.newInstance();
        Bundle args = new Bundle();
        args.putString("Position", position);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
