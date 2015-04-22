package de.flowment.funfacts;

import android.content.res.Resources;

import java.util.Random;

/**
 * Created by Khaled on 19.03.2015.
 */
public class ColorWheel {
    public int[] mColors;
    public int mRndNumber;

    public int getColor(Resources res){
        mColors = res.getIntArray(R.array.androidcolors);
        int lastRnd = mRndNumber;
        do {
            mRndNumber = new Random().nextInt(mColors.length);
        }
        while(lastRnd == mRndNumber);
        return mColors[mRndNumber];
    }
}
