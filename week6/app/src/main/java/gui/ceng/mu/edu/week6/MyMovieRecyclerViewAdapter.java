package gui.ceng.mu.edu.week6;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {
    private final List<Movie> mValues;
    private final MovieFragment.OnMovieSelected mListener;
    int selectedIndex = -1;

    public MyMovieRecyclerViewAdapter(List<Movie> items, MovieFragment.OnMovieSelected listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.movieSelected(holder.mItem);
                notifyItemChanged(selectedIndex);
                selectedIndex = holder.getLayoutPosition();
                notifyItemChanged(selectedIndex);
            }
        });

        holder.itemView.setBackgroundColor(selectedIndex == position ? Color.GREEN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mContentView = view.findViewById(R.id.content);
        }
    }
}
