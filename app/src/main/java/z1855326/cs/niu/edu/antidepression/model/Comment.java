package z1855326.cs.niu.edu.antidepression.model;

public class Comment {
    private String id;
    private String timestamp;
    private String comment;

    //constructor to modal
    public Comment(String id, String timestamp, String comment) {
        this.id = id;
        this.timestamp = timestamp;
        this.comment = comment;
    }

    //getter and setters
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
