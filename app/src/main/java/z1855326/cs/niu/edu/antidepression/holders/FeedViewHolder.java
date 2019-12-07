package z1855326.cs.niu.edu.antidepression.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.model.Post;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    private TextView timestamp;
    private TextView subject;

    //constructor
    public FeedViewHolder(View itemView) {
        super(itemView);
        this.timestamp = (TextView)itemView.findViewById(R.id.feed_timestamp);
        this.subject = (TextView)itemView.findViewById(R.id.feed_subject);
    }

    //mapping ui with data
    public void updateUI(Post post) {
        timestamp.setText(post.getTimestamp());
        subject.setText(post.getSubject());
    }
}
