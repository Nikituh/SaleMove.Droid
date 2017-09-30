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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import hackathon.com.salemovedroid.R;
import hackathon.com.salemovedroid.adapter.OperatorListAdapater;
import hackathon.com.salemovedroid.model.MockOperator;
import hackathon.com.salemovedroid.model.Operator;
import hackathon.com.salemovedroid.networking.Networking;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * Created by John on 9/29/2017.
 */

public class OperatorsActivity extends BaseActivity {
    static final String TAG = OperatorsActivity.class.getName();
    private Networking networking;
    private List<Operator> operators;
    private RecyclerView rv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);
        setupToolbar(getString(R.string.app_name));
        networking = new Networking();
        rv=(RecyclerView)findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        initializeData();
        initializeAdapter();
        String token = "";
        try {
            token = readFromAssets("token.txt");
            Networking.setToken(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
       //startCall();
    }

    private void initializeData(){
        operators = new ArrayList<>();
        Operator op1= new Operator();
        op1.setName("Jon Snow");
        op1.setPhone("+37253996694");
        op1.setEmail("dreamcodesoft@gmail.com");

        Operator op2= new Operator();
        op2.setName("Anna");
        op2.setPhone("+37253996694");
        op2.setEmail("anna.bass@gmail.com");

        Operator op3= new Operator();
        op3.setName("Jhon");
        op3.setPhone("+37253996694");
        op3.setEmail("aare@gmail.com");
        op3.setStatus(true);

        Operator op4= new Operator();
        op4.setName("Uri");
        op4.setPhone("+37253996694");
        op4.setEmail("aare@gmail.com");

        Operator op5= new Operator();
        op5.setName("Aare");
        op5.setPhone("+37253996694");
        op5.setEmail("aare@gmail.com");

        Operator op6= new Operator();
        op6.setName("Sveta");
        op6.setPhone("+37253996694");
        op6.setEmail("aare@gmail.com");

        Operator op7= new Operator();
        op7.setName("Sven");
        op7.setPhone("+37253996694");
        op7.setEmail("aare@gmail.com");
        operators.add(op1);
        operators.add(op2);
        operators.add(op3);
        operators.add(op4);
        operators.add(op5);
        operators.add(op6);
        operators.add(op7);

         }

    private void initializeAdapter(){
        OperatorListAdapater operatorListAdapater = new OperatorListAdapater(this,operators);
        rv.setAdapter(operatorListAdapater);
    }



    //TODO:Move startcall into the adapter onClick logic
    private void startCall() {
        networking.getOperators(new Function1<List<Operator>, Unit>() {
            @Override
            public Unit invoke(List<Operator> operators) {
                String phone = operators.get(0).getPhone();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
                if (ActivityCompat.checkSelfPermission(OperatorsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Log.i(TAG, "no call permision");
                    return Unit.INSTANCE;
                }
                startActivityForResult(intent, 1);
                return Unit.INSTANCE;
            }
        });
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
    public String readFromAssets(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }
}