package com.ksy.media.demo.ui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksy.media.demo.R;

public abstract class IndicatorFragment extends Fragment implements OnPageChangeListener {

	protected int mCurrentTab = 0;
	protected int mLastTab = -1;

	// 存放选项卡信息的列表
	protected ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

	// viewpager adapter
	protected MyAdapter myAdapter = null;

	// viewpager
	protected ViewPager mPager;

	// 选项卡控件
	protected TitleIndicator mIndicator;

	public TitleIndicator getIndicator() {

		return mIndicator;
	}

	public class MyAdapter extends FragmentPagerAdapter {

		ArrayList<TabInfo> tabs = null;
		Context context = null;

		public MyAdapter(Context context, FragmentManager fm, ArrayList<TabInfo> tabs) {

			super(fm);
			this.tabs = tabs;
			this.context = context;
		}

		@Override
		public Fragment getItem(int pos) {

			Fragment fragment = null;
			if (tabs != null && pos < tabs.size()) {
				TabInfo tab = tabs.get(pos);
				if (tab == null)
					return null;
				if (tab.fragment != null) {
					fragment = tab.fragment;
				} else
					fragment = tab.createFragment();
			}
			return fragment;
		}

		@Override
		public int getCount() {

			if (tabs != null && tabs.size() > 0)
				return tabs.size();
			return 0;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.titled_fragment, container, false);
		mCurrentTab = supplyTabs(mTabs);
		myAdapter = new MyAdapter(getActivity(), getActivity().getSupportFragmentManager(), mTabs);

		mPager = (ViewPager) rootView.findViewById(R.id.pager);
		mPager.setAdapter(myAdapter);
		mPager.setOnPageChangeListener(this);
		mPager.setOffscreenPageLimit(mTabs.size());

		mIndicator = (TitleIndicator) rootView.findViewById(R.id.pagerindicator);
		mIndicator.init(mCurrentTab, mTabs, mPager);

		mPager.setCurrentItem(mCurrentTab);
		mLastTab = mCurrentTab;

		mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin_width));
		mPager.setPageMarginDrawable(R.color.page_viewer_margin_color);
		return rootView;
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		mIndicator.onScrolled((mPager.getWidth() + mPager.getPageMargin()) * position + positionOffsetPixels);
	}

	@Override
	public void onPageSelected(int position) {

		mIndicator.onSwitched(position);
		mCurrentTab = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {

		if (state == ViewPager.SCROLL_STATE_IDLE) {
			mLastTab = mCurrentTab;
		}
	}

	protected TabInfo getFragmentById(int tabId) {

		if (mTabs == null)
			return null;
		for (int index = 0, count = mTabs.size(); index < count; index++) {
			TabInfo tab = mTabs.get(index);
			if (tab.getId() == tabId) {
				return tab;
			}
		}
		return null;
	}

	/**
	 * 跳转到任意选项卡
	 * 
	 * @param tabId
	 *            选项卡下标
	 */
	public void navigate(int tabId) {

		for (int index = 0, count = mTabs.size(); index < count; index++) {
			if (mTabs.get(index).getId() == tabId) {
				mPager.setCurrentItem(index);
			}
		}
	}

	/**
	 * 在这里提供要显示的选项卡数据
	 */
	protected abstract int supplyTabs(List<TabInfo> tabs);

	@Override
	public void onSaveInstanceState(Bundle outState) {

		// for fix a known issue in support library
		// https://code.google.com/p/android/issues/detail?id=19917
		outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
		super.onSaveInstanceState(outState);
	}

	public static class TabInfo implements Parcelable {

		private int id;
		private int icon;
		private String name = null;
		public boolean hasTips = false;
		public Fragment fragment = null;
		public boolean notifyChange = false;
		@SuppressWarnings("rawtypes")
		public Class fragmentClass = null;

		@SuppressWarnings("rawtypes")
		public TabInfo(int id, String name, Class clazz) {

			this(id, name, 0, clazz);
		}

		@SuppressWarnings("rawtypes")
		public TabInfo(int id, String name, boolean hasTips, Class clazz) {

			this(id, name, 0, clazz);
			this.hasTips = hasTips;
		}

		@SuppressWarnings("rawtypes")
		public TabInfo(int id, String name, int iconid, Class clazz) {

			super();

			this.name = name;
			this.id = id;
			icon = iconid;
			fragmentClass = clazz;
		}

		public TabInfo(Parcel p) {

			this.id = p.readInt();
			this.name = p.readString();
			this.icon = p.readInt();
			this.notifyChange = p.readInt() == 1;
		}

		public int getId() {

			return id;
		}

		public void setId(int id) {

			this.id = id;
		}

		public String getName() {

			return name;
		}

		public void setName(String name) {

			this.name = name;
		}

		public void setIcon(int iconid) {

			icon = iconid;
		}

		public int getIcon() {

			return icon;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Fragment createFragment() {

			if (fragment == null) {
				Constructor constructor;
				try {
					constructor = fragmentClass.getConstructor(new Class[0]);
					fragment = (Fragment) constructor.newInstance(new Object[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return fragment;
		}

		public static final Parcelable.Creator<TabInfo> CREATOR = new Parcelable.Creator<TabInfo>() {

			@Override
			public TabInfo createFromParcel(Parcel p) {

				return new TabInfo(p);
			}

			@Override
			public TabInfo[] newArray(int size) {

				return new TabInfo[size];
			}
		};

		@Override
		public int describeContents() {

			return 0;
		}

		@Override
		public void writeToParcel(Parcel p, int flags) {

			p.writeInt(id);
			p.writeString(name);
			p.writeInt(icon);
			p.writeInt(notifyChange ? 1 : 0);
		}

	}
}
