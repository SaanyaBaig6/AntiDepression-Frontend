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

public class PostService {
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    public String subject;
    public String desc;

    //code to create singleton class which is a design pattern to avoid redundant memory storage
    private static PostService ourInstance = new PostService();
    public static PostService getInstance() {
        return ourInstance;
    }
    private PostService() {}

    //service to hit backend api to get feed of a posts
    public void fetchFeed(final Context context, final Callback callback) {
        String method = "GET";
        String url = Constants.BASE_URL + "/post";
        JSONObject jsonRequest = new JSONObject();

        HTTPService.getInstance().httpRequest(context, method, url, jsonRequest, new Callback(){
            @Override
            public void onComplete(boolean result, JSONObject response) {
            if(result) {
                try {
                    posts.clear();
                    JSONArray jsonArray = response.getJSONArray("post");
                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String postId = obj.getString("_id");
                        String timestamp = DateHelper.getInstance().convertDate(obj.getString("timestamp"));
                        String subject = obj.getString("subject");
                        String desc = obj.getString("description");
                        JSONArray commentsArray = obj.getJSONArray("comment");
                        comments.clear();
                        for (int j=0; j<commentsArray.length(); j++) {
                            String commentid = obj.getString("_id");
                            String commenttimestamp = obj.getString("timestamp");
                            String comment = obj.getString("comment");
                            comments.add(new Comment(commentid,commenttimestamp,comment));
                        }
                        Log.d(TAG, "position "+ postId + " Comments "+ comments);
                        posts.add(new Post(postId,timestamp,subject,desc,comments));
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

    //service to hit backend api toadd a post
    public void AddPost(final Context context, final Callback callback) {
        String url = Constants.BASE_URL + "/post";
        Map<String, String> params = new HashMap<String, String>();
        params.put("timestamp", DateHelper.getInstance().currentTimestamp());
        params.put("subject", subject);
        params.put("description", desc);

        String method = "POST";
        JSONObject jsonRequest = new JSONObject(params);

        HTTPService.getInstance().httpRequest(context, method, url, jsonRequest, new Callback(){
            @Override
            public void onComplete(boolean result, JSONObject response) {
                if(result) {
                    callback.onComplete(true, null);
                }

                else {
                    String message = "Post failed, Please Try Again Later.";
                    Toast toast = Toast.makeText(context,
                            message,
                            Toast.LENGTH_SHORT);
                    toast.show();
                    callback.onComplete(false, null);
                }
            }
        } );
    }

    //getter for posts
    public ArrayList<Post> getPosts() {
        return posts;
    }

    //getter for comments
    public ArrayList<Comment> getComments(int position) {
        return posts.get(position).getComments();
    }
}
