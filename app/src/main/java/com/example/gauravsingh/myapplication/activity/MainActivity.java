package com.example.gauravsingh.myapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.gauravsingh.myapplication.R;
import com.example.gauravsingh.myapplication.databinding.ActivityMainBinding;
import com.example.gauravsingh.myapplication.fragment.RecentFragment;
import com.example.gauravsingh.myapplication.utility.AppConstants;
import com.example.gauravsingh.myapplication.utility.BottomNavigationViewHelper;
import com.example.gauravsingh.myapplication.utility.FragmentController;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ActivityMainBinding binding;
    public BottomNavigationView navigationView;
    public static String dialNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navigationView = findViewById(R.id.navigation);
        initView();
        setClickListener();
    }
    public void setToolbar(String title) {

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle(title);

        binding.toolbar.inflateMenu(R.menu.tool_menu);
        /*binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

    }

    private void initView(){
        dialNumber = "";
        BottomNavigationViewHelper.disableShiftMode(binding.navigation);
        FragmentController.replaceInfoFrag(this, R.id.frag_container);
        binding.lnrlayInfoToolbarLay.setVisibility(View.VISIBLE);
        binding.lnrlayKeypadToolbarLay.setVisibility(View.GONE);
        setToolbar("");
    }


    private void setClickListener(){
        binding.navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_info:
                //binding.toolbar.setVisibility(View.GONE);
                binding.lnrlayInfoToolbarLay.setVisibility(View.VISIBLE);
                binding.lnrlayKeypadToolbarLay.setVisibility(View.GONE);
                setToolbar("");
                FragmentController.replaceInfoFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_recent:
                binding.toolbar.setVisibility(View.VISIBLE);
                binding.lnrlayInfoToolbarLay.setVisibility(View.GONE);
                binding.lnrlayKeypadToolbarLay.setVisibility(View.GONE);
                setToolbar(getResources().getString(R.string.recent));
                FragmentController.replaceRecentFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_contact:
                binding.lnrlayInfoToolbarLay.setVisibility(View.GONE);
                binding.lnrlayKeypadToolbarLay.setVisibility(View.GONE);
                binding.toolbar.setVisibility(View.VISIBLE);
                setToolbar(getResources().getString(R.string.contacts));
                FragmentController.replaceContactFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_keypad:
                binding.lnrlayInfoToolbarLay.setVisibility(View.GONE);
                binding.lnrlayKeypadToolbarLay.setVisibility(View.VISIBLE);
                //binding.toolbar.setVisibility(View.GONE);
                setToolbar("");
                FragmentController.replaceKeypadFrag(this, R.id.frag_container);
                return true;
            case R.id.navigation_setting:
                binding.lnrlayInfoToolbarLay.setVisibility(View.GONE);
                binding.lnrlayKeypadToolbarLay.setVisibility(View.GONE);
                binding.toolbar.setVisibility(View.VISIBLE);
                setToolbar(getResources().getString(R.string.setting));
                FragmentController.replaceSettingFrag(this, R.id.frag_container);
                return true;
        }
        return false;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    @Override
    public void onBackPressed() {
        if (binding.navigation.getSelectedItemId() == R.id.navigation_info){

            super.onBackPressed();
        }else{
            setToolbar("");
            FragmentController.replaceInfoFrag(this, R.id.frag_container);
            binding.navigation.setSelectedItemId(R.id.navigation_info);
        }
    }
}
