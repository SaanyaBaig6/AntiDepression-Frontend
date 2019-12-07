package z1855326.cs.niu.edu.antidepression.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.controller.CommentService;
import z1855326.cs.niu.edu.antidepression.controller.PostService;
import z1855326.cs.niu.edu.antidepression.holders.CommentsViewHolder;
import z1855326.cs.niu.edu.antidepression.holders.FeedViewHolder;
import z1855326.cs.niu.edu.antidepression.model.Comment;
import z1855326.cs.niu.edu.antidepression.model.Post;
import z1855326.cs.niu.edu.antidepression.view.activities.AntiDepression;

public class CommentAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    private ArrayList<Post> posts;
    private Activity activity;
    private int position;

    public CommentAdapter(ArrayList<Post> posts, int position, Activity activity) {
        this.posts = posts;
        this.activity = activity;
        this.position = position;
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, final int position) {
        final Comment comment = posts.get(this.position).getComments().get(position);
        holder.updateUI(comment);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.get(position).getComments().size();
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_comments, parent, false);
        return new CommentsViewHolder(card);
    }
}
