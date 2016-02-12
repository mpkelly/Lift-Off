package org.liftoff.util;

import org.nano.Nano;

public class NanoDependencyContainer implements DependencyContainer {
  private final Nano.DependencyContainer container;

  public NanoDependencyContainer(Nano.DependencyContainer container) {
    this.container = container;
  }

  @Override public <T> T resolve(Class<T> type) {
    return container.resolve(type);
  }

  @Override public <T> T resolve(Class<T> type, String name) {
    return container.resolve(type, name);
  }
}
