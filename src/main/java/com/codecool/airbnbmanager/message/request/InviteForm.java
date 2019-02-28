package com.codecool.airbnbmanager.message.request;

public class InviteForm {
    private String email;
    private long lodgingsId;

        public String getEmail() {
        return email;
    }

    public long getLodgingsId() {
        return lodgingsId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLodgingsId(long lodgingsId) {
        this.lodgingsId = lodgingsId;
    }
}
