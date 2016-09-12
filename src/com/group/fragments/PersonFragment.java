package com.group.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group.fragments.MessageFragment.MyAdapter;
import com.group.fragments.MessageFragment.MyAdapter.viewHolder;
import com.group.hijack.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 联系人 Fragment
 * 
 * @author wangcong
 */
public class PersonFragment extends Fragment {

	List<Map<String, Object>> list = null;// 把数据先放到map集合中，在放到list集合中
	ListView listView;
	MyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_person, container, false);

		listView = (ListView) view.findViewById(R.id.listview_fragment_person);// view才有findviewby的权利,不能直接listView=findviewbyid().
		list = new ArrayList<Map<String, Object>>();

		/**
		 * 测试用的数据，可以从网上下载数据，初始化QQ时，请删除下面的for循环
		 */
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();// 接口的实现类,hashmap,键值对应
			map.put("name", "张三");
			map.put("note", "风起于青萍之末");
			map.put("img", R.drawable.qq_portrait__1);// 3个数据放在一个map里，map又放在list里
			list.add(map);
		}

		// 给lisView设置适配器
		adapter = new MyAdapter(list, getActivity());// context用getActivity()获取
		listView.setAdapter(adapter);

		// 设置监听
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 得到点击的item的放有数据的map集合。以备接下来的操作
				Map<String, Object> map = (Map<String, Object>) adapter.getItem(position);
			}
		});

		return view;
	}

	/**
	 * 适配器
	 * 
	 * @author wangcong
	 */
	class MyAdapter extends BaseAdapter {

		List<Map<String, Object>> list = null;
		private LayoutInflater inflater;

		/**
		 * 
		 * @param list
		 *            存放Map数据集合的list
		 * @param context
		 *            上下文对象
		 */
		public MyAdapter(List<Map<String, Object>> list, Context context) {
			super();
			this.list = list;
			inflater = LayoutInflater.from(context);
		}

		// 得到有多少条item
		@Override
		public int getCount() {
			return list.size();
		}

		// 得到一条数据
		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		// 得到id 下标
		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		// 得到视图
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			viewHolder holder = null;

			// 先判断convertView是否为空
			if (convertView == null) {
				// 视图加载者去加载，但还不够完善
				// 给item页面去findviewbyid（），注意是两个页面。前面要加convertView.findViewById(R.id.item_name);
				// 第一次加载convertView页面时findviewbyid，第二次没有必要再 findviewbyid()
				convertView = inflater.inflate(R.layout.item_person, parent, false);
				holder = new viewHolder(convertView);
				holder.name = (TextView) convertView.findViewById(R.id.item_person_name);
				holder.note = (TextView) convertView.findViewById(R.id.item_person_note);
				holder.imageView = (ImageView) convertView.findViewById(R.id.item_person_portrait);
				// 加载个标签
				convertView.setTag(holder);
			} else {
				// 已经有了convertView，不用再重新赋值
				holder = (viewHolder) convertView.getTag();// 获得这个标签
			}

			// 通过list设置UI
			holder.note.setText(list.get(position).get("note").toString());
			holder.name.setText(list.get(position).get("name").toString());
			holder.imageView.setImageResource(Integer.valueOf(list.get(position).get("img").toString()));

			return convertView;// 返回到UI
		}

		class viewHolder {
			private TextView name, note;
			private ImageView imageView;
			View view;

			public viewHolder(View view) {
				this.view = view;
				name = (TextView) view.findViewById(R.id.item_person_name);
				note = (TextView) view.findViewById(R.id.item_person_note);

				imageView = (ImageView) view.findViewById(R.id.item_person_portrait);

			}

		}

	}

}
