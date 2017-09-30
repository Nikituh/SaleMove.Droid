package hackathon.com.salemovedroid.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import butterknife.ButterKnife;
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
    private Socket socket;

    private List<Operator> operators;
    private RecyclerView rv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_recycler_view);
        setupToolbar(getString(R.string.app_name));

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

    // TODO: Move startcall into the adapter onClick logic
    void startCall(String phone) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.i(TAG, "no call permission");
        }
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "ok");
            }
        }
    }
}