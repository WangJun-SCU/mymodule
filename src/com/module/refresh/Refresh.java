package com.module.refresh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.module.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Refresh extends Activity implements SwipeRefreshLayout.OnRefreshListener{
	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private ListView mListView;
	private ArrayAdapter<String> mAdapter;
	private List<String> mDatas = new ArrayList<String>(Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json",
			"HTML"));

	private Handler mHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case REFRESH_COMPLETE:
				mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
				mAdapter.notifyDataSetChanged();
				mSwipeLayout.setRefreshing(false);
				break;
			}
		};
	};

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.refresh);
		
		ActionBar ac = getActionBar();
		ac.setDisplayHomeAsUpEnabled(true);

		mListView = (ListView) findViewById(R.id.id_listview);
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);

		mSwipeLayout.setOnRefreshListener(this);
		mSwipeLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
		mListView.setAdapter(mAdapter);

	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	public void onRefresh()
	{
		// Log.e("xxx", Thread.currentThread().getName());
		// UI Thread

		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

	}
}
