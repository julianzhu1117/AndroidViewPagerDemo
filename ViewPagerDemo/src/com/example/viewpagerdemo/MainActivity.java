package com.example.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	Context context = null;
	LocalActivityManager manager = null;
	ViewPager pager = null;
	ImageView imgStep = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		context = MainActivity.this;
		manager = new LocalActivityManager(this , true);
		manager.dispatchCreate(savedInstanceState);	
		
		imgStep = (ImageView)this.findViewById(R.id.imgStep);
		
		initPagerViewer();
	}
	
	private void initPagerViewer() {
		pager = (ViewPager) findViewById(R.id.viewpage);
		final ArrayList<View> list = new ArrayList<View>();
		Intent intentA = new Intent(context, AActivity.class);
		list.add(getView("A", intentA));
		Intent intentB = new Intent(context, BActivity.class);
		list.add(getView("B", intentB));
		Intent intentC = new Intent(context, CActivity.class);
		list.add(getView("C", intentC));

		pager.setAdapter(new ViewPagerAdapter(list));
		pager.setCurrentItem(0);
		pager.setOnPageChangeListener(new ViewPagerOnPageChangeListener());
	}

	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}
	
	public class ViewPagerAdapter extends PagerAdapter{
		List<View> list =  new ArrayList<View>();
		public ViewPagerAdapter(ArrayList<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(ViewGroup container, int position,
				Object object) {
			ViewPager pViewPager = ((ViewPager) container);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return list.size();
		}
		
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			ViewPager pViewPager = ((ViewPager) arg0);
			pViewPager.addView(list.get(arg1));
			return list.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
	
	public class ViewPagerOnPageChangeListener implements OnPageChangeListener {

		
		
		@Override
		public void onPageSelected(int arg0) {
			switch(arg0) {
			case 0:
				imgStep.setImageDrawable(getResources().getDrawable(R.drawable.step1));
				break;
			case 1:
				imgStep.setImageDrawable(getResources().getDrawable(R.drawable.step2));
				break;
			case 2:
				imgStep.setImageDrawable(getResources().getDrawable(R.drawable.step3));
				break;
			}
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
	}	
	

}
