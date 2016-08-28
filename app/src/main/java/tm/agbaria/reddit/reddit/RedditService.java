package tm.agbaria.reddit.reddit;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.*;

import java.util.ArrayList;

import tm.agbaria.reddit.RedditAdapter;

/**
 * Created by 3la2 on 24/08/2016.
 */
public class RedditService extends AsyncTask {
    private String category;
    private RecyclerView recyclerView;
    private Context context;

    public RedditService(String category, RecyclerView recyclerView, Context context) {
        this.category = category;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<Reddit> reddits = new ArrayList<>();
        try {
            String _json = HttpManager.downloadData("https://www.reddit.com/r/" + category + "/.json");
            JSONObject json = new JSONObject(_json);
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
        long created = data.getLong("created");
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
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        ArrayList<Reddit> reddits = (ArrayList<Reddit>) o;
        RedditAdapter adapter = new RedditAdapter(reddits, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
