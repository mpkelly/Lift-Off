package org.liftoff.app;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public abstract class AbstractService extends IntentService {

  public AbstractService(String name) {
    super(name);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  protected AbstractApplication app() {
    return (AbstractApplication) getApplication();
  }

  protected <T> T resolve(Class<T> clazz) {
    return app().resolveDependency(clazz);
  }
  protected <T> T resolve(Class<T> clazz, String name) {
    return app().resolveDependency(clazz, name);
  }

  @Override
  public final void onCreate() {
    super.onCreate();
    created();
  }

  protected void created() {
  }

  @Override
  public final void onDestroy() {
    super.onDestroy();
    destroyed();
  }

  protected void destroyed() {

  }
}
