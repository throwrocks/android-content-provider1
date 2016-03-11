package rocks.throw20.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by josel on 3/10/2016.
 */
public class DataCountriesStoreRecord {

    private static final String LOG_TAG = DataCountriesStoreRecord.class.getSimpleName();
    Context mContext;

    public DataCountriesStoreRecord(
            Context context,
            Integer id,
            String name,
            String capital,
            String region,
            Long population
    ) {
        this.mContext = context;
        // First, check if the country exists in the db
        Cursor countriesCursor = mContext.getContentResolver().query(
               DataCountriesContract.CountryEntry.CONTENT_URI,
               new String[]{DataCountriesContract.CountryEntry.countryId},
               DataCountriesContract.CountryEntry.countryId + " = ?",
               new String[]{Integer.toString(id)},
               null);
        //Log.e(LOG_TAG, "cursor:  " + countriesCursor);
        // Create the content values
        ContentValues countryValues = new ContentValues();
        // Update database

        countryValues.put(DataCountriesContract.CountryEntry.countryId, id);
        countryValues.put(DataCountriesContract.CountryEntry.countryName, name);
        countryValues.put(DataCountriesContract.CountryEntry.countryCapital, capital);
        countryValues.put(DataCountriesContract.CountryEntry.countryRegion, region);
        countryValues.put(DataCountriesContract.CountryEntry.countryPopulation, population);
        //Log.e(LOG_TAG, "cursorCount " + countriesCursor.getCount());

        // If the record does not exist, add it to the database
        if ( countriesCursor == null) {
            Log.e(LOG_TAG, "add record");
            mContext.getContentResolver().insert(
                    DataCountriesContract.CountryEntry.CONTENT_URI,
                    countryValues
            );

        }else {
            countriesCursor.close();
            Log.e(LOG_TAG, "records exists - do nothing");

            // Update Record
            String[] x = new String[]{Long.toString(id)};
            mContext.getContentResolver().update(
                    DataCountriesContract.CountryEntry.CONTENT_URI,
                    countryValues,
                    DataCountriesContract.CountryEntry.countryName + " = ?",
                    x
            );
        }

    }
}
