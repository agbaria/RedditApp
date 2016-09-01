package tm.agbaria.reddit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tm.agbaria.reddit.reddit.Reddit;

/**
 * Created by 3la2 on 28/08/2016.
 */
public class Utils {

    public static void attachReddit(RedditAdapter.RedditViewAdapter holder, Reddit reddit) {
        holder.tvAuthor.setText(reddit.getAuthor());
        holder.tvCreated.setText(created(reddit.getCreated()));
        holder.tvTitle.setText(reddit.getTitle());
        setIcon(holder.ivIcon, reddit.getThumbnail());
        holder.tvScore.setText(Integer.toString(reddit.getScore()));
        holder.tvComments.setText(Integer.toString(reddit.getNumComments()));
    }

    private static void setIcon(ImageView view, final String _url) {
        try {
            Picasso.with(view.getContext()).load(_url).resize(50, 50).into(view);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String created(long created) {
        Date date = new Date(created * 1000L); // Why (* 1000L) ?? :/
        Calendar topicDate = Calendar.getInstance();
        topicDate.setTime(date); // Topic's date
        Calendar today = Calendar.getInstance(); // Today's Date

        long topicMonths = 12 * topicDate.get(Calendar.YEAR) + topicDate.get(Calendar.MONTH) + 1; //months passed till the topic posted
        long todayMonths = 12 * today.get(Calendar.YEAR) + today.get(Calendar.MONTH) + 1; //months passed till today
        long monthsDiff = todayMonths - topicMonths;
        if(monthsDiff > 0) {
            if(monthsDiff / 12 > 0)
                return (monthsDiff / 12) + " years";
            else
                return monthsDiff + " months";
        }
        // less than a month
        int topicHours = 24 * (topicDate.get(Calendar.DAY_OF_MONTH) - 1) + topicDate.get(Calendar.HOUR_OF_DAY);
        int todayHours = 24 * (today.get(Calendar.DAY_OF_MONTH) - 1) + today.get(Calendar.HOUR_OF_DAY);
        int hoursDiff = todayHours - topicHours;
        if(hoursDiff > 0) {
            if(hoursDiff / 24 > 0)
                return (hoursDiff / 24) + " days";
            else
                return hoursDiff + " hours";
        }
        // less than an hour (no need to be so accurate)
        if(today.get(Calendar.MINUTE) > topicDate.get(Calendar.MINUTE))
            return (today.get(Calendar.MINUTE) - topicDate.get(Calendar.MINUTE)) + " minutes";
        else return (today.get(Calendar.SECOND) - topicDate.get(Calendar.SECOND)) + " seconds";
    }
}
