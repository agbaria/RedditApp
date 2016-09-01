package tm.agbaria.reddit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tm.agbaria.reddit.reddit.Reddit;

/**
 * Created by 3la2 on 24/08/2016.
 */
public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.RedditViewAdapter>{
    private final LayoutInflater inflater;
    private ArrayList<Reddit> reddits;

    public RedditAdapter(ArrayList<Reddit> reddits, Context context) {
        this.reddits = reddits;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RedditViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.reddit_item, parent, false);
        return new RedditViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(RedditViewAdapter holder, int position) {
        Reddit reddit = reddits.get(position);
        Utils.attachReddit(holder, reddit);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return reddits.size();
    }

    class RedditViewAdapter extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
        TextView tvCreated;
        TextView tvAuthor;
        TextView tvComments;
        TextView tvScore;

        public RedditViewAdapter(View v) {
            super(v);
            ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvCreated = (TextView) v.findViewById(R.id.tvCreated);
            tvAuthor = (TextView) v.findViewById(R.id.tvAuthor);
            tvComments = (TextView) v.findViewById(R.id.tvComments);
            tvScore = (TextView) v.findViewById(R.id.tvScore);
        }
    }
}
