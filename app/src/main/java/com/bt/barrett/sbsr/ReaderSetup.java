package com.bt.barrett.sbsr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
* Setup for reading environment. Paste paragraph or upload document.
* */
public class ReaderSetup extends Activity {

    TextView title;
    EditText paragraph; // For pasting text
    Button parse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.setup);

        initialize();
    }

    public void initialize() {
        title = findViewById(R.id.title_id);
        paragraph = findViewById(R.id.edit_id);
        parse = findViewById(R.id.parse_id);
    }

    public void onClick(View v) {
        if (v == parse) {
//            String s = paragraph.getText().toString();
//            System.out.println(paragraph.getText().toString());
            Bundle b = new Bundle();
            b.putString("paragraph", paragraph.getText().toString());
            Intent i = new Intent(getApplicationContext(), ReaderActivity.class);
            i.putExtras(b);
            this.startActivity(i);
        }
    }

}
