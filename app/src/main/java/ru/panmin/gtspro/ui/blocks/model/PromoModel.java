package ru.panmin.gtspro.ui.blocks.model;


import java.util.List;

import ru.panmin.gtspro.utils.LocalizableText;


public class PromoModel {
    String id;
    LocalizableText name;
    LocalizableText description;
    LocalizableText sku;
    DateEntity begin_date;
    DateEntity finish_date;
    List<String> attachedME;
    Boolean isActive;

    public PromoModel(String id,
                      LocalizableText name,
                      LocalizableText sku,
                      DateEntity begin_date,
                      DateEntity finish_date,
                      List<String> attachedME,
                      Boolean isActive
                 ) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.begin_date = begin_date;
        this.finish_date = finish_date;
        this.attachedME = attachedME;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalizableText getName() {
        return name;
    }

    public void setName(LocalizableText name) {
        this.name = name;
    }

    public LocalizableText getDescription() {
        return description;
    }

    public void setDescription(LocalizableText description) {
        this.description = description;
    }


    public DateEntity getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(DateEntity begin_date) {
        this.begin_date = begin_date;
    }

    public DateEntity getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(DateEntity finish_date) {
        this.finish_date = finish_date;
    }


    public LocalizableText getSku() {
        return sku;
    }

    public void setSku(LocalizableText sku) {
        this.sku = sku;
    }
}
