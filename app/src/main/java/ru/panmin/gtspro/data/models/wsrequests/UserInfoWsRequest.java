package ru.panmin.gtspro.data.models.wsrequests;

import ru.panmin.gtspro.utils.Constants;

public class UserInfoWsRequest extends BaseWsRequest {

    public UserInfoWsRequest() {
        super(Constants.WS_TYPE_USER_INFO);
    }

}