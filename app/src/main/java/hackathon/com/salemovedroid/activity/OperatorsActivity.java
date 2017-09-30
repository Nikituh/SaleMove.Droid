package hackathon.com.salemovedroid.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hackathon.com.salemovedroid.R;
import hackathon.com.salemovedroid.adapter.OperatorListAdapter;
import hackathon.com.salemovedroid.model.Operator;
import hackathon.com.salemovedroid.networking.Networking;
import hackathon.com.salemovedroid.networking.Socket;
import hackathon.com.salemovedroid.utils.Utils;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_recycler_view);
        setupToolbar(getString(R.string.app_name));

        if (ActivityCompat.checkSelfPermission(OperatorsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(OperatorsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            onPermissionsGranted();
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

        OperatorListAdapter adapter = new OperatorListAdapter(this, operators);
        rv.setAdapter(adapter);

        try {
            String token = Utils.readFromAssets(getAssets(), "token.txt");
            Networking.setToken(token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getOperators();

        socket.connect();

    }

    private void getOperators() {
        networking.getOperators(new Function1<List<Operator>, Unit>() {
            @Override
            public Unit invoke(List<Operator> operators) {
                OperatorsActivity.this.operators.addAll(operators);
                rv.getAdapter().notifyDataSetChanged();
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
                Log.i("OK", "ok");
            }
        }
    }

    public void startWebActivity() {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }
}