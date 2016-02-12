package org.liftoff.app;

import android.app.Application;
import android.support.annotation.NonNull;

import org.liftoff.Bug;
import org.liftoff.app.ApplicationConfigurationBuilder.ApplicationConfiguration;
import org.liftoff.view.VectorFontView;


public class AbstractApplication extends Application {
  private ApplicationConfiguration configuration;

  protected AbstractApplication() {

  }

  protected void applyConfiguration(ApplicationConfiguration configuration) {
    this.configuration = configuration;
  }

  public boolean isAutomaticResourceResolutionEnabled() {
    return configuration.automaticResourceResolution;
  }

  public int resolveLayout(@NonNull Class<?> clazz) {
    if (!isAutomaticResourceResolutionEnabled()) {
      throw new Bug("Cannot resolve layout when automatic resource resolution is disabled.");
    }
    return configuration.layoutResolver.resolveLayout(clazz);
  }

  public int resolveMenu(@NonNull Class<?> clazz) {
    if (!isAutomaticResourceResolutionEnabled()) {
      throw new Bug("Cannot resolve menu when automatic resource resolution is disabled.");
    }
    return configuration.layoutResolver.resolveMenu(clazz);
  }

  public <T> T resolveDependency(@NonNull Class<T> clazz) {
    return configuration.dependencyContainer.resolve(clazz);
  }

  public <T> T resolveDependency(@NonNull Class<T> clazz, String name) {
    return configuration.dependencyContainer.resolve(clazz, name);
  }

  public void applyFont(VectorFontView fontView) {
    if (configuration.fontManager != null) {
      configuration.fontManager.applyFont(fontView);
    }
  }

  protected void onLogout(){
    //Override to do any clean up
  }
}
