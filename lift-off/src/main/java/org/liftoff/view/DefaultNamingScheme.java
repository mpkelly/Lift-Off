package org.liftoff.view;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

public class DefaultNamingScheme implements NamingScheme {

  @Override public String nameFor(@NonNull Field field) {
    return applyScheme(field.getName());
  }

  @Override public String nameFor(@NonNull Class<?> fragmentOrActivity) {
    return applyScheme(fragmentOrActivity.getSimpleName());
  }

  private String applyScheme(String name) {
    return name.toLowerCase().replaceAll("_", "");
  }
}
