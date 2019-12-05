package z1855326.cs.niu.edu.antidepression.controller;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import z1855326.cs.niu.edu.antidepression.constants.Constants;
import z1855326.cs.niu.edu.antidepression.service.Callback;
import z1855326.cs.niu.edu.antidepression.service.HTTPService;
import z1855326.cs.niu.edu.antidepression.utils.DateHelper;

public class CommentService {
    public String postId;
    public String comment;

    public void AddComment(final Context context, final Callback callback) {
        String url = Constants.BASE_URL + "/post";
        Map<String, String> params = new HashMap<String, String>();
        params.put("timestamp", DateHelper.getInstance().currentTimestamp());
        params.put("id", postId);
        params.put("comment", comment);

        String method = "POST";
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
}
