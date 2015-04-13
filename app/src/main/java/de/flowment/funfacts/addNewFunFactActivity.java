package de.flowment.funfacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class AddNewFunFactActivity extends ActionBarActivity {

    private EditText mNewFunFactEditText;
    private EditText mNameEditText;
    private EditText mSourceEditText;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_fun_fact);
        mNewFunFactEditText = (EditText) findViewById(R.id.newFunFactEditText);
        mNameEditText = (EditText) findViewById(R.id.enterNameEditText);
        mSourceEditText = (EditText) findViewById(R.id.addSourceEditText);
        mCheckBox = (CheckBox) findViewById(R.id.publishNameCheckBox);

        // Checkbox visibility
        mCheckBox.setVisibility(View.INVISIBLE);
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if(!s.toString().equals("")){
                        mCheckBox.setVisibility(View.VISIBLE);
                    }
                    else {
                        mCheckBox.setVisibility(View.INVISIBLE);
                    }
                }
                catch (IllegalArgumentException e) {}
            }
        });

        findViewById(R.id.sendNewFactFloatingBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"khaled.reguieg@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "New FunFact For You!");
                i.putExtra(Intent.EXTRA_TEXT   , "FunFact: \n\n" + mNewFunFactEditText.getText() + "\n\nName:\n\n" + mNameEditText.getText() + "\n\nSource: \n\n" + mSourceEditText.getText()+ "\n\nPublish name: " + mCheckBox.isChecked());
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
