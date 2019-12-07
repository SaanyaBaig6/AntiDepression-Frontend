package z1855326.cs.niu.edu.antidepression.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONObject;

import z1855326.cs.niu.edu.antidepression.R;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        navigateToHome();

    }

    // after 3 second, the main activity will be rendered
    private void navigateToHome() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),AntiDepression.class);
                startActivity(i);
            }
        }, SPLASH_TIME);
    }

}
