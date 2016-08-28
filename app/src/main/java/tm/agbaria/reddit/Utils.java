package tm.agbaria.reddit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.MalformedURLException;
import java.net.URL;

import tm.agbaria.reddit.reddit.Reddit;

/**
 * Created by 3la2 on 28/08/2016.
 */
public class Utils {

    /*
    attach the following data:
    String author;
    long created;
    String title;
    String thumbnail;
    int score;
    int numComments;
     */

    public static void attachReddit(RedditAdapter.RedditViewAdapter holder, Reddit reddit, Context context) {
        holder.tvAuthor.setText(reddit.getAuthor());
        holder.tvCreated.setText(created(reddit.getCreated()));
        holder.tvTitle.setText(reddit.getTitle());
        holder.ivIcon.setImageBitmap(getIcon(reddit.getThumbnail(), context));
        holder.tvScore.setText(reddit.getScore());
        holder.tvComments.setText(reddit.getNumComments());
    }

    private static Bitmap getIcon(String _url, Context context) {
        Bitmap bitmap;
        try {
            URL url = new URL(_url);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.reddit);
        }
        return bitmap;
    }

    private static String created(long created) {
        return new Long(created).toString();
        //TODO: convert the unix time to a readable time
    }
}
