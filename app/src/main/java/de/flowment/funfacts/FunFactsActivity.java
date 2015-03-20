package de.flowment.funfacts;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class FunFactsActivity extends ActionBarActivity {

    private FactBook mFactBook = new FactBook();
    private ColorWheel mColor = new ColorWheel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        // Declare our View variables and asign the Views from the layout file
        final TextView factLbl = (TextView) findViewById(R.id.funFactTxt);
        final Button showFactBtn = (Button) findViewById(R.id.nextFunFactBtn);
        final Resources res = getResources();
        final int[] colors = res.getIntArray(R.array.androidcolors);
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
}
