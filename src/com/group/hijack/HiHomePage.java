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

	
	// ����Ŀؼ���ʾ��
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

		initialViews();// ��ʼ���ؼ���
	}

	/**
	 * ��ʼ�������ؼ�
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
				//finish();�ص�����
			}
		});
		mTopbar.setTitle("��Ϣ");
		// ѭ�������˵����Ϣ����ϵ�ˣ�Ⱥ�飩�����ü������
		for (int i = 0; i < texts.length; i++) {
			texts[i].setTextColor(Color.BLACK);
			texts[i].setEnabled(true);// ��ʼ��ÿ��ѡ�������Ա����
			// ���ü���
			texts[i].setOnClickListener(this);
		}
		texts[0].setEnabled(false);// Ĭ����ʾ��һ������ʼʱ��һ�����ɱ����
		texts[0].setTextColor(Color.GREEN);// ���㵽�Ĳ˵�����Ϊ��ɫ

		mFmanger = getSupportFragmentManager();// ��ȡ��fragmentmanager
		FragmentTransaction transaction = mFmanger.beginTransaction();// ��������
		MessageFragment messageFragment = new MessageFragment();
		transaction.add(R.id.activity_fragment_layout, messageFragment);// �������ڼ���Fragment
		transaction.commit();// �ύ����

	}

	/**
	 * ��ת���ĸ�����
	 */
	private void goTowhich(int index) {

		// �ò˵���ɱ����
		for (int i = 0; i < texts.length; i++) {
			texts[i].setEnabled(true);
			texts[i].setTextColor(Color.BLACK);
		}
		texts[index].setEnabled(false);// �õ�ǰҳ��Ĳ˵���ܱ����

		mFmanger = getSupportFragmentManager();// ��ȡ��fragmentmanager
		FragmentTransaction transaction;

		switch (index) {
		case 0:
			mTopbar.setTitle("��Ϣ");
			texts[0].setTextColor(Color.GREEN);
			transaction = mFmanger.beginTransaction();// ��������
			MessageFragment messageFragment = new MessageFragment();
			transaction.add(R.id.activity_fragment_layout, messageFragment);// �������ڼ���Fragment
			transaction.addToBackStack("messageFragment");//�������ջ
			transaction.commit();// �ύ����

			break;
		case 1:
			texts[1].setTextColor(Color.GREEN);
			mTopbar.setTitle("��ϵ��");
			transaction = mFmanger.beginTransaction();// ��������

			PersonFragment personFragment = new PersonFragment();
			transaction.add(R.id.activity_fragment_layout, personFragment);// �������ڼ���Fragment
			transaction.addToBackStack("personFragment");//�������ջ
			transaction.commit();// �ύ����
			break;
		case 2:
			texts[2].setTextColor(Color.GREEN);
			mTopbar.setTitle("Ⱥ��");
			mFmanger = getSupportFragmentManager();// ��ȡ��fragmentmanager
			transaction = mFmanger.beginTransaction();// ��������

			GroupFragment groupFragment = new GroupFragment();
			transaction.add(R.id.activity_fragment_layout, groupFragment);// �������ڼ���Fragment
			transaction.addToBackStack("groupFragment");//�������ջ
			transaction.commit();// �ύ����
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
