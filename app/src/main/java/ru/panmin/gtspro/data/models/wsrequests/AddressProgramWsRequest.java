package ru.panmin.gtspro.data.models.wsrequests;

import ru.panmin.gtspro.utils.Constants;

public class AddressProgramWsRequest extends BaseWsRequest {

    public AddressProgramWsRequest(String id) {
        super(id, Constants.WS_TYPE_ADDRESS_PROGRAM);
    }

}