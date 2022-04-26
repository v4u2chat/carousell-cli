package com.carousell.common;

import java.util.Comparator;

public class ListingTimeComparator implements Comparator<Listing> {

    @Override
    public int compare(Listing listing1, Listing listing2) {
        return listing1.getCreatedTime().compareTo(listing2.getCreatedTime());
    }
    
}
