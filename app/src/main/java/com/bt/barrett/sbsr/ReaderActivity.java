package com.bt.barrett.sbsr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.ListIterator;

public class ReaderActivity extends Activity {

    TextView sentence;
    Button left, right;
    LinkedList<String> words;
    ListIterator<String> wordCursor;
    String paragraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.reader);

        Bundle b = getIntent().getExtras();
        paragraph = b.getString("paragraph");

        initialize();
    }

    private void initialize() {
        sentence = findViewById(R.id.sentence_id);
        left = findViewById(R.id.leftButtonID);
        right = findViewById(R.id.rightButtonID);

        words = new LinkedList<>();

        paragraph = paragraph.replaceAll("[\n]+", ".");
        paragraph = paragraph.replaceAll("\\?", "**Q?");
        paragraph = paragraph.replaceAll("!", "**E!");
        paragraph = paragraph.replaceAll("\\.", "**P.");

        String wordArray[] = paragraph.split("[.?!]");

        for (int i = 0; i < wordArray.length; i++) {

            words.addLast(replace(wordArray[i]));
        }

        wordCursor = words.listIterator();

//        System.out.println(wordArray[0]);

        sentence.setText(wordCursor.next());
//        left.setVisibility(View.INVISIBLE);
//        right.setVisibility(View.INVISIBLE);
    }

    public void onClick(View v) {
        if (v == left) {
            if (wordCursor.hasPrevious())
                sentence.setText(wordCursor.previous());
//            System.out.println("left");
        } else if (v == right) {
            if (wordCursor.hasNext())
                sentence.setText(wordCursor.next());
//            System.out.println("right");
        }
    }

    /*
    *
    * */
    public void next(View v) {
        if (wordCursor.hasNext())
            sentence.setText(wordCursor.next());
//        wordCursor.next();
    }

    public void previous(View v) {
        if (wordCursor.hasPrevious())
            sentence.setText(wordCursor.previous());
//        wordCursor.previous();
    }

    private String replace(String s) {

        String fixedString = s.replaceAll("\\*\\*Q", "?");
        fixedString = fixedString.replaceAll("\\*\\*E", "!");
        fixedString = fixedString.replaceAll("\\*\\*P", ".");

        return fixedString;
    }

}
