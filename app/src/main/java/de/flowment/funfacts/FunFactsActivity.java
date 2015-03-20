package de.flowment.funfacts;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class FunFactsActivity extends ActionBarActivity {

    private FactBook mFactBook = new FactBook();
    private ColorWheel mColor = new ColorWheel();
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        // Declare our View variables and asign the Views from the layout file
        final TextView factLbl = (TextView) findViewById(R.id.funFactTxt);
        final Button showFactBtn = (Button) findViewById(R.id.nextFunFactBtn);
        final Resources res = getResources();
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayoutId);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change Background Color
                rl.setBackgroundColor(mColor.getColor(res));
                showFactBtn.setTextColor(mColor.getColor(res));
                // The button was clicked, so update the fact label
                // with a new random fact and color
                factLbl.setText(mFactBook.getFact(res));
            }
        };

        showFactBtn.setOnClickListener(listener);
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }
}
