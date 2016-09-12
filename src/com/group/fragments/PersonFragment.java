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
 * ��ϵ�� Fragment
 * 
 * @author wangcong
 */
public class PersonFragment extends Fragment {

	List<Map<String, Object>> list = null;// �������ȷŵ�map�����У��ڷŵ�list������
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

		listView = (ListView) view.findViewById(R.id.listview_fragment_person);// view����findviewby��Ȩ��,����ֱ��listView=findviewbyid().
		list = new ArrayList<Map<String, Object>>();

		/**
		 * �����õ����ݣ����Դ������������ݣ���ʼ��QQʱ����ɾ�������forѭ��
		 */
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();// �ӿڵ�ʵ����,hashmap,��ֵ��Ӧ
			map.put("name", "����");
			map.put("note", "��������Ƽ֮ĩ");
			map.put("img", R.drawable.qq_portrait__1);// 3�����ݷ���һ��map�map�ַ���list��
			list.add(map);
		}

		// ��lisView����������
		adapter = new MyAdapter(list, getActivity());// context��getActivity()��ȡ
		listView.setAdapter(adapter);

		// ���ü���
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// �õ������item�ķ������ݵ�map���ϡ��Ա��������Ĳ���
				Map<String, Object> map = (Map<String, Object>) adapter.getItem(position);
			}
		});

		return view;
	}

	/**
	 * ������
	 * 
	 * @author wangcong
	 */
	class MyAdapter extends BaseAdapter {

		List<Map<String, Object>> list = null;
		private LayoutInflater inflater;

		/**
		 * 
		 * @param list
		 *            ���Map���ݼ��ϵ�list
		 * @param context
		 *            �����Ķ���
		 */
		public MyAdapter(List<Map<String, Object>> list, Context context) {
			super();
			this.list = list;
			inflater = LayoutInflater.from(context);
		}

		// �õ��ж�����item
		@Override
		public int getCount() {
			return list.size();
		}

		// �õ�һ������
		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		// �õ�id �±�
		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		// �õ���ͼ
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			viewHolder holder = null;

			// ���ж�convertView�Ƿ�Ϊ��
			if (convertView == null) {
				// ��ͼ������ȥ���أ�������������
				// ��itemҳ��ȥfindviewbyid������ע��������ҳ�档ǰ��Ҫ��convertView.findViewById(R.id.item_name);
				// ��һ�μ���convertViewҳ��ʱfindviewbyid���ڶ���û�б�Ҫ�� findviewbyid()
				convertView = inflater.inflate(R.layout.item_person, parent, false);
				holder = new viewHolder(convertView);
				holder.name = (TextView) convertView.findViewById(R.id.item_person_name);
				holder.note = (TextView) convertView.findViewById(R.id.item_person_note);
				holder.imageView = (ImageView) convertView.findViewById(R.id.item_person_portrait);
				// ���ظ���ǩ
				convertView.setTag(holder);
			} else {
				// �Ѿ�����convertView�����������¸�ֵ
				holder = (viewHolder) convertView.getTag();// ��������ǩ
			}

			// ͨ��list����UI
			holder.note.setText(list.get(position).get("note").toString());
			holder.name.setText(list.get(position).get("name").toString());
			holder.imageView.setImageResource(Integer.valueOf(list.get(position).get("img").toString()));

			return convertView;// ���ص�UI
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
