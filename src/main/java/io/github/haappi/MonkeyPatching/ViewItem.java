package io.github.haappi.MonkeyPatching;

import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.views.ViewEnums;

public class ViewItem extends NavigationDrawer.ViewItem {

  public ViewItem(String title, String viewName) {
    super(title, viewName);
  }

  public ViewItem(String title, String viewName, ViewStackPolicy viewStackPolicy) {
    super(title, viewName, viewStackPolicy);
  }

  public ViewItem(String title, MaterialDesignIcon graphic, String viewName) {
    super(title, graphic.graphic(), viewName);
  }

  public ViewItem(
      String title, MaterialDesignIcon graphic, String viewName, ViewStackPolicy viewStackPolicy) {
    super(title, graphic.graphic(), viewName, viewStackPolicy);
  }

  public ViewItem(String title, MaterialDesignIcon graphic, ViewEnums viewName) {
    super(title, graphic.graphic(), viewName.toString());
  }

  public ViewItem(
      String title,
      MaterialDesignIcon graphic,
      ViewEnums viewName,
      ViewStackPolicy viewStackPolicy) {
    super(title, graphic.graphic(), viewName.toString(), viewStackPolicy);
  }

  public ViewItem(String title, ViewEnums viewName) {
    super(title, viewName.toString());
  }

  public ViewItem(String title, ViewEnums viewName, ViewStackPolicy viewStackPolicy) {
    super(title, viewName.toString(), viewStackPolicy);
  }
}
