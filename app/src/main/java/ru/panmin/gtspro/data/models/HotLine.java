package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HotLine extends RealmObject {

    @SerializedName("causes") private RealmList<Cause> causes = new RealmList<>();
    @SerializedName("contexts") private RealmList<HotLineContext> contexts = new RealmList<>();

    public HotLine() {
    }

    public HotLine(RealmList<Cause> causes, RealmList<HotLineContext> contexts) {
        this.causes = causes;
        this.contexts = contexts;
    }

    public RealmList<Cause> getCauses() {
        return causes;
    }

    public void setCauses(RealmList<Cause> causes) {
        this.causes = causes;
    }

    public RealmList<HotLineContext> getContexts() {
        return contexts;
    }

    public void setContexts(RealmList<HotLineContext> contexts) {
        this.contexts = contexts;
    }

}