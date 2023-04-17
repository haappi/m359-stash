package io.github.haappi.sample;

import com.gluonhq.cloudlink.client.data.DataClient;
import com.gluonhq.cloudlink.client.data.DataClientBuilder;
import com.gluonhq.cloudlink.client.data.OperationMode;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.provider.DataProvider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CloudLinkService {

    private ObjectProperty<UserInfo> userInfo = new SimpleObjectProperty<>(new UserInfo());

    private DataClient dataClient;

    public CloudLinkService() {
        this.dataClient = DataClientBuilder.create()
                .operationMode(OperationMode.LOCAL_ONLY)
//                .operationMode(OperationMode.CLOUD_FIRST) // requires CloudLink account on https://gluon.io
                .build();

        retrieveUserInfo();
    }

    public ObjectProperty<UserInfo> userInfoProperty() {
        return userInfo;
    }

    public void storeUserInfo() {
        DataProvider.storeObject(userInfo.get(),
                dataClient.createObjectDataWriter("user_info", UserInfo.class));
    }

    private void retrieveUserInfo() {
        GluonObservableObject<UserInfo> gluonUserInfo = DataProvider.retrieveObject(
                dataClient.createObjectDataReader("user_info", UserInfo.class));
        gluonUserInfo.stateProperty().addListener((obs, ov, nv) -> {
            if (ConnectState.SUCCEEDED.equals(nv)) {
                if (gluonUserInfo.get() == null) {
                    userInfo.set(new UserInfo());
                    storeUserInfo();
                } else {
                    userInfo.set(gluonUserInfo.get());
                }
            }
        });
    }
}
