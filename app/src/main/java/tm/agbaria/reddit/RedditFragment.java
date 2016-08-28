package tm.agbaria.reddit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tm.agbaria.reddit.reddit.RedditService;


/**
 * A simple {@link Fragment} subclass.
 */
public class RedditFragment extends Fragment {

    public static RedditFragment newInsrance(String category) {
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
        String category = args.getString("category");

        View v = inflater.inflate(R.layout.fragment_reddit, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.RedditRecycler);
        new RedditService(category, recyclerView, getContext()).execute();
        return v;
    }

}
