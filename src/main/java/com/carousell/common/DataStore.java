package com.carousell.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataStore {

    private static final String DSC = "dsc";
    private static final String SUCCESS = "Success";
    private static final String SORT_PRICE = "sort_price";
    private static final String ERROR_NOT_FOUND = "Error - not found";
    private static final String ERROR_UNKNOWN_USER = "Error - unknown user";
    private static final String ERROR_CATEGORY_NOT_FOUND = "Error - category not found";
    private static final String ERROR_LISTING_DOES_NOT_EXIST = "Error - listing does not exist";
    private static final String ERROR_LISTING_OWNER_MISMATCH = "Error - listing owner mismatch";

    private static final Logger LOGGER = Logger.getLogger(DataStore.class.getName());

    private String topCategory;
    private Set<String> users = new HashSet<>();
    private Map<Integer, Listing> listingInfo = new ConcurrentHashMap<>();
    private Map<String, List<Listing>> catagories = new ConcurrentHashMap<>();

    private AtomicInteger listingCounter = new AtomicInteger(100000);

    public String getTopCategory(String userName) {
        if (!users.contains(userName)) {
            return ERROR_UNKNOWN_USER;
        }

        return this.topCategory;
    }

    public String getCategory(String catagory, String userName, String sortType, String direction) {
        if (!catagories.containsKey(catagory)) {
            return ERROR_CATEGORY_NOT_FOUND;
        }
        if (!users.contains(userName)) {
            return ERROR_UNKNOWN_USER;
        }

        Comparator<Listing> comparator = SORT_PRICE.equalsIgnoreCase(sortType) ? new ListingPriceComparator()
                : new ListingTimeComparator();
        if (DSC.equalsIgnoreCase(direction)) {
            comparator.reversed();
            System.out.println(sortType+"\t"+direction);
        }

        return catagories.get(catagory).stream().sorted(comparator).map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public String deleteListing(String userName, Integer listingId) {

        if (users.contains(userName)) {
            Listing listing = listingInfo.get(listingId);
            if (listing == null) {
                return ERROR_LISTING_DOES_NOT_EXIST;
            }
            if (userName.equals(listing.getUserName())) {
                catagories.get(listing.getCategory()).remove(listing);
                listingInfo.remove(listingId);
                updateTopCategory();
                return SUCCESS;

            } else {
                return ERROR_LISTING_OWNER_MISMATCH;
            }
        } else {
            return ERROR_UNKNOWN_USER;
        }
    }

    public String getListing(String userName, Integer listingId) {
        if (users.contains(userName)) {
            Listing listing = listingInfo.get(listingId);
            return listing != null ? listing.toString() : ERROR_NOT_FOUND;
        } else {
            return ERROR_UNKNOWN_USER;
        }
    }

    public String addListing(Listing listing, String userName, String catagory) {
        if (users.contains(userName)) {
            listing.setId(listingCounter.incrementAndGet());
            listingInfo.put(listing.getId(), listing);

            addToCategories(listing, userName, catagory);

        } else {
            return ERROR_UNKNOWN_USER;
        }
        return "" + listing.getId();
    }

    private void addToCategories(Listing listing, String userName, String catagory) {
        List<Listing> categoryListings = catagories.getOrDefault(catagory, new ArrayList<>());
        categoryListings.add(listing);
        catagories.put(catagory, categoryListings);
        updateTopCategory();
    }

    private void updateTopCategory() {

        String topCategory = null;
        Integer topCategoryCount = Integer.MIN_VALUE;

        for (String key : catagories.keySet()) {
            if (catagories.get(key).size() > topCategoryCount) {
                topCategoryCount = catagories.get(key).size();
                topCategory = key;
            }
        }

        this.topCategory = topCategory;
        // LOGGER.info("Top Category >> " + topCategory);
    }

    public boolean registerUser(String userName) {
        if (!users.contains(userName)) {
            return users.add(userName);
        }
        return false;
    }

}
