package ru.panmin.gtspro.data.models.wsrequests;

import ru.panmin.gtspro.utils.Constants;

public class UserInfoWsRequest extends BaseWsRequest {

    public UserInfoWsRequest(String id) {
        super(id, Constants.WS_TYPE_USER_INFO);
    }

}