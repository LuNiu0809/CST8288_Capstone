/**
 * 
 */
package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.dao.BusinessDao;

/**
 * 
 */
public class BusinessService implements BusinessServiceInterface{
	
	private BusinessDao businessDao;
	
	public BusinessService() {
		businessDao = new BusinessDao();
	}

	@Override
	public int createBusiness(Business business) {
		return businessDao.createBusiness(business);
	}

	@Override
	public ArrayList<Business> readNumBusiness(int numBusiness) throws SQLException {
		return businessDao.readNumBusiness(numBusiness);
	}

	@Override
	public Business readBusiness(int businessID) throws SQLException {
		return businessDao.readBusiness(businessID);
	}

	@Override
	public int updateRatings(int businessID, int newPriceRating, int newOverallRating) {
		return businessDao.updateRatings(businessID, newPriceRating, newOverallRating);
	}

}
