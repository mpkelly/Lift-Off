package org.liftoff.view;

import org.liftoff.Bug;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class LayoutResolver {
  private final NamingScheme namingScheme;
  private Map<String, Integer> menus = new HashMap<>();
  private Map<String, Integer> layouts = new HashMap<>();

  public LayoutResolver() {
    this(new DefaultNamingScheme());
  }

  public LayoutResolver(NamingScheme namingScheme) {
    this.namingScheme = namingScheme;
  }

  public void register(Iterable<Class<?>> layoutClasses, Iterable<Class<?>> menuClasses) {
    for (Class<?> clazz : layoutClasses) {
      registerResources(clazz, layouts);
    }
    for (Class<?> clazz : menuClasses) {
      registerResources(clazz, menus);
    }
  }

  public int resolveLayout(Class<?> clazz) {
    return resolve(clazz, layouts);
  }

  public int resolveMenu(Class<?> clazz) {
    return resolve(clazz, menus);
  }

  private int resolve(Class<?> clazz, Map<String, Integer> container) {
    while (clazz != null) {
      String name = namingScheme.nameFor(clazz);
      if (container.containsKey(name)) {
        return container.get(name);
      }
      clazz = clazz.getSuperclass();
    }
    return -1;
  }

  private void registerResources(Class<?> clazz, Map<String, Integer> container) {
    for (Field field : clazz.getFields()) {
      try {
        container.put(namingScheme.nameFor(field), field.getInt(null));
      } catch (IllegalAccessException e) {
        throw new Bug(e, "Can not access field {0} on class {1}. Did you provide the correct layout and menu classes?", field, clazz);
      }
    }
  }
}
