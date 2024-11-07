package com.acarpio.acarpio_scrollingtext;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class CharCounter implements TextWatcher {
    private final TextView charCounterTextView;
    private final int maxChars;

    public CharCounter(TextView charCounterTextView, int maxChars) {
        this.charCounterTextView = charCounterTextView;
        this.maxChars = maxChars;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Not used
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Not used
        charCounterTextView.setText(s.length() + "/" + maxChars);
        if (s.length() > 180) {
            charCounterTextView.setTextColor(Color.RED);
        } else if (s.length() > 150) {
            charCounterTextView.setTextColor(Color.YELLOW);
        } else {
            charCounterTextView.setTextColor(Color.GREEN);
        }
    }


    @Override
    public void afterTextChanged(Editable s) {
        // Not used
    }
}
