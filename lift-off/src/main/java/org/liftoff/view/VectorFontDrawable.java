package org.liftoff.view;

import org.liftoff.app.AbstractActivity;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

public class VectorFontDrawable extends TextDrawable implements VectorFontView {

  private final AbstractActivity context;

  public VectorFontDrawable(AbstractActivity context) {
    super(context);
    this.context = context;
    setTextSize(COMPLEX_UNIT_DIP, 24);
  }

  public void setVectorText(CharSequence text) {
    setText(text);
    context.app().applyFont(this);
  }
}
