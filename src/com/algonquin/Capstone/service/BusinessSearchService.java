package com.algonquin.Capstone.service;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.dao.business.BusinessDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessSearchService {

    private BusinessDao businessDao;

    public BusinessSearchService() {
        businessDao = new BusinessDao();
    }
    /**
     * Search for businesses by their name or type of food.
     *
     * @param keyword The keyword to search for (business name or type of food).
     * @return A list of businesses that match the search criteria.
     * @throws SQLException If there's an error while accessing the database.
     */
    public List<Business> searchBusinesses(String keyword) throws SQLException {
        List<Business> businesses = new ArrayList<>();

        // Search by business name
        List<Business> businessesByName = businessDao.searchBusinessesByName(keyword);
        businesses.addAll(businessesByName);

        // Search by type of food
        List<Business> businessesByFoodType = businessDao.searchBusinessesByFoodType(keyword);
        businesses.addAll(businessesByFoodType);

        return businesses;
    }
}