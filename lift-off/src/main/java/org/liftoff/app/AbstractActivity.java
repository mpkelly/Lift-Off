package org.liftoff.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class AbstractActivity extends AppCompatActivity {
  private final Map<Class<?>, Object> storage = new HashMap<>();

  public <T extends View> T findView(int id) {
    return (T) super.findViewById(id);
  }

  public AbstractApplication app() {
    return (AbstractApplication) getApplication();
  }

  public <T> void store(Class<T> type, T instance) {
    storage.put(type, instance);
  }

  public <T> T retrieve(Class<T> type) {
    return type.cast(storage.get(type));
  }

  public void clearStorage() {
    storage.clear();
  }

  public <T> T resolve(Class<T> type) {
    return app().resolveDependency(type);
  }
  public <T> T resolve(Class<T> type, String name) {
    return app().resolveDependency(type, name);
  }

  @Override protected final void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (app().isAutomaticResourceResolutionEnabled()) {
      resolveLayout();
    }
    created(savedInstanceState);
  }

  private void resolveLayout() {
    int layoutId = app().resolveLayout(getClass());
    if (layoutId > 0) {
      setContentView(layoutId);
    }
  }

  protected void created(Bundle savedInstanceState) {

  }

  protected <T extends AbstractFragment> void show(Class<T> fragmentClass) {

  }

  @Override protected final void onPause() {
    super.onPause();
    paused();
  }

  protected void paused() {

  }

  @Override protected final void onResume() {
    super.onResume();
    resumed();
  }

  protected void resumed() {

  }
}
