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

    private ArrayList<Comment> comments;
    private Activity activity;
    private int position;

    public CommentAdapter(ArrayList<Comment> comments, Activity activity) {
        this.comments = comments;
        this.activity = activity;
    }

    //to bind data with the ui
    @Override
    public void onBindViewHolder(CommentsViewHolder holder, final int position) {
        final Comment comment = comments.get(position);
        holder.updateUI(comment);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    // get size of ui list
    @Override
    public int getItemCount() {
        return comments.size();
    }

    //mapping card with the recycler view
    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_comments, parent, false);
        return new CommentsViewHolder(card);
    }
}
