package cn.jitash.exercise.android_advanced_light.chapter_1.recycler_view;

import android.content.DialogInterface;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jitash.exercise.R;

public class MyRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private StaggeredHomeAdapter mStaggeredHomeAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);

        initData();
        mRecyclerView = findViewById(R.id.recycler_view);

//        setListView();
//        setGridView();

        setStaggerHomeAdapter();
        mStaggeredHomeAdapter = new StaggeredHomeAdapter(MyRecyclerViewActivity.this,mList);

        mAdapter = new HomeAdapter(mList, this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        setClickListener();
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
//        mRecyclerView.setAdapter(mAdapter);
    }

    private void setStaggerHomeAdapter() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }

    private void setGridView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }

    private void setListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }

    private void setClickListener() {
        mAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MyRecyclerViewActivity.this, "点击第" + position + "个", Toast
                        .LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View v, final int position) {
                new AlertDialog.Builder(MyRecyclerViewActivity.this).setTitle("确认删除么")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAdapter.removeData(position);
                            }
                        }).show();

            }
        });
    }

    private void initData() {
        mList = new ArrayList<String>();
        for (int i = 1; i < 20; i++) {
            mList.add(i + "");
        }
    }
}
