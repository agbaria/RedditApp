package tm.agbaria.reddit.reddit;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.*;

import java.util.ArrayList;

import tm.agbaria.reddit.RedditAdapter;

/**
 * Created by 3la2 on 24/08/2016.
 */
public class RedditService extends AsyncTask<String, Void, ArrayList<Reddit>> {
    private RecyclerView recyclerView;
    private Context context;
    private String[] beforeAfter;

    public RedditService(String[] beforeAfter, RecyclerView recyclerView, Context context) {
        this.beforeAfter = beforeAfter;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    protected ArrayList<Reddit> doInBackground(String... url) {
        ArrayList<Reddit> reddits = new ArrayList<>();
        try {
            System.out.println("URL: " + url[0]);
            String _json = HttpManager.downloadData(url[0]);
            JSONObject json = new JSONObject(_json);
            beforeAfter[1] = json.getJSONObject("data").getString("after");
            beforeAfter[0] = json.getJSONObject("data").getString("before");

            JSONArray children = json.getJSONObject("data").getJSONArray("children");
            for (int i = 0; i < children.length(); i++) {
                JSONObject data = children.getJSONObject(i).getJSONObject("data");
                Reddit reddit = jsonToReddit(data);
                reddits.add(reddit);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reddits;
    }

    private Reddit jsonToReddit(JSONObject data) throws JSONException {
        String author = data.getString("author");
        long created = data.getLong("created_utc");
        String url = data.getString("url");
        String title = data.getString("title");
        String thumbnail = data.getString("thumbnail");
        int score = data.getInt("score");
        int numComments = data.getInt("num_comments");
        String urlComments = data.getString("permalink");
        Reddit r = new Reddit(author, created, url, title, thumbnail, score, numComments, urlComments);
        return r;
    }

    @Override
    protected void onPostExecute(ArrayList<Reddit> reddits) {
        super.onPostExecute(reddits);
        RedditAdapter adapter = new RedditAdapter(reddits, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
