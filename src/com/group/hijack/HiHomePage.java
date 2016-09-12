package com.group.hijack;

import java.util.ArrayList;

import android.R.layout;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group.fragments.*;
import com.group.utils.TopBar;
import com.group.utils.TopBar.OnBarClickListener;

public class HiHomePage extends FragmentActivity implements OnClickListener {

	private TopBar mTopbar;

	
	// 下面的控件显示栏
	private TextView text_persons;
	private TextView text_msg;
	private TextView text_group;
	private TextView texts[];

	private FragmentManager mFmanger;
	private ArrayList<Fragment> mLists;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub

		super.onCreate(arg0);
		setContentView(R.layout.activity_chat);

		initialViews();// 初始化控件。
	}

	/**
	 * 初始化各个控件
	 */
	private void initialViews() {
		mTopbar = (TopBar) findViewById(R.id.activity_topbar);
		texts = new TextView[3];
		texts[0] = (TextView) findViewById(R.id.text_chat_msg);
		texts[1] = (TextView) findViewById(R.id.text_chat_persons);
		texts[2] = (TextView) findViewById(R.id.text_chat_group);

		mTopbar.setOnBarClickListener(new OnBarClickListener() {
			
			@Override
			public void backClickListener() {
				mFmanger.popBackStack();
				//finish();回到桌面
			}
		});
		mTopbar.setTitle("消息");
		// 循环遍历菜单项（消息，联系人，群组），设置监听相关
		for (int i = 0; i < texts.length; i++) {
			texts[i].setTextColor(Color.BLACK);
			texts[i].setEnabled(true);// 初始化每个选项，让其可以被点击
			// 设置监听
			texts[i].setOnClickListener(this);
		}
		texts[0].setEnabled(false);// 默认显示第一个，初始时第一个不可被点击
		texts[0].setTextColor(Color.GREEN);// 被点到的菜单项设为绿色

		mFmanger = getSupportFragmentManager();// 获取到fragmentmanager
		FragmentTransaction transaction = mFmanger.beginTransaction();// 开启事物
		MessageFragment messageFragment = new MessageFragment();
		transaction.add(R.id.activity_fragment_layout, messageFragment);// 向容器内加入Fragment
		transaction.commit();// 提交事物

	}

	/**
	 * 跳转到哪个界面
	 */
	private void goTowhich(int index) {

		// 让菜单项都可被点击
		for (int i = 0; i < texts.length; i++) {
			texts[i].setEnabled(true);
			texts[i].setTextColor(Color.BLACK);
		}
		texts[index].setEnabled(false);// 让当前页面的菜单项不能被点击

		mFmanger = getSupportFragmentManager();// 获取到fragmentmanager
		FragmentTransaction transaction;

		switch (index) {
		case 0:
			mTopbar.setTitle("消息");
			texts[0].setTextColor(Color.GREEN);
			transaction = mFmanger.beginTransaction();// 开启事务
			MessageFragment messageFragment = new MessageFragment();
			transaction.add(R.id.activity_fragment_layout, messageFragment);// 向容器内加入Fragment
			transaction.addToBackStack("messageFragment");//加入回退栈
			transaction.commit();// 提交事物

			break;
		case 1:
			texts[1].setTextColor(Color.GREEN);
			mTopbar.setTitle("联系人");
			transaction = mFmanger.beginTransaction();// 开启事物

			PersonFragment personFragment = new PersonFragment();
			transaction.add(R.id.activity_fragment_layout, personFragment);// 向容器内加入Fragment
			transaction.addToBackStack("personFragment");//加入回退栈
			transaction.commit();// 提交事物
			break;
		case 2:
			texts[2].setTextColor(Color.GREEN);
			mTopbar.setTitle("群组");
			mFmanger = getSupportFragmentManager();// 获取到fragmentmanager
			transaction = mFmanger.beginTransaction();// 开启事物

			GroupFragment groupFragment = new GroupFragment();
			transaction.add(R.id.activity_fragment_layout, groupFragment);// 向容器内加入Fragment
			transaction.addToBackStack("groupFragment");//加入回退栈
			transaction.commit();// 提交事物
			break;

		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		int index = -1;
		switch (v.getId()) {
		case R.id.text_chat_msg:
			index = 0;
			break;
		case R.id.text_chat_persons:
			index = 1;
			break;
		case R.id.text_chat_group:
			index = 2;
			break;
		default:
			break;
		}
		goTowhich(index);

	}
}
