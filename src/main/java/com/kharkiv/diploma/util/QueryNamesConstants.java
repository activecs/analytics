package com.kharkiv.diploma.util;

public final class QueryNamesConstants {

	private QueryNamesConstants() {
	}

	public static class UserQueries {
		private UserQueries() {
		}

		public static final String GET_ALL = "Users.getAll";
		public static final String GET_BY_ID = "Users.getById";
		public static final String GET_BY_USERNAME = "Users.getByUsername";

		public static final String DELETE_BY_ID = "User.deleteById";
		public static final String DELETE_BY_USERNAME = "User.deleteByUsername";
	}

}
