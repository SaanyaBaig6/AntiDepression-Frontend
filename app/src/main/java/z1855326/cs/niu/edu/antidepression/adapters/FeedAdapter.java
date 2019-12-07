package z1855326.cs.niu.edu.antidepression.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.controller.PostService;
import z1855326.cs.niu.edu.antidepression.holders.FeedViewHolder;
import z1855326.cs.niu.edu.antidepression.model.Post;
import z1855326.cs.niu.edu.antidepression.view.activities.AntiDepression;
import z1855326.cs.niu.edu.antidepression.view.fragments.Feed;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private ArrayList<Post> posts;
    private Activity activity;

    public FeedAdapter(ArrayList<Post> posts, Activity activity) {
        this.posts = posts;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, final int position) {
        final Post post = posts.get(position);
        holder.updateUI(post);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AntiDepression) activity).gotoPostFragement(position, post);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_feed, parent, false);
        return new FeedViewHolder(card);
    }
}
