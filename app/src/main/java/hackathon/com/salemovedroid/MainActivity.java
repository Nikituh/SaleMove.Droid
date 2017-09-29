package hackathon.com.salemovedroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hackathon.com.salemovedroid.networking.Networking;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Networking().doStuff();
    }
}
