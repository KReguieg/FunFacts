package de.flowment.funfacts;

import android.content.res.Resources;

import java.util.Random;

/**
 * Created by Khaled on 19.03.2015.
 */
public class FactBook {

    public String[] mFunFacts;

    public String getFact(Resources res){
        mFunFacts = res.getStringArray(R.array.funFactsList);
        return mFunFacts[new Random().nextInt(mFunFacts.length)];
    }
}
