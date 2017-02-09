package com.example.wen.mylife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.wen.mylife.R;

/**
 * Created by Administrator on 2017/2/9.
 */

public class DiaryRecyclerAdapter extends RecyclerView.Adapter<DiaryRecyclerAdapter.DiaryViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;

    public DiaryRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        inflater=LayoutInflater. from(mContext);
    }

    @Override
    public DiaryRecyclerAdapter.DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_diary_item,parent, false);
        DiaryViewHolder viewHolder = new DiaryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DiaryRecyclerAdapter.DiaryViewHolder holder, int position) {
        holder.title.setText("美文");
        holder.account.setText("浏览数：4000000");
        holder.content.setText("故事，启迪你的人生；美文，陶冶你的情操，有声朗读，洗礼你的耳朵……");
        holder.time.setText("2017年2月7日 20:08");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class DiaryViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView account;
        private TextView content;
        private TextView time;
        public DiaryViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_diary_title);
            account = (TextView) view.findViewById(R.id.tv_diary_account);
            content = (TextView) view.findViewById(R.id.tv_diary_content);
            time = (TextView) view.findViewById(R.id.tv_diary_time);
        }
    }
}
