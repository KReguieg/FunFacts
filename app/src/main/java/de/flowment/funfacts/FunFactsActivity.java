package de.flowment.funfacts;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


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
/*

        // Creating a floatingActionMenue
        ImageView icon = new ImageView(this);
        icon.setBackground(R.drawable.ic_arrow_forward_smallest);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();
        // Creating a Builder
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // Create Buttons and actually build them
        ImageView iconAddNewFunFact = new ImageView(this);
        iconAddNewFunFact.setImageResource(R.drawable.ic_create);
        ImageView iconShareFunFact = new ImageView(this);
        iconShareFunFact.setImageResource(R.drawable.ic_share);
        ImageView iconLikeFunFact = new ImageView(this);
        iconLikeFunFact.setImageResource(R.drawable.ic_like);


        SubActionButton addNewFunFactButton = itemBuilder.setContentView(iconAddNewFunFact).build();
        SubActionButton shareFunFactButton = itemBuilder.setContentView(iconShareFunFact).build();
        SubActionButton likeFunFactButton = itemBuilder.setContentView(iconLikeFunFact).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(addNewFunFactButton)
                .addSubActionView(shareFunFactButton)
                .addSubActionView(likeFunFactButton)
                .attachTo(actionButton)
                .build();
*/

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        FloatingActionsMenu floatingActionMenue = (FloatingActionsMenu) findViewById(R.id.floatingActionMenu);
        int menuWidth = floatingActionMenue.getWidth();
        floatingActionMenue.setRotation(90.0f);
        // floatingActionMenue.setTranslationY(menuWidth/3);
        findViewById(R.id.sendNewFactFloatingBtn).setRotation(-90f);
        findViewById(R.id.nextFunFactFloatingBtn).setRotation(-90f);

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
