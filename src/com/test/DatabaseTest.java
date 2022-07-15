package com.test;

import org.junit.Before;
import org.junit.Test;

import com.main.db.Database;

public class DatabaseTest {
	Database db;
	
	@Before //executes once before each test case
	public void init() {
		db = new Database();
	}
	
	@Test
	public void test1() {
		//INSERT YOUR TESTS HERE
	}
}
