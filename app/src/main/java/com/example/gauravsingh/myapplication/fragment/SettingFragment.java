package com.example.gauravsingh.myapplication.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.gauravsingh.myapplication.R;
import com.example.gauravsingh.myapplication.activity.MainActivity;
import com.example.gauravsingh.myapplication.databinding.FragmentSettingBinding;
import com.example.gauravsingh.myapplication.utility.AppConstants;
import com.example.gauravsingh.myapplication.utility.SharedPrefsUtils;

public class SettingFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private FragmentSettingBinding binding;


    public SettingFragment() {
    }

    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        View view = binding.getRoot();
        //((MainActivity)getActivity()).setToolbar(getResources().getString(R.string.setting));
        setClickListener();
        setView();
        return view;

    }

    private void setClickListener(){
        binding.switchAccessSetting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set the value in shared preference
                SharedPrefsUtils.setSharedPrefBoolean(getActivity(), AppConstants.preferences.PREF_NAME,
                        AppConstants.preferences.ACCESS_SETTING, isChecked);
            }
        });
        binding.imgUserGuide.setOnClickListener(this);
        binding.imgTellFriends.setOnClickListener(this);
        binding.imgContact.setOnClickListener(this);
        binding.txtFreeCall.setOnClickListener(this);
    }

    private void setView(){
        boolean isAccessSet = SharedPrefsUtils.getSharedPrefBoolean(getActivity(), AppConstants.preferences.PREF_NAME,
                AppConstants.preferences.ACCESS_SETTING, false);
        if (isAccessSet){
            binding.switchAccessSetting.setChecked(true);
        }else{
            binding.switchAccessSetting.setChecked(false);
        }
        binding.txtVersion.setText(String.format(getResources().getString(R.string.version),
                getResources().getString(R.string.version_value)));

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgUserGuide:
                String url = "http://supercallinternational.com/";
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "action can not be performed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgTellFriends:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "Super International Call");
                share.putExtra(Intent.EXTRA_TEXT, "http://supercallinternational.com/");
                if (share.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(Intent.createChooser(share, "Share App"));
                }else{
                    Toast.makeText(getActivity(), "action can not be performed", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.imgContact:
                Toast.makeText(getActivity(), "under development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtFreeCall:
                Toast.makeText(getActivity(), "under development", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
