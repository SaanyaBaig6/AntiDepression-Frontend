package z1855326.cs.niu.edu.antidepression.model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Post {
    private String id;
    private String timestamp;
    private String subject;
    private String desc;
    private ArrayList<Comment> comments;

    public Post(String id, String timestamp, String subject, String desc, ArrayList<Comment> comments) {
        this.id = id;
        this.timestamp = timestamp;
        this.subject = subject;
        this.desc = desc;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

}
