package tm.agbaria.reddit;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tm.agbaria.reddit.reddit.RedditService;


/**
 * A simple {@link Fragment} subclass.
 */
public class RedditFragment extends Fragment {
    private Context context;
    private Button prevBtn;
    private Button nextBtn;
    private RecyclerView recyclerView;
    private String category;
    private String[] beforeAfter;
    private int first;
    private int last;

    public static RedditFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("category", category);
        RedditFragment fragment = new RedditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle args = getArguments();
        this.category = args.getString("category");
        this.context = getContext();
        this.beforeAfter = new String[2];
        View v = inflater.inflate(R.layout.fragment_reddit, container, false);
        findViews(v);
        changeCategory(category);
        return v;
    }

    private void findViews(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.RedditRecycler);
        prevBtn = (Button) v.findViewById(R.id.btnPrev);
        nextBtn = (Button) v.findViewById(R.id.btnNext);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(beforeAfter[0].compareTo("null") != 0) {
                    int tmp = first;
                    first -= 25;
                    last -= 25;
                    String url = "https://www.reddit.com/r/" + category + "/.json?count=" + tmp +
                            "&before=" + beforeAfter[0];
                    new RedditService(beforeAfter, recyclerView, context).execute(url);
                    if(tmp == 26) prevBtn.setEnabled(false);
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (beforeAfter[1].compareTo("null") != 0) {
                    int tmp = last;
                    first += 25;
                    last += 25;
                    String url = "https://www.reddit.com/r/" + category + "/.json?count=" + tmp +
                            "&after=" + beforeAfter[1];
                    new RedditService(beforeAfter, recyclerView, context).execute(url);
                    prevBtn.setEnabled(true);
                }
            }
        });
        prevBtn.setEnabled(false);
    }

    public void changeCategory(String category) {
        this.category = category;
        first = 1;
        last = 25;
        new RedditService(beforeAfter, recyclerView, context).
                execute("https://www.reddit.com/r/" + category + "/.json");
        prevBtn.setEnabled(false);
    }
}
