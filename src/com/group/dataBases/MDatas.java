package com.group.dataBases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 在每个类里都定义一些String的常量，用于作为查询操作的条件。
 * 
 * @author Administrator
 * 
 */
public class MDatas implements AbstractDB {
	private MyOpenHelper openHelper;

	private String table_name = "tabledatas";

	// 表单创建的语句。
	public static String create_table_chathistory = "create table tabledatas("//
			
			+ "number varchar(20) not null primary key,"// 编号
			+ "whichgroup varchar(20) not null"// 所在的分组。
			+ ");";

	/**
	 * 限制条件的集合。
	 * 
	 */
	public static class TableDatasLimits {
		public static final String WhRER_NUMBER = " number = ? ";
		public static final String WhRER_WHICHGROUP = " whichgroup = ? ";

		public static final String CON_AND = "and";
	}

	private static MDatas mInstance = null;

	public static MDatas getInstance(Context context, String name) {
		if (mInstance == null) {
			mInstance = new MDatas(context, name);
		}

		return mInstance;
	}

	private MDatas(Context context, String name) {
		openHelper = new MyOpenHelper(context, name);
	}

	@Override
	public void add(Object[] objects) {
		SQLiteDatabase date = openHelper.getWritableDatabase();
		try {
			String sql = "insert into " + table_name
					+ "(group,who,message,time)values(?,?,?,?)";
			date.execSQL(sql, objects);
		} catch (Exception e) {
			Log.i("MSGERROR", "ERROR _liubenm ");
		} finally {
			if (date != null)
				date.close();
		}
	}

	@Override
	public void delete(String where, Object[] objects) {
		SQLiteDatabase date = openHelper.getWritableDatabase();
		try {
			String sql = "delete from" + table_name + " where " + where;
			date.execSQL(sql, objects);
		} catch (Exception e) {
		}
		if (date != null)
			date.close();
	}

	@Override
	public List<Map<String, String>> quere(String where, String[] objects) {
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		SQLiteDatabase date = openHelper.getReadableDatabase();
		String sql;
		if (where == null) {
			sql = "select * from " + table_name;
		} else {
			sql = "select * from " + table_name + " where " + where;
		}
		Cursor cursor = date.rawQuery(sql, objects);
		int n = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			Map<String, String> data = new HashMap<String, String>();
			for (int i = 0; i < n; i++) {
				String name = cursor.getColumnName(i);
				String value = cursor.getString(cursor.getColumnIndex(name));
				if (value == null) {
					value = "";
				}
				data.put(name, value);
			}
			lists.add(data);
		}
		if (date != null)
			date.close();
		return lists;
	}
}
