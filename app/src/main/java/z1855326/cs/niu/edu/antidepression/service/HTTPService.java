package z1855326.cs.niu.edu.antidepression.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HTTPService {
    private String method;
    private String url;
    private JSONObject jsonRequest;
    private HashMap headers;
    private HashMap params;
    private JSONObject response;

    private static HTTPService ourInstance = new HTTPService();

    public static HTTPService getInstance() {
        return ourInstance;
    }

    private HTTPService() {
    }

    public void httpRequest(Context context, String method, String url, JSONObject jsonRequest, final Callback callback) {

        int requestMethod = 0;
        switch (method) {
            case "GET": {
                requestMethod = Request.Method.GET;
                break;
            }
            case "POST": {
                requestMethod = Request.Method.POST;
                break;
            }
            case "PUT": {
                requestMethod = Request.Method.PUT;
                break;
            }
            case "DELETE": {
                requestMethod = Request.Method.DELETE;
                break;
            }
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, jsonRequest, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());
                callback.onComplete(true, response);
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.toString());
                        callback.onComplete(false, null);
                    }
                }){

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=UTF-8");
                //headers.put("Authorization", AuthService.getInstance().getIdToken());
                return headers;
            }
        };
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }


}
