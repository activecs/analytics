package com.kharkiv.diploma.google;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ColumnHeaders;
import com.google.api.services.analytics.model.GaData.ProfileInfo;
import com.google.api.services.analytics.model.GaData.Query;

public class CoreReportingApiReferenceSample {

  private static final String APPLICATION_NAME = "api-project-837863644970";
  private static final String TABLE_ID = "ga:90810667";
  private static final File DATA_STORE_DIR = new File(System.getProperty("user.home"), ".store/analytics_sample");

  private FileDataStoreFactory DATA_STORE_FACTORY;
  private HttpTransport HTTP_TRANSPORT;
  private final JsonFactory JSON_FACTORY = new JacksonFactory();

  
  public static void main(String[] args) throws Exception {
	  CoreReportingApiReferenceSample sample = new CoreReportingApiReferenceSample();
	  sample.getAndPrintReportData();
  }
  
  public void getAndPrintReportData() throws Exception{
	  try {
	      HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	      DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
	      Analytics analytics = initializeAnalytics();
	      GaData gaData = executeDataQuery(analytics, TABLE_ID);
	
	      printReportInfo(gaData);
	      printProfileInfo(gaData);
	      printQueryInfo(gaData);
	      printPaginationInfo(gaData);
	      printTotalsForAllResults(gaData);
	      printColumnHeaders(gaData);
	      printDataTable(gaData);
	
	    } catch (GoogleJsonResponseException e) {
	      System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
	          + e.getDetails().getMessage());
	    }
  }

  /** Authorizes the installed application to access user's protected data. */
  private Credential authorize() throws Exception {
    // load client secrets
	InputStreamReader clientSecretStream =  new InputStreamReader(CoreReportingApiReferenceSample.class.getResourceAsStream("/client_secrets.json"));
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecretStream);
    
    if (isNotSecretLoaded(clientSecrets)) {
      System.out.println(
          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=analytics "
          + "into analytics-cmdline-sample/src/main/resources/client_secrets.json");
      System.exit(1);
    }
    
    // set up authorization code flow
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, asList(AnalyticsScopes.ANALYTICS_READONLY))
    	.setDataStoreFactory(DATA_STORE_FACTORY).build();
    // authorize
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

	private boolean isNotSecretLoaded(GoogleClientSecrets clientSecrets) {
		return clientSecrets.getDetails().getClientId().startsWith("Enter") || clientSecrets.getDetails().getClientSecret().startsWith("Enter ");
	}

  /**
   * Performs all necessary setup steps for running requests against the API.
   *
   * @return an initialized Analytics service object.
   *
   * @throws Exception if an issue occurs with OAuth2Native authorize.
   */
  private Analytics initializeAnalytics() throws Exception {
    // Authorization.
    Credential credential = authorize();
    // Set up and Google Analytics API client.
    Analytics client =  new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
    return client;
  }

  /**
   * Returns the top 25 organic search keywords and traffic sources by visits. The Core Reporting
   * API is used to retrieve this data.
   *
   * @param analytics the Analytics service object used to access the API.
   * @param tableId the table ID from which to retrieve data.
   * @return the response from the API.
   * @throws IOException if an API error occured.
   */
  private GaData executeDataQuery(Analytics analytics, String tableId) throws IOException {
    return analytics.data().ga().get(tableId, // Table Id.
        "2014-09-01", // Start date.
        "2014-11-01", // End date.
        "ga:visits") // Metrics.
        .setDimensions("ga:source")
        .setSort("-ga:visits,ga:source")
        .setMaxResults(25)
        .execute();
  }

  /**
   * Prints general information about this report.
   *
   * @param gaData the data returned from the API.
   */
  private void printReportInfo(GaData gaData) {
    System.out.println();
    System.out.println("Response:");
    System.out.println("ID:" + gaData.getId());
    System.out.println("Self link: " + gaData.getSelfLink());
    System.out.println("Kind: " + gaData.getKind());
    System.out.println("Contains Sampled Data: " + gaData.getContainsSampledData());
  }

  /**
   * Prints general information about the profile from which this report was accessed.
   *
   * @param gaData the data returned from the API.
   */
  private void printProfileInfo(GaData gaData) {
    ProfileInfo profileInfo = gaData.getProfileInfo();

    System.out.println("Profile Info");
    System.out.println("Account ID: " + profileInfo.getAccountId());
    System.out.println("Web Property ID: " + profileInfo.getWebPropertyId());
    System.out.println("Internal Web Property ID: " + profileInfo.getInternalWebPropertyId());
    System.out.println("Profile ID: " + profileInfo.getProfileId());
    System.out.println("Profile Name: " + profileInfo.getProfileName());
    System.out.println("Table ID: " + profileInfo.getTableId());
  }

  /**
   * Prints the values of all the parameters that were used to query the API.
   *
   * @param gaData the data returned from the API.
   */
  private void printQueryInfo(GaData gaData) {
    Query query = gaData.getQuery();

    System.out.println("Query Info:");
    System.out.println("Ids: " + query.getIds());
    System.out.println("Start Date: " + query.getStartDate());
    System.out.println("End Date: " + query.getEndDate());
    System.out.println("Metrics: " + query.getMetrics()); // List
    System.out.println("Dimensions: " + query.getDimensions()); // List
    System.out.println("Sort: " + query.getSort()); // List
    System.out.println("Segment: " + query.getSegment());
    System.out.println("Filters: " + query.getFilters());
    System.out.println("Start Index: " + query.getStartIndex());
    System.out.println("Max Results: " + query.getMaxResults());
  }

  /**
   * Prints common pagination information.
   *
   * @param gaData the data returned from the API.
   */
  private void printPaginationInfo(GaData gaData) {
    System.out.println("Pagination Info:");
    System.out.println("Previous Link: " + gaData.getPreviousLink());
    System.out.println("Next Link: " + gaData.getNextLink());
    System.out.println("Items Per Page: " + gaData.getItemsPerPage());
    System.out.println("Total Results: " + gaData.getTotalResults());
  }

  /**
   * Prints the total metric value for all rows the query matched.
   *
   * @param gaData the data returned from the API.
   */
  private void printTotalsForAllResults(GaData gaData) {
    System.out.println("Metric totals over all results:");
    Map<String, String> totalsMap = gaData.getTotalsForAllResults();
    for (Map.Entry<String, String> entry : totalsMap.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
  }

  /**
   * Prints the information for each column. The reporting data from the API is returned as rows of
   * data. The column headers describe the names and types of each column in rows.
   *
   * @param gaData the data returned from the API.
   */
  private void printColumnHeaders(GaData gaData) {
    System.out.println("Column Headers:");

    for (ColumnHeaders header : gaData.getColumnHeaders()) {
      System.out.println("Column Name: " + header.getName());
      System.out.println("Column Type: " + header.getColumnType());
      System.out.println("Column Data Type: " + header.getDataType());
    }
  }

  /**
   * Prints all the rows of data returned by the API.
   *
   * @param gaData the data returned from the API.
   */
  private void printDataTable(GaData gaData) {
    if (gaData.getTotalResults() > 0) {
      System.out.println("Data Table:");

      // Print the column names.
      for (ColumnHeaders header : gaData.getColumnHeaders())
        System.out.format("%-32s", header.getName());
      System.out.println();

      // Print the rows of data.
      for (List<String> rowValues : gaData.getRows()) {
        for (String value : rowValues) {
          System.out.format("%-32s", value);
        }
        System.out.println();
      }
    } else {
      System.out.println("No data");
    }
  }
}
