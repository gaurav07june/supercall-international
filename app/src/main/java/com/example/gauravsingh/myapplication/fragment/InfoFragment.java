package com.example.gauravsingh.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gauravsingh.myapplication.R;
import com.example.gauravsingh.myapplication.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private FragmentInfoBinding binding;
    private TextView txtBottomInfo;

    public InfoFragment() {
    }
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        View view = binding.getRoot();
        setView();
        return view;
    }

    private void setView(){
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.info_bottom));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String url = getResources().getString(R.string.super_call_link);
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.no_app_found_error), Toast.LENGTH_SHORT).show();
                }
            }
        };

        spannableString.setSpan(clickableSpan, 0, 10, 0);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.colorBlueDim)), 0, 10, 0);
        // this step is mandated for the url and clickable styles.
        binding.txtInfoBottom.setMovementMethod(LinkMovementMethod.getInstance());
        // make it neat
        binding.txtInfoBottom.setGravity(Gravity.CENTER);
        binding.txtInfoBottom.setBackgroundColor(Color.WHITE);
        binding.txtInfoBottom.setText(spannableString);

    }

}
