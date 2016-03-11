package rocks.throw20.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

/**
 * Created by josel on 3/10/2016.
 */
public class DataCountriesStoreBulk {

    private static final String LOG_TAG = DataCountriesStoreRecord.class.getSimpleName();
    Context mContext;

    /**
     * Constructor
     * @param context the context
     * @param countryValues the ContentValues[]
     * @param method the method of inserting
     *               bulk = bulk insert, use this when populating for the first time
     *               insert/update = loops through, created if it doesn't exist, updates if it does
     */
    public DataCountriesStoreBulk(
            Context context,
            ContentValues[] countryValues,
            String method
    ){
        Log.e(LOG_TAG, "Lets bulk!");
        this.mContext = context;

        mContext.getContentResolver().bulkInsert(
                DataCountriesContract.CountryEntry.CONTENT_URI,
                countryValues
        );

    }


}
