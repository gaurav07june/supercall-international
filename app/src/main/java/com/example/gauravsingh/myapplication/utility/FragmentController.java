package com.example.gauravsingh.myapplication.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.gauravsingh.myapplication.fragment.ContactFragment;
import com.example.gauravsingh.myapplication.fragment.InfoFragment;
import com.example.gauravsingh.myapplication.fragment.KeypadFragment;
import com.example.gauravsingh.myapplication.fragment.RecentFragment;
import com.example.gauravsingh.myapplication.fragment.SettingFragment;

import java.util.List;

public class FragmentController {

    private FragmentController()
    {

    }



    public static final void addInfoFragment(AppCompatActivity activity,int container)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(InfoFragment.class.getName());
        if (fragment==null)
        {
            fragmentTransaction.add(container, InfoFragment.newInstance("",""),InfoFragment.class.getName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            hideAllFragments(activity,fragmentTransaction);
            fragmentTransaction.show(fragment).commit();
        }

    }

    public static final void addRecentFragment(AppCompatActivity activity,int container)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(RecentFragment.class.getName());
        if (fragment==null)
        {


            fragmentTransaction.add(container, RecentFragment.newInstance("",""),RecentFragment.class.getName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {


            hideAllFragments(activity, fragmentTransaction);
            fragmentTransaction.show(fragment).commit();
        }
    }
    public static final void addContactFragment(AppCompatActivity activity,int container)
    {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(ContactFragment.class.getName());
        if (fragment==null)
        {


            fragmentTransaction.add(container, ContactFragment.newInstance("",""),ContactFragment.class.getName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            hideAllFragments(activity, fragmentTransaction);
            fragmentTransaction.show(fragment).commit();
        }
    }
    public static final void addKeypadFragment(AppCompatActivity activity,int container)
    {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(KeypadFragment.class.getName());
        if (fragment==null)
        {


            fragmentTransaction.add(container, KeypadFragment.newInstance("",""),KeypadFragment.class.getName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            hideAllFragments(activity, fragmentTransaction);
            fragmentTransaction.show(fragment).commit();
        }
    }
    public static final void addSettingFragment(AppCompatActivity activity,int container)
    {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(SettingFragment.class.getName());
        if (fragment==null)
        {


            fragmentTransaction.add(container, SettingFragment.newInstance("",""),SettingFragment.class.getName());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            hideAllFragments(activity, fragmentTransaction);
            fragmentTransaction.show(fragment).commit();
        }
    }

    public static final void replaceInfoFrag(AppCompatActivity activity,int container)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(container, InfoFragment.newInstance("",""));
        fragmentTransaction.commit();

    }
    public static final void replaceRecentFrag(AppCompatActivity activity,int container)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(container, RecentFragment.newInstance("",""), AppConstants.fragments.RECENT_FRAG);
        fragmentTransaction.commit();

    }
    public static final void replaceContactFrag(AppCompatActivity activity,int container)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(container, ContactFragment.newInstance("",""));
        fragmentTransaction.commit();

    }
    public static final void replaceKeypadFrag(AppCompatActivity activity,int container)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(container, KeypadFragment.newInstance("",""));
        fragmentTransaction.commit();

    }
    public static final void replaceSettingFrag(AppCompatActivity activity,int container)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(container, SettingFragment.newInstance("",""));
        fragmentTransaction.commit();

    }


    public static final void showFragmentIfHidden(AppCompatActivity activity)
    {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

       List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        if (fragments!=null && fragments.size()>0)
        {
            Fragment curFragment = fragments.get(fragments.size()-1);
            if (curFragment!=null )
            {

                fragmentTransaction.show(curFragment).commit();
            }
        }
    }

    private static final void hideAllFragments(AppCompatActivity activity, FragmentTransaction fragmentTransaction)
    {
         for (Fragment fragment : activity.getSupportFragmentManager().getFragments())
        {
            fragmentTransaction.hide(fragment);
        }
    }
}
