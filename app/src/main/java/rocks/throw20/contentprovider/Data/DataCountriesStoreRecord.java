package rocks.throw20.contentprovider.Data;

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
            ContentValues countryValues
    ) {
        this.mContext = context;

        // Get the name to see if it already exists
        String name = countryValues.getAsString(DataCountriesContract.CountryEntry.countryName);

        // Query the database for the name
        Cursor countriesCursor = mContext.getContentResolver().query(
               DataCountriesContract.CountryEntry.CONTENT_URI,
               new String[]{DataCountriesContract.CountryEntry.countryId},
               DataCountriesContract.CountryEntry.countryName + " = ?",
               new String[]{name},
               null);

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

            // Update the record
            mContext.getContentResolver().update(
                    DataCountriesContract.CountryEntry.CONTENT_URI,
                    countryValues,
                    DataCountriesContract.CountryEntry.countryName + " = ?",
                    new String[]{name}
            );
        }

    }
}
