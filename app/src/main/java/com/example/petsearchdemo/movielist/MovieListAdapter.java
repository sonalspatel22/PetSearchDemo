package com.example.petsearchdemo.movielist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petsearchdemo.R;
import com.example.petsearchdemo.data.viewmodel.MovieViewModel;
import com.example.petsearchdemo.widget.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final Context mContext;
    private final List<MovieViewModel> mData;
    private OnItemClickListener mOnItemClickListener;

    MovieListAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.next.setOnClickListener((v) -> {
            if(viewHolder.getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }

            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClicked(viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieViewModel movieViewModel = mData.get(position);
        Picasso.with(mContext).load(movieViewModel.getPosterUrl()).fit().into(holder.mImageView);
        holder.name.setText(movieViewModel.getTitle());
        holder.description.setText(movieViewModel.getPlotSynopsis());
        holder.date.setText(formateBirthdayDate(movieViewModel.getReleaseDate()));
        String laung = movieViewModel.getLaungage();
        if(laung != null && laung.length() > 0) {
            holder.launguage.setText(movieViewModel.getLaungage());
        } else {
            holder.launguage.setText(mContext.getString(R.string.defaultLaunguge));
        }
        holder.rate.setText(String.valueOf(movieViewModel.getUserRating()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<MovieViewModel> getData() {
        // defensive copy
        return new ArrayList<>(mData);
    }

    public void setOnItemClickListener(MovieListActivity movieListActivity) {
        this.mOnItemClickListener = movieListActivity;
    }

    void addData(final List<MovieViewModel> data) {
        final int oldSize = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(oldSize, data.size());
    }

    void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView name;
        private TextView description;
        private TextView rate;
        private TextView launguage;
        private TextView date;
        private ImageView next;


        ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            rate = (TextView) itemView.findViewById(R.id.rate);
            launguage = (TextView) itemView.findViewById(R.id.launguage);
            date = (TextView) itemView.findViewById(R.id.date);
            next = (ImageView) itemView.findViewById(R.id.next);
        }
    }

    private String formateBirthdayDate(String date) {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        Date serverDate = null;
        try {
            serverDate = serverDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDateFormat.format(serverDate);
    }
}
