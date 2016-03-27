package rocks.throw20.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rocks.throw20.contentprovider.dummy.DummyContent;

/**
 * Created by josel on 3/26/2016.
 */
public class CountriesListAdapter extends RecyclerView.Adapter<CountriesListAdapter.ViewHolder> {

    private final String LOG_TAG = CountriesListAdapter.class.getSimpleName();
    private boolean mTwoPane;
    private final Cursor mCursor;
    private final Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.country_id);
            mContentView = (TextView) view.findViewById(R.id.country_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

        public CountriesListAdapter(Context context, Cursor countriesCursor) {
            mContext = context;
            mCursor = countriesCursor;
            Log.e(LOG_TAG, "Constructor -> " + true);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e(LOG_TAG, "onCreateViewHolder -> " + true);
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_countries, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Log.e(LOG_TAG, "onBindviewHolder -> " + true);
            mCursor.moveToPosition(position);

            holder.mIdView.setText(mCursor.getString(1));
            holder.mContentView.setText(mCursor.getString(2));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        CharSequence itemId = holder.mContentView.getText();

                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(mContext, itemId, duration);
                        toast.show();


                    } else {

                        CharSequence itemId = holder.mContentView.getText();

                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(mContext, itemId, duration);
                        toast.show();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            Log.e(LOG_TAG, "getItemCount -> " + mCursor.getCount());
            return mCursor.getCount();
        }
}

