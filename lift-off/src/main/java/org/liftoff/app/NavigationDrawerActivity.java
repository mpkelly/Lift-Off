package org.liftoff.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.liftoff.R;
import org.liftoff.view.VectorFontDrawable;

public class NavigationDrawerActivity extends FragmentHostActivity {
  private DrawerLayout drawerLayout;
  private ActionBarDrawerToggle drawerToggle;

  @Override protected void created(Bundle savedInstanceState) {
    setupDrawer();
    setupActionBar();
  }

  private void setupDrawer() {
    drawerLayout = findView(R.id.drawer_layout);
    Toolbar toolbar = findView(R.id.toolbar);
    setSupportActionBar(toolbar);
    drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,  toolbar, R.string.open, R.string.close);
    drawerLayout.setDrawerListener(drawerToggle);
  }

  private void setupActionBar() {
    VectorFontDrawable hamburger = new VectorFontDrawable(this);
    hamburger.setVectorText(getString(R.string.hamburger_icon));
    hamburger.setTextColor(getResources().getColor(R.color.colorActionBarText));

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(hamburger);
  }

  @Override public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onPostCreate(savedInstanceState, persistentState);
    drawerToggle.syncState();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    drawerToggle.onConfigurationChanged(newConfig);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
  }
}
