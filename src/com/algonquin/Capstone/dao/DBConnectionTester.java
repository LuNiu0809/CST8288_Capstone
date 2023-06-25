/**
 * 
 */
package com.algonquin.Capstone.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

/**
 * Tests the DBConnection Class
 */
class DBConnectionTester {
	
	Connection connnection;

	/**
	 * Test the connection to the DB
	 */
	@Test
	void testDBConnection() {
		

			assertNotNull(connnection = DBConnection.getConnectionToDatabase());

	}

}
