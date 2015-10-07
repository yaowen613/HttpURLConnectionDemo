package com.jay.example.httpurlconnectiondemo;

import com.jay.example.httpurlconnectiondemo.util.PostUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestPostActivity extends Activity implements OnClickListener {

	private final static String LOGIN_URL = "";
	private EditText editNum, editPawd;
	private Button btnLogin;
	private String result = "";

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(TestPostActivity.this, result, Toast.LENGTH_SHORT).show();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testpost);
		initView();
		setView();

	}

	private void initView() {
		editNum = (EditText) findViewById(R.id.editNum);
		editPawd = (EditText) findViewById(R.id.editPawd);
		btnLogin = (Button) findViewById(R.id.btnLogin);
	}

	private void setView() {
		btnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		new Thread() {
			public void run() {
				result = PostUtils.LoginByPost(editNum.getText().toString(), editPawd.getText().toString());
				handler.sendEmptyMessage(0x123);
			};
		}.start();
	}
}
