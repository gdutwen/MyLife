package com.example.wen.mylife.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wen.mylife.DB.CommDB;
import com.example.wen.mylife.DB.DiaryDB;
import com.example.wen.mylife.MainActivity;
import com.example.wen.mylife.R;
import com.example.wen.mylife.adapter.DiaryRecyclerAdapter;

/**
 * Created by Administrator on 2017/2/7.
 */

public class DiaryFragment extends Fragment {

    private View view ;
    private ListView listView;
    private RecyclerView mRecyclerView;
    private CommDB commDB;
    private DiaryDB diaryDB;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diary,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commDB = new CommDB(getContext());
        diaryDB  = new DiaryDB(getContext());
        commDB.open();
        diaryDB.open();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new DiaryRecyclerAdapter(getContext()));
    }
}
