package org.liftoff.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import static android.graphics.Typeface.DEFAULT;
import static java.lang.Integer.parseInt;

public class FontManager {
  private final Context context;
  private final Map<String, Typeface> fonts = new HashMap<>();
  private Map<String, String> definitions = new HashMap<String, String>() {{
    put("fa", "fonts/fontawesome-webfont.ttf");
    put("mi", "fonts/MaterialIcons-Regular.ttf");
  }};

  public FontManager(Context context) {
    this.context = context;
  }

  public void applyFont(VectorFontView fontView) {
    CharSequence text = fontView.getText();
    if (text == null) {
      text = "";
    }
    fontView.setTypeface(typefaceFor(text.toString()));
    setTextOrCode(text.toString(), fontView);
  }

  private Typeface typefaceFor(@NonNull String text) {
    String prefix = prefix(text);
    if (definitions.containsKey(prefix)) {
      if (!fonts.containsKey(prefix)) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), definitions.get(prefix));
        fonts.put(prefix, typeface);
      }
      return fonts.get(prefix);
    }
    return DEFAULT;
  }

  private String prefix(String text) {
    int index = text.indexOf('_');
    if (index == -1) {
      return text;
    }
    return text.substring(0, index);
  }

  private void setTextOrCode(String text, VectorFontView fontView) {
    int index = text.indexOf("_code_");
    if (index > 0 && text.length() > 8) {
      //Take a string like fa_code_f16a and convert it into a character code e.g. \uf16a
      try {
        int code = parseInt(text.substring(8), 16);
        fontView.setText(Character.toString((char) code));
      } catch(Exception e) {
        e.printStackTrace();
      }
    } else {
      fontView.setText(context.getString(resourceId(text)));
    }
  }

  public int resourceId(String name) {
    try {
      return context.getResources().getIdentifier(name, "string", context.getPackageName());
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
