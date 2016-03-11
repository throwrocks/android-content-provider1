package rocks.throw20.contentprovider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by josel on 3/10/2016.
 */
public class DataCountriesContract {

    // The content authority
    public static final String CONTENT_AUTHORITY = "rocks.throw2.countries";
    //The content uri for the top level authority
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // The path to the countries database
    public static final String PATH_COUNTRIES = "countries";
    // The path to the country table
    public static final String PATH_COUNTRY_ID = "country/";
    // This is the complete path to the Countries database
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COUNTRIES)
            .build();

    // This Uri returns multiple records
    public  static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
            "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTRIES;
    // This Uri returns one record
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
            "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTRY_ID;

    /**
     * Country Entry
     * The inner class that defines the contents of the countries table
     */

    public static final class CountryEntry implements BaseColumns{
        // This is the complete path to the countries database
        public static final Uri ContentUri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COUNTRIES)
                .build();
        // Returns multiple records
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTRIES;
        // Returns a single record
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTRY_ID;

        // The internal id is used by all tables
        public static final String COUNTRIES_TABLE_NAME = "countries";

        //The countries table fields
        public static final String countryId = "id";
        public static final String countryName = "name";
        public static final String countryCapital = "capital";
        public static final String countryRegion = "region";
        public static final String countryPopulation = "population";

        // Build the countries Uri
        public static Uri buildCountriesUri() {
            return CONTENT_URI.buildUpon().build();
        }

        // Build the countries uri with a country id
        public static Uri buildCountriesUriWithID(long id) {
            return CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        }

        // Get the country id from the uri
        public static String getCountryIdFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
