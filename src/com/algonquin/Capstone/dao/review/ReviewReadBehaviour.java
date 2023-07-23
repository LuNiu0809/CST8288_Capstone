package com.algonquin.Capstone.dao.review;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ReviewReadBehaviour {
	
	public PreparedStatement read(int businessID, int numReviews) throws SQLException;

}
