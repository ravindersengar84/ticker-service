package com.publisap.stockStats.util.message;

public final class AppMessage {

	public static class GeneralException {
		public static final String SYSTEM_EXCEPTION 				= "exception.server.error";
	}

	public static class ApplicationException {
		public static final String NO_RECORD_EXCEPTION 				= "No Record found";
		public static final String RESOURCE_NOT_FOUND_EXCEPTION 	= "Requested resource not found";
	}
	
}
