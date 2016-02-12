package org.liftoff.view;

import android.graphics.Typeface;

public interface VectorFontView {

  void setTypeface(Typeface typeface);

  void setText(CharSequence text);

  CharSequence getText();
}
