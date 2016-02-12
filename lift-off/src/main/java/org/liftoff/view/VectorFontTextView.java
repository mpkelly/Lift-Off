package org.liftoff.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import org.liftoff.app.AbstractApplication;

public class VectorFontTextView extends TextView implements VectorFontView {
  public VectorFontTextView(Context context) {
    super(context);
  }

  public VectorFontTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public VectorFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    if (getText() != null && getText().length() > 0) {
      setIconFontText(getText());
    }
  }

  public void setIconFontText(CharSequence text) {
    super.setText(text);

    Context context = getContext().getApplicationContext();
    if (context instanceof AbstractApplication) {
      AbstractApplication app = (AbstractApplication) context;
      app.applyFont(this);
    }
  }
}
