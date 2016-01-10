package com.zhanjixun.activity;


import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.views.MessageDialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

/**
 * 搜索页面
 * @author Imissyou
 *
 */
public class SearchActivity extends BackActivity implements OnDataReturnListener, OnKeyListener{
	
	private EditText searchET;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initViews();
		intent = new Intent(this,GoodListActivity.class);
		intent.putExtra("kind", GoodListActivity.SEARCH);
	}

	private void initViews() {
		searchET = (EditText) findViewById(R.id.searche_ET);
		/*设置焦点变化触发事件*/
		searchET.setOnKeyListener(this);
	}

	
	/*加载数据*/
	private void initData() {
		
		String search = searchET.getText().toString().trim();
		Log.v("miss====>Search", search + "misss");
		DC.getInstance().searchGoods(this, search);
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		if (result.getServiceResult()) {
			
			Log.v("result", result.getResultParam().toString() + "miss");
			
			if (taskTag.equals(TaskTag.SEARCH_GOOD)) {
				intent.putExtra("goodlist", result.getResultParam().get("categoryList").toString());	
				this.startActivity(intent);
			}
		} else {
			new MessageDialog(this, result.getResultParam().get("error"));
		}	
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == 66) {
			initData();
			return true;
		} else {
			return false;
		}	
	}

}
