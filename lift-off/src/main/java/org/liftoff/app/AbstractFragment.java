package org.liftoff.app;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.liftoff.R;
import org.liftoff.view.IconFontMenuItem;
import org.liftoff.view.VectorFontDrawable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFragment extends Fragment {
  private AbstractActivity parent;
  private Map<Integer, IconFontMenuItem> menuItems = new HashMap<>();

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    this.parent = (AbstractActivity) activity;
  }

  public <T> void store(Class<T> type, T instance) {
    parent.store(type, instance);
  }

  public <T> T retrieve(Class<T> type) {
    return type.cast(parent.retrieve(type));
  }

  public void clearStorage() {
    parent.clearStorage();
  }

  public AbstractApplication app() {
    return (AbstractApplication) parent.getApplication();
  }

  public <T extends AbstractFragment> void show(Class<T> fragment) {
    parent.show(fragment);
  }

  public boolean onBackPressed() {
    return false;
  }

  protected <T extends View> T getView(View parent, int id) {
    return (T) parent.findViewById(id);
  }

  public <T> T resolve(Class<T> type) {
    return app().resolveDependency(type);
  }
  public <T> T resolve(Class<T> type, String name) {
    return app().resolveDependency(type, name);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    View parent = container;
    if (app().isAutomaticResourceResolutionEnabled()) {
      parent = setLayout(inflater);
      createChildViews(parent, savedInstanceState);
    }
    return parent;
  }

  protected abstract void createChildViews(View parent, Bundle savedInstanceState);

  private View setLayout(LayoutInflater inflater) {
    int layoutId = app().resolveLayout(getClass());
    if (layoutId > 0) {
      return inflater.inflate(layoutId, null);
    }
    return null;
  }

  @Override public final void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    created(savedInstanceState);
  }

  private void created(Bundle savedInstanceState) {

  }

  @Override public final void onPause() {
    super.onPause();
    paused();
  }

  private void paused() {

  }

  @Override public final void onResume() {
    super.onResume();
    resumed();
  }

  private void resumed() {

  }

  @Override public void onStop() {
    super.onStop();
    stopped();
  }

  private void stopped() {

  }

  protected void addMenuItems(IconFontMenuItem ... items) {
    for(IconFontMenuItem item : items) {
      menuItems.put(item.menuItemId, item);
    }
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    int menuId = app().resolveMenu(getClass());
    if (menuId != -1) {
      inflater.inflate(menuId, menu);
      createFontIcons(menu);
    }
  }

  private void createFontIcons(Menu menu) {
    for (int i =0; i < menu.size(); i++) {
      MenuItem item = menu.getItem(i);
      if (menuItems.containsKey(item.getItemId())) {
        IconFontMenuItem iconItem = menuItems.get(item.getItemId());
        item.setIcon(createIconDrawable(iconItem));
      }
    }
  }

  private Drawable createIconDrawable(IconFontMenuItem item) {
    VectorFontDrawable drawable = new VectorFontDrawable(this.parent);
    drawable.setVectorText(getString(item.iconFontId));
    drawable.setTextColor(getResources().getColor(R.color.colorActionBarText));
    return drawable;
  }
}
