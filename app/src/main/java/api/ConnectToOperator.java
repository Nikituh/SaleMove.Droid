package api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by bassa on 29/09/2017.
 */

public class ConnectToOperator extends AsyncTask<Void, Void, Void> {

    private static final String API_TOKEN = "ZKz6KtLonPlK-swkwwHyKA";
    private static final String SITE_ID = "4acb779c-a1d8-4d38-a4e6-402b7ed1453d";
    private static final String OPERATOR_ID = "40531de4-79a1-43de-940f-4aed74aa9e7b";
    private static final String BASE_URL = "https://api.beta.salemove.com/";

    public void findAvailableOperator() {

        String charset = "UTF-8";
        String param2 = "false";

        String query = null;
        try {
            query = String.format("site_ids[]=%s&include_offline=%s",
                    URLEncoder.encode(SITE_ID, charset),
                    URLEncoder.encode(param2, charset));
            URLConnection connection = new URL(BASE_URL + "operators?" + query).openConnection();
            connection.setRequestProperty("Authorization", "Token " + API_TOKEN);
            connection.setRequestProperty("Accept", "application/vnd.salemove.v1+json");
            InputStream response = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(response));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            Log.i("!!!!!!!!!!!!! RESPONSE", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(Void... params) {
        findAvailableOperator();
        return null;
    }

}
