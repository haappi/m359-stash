package io.github.haappi.views;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;

public enum ViewEnums {
    SPLASH(HOME_VIEW),
    LOGIN("Login"),
    HMMM("Hmmm"),
    SECONDARY("Secondary"), THIRD("Third");

    private final String viewName;

    ViewEnums(String viewName) {
        this.viewName = viewName;
    }

    public String toString() {
        return viewName;
    }

    public static ViewEnums fromViewName(String viewName) {
        for (ViewEnums viewEnum : values()) {
            if (viewEnum.toString().equals(viewName)) {
                return viewEnum;
            }
        }
        return null;
    }



}
