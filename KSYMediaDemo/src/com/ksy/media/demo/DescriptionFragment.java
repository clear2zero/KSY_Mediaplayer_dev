package com.ksy.media.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ksy.media.demo.ui.CircularImage;

public class DescriptionFragment extends Fragment {

	protected View mRootView;
	protected static List<Comment> mComments;
	protected Context mContext;
	private static final Random random = new Random();

	private static final String[] names = new String[] {
			"麦小迪", "豆瓣猪", "狂龙斗士", "拜仁戈麦斯", "牛逼的大爷",
			"皇马CR7", "一清", "andy", "小林"
	};

	private static final String[] froms = new String[] {
			"腾讯网友", "金山开发", "猎豹网友", "北京海淀区", "北京朝阳区",
			"河南驻马店", "圣地亚哥", "LOS Angles", "索马里"
	};

	private static final String[] dates = new String[] {
			"十分钟前", "一小时前", "刚刚", "昨天", "上周", "十五分钟前",
			"很久以前", "20:20", "去年今天"
	};

	private static final String[] contents = new String[] {
			"就这么一个小明星，每天各种烂新闻炒来炒去，无聊不无聊？典型的演艺圈暴发户",
			"我只注意点到她的胸，哈哈",
			"为什么，国内最高等级公路限速120，汽车最高时速却可以达到200,300？要从根本上解决超速问题，就应该让汽车在出厂时强制最高时速只能达到120.",
			"应该判十年，以后老胳膊老腿的出来也不能害人了",
			"这些在城市飙车的都应该枪毙",
			"等等，商量个事。你们都干了，不差我这个给剧组送盒饭的吧，不行，盒饭钱不要了，再加两个笨鸡蛋。",
			"你们急什么，大牛，狗子你们快去收麦子，我是村长，我先上。",
			"我来晚了！给我留下活口！",
			"领导看完了吗？距离兄弟们都等着",
			"博主,请教个问题, fragment在被调用hide走onHiddenChanged(boolean hidden)后再 show出来, 走的是哪个生命周期方法? 我打日志一个生命周期方法都没走",
			"嗯, 就当前显示的和他相邻的两个;现在我懒得自己管理去 hide和show了, 直接丢给了FragmentPagerAdapter",
			"喷子真是厉害，除了会黑什么票选冠军啊，什么没节操啊，就看不得别人好？外国战队千方百计拉票，连什么充气娃娃都用上，怎么不说别人？中国人只会窝里斗？香港斗大陆，大陆斗台湾？南方斗北方，清朝斗明朝？",
			"嗯，这两天尽看到窝里斗，还把票选第一给A队。是真的A队粉丝还是脑子被门夹了？",
			"60块钱 蓝胖神话套装 多款式卡尔面罩 更有磨了你大神定制游戏内表情图标 以及 鼠标指针萌蠢可爱款型 60元你卜吃亏 60元妮卜上当 最后请投IG一嫖",
			"我支持IG"
	};

	private static final int[] headers = new int[] {
			R.drawable.header_1, R.drawable.header_2, R.drawable.header_3,
			R.drawable.header_4, R.drawable.header_5, R.drawable.header_6,
			R.drawable.header_7, R.drawable.header_8, R.drawable.header_9,
			R.drawable.header_10, R.drawable.header_11,
	};

	private static String random(String[] datas) {

		return datas[random.nextInt(datas.length)];
	}

	private static int random(int[] datas) {

		return datas[random.nextInt(datas.length)];
	}

	public DescriptionFragment() {

		super();
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		mContext = activity.getApplicationContext();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mComments = new ArrayList<DescriptionFragment.Comment>();
		for (int i = 0; i < 20; i++) {
			Comment comment = new DescriptionFragment.Comment(random(names), random(froms), random(dates), random(contents), random(headers));
			mComments.add(comment);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mRootView = inflater.inflate(R.layout.description_fragment, container, false);
		ListView listView = (ListView) mRootView.findViewById(R.id.list);
		CommentsAdapter adapter = new CommentsAdapter(mContext, mComments);
		listView.setAdapter(adapter);
		return mRootView;
	}

	class CommentsAdapter extends BaseAdapter {

		private List<Comment> mComments;

		private final LayoutInflater mInflater;

		public CommentsAdapter(Context context, List<Comment> comments) {

			if (mComments == null)
				mComments = new ArrayList<DescriptionFragment.Comment>();
			mComments.clear();
			if (comments != null)
				mComments.addAll(comments);

			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {

			return mComments.size();
		}

		@Override
		public Comment getItem(int position) {

			return mComments.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			CommentViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.comments_item, null);
				holder = new CommentViewHolder();
				holder.nameView = (TextView) convertView.findViewById(R.id.name);
				holder.fromView = (TextView) convertView.findViewById(R.id.from);
				holder.dateView = (TextView) convertView.findViewById(R.id.date);
				holder.contentView = (TextView) convertView.findViewById(R.id.content);
				holder.headerImage = (CircularImage) convertView.findViewById(R.id.cover_user_photo);
				convertView.setTag(holder);
			}
			holder = (CommentViewHolder) convertView.getTag();
			Comment comment = getItem(position);
			holder.nameView.setText(comment.name);
			holder.fromView.setText(comment.from);
			holder.dateView.setText(comment.date);
			holder.contentView.setText(comment.content);
			holder.headerImage.setImageResource(comment.imageResource);
			return convertView;
		}
	}

	class CommentViewHolder {

		TextView nameView;
		TextView fromView;
		TextView dateView;
		TextView contentView;
		CircularImage headerImage;
	}

	class Comment {

		String name;
		String from;
		String date;
		String content;
		int imageResource;

		public Comment(String name, String from, String date, String content, int imageResource) {

			this.name = name;
			this.from = from;
			this.date = date;
			this.content = content;
			this.imageResource = imageResource;
		}

	}
}
