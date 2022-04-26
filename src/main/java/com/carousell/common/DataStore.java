package com.carousell.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class DataStore {

    private static final Logger LOGGER = Logger.getLogger(DataStore.class.getName());

    private Set<String> users = new HashSet<>();
    private Map<Integer, Listing> listingInfo = new ConcurrentHashMap<>();
    private Map<String, List<Listing>> catagories = new ConcurrentHashMap<>();

    private AtomicInteger listingCounter = new AtomicInteger(100000);

    public String deleteListing(String userName, Integer listingId) {

        if (users.contains(userName)) {
            Listing listing = listingInfo.get(listingId);
            if(listing==null){
                return "Error - listing does not exist";
            }
            if(userName.equals(listing.getUserName())){
                catagories.get(listing.getCategory()).remove(listing);
                listingInfo.remove(listingId);
                return "Success";

            }else{
                return "Error - listing owner mismatch";
            }
        } else {
            return "Error - unknown user";
        }
    }

    public String getListing(String userName, Integer listingId) {
        if (users.contains(userName)) {
            Listing listing = listingInfo.get(listingId);
            return listing != null ? listing.toString() : "Error - not found";
        } else {
            return "Error - unknown user";
        }
    }

    public String addListing(Listing listing, String userName, String catagory) {
        if (users.contains(userName)) {
            listing.setId(listingCounter.incrementAndGet());
            listingInfo.put(listing.getId(), listing);

            addToCategories(listing, userName, catagory);

        } else {
            return "Error - unknown user";
        }
        return "" + listing.getId();
    }

    private void addToCategories(Listing listing, String userName, String catagory) {
        List<Listing> categoryListings = catagories.getOrDefault(catagory, new ArrayList<>());
        categoryListings.add(listing);
        catagories.put(catagory, categoryListings);
        
        // catagories.forEach((key, value) -> LOGGER.log(Level.INFO,key + " " + value));
    }

    public boolean registerUser(String userName) {
        if (!users.contains(userName)) {
            return users.add(userName);
        }
        return false;
    }
}
