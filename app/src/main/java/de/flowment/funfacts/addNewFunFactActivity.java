package de.flowment.funfacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddNewFunFactActivity extends ActionBarActivity {

    private EditText mNewFunFactEditText;
    private EditText mNameEditText;
    private EditText mSourceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_fun_fact);
        mNewFunFactEditText = (EditText) findViewById(R.id.newFunFactEditText);
        mNameEditText = (EditText) findViewById(R.id.enterNameEditText);
        mSourceEditText = (EditText) findViewById(R.id.addSourceEditText);
        findViewById(R.id.sendNewFactFloatingBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"khaled.reguieg@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "New FunFact For You!");
                i.putExtra(Intent.EXTRA_TEXT   , "FunFact: \n\n" + mNewFunFactEditText.getText() + "Name:\n\n" + mNameEditText.getText() + "Source: \n\n" + mSourceEditText.getText());
                Toast.makeText(AddNewFunFactActivity.this,"Sending new FunFact...", Toast.LENGTH_SHORT).show();
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AddNewFunFactActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
