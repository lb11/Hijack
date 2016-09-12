package com.group.adapters;

import java.util.List;



import com.group.hijack.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MListAdapter extends BaseAdapter {

	private List<String> mList;

	private LayoutInflater inflater;

	public MListAdapter(List<String> list, Context context) {
		super();
		mList = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public String getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.chatroom_layout, parent,
					false);
			viewholder = new ViewHolder();
			viewholder.text = (TextView) convertView.findViewById(R.id.text_id);

			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}

		viewholder.text.setText(mList.get(position));

		return convertView;
	}

	public class ViewHolder {
		private TextView text;
	}

}
