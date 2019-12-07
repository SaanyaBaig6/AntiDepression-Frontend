package z1855326.cs.niu.edu.antidepression.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import z1855326.cs.niu.edu.antidepression.constants.Constants;
import z1855326.cs.niu.edu.antidepression.model.Comment;
import z1855326.cs.niu.edu.antidepression.model.Post;
import z1855326.cs.niu.edu.antidepression.service.Callback;
import z1855326.cs.niu.edu.antidepression.service.HTTPService;
import z1855326.cs.niu.edu.antidepression.utils.DateHelper;

import static android.content.ContentValues.TAG;

public class CommentService {
    public String postId;
    public String comment;
    private ArrayList<Comment> comments = new ArrayList<>(); // to store all comments of a post

    //code to create singleton class which is a design pattern to avoid redundant memory storage
    private static CommentService ourInstance = new CommentService();
    public static CommentService getInstance() {
        return ourInstance;
    }
    private CommentService() {}

    //service to hit backend api to add a comment
    public void AddComment(final Context context, final Callback callback) {
        String url = Constants.BASE_URL + "/post";
        Map<String, String> params = new HashMap<String, String>();
        params.put("timestamp", DateHelper.getInstance().currentTimestamp());
        params.put("id", postId);
        params.put("comment", comment);

        String method = "PUT";
        JSONObject jsonRequest = new JSONObject(params);

        HTTPService.getInstance().httpRequest(context, method, url, jsonRequest, new Callback(){
            @Override
            public void onComplete(boolean result, JSONObject response) {
                if(result) {
                    callback.onComplete(true, null);
                }

                else {
                    String message = "Comment failed, Please Try Again Later.";
                    Toast toast = Toast.makeText(context,
                            message,
                            Toast.LENGTH_SHORT);
                    toast.show();
                    callback.onComplete(false, null);
                }
            }
        } );
    }

    //service to hit backend api to get comments of a post
    public void fetchComments(final Context context, final Callback callback) {
        String method = "GET";
        String url = Constants.BASE_URL + "/post/comment?id=" + postId;
        JSONObject jsonRequest = new JSONObject();

        HTTPService.getInstance().httpRequest(context, method, url, jsonRequest, new Callback(){
            @Override
            public void onComplete(boolean result, JSONObject response) {
                if(result) {
                    try {
                        comments.clear();
                        JSONArray jsonArray = response.getJSONArray("comments");
                        for (int i=0; i<jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            String id = obj.getString("_id");
                            String timestamp = DateHelper.getInstance().convertDate(obj.getString("timestamp"));
                            String comment = obj.getString("comment");
                            comments.add(new Comment(id, timestamp, comment));
                        }
                        callback.onComplete(true, null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast toast = Toast.makeText(context,
                                (CharSequence) e,
                                Toast.LENGTH_SHORT);
                        toast.show();
                        callback.onComplete(false, null);
                    }
                }
                else {
                    String message = "Fetch Feed API failed, Please Try Again Later.";
                    Toast toast = Toast.makeText(context,
                            message,
                            Toast.LENGTH_SHORT);
                    toast.show();
                    callback.onComplete(false, null);
                }
            }
        } );
    }

    //getter for comments
    public ArrayList<Comment> getComments() {
        return comments;
    }

}
