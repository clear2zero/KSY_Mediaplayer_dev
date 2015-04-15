package com.ksy.media.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksy.media.player.util.Constants;
import com.ksy.media.widget.MediaPlayerView;

public class MediaPlayerFragment extends Fragment implements MediaPlayerView.PlayerViewCallback {

	private MediaPlayerView mPlayerView;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.player_fragment, container, false);
		mPlayerView = (MediaPlayerView) rootView.findViewById(R.id.player_view);
		startPlayer();
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	private void startPlayer() {

		Log.i(Constants.LOG_TAG, "fragment start");
		mPlayerView.setPlayerViewCallback(this);
		mPlayerView.play(((CameraMainActivity) getActivity()).randomURL());
	}

	@Override
	public void onResume() {

		super.onResume();
		mPlayerView.onResume();
	}

	@Override
	public void onPause() {

		super.onPause();
		mPlayerView.onPause();
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
		mPlayerView.onDestroy();
	}

	@Override
	public void hideViews() {

		CameraMainActivity activity = (CameraMainActivity) getActivity();
		activity.hideFragment(R.id.detail_fragment);
	}

	@Override
	public void restoreViews() {

		CameraMainActivity activity = (CameraMainActivity) getActivity();
		activity.showFragment(R.id.detail_fragment);
	}

	@Override
	public void onPrepared() {

	}

	@Override
	public void onQualityChanged() {

	}

	@Override
	public void onFinish(int playMode) {

		getActivity().finish();
	}

	@Override
	public void onError(int errorCode, String errorMsg) {

	}

}
