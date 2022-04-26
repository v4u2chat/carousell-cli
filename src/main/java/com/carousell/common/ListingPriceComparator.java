package com.carousell.common;

import java.util.Comparator;

public class ListingPriceComparator implements Comparator<Listing>{

    @Override
    public int compare(Listing listing1, Listing listing2) {
        return Double.compare(listing1.getPrice(), listing2.getPrice());
    }
    
}
