package com.example.gauravsingh.myapplication.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gauravsingh.myapplication.R;
import com.example.gauravsingh.myapplication.adapter.ScreenSlidePagerAdapter;
import com.example.gauravsingh.myapplication.databinding.ActivityHomeBinding;
import com.example.gauravsingh.myapplication.fragment.InfoFragment;
import com.example.gauravsingh.myapplication.fragment.RecentFragment;
import com.example.gauravsingh.myapplication.utility.AppConstants;
import com.example.gauravsingh.myapplication.utility.BottomNavigationViewHelper;
import com.example.gauravsingh.myapplication.utility.FragmentController;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private static final int NUM_PAGES = 5;

    private PagerAdapter pagerAdapter;
    private ActivityHomeBinding binding;
    public BottomNavigationView navigationView;
    public static String dialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initView();
        setClickListener();
        setView();

    }

    private void initView(){
        dialNumber = "";
        BottomNavigationViewHelper.disableShiftMode(binding.navigation);
       // FragmentController.replaceInfoFrag(this, R.id.frag_container);
        binding.toolbar.setVisibility(View.GONE);
    }


    private void setClickListener(){
        binding.navigation.setOnNavigationItemSelectedListener(this);

    }

    private void setView(){
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        binding.fragContainer.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_info:
                binding.toolbar.setVisibility(View.GONE);
                binding.fragContainer.setCurrentItem(0);
                //FragmentController.replaceInfoFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_recent:
                binding.toolbar.setVisibility(View.VISIBLE);
                setToolbar(getResources().getString(R.string.recent));
                binding.fragContainer.setCurrentItem(1);
                //FragmentController.replaceRecentFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_contact:
                binding.toolbar.setVisibility(View.VISIBLE);
                setToolbar(getResources().getString(R.string.contacts));
                binding.fragContainer.setCurrentItem(2);
                //FragmentController.replaceContactFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_keypad:
                binding.toolbar.setVisibility(View.GONE);
                binding.fragContainer.setCurrentItem(3);
                //FragmentController.replaceKeypadFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_setting:
                binding.toolbar.setVisibility(View.VISIBLE);
                setToolbar(getResources().getString(R.string.setting));
                binding.fragContainer.setCurrentItem(4);
                //FragmentController.replaceSettingFrag(this, R.id.frag_container);
                return true;
        }
        return false;

    }

    public void setToolbar(String title) {

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        actionBar.setTitle(title);
        binding.toolbar.inflateMenu(R.menu.tool_menu);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        if (binding.fragContainer.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            binding.fragContainer.setCurrentItem(binding.fragContainer.getCurrentItem() - 1);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_delete);
        if (binding.navigation.getSelectedItemId() == R.id.navigation_recent){
            menuItem.setVisible(true);
        }else{
            menuItem.setVisible(false);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_delete:
                RecentFragment fragment =  (RecentFragment) getSupportFragmentManager().findFragmentByTag(AppConstants.fragments.RECENT_FRAG);
                fragment.performDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
