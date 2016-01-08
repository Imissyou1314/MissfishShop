package com.zhanjixun.activity;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class SearchActivity extends BackActivity implements OnFocusChangeListener{
	
	private RelativeLayout searchRL;
	private EditText searchET;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initViews();
	}

	private void initViews() {
		searchET = (EditText) findViewById(R.id.searche_ET);
		searchRL = (RelativeLayout) findViewById(R.id.search_Relative);
		/*设置焦点变化触发事件*/
		searchRL.setOnFocusChangeListener(this);
		searchET.setOnFocusChangeListener(this);
		searchET.setFocusable(true);
		
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		
			if (hasFocus) {
				searchOrders();
			} else {
				searchOrders();
			}
	}
	private void searchOrders() {
			String search = searchET.getText().toString();
			Log.v("miss==========>search", search + "miss");
	}

}
