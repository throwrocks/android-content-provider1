package rocks.throw20.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joselopez on 3/10/16.
 */
class DataCountriesJSONParser {

    private static final String LOG_TAG = DataCountriesAPI.class.getSimpleName();

    ContentValues[] mContentValues;

    private final Context mContext;


    // Constructor
    public DataCountriesJSONParser(Context context){

        this.mContext = context;

    }

    public ContentValues[] getCountriesFromJSON(String countriesJSONString ){
        try {

            // Create the countriesJSON object with the countriesJSONString parameter
            JSONArray countriesArray = new JSONArray(countriesJSONString);

            // These are the individual fields
            final String fieldCountryId = "id";
            final String fieldCountryName = "name";
            final String fieldCountryCapital = "capital";
            final String fieldCountryRegion = "region";
            final String fieldCountryPopulation = "population";

            int countriesQty = countriesArray.length();
            //Log.e(LOG_TAG, "CountriesQty: " + countriesQty);


            mContentValues = new ContentValues[countriesQty];

            // Loop through the countriesArray to parse each Json object needed
            for (int i = 0; i < countriesQty; i++) {
                JSONObject countryRecord = countriesArray.getJSONObject(i);
                //Log.e(LOG_TAG, "countryRecord: " + countryRecord);

                // Parse the individual data elements needed
                String countryName = countryRecord.getString(fieldCountryName);
                String countryCapital = countryRecord.getString(fieldCountryCapital);
                String countryRegion = countryRecord.getString(fieldCountryRegion);
                Long countryPopulation = countryRecord.getLong(fieldCountryPopulation);
                //Log.e(LOG_TAG, "Parsing string: " + countryName + " " + countryCapital + " " + countryRegion);

                //----------------------------------------------------------------------------------
                // This method allows you to store (insert or update) each record as you loop through
                // This could be useful but it should happen outside this class
                //----------------------------------------------------------------------------------
                /*DataCountriesStoreRecord store = new DataCountriesStoreRecord(
                        mContext,
                        i,
                        countryName,
                        countryCapital,
                        countryRegion,
                        countryPopulation
                );*/

                // Create a content values object
                ContentValues countryValues = new ContentValues();
                countryValues.put(fieldCountryId, i);
                countryValues.put(fieldCountryName, countryName);
                countryValues.put(fieldCountryCapital, countryCapital);
                countryValues.put(fieldCountryRegion, countryRegion);
                countryValues.put(fieldCountryPopulation, countryPopulation);


                //----------------------------------------------------------------------------------
                // Build an array of content values to be returned and processed outside this method
                //----------------------------------------------------------------------------------
                mContentValues[i] = countryValues;
            }
        }
        catch (JSONException e) {
        e.printStackTrace();
        }

        return mContentValues;
    }

}

