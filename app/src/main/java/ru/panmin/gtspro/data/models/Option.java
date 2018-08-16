package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;

public class Option extends RealmObject {

    private String id;

    public Option() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        return id != null ? id.equals(option.id) : option.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}