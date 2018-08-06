package ru.panmin.gtspro.utils;

import android.content.res.Resources;
import android.location.Location;

import ru.panmin.gtspro.data.models.Coordinates;

public class OtherUtils {

    private static final double RADIUS_EARTH = 6468526.21492;

    private OtherUtils() {
    }

    public static int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static double distance(Location location1, Coordinates coordinates) {
        if (location1 != null && coordinates != null) {
            return (
                    RADIUS_EARTH
                            * Math.acos(
                            Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(Math.toRadians(coordinates.getLatitude()))
                                    * Math.cos(Math.toRadians(location1.getLongitude() - coordinates.getLongitude()))
                                    + Math.sin(Math.toRadians(location1.getLatitude())) * Math.sin(Math.toRadians(coordinates.getLatitude()))
                    )
            );
        } else {
            return 0.0d;
        }
    }

}