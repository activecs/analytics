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
	
	public static class SessionsQueries {
		private SessionsQueries() {
		}

		public static final String GET_ALL = "Session.getAll";
		public static final String GET_BY_ID = "Session.getById";
		public static final String DELETE_BY_ID = "Session.deleteById";
	}
	
	public static class EventQueries {
		private EventQueries() {
		}

		public static final String GET_ALL = "Event.getAll";
		public static final String GET_BY_ID = "Event.getById";
		public static final String GET_BY_CATEGORY_AND_ACTION = "Event.getByCategoryAndAction";
		public static final String DELETE_BY_ID = "Event.deleteById";
	}
	
	public static class OrganicRefererQueries {
		private OrganicRefererQueries() {
		}

		public static final String GET_ALL = "OrganicReferer.getAll";
	}
	

}
