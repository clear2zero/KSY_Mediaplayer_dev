package com.ksy.media.demo;

import java.util.List;

import com.ksy.media.demo.ui.IndicatorFragment;

public class MediaDetaiFragment extends IndicatorFragment {

	@Override
	protected int supplyTabs(List<TabInfo> tabs) {

		tabs.add(new TabInfo(0, "评论", true, DescriptionFragment.class));
		tabs.add(new TabInfo(1, "详情", true, DescriptionFragment.class));
		tabs.add(new TabInfo(2, "点播", true, DescriptionFragment.class));
		return 0;
	}
}
