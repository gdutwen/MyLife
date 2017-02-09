package com.example.wen.mylife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wen.mylife.R;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ListDiaryAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    public ListDiaryAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_diary_item, null);

            viewHolder.title = (TextView) view.findViewById(R.id.tv_diary_title);
            viewHolder.account = (TextView) view.findViewById(R.id.tv_diary_account);
            viewHolder.content = (TextView) view.findViewById(R.id.tv_diary_content);
            viewHolder.time = (TextView) view.findViewById(R.id.tv_diary_time);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText("美文");
        viewHolder.account.setText("浏览数：4000000");
        viewHolder.content.setText("故事，启迪你的人生；美文，陶冶你的情操，有声朗读，洗礼你的耳朵……");
        viewHolder.time.setText("2017年2月7日 20:08");

        return view;

    }
    public class ViewHolder{
        private TextView title;
        private TextView account;
        private TextView content;
        private TextView time;
    }
}
