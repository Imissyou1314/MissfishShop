package com.zhanjixun.activity;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain2.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.views.MessageDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
public class SearchActivity extends BackActivity implements OnDataReturnListener{
	
	private EditText searchET;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initViews();
	}

	private void initViews() {
		searchET = (EditText) findViewById(R.id.searche_ET);
		/*设置焦点变化触发事件*/
		searchET.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == 66) {
					searchOrders();
					return true;
				} else {
					return false;
				}
				
			}
		});
		
	}

	private void searchOrders() {
			String search = searchET.getText().toString();
			Log.v("miss==========>search", search + "miss");
			initData(search);
	}
	
	private void initData(String search) {
//		DC.getInstance().searchGoods(this, search);  TODO
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		if (result.getServiceResult()) {
			Intent intent = new Intent(this,GoodListActivity.class);
			intent.putExtra("kind", GoodListActivity.SEARCH_SERVICE);
			intent.putExtra("goodlist", result.getResultParam().toString());
			//TODO  传过去GoodList
		} else {
			new MessageDialog(this, result.getResultParam().get("error"));
		}
		
	}

}
