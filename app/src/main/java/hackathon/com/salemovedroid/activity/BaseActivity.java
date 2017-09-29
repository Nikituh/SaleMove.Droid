package hackathon.com.salemovedroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by John on 9/29/2017.
 */
public class BaseActivity extends AppCompatActivity {
    protected Logger log = Logger.getLogger(BaseActivity.class.getName());

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}