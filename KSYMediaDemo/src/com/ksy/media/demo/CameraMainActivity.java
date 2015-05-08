package com.ksy.media.demo;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class CameraMainActivity extends FragmentActivity {

	private FragmentManager mFragmentManager;
	private final String[] urls = new String[] {
			// "rtmp://192.168.135.185/myLive/guoli1234",
			// "rtmp://192.168.135.185/myLive/drm1",
			"rtmp://192.168.135.185/myLive/drm",
			// "rtsp://10.0.2.11:8086",
			// "http://192.168.43.42:8080",
			// "http://live.3gv.ifeng.com/zixun.m3u8",
			// "http://115.231.96.85:9000/hls/test1",
			// new File(Environment.getExternalStorageDirectory(),
			// "a.mp4").getPath(),

			// "rtmp://192.168.135.185:1935/myLive/tangluo"

	};

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		mFragmentManager = getSupportFragmentManager();

		// String auth;
		// try {
		// auth =
		// AuthUtils.calAuthorizationForDRM("ilZQ9p/NHAK1dOYA/dTKKeIqT/t67rO6V2PrXUNr",
		// "1710333224", "4e1f2519c626cbfbab1520c255830c26");
		// Log.e("guoli", "auth :" + auth);
		// } catch (SignatureException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

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
