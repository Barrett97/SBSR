package com.bt.barrett.sbsr

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.reader.*
import java.util.*

class ReaderActivity : Activity() {

    private var words: LinkedList<String>? = null
    private var wordCursor: ListIterator<String>? = null
    private var paragraph: String? = null

    public override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.reader)
        val b = intent.extras
        paragraph = b.getString("paragraph")
        initialize()
    }

    private fun initialize() {
        words = LinkedList()
        paragraph = paragraph!!.replace("[\n]+".toRegex(), ".")
        paragraph = paragraph!!.replace("\\?".toRegex(), "**Q?")
        paragraph = paragraph!!.replace("!".toRegex(), "**E!")
        paragraph = paragraph!!.replace("\\.".toRegex(), "**P.")
        val wordArray = paragraph!!.split("[.?!]").toTypedArray()
        for (i in wordArray.indices) {
            words!!.addLast(replace(wordArray[i]))
        }
        wordCursor = words!!.listIterator()

//        System.out.println(wordArray[0]);
        sentence.text = (wordCursor as MutableListIterator<String>).next()
//        left.setVisibility(View.INVISIBLE);
//        right.setVisibility(View.INVISIBLE);
    }

    fun onClick(v: View) {
        if (v === leftButtonID) {
            if (wordCursor!!.hasPrevious()) {
                sentence!!.text = wordCursor!!.previous()
            }
        } else if (v === rightButtonID) {
            if (wordCursor!!.hasNext()) {
                sentence!!.text = wordCursor!!.next()
            }
        }
    }

    /*
    *
    * */
    fun next(v: View?) {
        if (wordCursor!!.hasNext()) sentence!!.text = wordCursor!!.next()
        //        wordCursor.next();
    }

    fun previous(v: View?) {
        if (wordCursor!!.hasPrevious()) sentence!!.text = wordCursor!!.previous()
        //        wordCursor.previous();
    }

    private fun replace(s: String): String {
        return s.replace("\\*\\*Q".toRegex(), "?")
                .replace("\\*\\*E".toRegex(), "!")
                .replace("\\*\\*P".toRegex(), ".")
    }
}