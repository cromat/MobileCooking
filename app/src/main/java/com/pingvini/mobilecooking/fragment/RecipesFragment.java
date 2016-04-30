package com.pingvini.mobilecooking.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pingvini.mobilecooking.R;
import com.pingvini.mobilecooking.enums.RecipeTypeEnum;

/**
 * Created by Ivan Cvitas on 30.4.2016..
 */
public class RecipesFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RecipesFragment newInstance(int sectionNumber, RecipeTypeEnum recipeType) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();

        InitRecipesData(recipeType);

        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    private static void InitRecipesData(RecipeTypeEnum recipeType) {
        //dohvatiti recepte iz baze preko parametra recipeType
        switch (recipeType)
        {
            case New:
                break;
            case Popular:
                break;
            case Own:
                break;
        }
    }
}
