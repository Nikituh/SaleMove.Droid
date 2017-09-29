package hackathon.com.salemovedroid.activity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hackathon.com.salemovedroid.R;
import hackathon.com.salemovedroid.networking.Networking;

/**
 * Created by John on 9/29/2017.
 */

public class OperatorsActivity extends BaseActivity {
    private Networking networking;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_operatorlist);
        setupToolbar(getString(R.string.app_name));
        networking = new Networking();

        String token = "";
        try {
            token = readFromAssets("token.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Networking.setToken(token);

        networking.getOperators();
        
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
