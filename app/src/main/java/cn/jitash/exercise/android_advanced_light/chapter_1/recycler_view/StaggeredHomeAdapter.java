package cn.jitash.exercise.android_advanced_light.chapter_1.recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.jitash.exercise.R;

public class StaggeredHomeAdapter extends RecyclerView.Adapter<StaggeredHomeAdapter.MyViewHolder> {
    private List<String> mList;
    private LayoutInflater mInflater;
    private List<Integer> mHeight;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_recycler, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeight.get(position);
        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mList.get(position));
        if (mOnItemClickListener != null) {
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public interface onItemClickListener {
        void onItemClick(View v, int pos);

        void onItemLongClick(View v, int pos);
    }

    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public StaggeredHomeAdapter(Context mContext, List<String> list) {
        mInflater = LayoutInflater.from(mContext);
        mList = list;
        mHeight = new ArrayList<Integer>();
        for (int i = 0; i < mList.size(); i++) {
            mHeight.add((int) (100 + Math.random() * 3000));
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View v) {
            super(v);
            tv = v.findViewById(R.id.tv_item);
        }
    }
}
