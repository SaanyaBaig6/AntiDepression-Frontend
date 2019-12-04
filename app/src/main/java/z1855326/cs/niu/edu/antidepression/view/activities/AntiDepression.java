package z1855326.cs.niu.edu.antidepression.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import z1855326.cs.niu.edu.antidepression.R;
import z1855326.cs.niu.edu.antidepression.view.fragments.Feed;

public class AntiDepression extends AppCompatActivity {

    private Fragment fragment;
    public static final String MAIN_FRAGMENT = "MAIN_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_depression);

        fragment = getSupportFragmentManager().findFragmentByTag(MAIN_FRAGMENT);
        Feed feedFragment = Feed.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, feedFragment,
                MAIN_FRAGMENT).commit();

    }
}
