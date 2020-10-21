package com.bt.barrett.sbsr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.setup.*
import kotlin.text.Typography.paragraph

/*
* Setup for reading environment. Paste paragraph or upload document.
* */
class ReaderSetup : Activity() {

    public override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.setup)
    }

    fun onClick(v: View) {
        if (v === parseButton) {
//            String s = paragraph.getText().toString();
//            System.out.println(paragraph.getText().toString());
            val b = Bundle()
            b.putString("paragraph", paragraph.toString().trim())
            val i = Intent(applicationContext, ReaderActivity::class.java)
            i.putExtras(b)
            this.startActivity(i)
        }
    }
}