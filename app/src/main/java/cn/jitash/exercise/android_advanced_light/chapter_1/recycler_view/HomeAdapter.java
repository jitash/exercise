package cn.jitash.exercise.android_advanced_light.chapter_1.recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.jitash.exercise.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View
        .OnClickListener, View.OnLongClickListener {
    private List<String> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v, (Integer) v.getTag());
        }
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        MyViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv_item);
        }
    }


}
