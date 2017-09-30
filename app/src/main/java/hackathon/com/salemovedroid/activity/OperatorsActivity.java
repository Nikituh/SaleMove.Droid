package hackathon.com.salemovedroid.activity;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import hackathon.com.salemovedroid.R;
import hackathon.com.salemovedroid.adapter.OperatorListAdapter;
import hackathon.com.salemovedroid.model.Operator;
import hackathon.com.salemovedroid.networking.Networking;
import hackathon.com.salemovedroid.networking.Socket;
import hackathon.com.salemovedroid.utils.Utils;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by John on 9/29/2017.
 */

public class OperatorsActivity extends BaseActivity {

    static final String TAG = OperatorsActivity.class.getName();

    private Networking networking;
    public Socket socket;

    public static final int REQUEST_CALL_PERMISSION = 1;
    public static final int REQUEST_CALLING = 2;

    private List<Operator> operators;
    private RecyclerView rv;
    private OperatorListAdapter listAdapter;
    private ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_recycler_view);
        setupToolbar(getString(R.string.app_name));

        if (ActivityCompat.checkSelfPermission(OperatorsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(OperatorsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            onPermissionsGranted();
            final ScheduledFuture<?> timeHandle =
                    scheduler.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "ok time");

                        }
                    }, 0, 10, SECONDS);
            // Schedule the event, and run for 1 hour (60 * 60 seconds)
            scheduler.schedule(new Runnable() {
                public void run() {
                    timeHandle.cancel(false);
                }
            }, 60*60, SECONDS);
        }
    }

    private void onPermissionsGranted() {

        networking = new Networking();
        socket = new Socket();

        operators = new ArrayList<>();

        rv = (RecyclerView) findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        listAdapter = new OperatorListAdapter(this, operators);
        rv.setAdapter(listAdapter);

        try {
            String token = Utils.readFromAssets(getAssets(), "token.txt");
            Networking.setToken(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getOperators();
        socket.connect();
    }

    public void getOperators() {
        networking.getOperators(new Function1<List<Operator>, Unit>() {
            @Override
            public Unit invoke(List<Operator> operators) {
                listAdapter.updateAdapter(operators);
                return Unit.INSTANCE;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    onPermissionsGranted();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_CALLING) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "ok");
            }
        }
    }

    public void startWebActivity() {

        String url = "http://rocktoriin.ee/salemove";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null);
            startActivity(intent);
        }
    }

}