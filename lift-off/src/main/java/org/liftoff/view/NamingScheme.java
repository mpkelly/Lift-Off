package org.liftoff.view;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

public interface NamingScheme {

  String nameFor(@NonNull Field field);

  String nameFor(@NonNull Class<?> fragmentOrActivity);

}
