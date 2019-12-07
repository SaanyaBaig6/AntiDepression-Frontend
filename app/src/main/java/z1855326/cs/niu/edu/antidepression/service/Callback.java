package z1855326.cs.niu.edu.antidepression.service;

import org.json.JSONObject;

public interface Callback { //once a function is executed then callback function get called
    void onComplete(boolean result, JSONObject jsonObject);
}
