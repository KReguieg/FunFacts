package de.flowment.funfacts;

import android.content.res.Resources;

import java.util.Random;

/**
 * Created by Khaled on 19.03.2015.
 */
public class FactBook {

    public String[] mFunFacts;
    public int mRndNumber;

    public String getFact(Resources res){
        mFunFacts = res.getStringArray(R.array.funFactsList);
        int lastRnd = mRndNumber;
        do {
            mRndNumber = new Random().nextInt(mFunFacts.length);
        }
        while(lastRnd == mRndNumber);
        return mFunFacts[mRndNumber];
    }
}
