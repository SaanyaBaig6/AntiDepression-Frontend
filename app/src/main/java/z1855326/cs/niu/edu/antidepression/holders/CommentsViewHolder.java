package z1855326.cs.niu.edu.antidepression.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.model.Comment;
import z1855326.cs.niu.edu.antidepression.model.Post;

public class CommentsViewHolder extends RecyclerView.ViewHolder {

    private TextView timestamp;
    private TextView comment;

    public CommentsViewHolder(View itemView) {
        super(itemView);
        this.timestamp = (TextView)itemView.findViewById(R.id.comment_timestamp);
        this.comment = (TextView)itemView.findViewById(R.id. comment_comment);
    }

    public void updateUI(Comment comment) {
        this.timestamp.setText(comment.getTimestamp());
        this.comment.setText(comment.getComment().);
    }
}
