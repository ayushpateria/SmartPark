package com.ayush.smartpark.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ayush.smartpark.R;


public class Spot {

    /**
     * An array of sample items.
     */
    public static final List<SpotItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample items. Key: sample ID; Value: Item.
     */
    public static final Map<String, SpotItem> ITEM_MAP = new HashMap<>(5);

    /*static {
        addItem(new SpotItem("1", R.drawable.p1, "Parking Spot #1", "0", "0", "Focusing is about saying No.", " ", " "));
        addItem(new SpotItem("2", R.drawable.p2, "Parking Spot #2", "0", "0", "A quitter never wins and a winner never quits.", " ", " "));
        addItem(new SpotItem("3", R.drawable.p3, "Parking Spot #3", "0", "0",  "Action is the foundational key to all success.", " ", " "));
        addItem(new SpotItem("4", R.drawable.p4, "Parking Spot #4", "0", "0",  "Our only limitations are those we set up in our own minds.", " ", " "));
        addItem(new SpotItem("5", R.drawable.p5, "Parking Spot #5", "0", "0",  "Deciding what not do do is as important as deciding what to do.", " ", " "));
    }*/

    public static void addItem(SpotItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static void fetchItems(SpotItem item) {
        ITEMS.clear();
        ITEM_MAP.clear();
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    public static class SpotItem {
        public final String id;
        public final int photoId;
        public final String title;
        public final String lat;
        public final String lon;
        public final String addr;
        public final String cost;
        public final String distance;

        public SpotItem(String id, int photoId, String title, String lat, String lon, String addr, String cost, String distance) {
            this.id = id;
            this.photoId = photoId;
            this.title = title;
            this.lat = lat;
            this.lon = lon;
            this.addr = addr;
            this.cost = cost;
            this.distance = distance;
        }
    }
}

