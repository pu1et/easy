package com.easy.v1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TutorialActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btn_skip, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_tutorial);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btn_skip = findViewById(R.id.btn_skip);
        btn_next = findViewById(R.id.btn_next);


        addBottomDots(0);

        changeStatusBarColor();

        pagerAdapter = new PagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btn_skip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveMainPage();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if(current < layouts.length){
                   // 마지막 페이지가 아니라면 다음 페이지로 이동
                    viewPager.setCurrentItem(current);
                }else{
                    //마지막 페이지라면 메인페이지로 이동
                    moveMainPage();
                }
            }
        });
    }
    private void addBottomDots(int currenPage){ // 하단점(선택된 점, 선택되지 않은 점)
        dots = new TextView[layouts.length]; // 레이아웃 크기만큼 하단 점 배열에 추가
        Log.v("log",""+dots.length+", "+layouts.length);
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for(int i=0;i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText("○ ");
            //dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(10);
            //dots[i].setTextSize(35);
            //dots[i].setTextColor(Color.parseColor("#ffffff"));
            dotsLayout.addView(dots[i]);
        }
        if(dots.length > 0)
            dots[currenPage].setText("● ");
            //dots[currenPage].setTextColor(Color.parseColor("#000000"));
    }
    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    private void moveMainPage(){
        startActivity(new Intent(TutorialActivity.this, MainActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // 다음 / 시작 버튼 바꾸기
            if(position == layouts.length -1){
                // 마지막 페이지에서 다음 버튼 -> 시작 버튼으로 교체
                btn_next.setText("시작");
                btn_skip.setVisibility(View.GONE);
            }else{
                // 마지막 페이지가 아니면) 다음과 건너띄기 버튼 출력
                btn_next.setText("다음");
                btn_skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {
        private LayoutInflater layoutInflater;

        public PagerAdapter(){

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            View view = (View) object;
            container.removeView(view);
        }
    }
}
