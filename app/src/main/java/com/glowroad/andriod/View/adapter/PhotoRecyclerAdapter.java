package com.glowroad.andriod.View.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glowroad.andriod.R;
import com.glowroad.andriod.model.PhotoItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.ViewHolder> {
    private static final int VIEW_TYPE_LIST_ITEM = 0;
    public static final int VIEW_TYPE_MORE_LOADING = 1;

    private List<PhotoItem> mPhotosList;
    private int mLastRequestPosition;
    private LoadMoreListener mLoadMoreListener;

    public PhotoRecyclerAdapter(List<PhotoItem> photosList, LoadMoreListener loadMoreListener) {
        mPhotosList = photosList;
        mLoadMoreListener = loadMoreListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LIST_ITEM) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_recycler_layout_item, parent, false));
        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.more_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoItem item = mPhotosList.get(position);

        switch (item.getViewType()) {
            case VIEW_TYPE_LIST_ITEM:
                holder.title.setText(item.getTitle());
                Picasso.get().load(item.getImageUrl())
                        .resize(item.getImageWidth(), item.getImageHeight())
                        .error(R.color.colorPrimaryDark)
                        .placeholder(R.color.colorPrimaryDark)
                        .into(holder.image);
                break;
            case VIEW_TYPE_MORE_LOADING:
                if (mLastRequestPosition != position) {
                    mLoadMoreListener.onLoadMore();
                    mLastRequestPosition = position;
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mPhotosList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mPhotosList.get(position).getViewType();
    }

    public void updateData(List<PhotoItem> photoList) {
        int lastPosition = mPhotosList.size();
        mPhotosList = photoList;
        notifyItemRangeChanged(lastPosition - 1, mPhotosList.size());

    }

     class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}

