package org.liftoff.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import org.liftoff.R;

public class FragmentHostActivity extends AbstractActivity implements FragmentManager.OnBackStackChangedListener {
  private AbstractFragment currentFragment;

  @Override public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onPostCreate(savedInstanceState, persistentState);
    getSupportFragmentManager().addOnBackStackChangedListener(this);
  }

  @Override protected <T extends AbstractFragment> void show(@NonNull Class<T> fragmentClass) {
    if (currentFragment != null && currentFragment.getClass() == fragmentClass) {
      return;
    }
    try {
      AbstractFragment fragment = fragmentClass.newInstance();
      getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .addToBackStack(fragment.getTag())
        .commit();
    } catch (InstantiationException e) {
      //TODO proper error reporting
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Override public void onBackPressed() {
    if (currentFragment != null && currentFragment.onBackPressed()) {
      return;
    }
    if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
      logout();
    } else {
      getSupportFragmentManager().popBackStackImmediate();
    }
  }

  protected void logout() {
    getSupportFragmentManager().popBackStackImmediate();
    finish();
  }

  @Override public void onBackStackChanged() {
    currentFragment = (AbstractFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
  }
}
