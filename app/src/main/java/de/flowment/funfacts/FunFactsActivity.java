package de.flowment.funfacts;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;


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
        final EditText factLbl = (EditText) findViewById(R.id.funFactTxt);
        factLbl.setMovementMethod(new ScrollingMovementMethod());
        final Resources res = getResources();
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayoutId);

        FloatingActionsMenu floatingActionMenu = (FloatingActionsMenu) findViewById(R.id.floatingActionMenu);
        floatingActionMenu.setRotation(90.0f);
        findViewById(R.id.sendNewFactFloatingBtn).setRotation(-90f);
        findViewById(R.id.nextFunFactFloatingBtn).setRotation(-90f);
        findViewById(R.id.shareFloatingActionButton).setRotation(-90f);
        findViewById(R.id.sendNewFactFloatingBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewFunFactActivity();
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change Background Color
                rl.setBackgroundColor(mColor.getColor(res));
                // The button was clicked, so update the fact label
                // with a new random fact and color
                factLbl.setText(mFactBook.getFact(res));
            }
        };

        findViewById(R.id.nextFunFactFloatingBtn).setOnClickListener(listener);
        findViewById(R.id.shareFloatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "HAHAHA we just talked about this yesterday: \"" + factLbl.getText() + "\"\n\n Want more FunFacts download FunFact.Ory at https://play.google.com/store/apps/details?id=de.flowment.funfacts");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });

    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
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

    private void startNewFunFactActivity() {
        Intent intent = new Intent(this, AddNewFunFactActivity.class);
        startActivity(intent);
    }
}
