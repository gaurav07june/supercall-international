package com.example.gauravsingh.myapplication.fragment;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
    private final int CALL_PHONE_PERMISSION_REQ_CODE =  101;



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
        binding.rltlayAccessSetting.setOnClickListener(this);
        binding.rltlayTellFriend.setOnClickListener(this);
        binding.rltlayContactUs.setOnClickListener(this);
        binding.rltlayCustomerService.setOnClickListener(this);
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
                String url = getResources().getString(R.string.super_call_link);
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.no_app_found_error), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgTellFriends:
                /*TODO format the mail content*/
                doTellFriendTask();
                break;
            case R.id.imgContact:
                doContactUsTask();
                break;
            case R.id.txtFreeCall:
                checkPermissionForPhoneCall();
                break;

            case R.id.rltlay_access_setting:
                binding.switchAccessSetting.performClick();
                break;
            case R.id.rltlay_tell_friend:
                doTellFriendTask();
                break;
            case R.id.rltlay_contact_us:
                doContactUsTask();
                break;
            case R.id.rltlay_customer_service:
                checkPermissionForPhoneCall();
                break;

        }
    }

    private void doContactUsTask() {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:"));
        String[] addresses = {getResources().getString(R.string.super_call_email)};
        mailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        if (mailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mailIntent);
        }else{
            Toast.makeText(getActivity(), getResources().getString(R.string.no_app_found_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void doTellFriendTask() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        String shareContent = getResources().getString(R.string.super_call_share_content);
        String shareLink = getResources().getString(R.string.super_call_link);

        SpannableString spannableString = new SpannableString(shareContent+shareLink);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, shareContent.length(), 0);
        share.putExtra(Intent.EXTRA_TEXT, spannableString);
        if (share.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(share, "Share App"));
        }else{
            Toast.makeText(getActivity(), getResources().getString(R.string.no_app_found_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissionForPhoneCall() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PHONE_PERMISSION_REQ_CODE);
        } else{
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getResources().getString(R.string.free_call_number))));

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case CALL_PHONE_PERMISSION_REQ_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try{
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getResources().getString(R.string.free_call_number))));
                    }catch (SecurityException e){

                    }

                } else {
                    Toast.makeText(getActivity(), R.string.call_permission,Toast.LENGTH_SHORT).show();
                }
                return;

        }
    }


}
