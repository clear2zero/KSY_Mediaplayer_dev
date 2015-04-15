package com.ksy.media.demo;

import java.io.File;
import java.util.Random;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class CameraMainActivity extends FragmentActivity {

	private FragmentManager mFragmentManager;
	private final String[] urls = new String[] {
			// "rtmp://192.168.135.185/myLive/guoli1234",
			// "rtmp://192.168.135.185/myLive/drm",
			// "http://live.3gv.ifeng.com/zixun.m3u8",
			// "http://115.231.96.85:9000/hls/test1",
			new File(Environment.getExternalStorageDirectory(), "a.mp4").getPath()
	};

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		mFragmentManager = getSupportFragmentManager();
	}

	public void hideFragment(Fragment fragment) {

		if (fragment != null) {
			mFragmentManager.beginTransaction().hide(fragment).commit();
		}
	}

	public void hideFragment(int fragmentId) {

		Fragment fragment = mFragmentManager.findFragmentById(fragmentId);
		if (fragment != null) {
			mFragmentManager.beginTransaction().hide(fragment).commit();
		}
	}

	public void showFragment(Fragment fragment) {

		if (fragment != null) {
			mFragmentManager.beginTransaction().show(fragment).commit();
		}
	}

	public void showFragment(int fragmentId) {

		Fragment fragment = mFragmentManager.findFragmentById(fragmentId);
		if (fragment != null) {
			mFragmentManager.beginTransaction().show(fragment).commit();
		}
	}

	public String randomURL() {

		Random random = new Random();
		return urls[random.nextInt(urls.length)];
	}

}
