package org.liftoff.util;

public interface DependencyContainer {

  <T> T resolve(Class<T> type);
  <T> T resolve(Class<T> type, String name);
}
