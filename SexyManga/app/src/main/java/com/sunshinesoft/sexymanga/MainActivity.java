package com.sunshinesoft.sexymanga;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.kekstudio.dachshundtablayout.indicators.LineMoveIndicator;
import com.sunshinesoft.sexymanga.fragment.HomeFragment;
import com.sunshinesoft.sexymanga.fragment.CategoryFragment;
import com.sunshinesoft.sexymanga.fragment.ContactsFragment;
import com.sunshinesoft.sexymanga.fragment.ViewPagerAdapter;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    // This is our tablayout
    private DachshundTabLayout tabLayout;

    // This is our viewPager
    private ViewPager viewPager;


    private ViewPagerAdapter adapter;

    //Fragments
    private CategoryFragment categoryFragment;
    private HomeFragment homeFragment;
    private String[] tabTitle = {"HOME", "CATEGORY"};

    // Button tab footer
    private ImageView btnReading, btnHome, btnAccount;
    private List<ImageButton> footerBtnList;

    int[] unreadCount = {0, 5, 0};


    private View.OnClickListener footerBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == btnAccount.getId()) {
                btnAccount.setImageResource(R.drawable.account_selected);
                btnReading.setImageResource(R.drawable.reading);
                btnHome.setImageResource(R.drawable.home);
            } else if (id == btnReading.getId()) {
                btnAccount.setImageResource(R.drawable.account);
                btnReading.setImageResource(R.drawable.reading_seleted);
                btnHome.setImageResource(R.drawable.home);
            } else if (id == btnHome.getId()) {
                btnAccount.setImageResource(R.drawable.account);
                btnReading.setImageResource(R.drawable.reading);
                btnHome.setImageResource(R.drawable.home_selected);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing tab footer
        btnAccount =  findViewById(R.id.btn_account);
        btnReading = findViewById(R.id.btn_reading);
        btnHome = findViewById(R.id.btn_home);

        btnHome.setOnClickListener(footerBtnClickListener);
        btnReading.setOnClickListener(footerBtnClickListener);
        btnAccount.setOnClickListener(footerBtnClickListener);

        // Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);

        //Initializing the tablayout
        tabLayout = (DachshundTabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
//
//        LineMoveIndicator indicator = new LineMoveIndicator(tabLayout);
//        tabLayout.setAnimatedIndicator(indicator);

        try {
            setupTabIcons();
        } catch (Exception e) {
            e.printStackTrace();
        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.setAnimatedIndicator(new LineMoveIndicator(tabLayout));
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        adapter.addFragment(homeFragment, "HOME");
        adapter.addFragment(categoryFragment, "CATEGORY");
        viewPager.setAdapter(adapter);
    }

    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(tabTitle[pos]);

        return view;
    }

    private void setupTabIcons() {
        for (int i = 0; i < tabTitle.length; i++) {
            /*TabLayout.Tab tabitem = tabLayout.newTab();
            tabitem.setCustomView(prepareTabView(i));
            tabLayout.addTab(tabitem);*/

            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
        }
    }

    public void onChange(View v) {
        tabLayout.setAnimatedIndicator(new LineMoveIndicator(tabLayout));
    }

}
