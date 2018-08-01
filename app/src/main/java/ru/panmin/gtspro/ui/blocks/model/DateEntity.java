package ru.panmin.gtspro.ui.blocks.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEntity {
    String dateFormatted;
    Date date;

    public DateEntity(String dateFormatted, Date date) {
        this.dateFormatted = dateFormatted;
        this.date = date;
    }

    public DateEntity(Date date) {
        this.date = date;
    }

    public String getDateFormatted() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(date);
    }

    public Date getDate() {
        return date;
    }
}
