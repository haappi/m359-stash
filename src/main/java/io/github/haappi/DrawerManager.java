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

    public static void buildDrawer(AppManager app) {
        NavigationDrawer drawer = app.getDrawer();

                NavigationDrawer.Header header = new NavigationDrawer.Header("Productivity App",
                        "A rather, strange \"productivity\" app",
                        new Avatar(21, new
                                Image(DrawerManager.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);

//        final Item blank = new Item("Blank", MaterialDesignIcon.EMPTY.graphic());

        final Item primaryItem =
                new ViewItem(
                        "Login", MaterialDesignIcon.LOCK, ViewEnums.SPLASH, ViewStackPolicy.SKIP);

        final Item secondaryItem =
                new ViewItem("Secondary", MaterialDesignIcon.DASHBOARD, ViewEnums.THIRD);

        final Item thirdItem =
                new ViewItem("Third", MaterialDesignIcon.DASHBOARD, ViewEnums.HMMM);

        drawer.getItems().addAll(primaryItem, secondaryItem, thirdItem);

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
