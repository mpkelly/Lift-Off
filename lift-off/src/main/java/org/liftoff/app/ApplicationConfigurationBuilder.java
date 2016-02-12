package org.liftoff.app;

import android.support.annotation.NonNull;

import org.liftoff.util.DependencyContainer;
import org.liftoff.view.FontManager;
import org.liftoff.view.LayoutResolver;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ApplicationConfigurationBuilder {
  private LayoutResolver layoutResolver = new LayoutResolver();
  private boolean automaticResourceResolution;
  private List<Class<?>> layoutClasses = new ArrayList<>();
  private List<Class<?>> menuClasses = new ArrayList<>();
  private DependencyContainer dependencyContainer;
  private FontManager fontManager;

  public ApplicationConfigurationBuilder() {

  }

  public ApplicationConfigurationBuilder withMenuResourceClasses(@NonNull Class<?>... classes) {
    menuClasses.addAll(asList(classes));
    this.automaticResourceResolution = true;
    return this;
  }

  public ApplicationConfigurationBuilder withLayoutResourceClasses(@NonNull Class<?>... classes) {
    layoutClasses.addAll(asList(classes));
    automaticResourceResolution = true;
    return this;
  }

  public ApplicationConfigurationBuilder withLayoutResolver(@NonNull LayoutResolver layoutResolver) {
    this.layoutResolver = layoutResolver;
    this.automaticResourceResolution = true;
    return this;
  }

  public ApplicationConfigurationBuilder withDependencyContainer(@NonNull DependencyContainer container) {
    this.dependencyContainer = container;
    return this;
  }

  public ApplicationConfigurationBuilder withFontManager(FontManager fontManager) {
    this.fontManager = fontManager;
    return this;
  }

  private LayoutResolver layoutResolver() {
    if(automaticResourceResolution) {
      layoutResolver.register(layoutClasses, menuClasses);
    }
    return layoutResolver;
  }

  public ApplicationConfiguration build() {
    return new ApplicationConfiguration(this);
  }

  public static class ApplicationConfiguration {
    public final boolean automaticResourceResolution;
    public final LayoutResolver layoutResolver;
    public final DependencyContainer dependencyContainer;
    public final FontManager fontManager;

    private ApplicationConfiguration(ApplicationConfigurationBuilder builder) {
      this.automaticResourceResolution = builder.automaticResourceResolution;
      this.layoutResolver = builder.layoutResolver();
      this.dependencyContainer = builder.dependencyContainer;
      this.fontManager = builder.fontManager;
    }
  }
}
