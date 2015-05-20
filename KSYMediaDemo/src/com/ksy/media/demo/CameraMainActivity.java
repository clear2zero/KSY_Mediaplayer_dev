package com.ksy.media.demo;

import java.security.SignatureException;
import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.ksy.media.player.util.AuthUtils;

public class CameraMainActivity extends FragmentActivity {

	private FragmentManager mFragmentManager;
	private final String[] urls = new String[] {
			// "rtmp://192.168.135.185/myLive/guoli1234",
			// "rtmp://192.168.135.185/myLive/drm1",
			// "rtmp://192.168.135.185/myLive/drm",
			// "rtmp://192.168.135.185/myLive/drm3",
			// "rtmp://101.71.27.121/live/drm"
			// "rtsp://10.0.2.11:8086",
			// "http://192.168.43.42:8080",
			// "http://live.3gv.ifeng.com/zixun.m3u8",
			// "http://115.231.96.85:9000/hls/test1",
			// "http://192.168.135.185:8080/hls/2.mp4",
			// "http://www.modrails.com/videos/passenger_nginx.mov",
			// new File(Environment.getExternalStorageDirectory(),
			// "log.flv").getPath(),
			// new File(Environment.getExternalStorageDirectory(),
			// "test.flv").getPath(),
			// "rtmp://192.168.135.185:1935/myLive/tangluo"

			};

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		mFragmentManager = getSupportFragmentManager();

		String auth;
		try {
			auth = AuthUtils.calAuthorizationForDRM("ilZQ9p/NHAK1dOYA/dTKKeIqT/t67rO6V2PrXUNr", "1431093746", "1431093746");
			Log.e("guoli", "auth :" + auth);
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
