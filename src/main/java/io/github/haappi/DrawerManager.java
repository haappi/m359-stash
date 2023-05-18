package io.github.haappi;

import com.gluonhq.attach.lifecycle.LifecycleService;
import com.gluonhq.attach.util.Platform;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.MonkeyPatching.ViewItem;
import io.github.haappi.views.ViewEnums;
import javafx.scene.image.Image;


public class DrawerManager {

    public static void

    buildDrawer(AppManager app) {
        NavigationDrawer drawer = app.getDrawer();
        drawer.getItems().clear();

        NavigationDrawer.Header header = new NavigationDrawer.Header("Productivity App",
                "A rather, strange \"productivity\" app",
                new Avatar(21, new
                        Image(DrawerManager.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);

        final Item primaryItem =
                new ViewItem(
                        "Login", MaterialDesignIcon.LOCK, ViewEnums.SPLASH, ViewStackPolicy.SKIP);

        if (!(Config.getInstance().getDisplayName() == null)) {
            final Item secondaryItem =
                    new ViewItem("Pomodoro", MaterialDesignIcon.TIMER, ViewEnums.POMODORO);

            final Item thirdItem =
                    new ViewItem("Habits", MaterialDesignIcon.ASSIGNMENT, ViewEnums.HABITS);

            final Item settingsItem =
                    new ViewItem("Settings", MaterialDesignIcon.SETTINGS, ViewEnums.SETTINGS);

            final Item chartsItem =
                    new ViewItem("Charts", MaterialDesignIcon.TRENDING_UP, ViewEnums.CHARTS);
            drawer.getItems().addAll(secondaryItem, thirdItem, settingsItem);

        }

        drawer.getItems().addAll(primaryItem);

        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem
                    .selectedProperty()
                    .addListener(
                            (obs, ov, nv) -> {
                                if (nv) {
                                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                                }
                            });
            drawer.getItems().add(quitItem);
        }
    }
}
