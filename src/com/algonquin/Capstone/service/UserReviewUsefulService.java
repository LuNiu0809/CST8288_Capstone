/**
 * 
 */
package com.algonquin.Capstone.service;

import java.sql.SQLException;

import com.algonquin.Capstone.dao.UserReviewUsefulDao;

/**
 * Acts as a service between the User Review Useful Dao Class and the Web Interface. 
 */
public class UserReviewUsefulService implements UserReviewUsefulInterface{
	
	private UserReviewUsefulDao dao;

	@Override
	public int addUserReviewHelpfulRecord(int userID, int reviewID) {
		dao = new UserReviewUsefulDao();
		return dao.addUserReviewHelpfulRecord(userID, reviewID);
	}

	@Override
	public int deleteUserReviewHelpfulRecord(int userID, int reviewID) {
		dao = new UserReviewUsefulDao();
		return dao.deleteUserReviewHelpfulRecord(userID, reviewID);
	}

	@Override
	public boolean checkUserReviewHelpfulRecord(int userID, int reviewID) throws SQLException {
		dao = new UserReviewUsefulDao();
		return dao.checkUserReviewHelpfulRecord(userID, reviewID);
	}

}
