package tm.agbaria.reddit.reddit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 3la2 on 24/08/2016.
 */
public class HttpManager {
    public static String downloadData(String _url) throws Exception {
        URL url = new URL(_url);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder strBuilder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null)
            strBuilder.append(line);
        return strBuilder.toString();
    }
}
