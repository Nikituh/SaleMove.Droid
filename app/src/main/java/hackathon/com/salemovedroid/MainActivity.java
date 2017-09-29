package hackathon.com.salemovedroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import hackathon.com.salemovedroid.model.Operator;
import hackathon.com.salemovedroid.networking.Networking;

public class MainActivity extends AppCompatActivity {

    private Networking networking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networking = new Networking();

        String token = "";
        try {
            token = readFromAssets("token.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Networking.setToken(token);

        List<Operator> operators = networking.getOperators();
        System.out.print(operators);
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
