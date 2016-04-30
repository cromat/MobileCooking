package com.pingvini.mobilecooking.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pingvini.mobilecooking.enums.RecipeTypeEnum;
import com.pingvini.mobilecooking.fragment.RecipesFragment;

/**
 * Created by Ivan Cvitas on 30.4.2016..
 */
public class FragmentRecipesAdapter extends FragmentPagerAdapter {

    int numOfTabs;

    public FragmentRecipesAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        RecipesFragment recipesFragment = new RecipesFragment();
        switch (position) {
            case 0:
                return recipesFragment.newInstance(position, RecipeTypeEnum.New);
            case 1:
                return recipesFragment.newInstance(position, RecipeTypeEnum.Popular);
            case 2:
                return recipesFragment.newInstance(position, RecipeTypeEnum.Own);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "New recipes";
            case 1:
                return "Popular recipes";
            case 2:
                return "Own recipes";
        }
        return null;
    }
}