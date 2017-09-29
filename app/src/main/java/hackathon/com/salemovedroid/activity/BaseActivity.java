package hackathon.com.salemovedroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.logging.Logger;

import hackathon.com.salemovedroid.R;

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
    protected Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.main_toolbar);
    }

    protected void setupToolbar(final String title) {
        setupToolbar(title, false);
    }

    protected void setupToolbar(final String title, final boolean enableUpNavigation) {
        final Toolbar toolbar = getToolbar();
        if (toolbar == null) {
            return;
        }
        toolbar.setTitleTextAppearance(this, R.style.AppTheme);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if (enableUpNavigation) {
            toolbar.setNavigationIcon(R.drawable.ic_action_name );
        }
    }
}