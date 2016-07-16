package me.alam.sadar.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import me.alam.sadar.stockhawk.R;
import me.alam.sadar.stockhawk.data.QuoteColumns;
import me.alam.sadar.stockhawk.data.QuoteProvider;

/**
 * Created by yesalam on 16-07-2016.
 */
public class WidgetDataService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StockQuoteWidgetFactory(getApplicationContext(),intent);
    }

    public class StockQuoteWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

        private Cursor mCursor;
        private Context mContext;
        private int mWidgetId;

        public StockQuoteWidgetFactory(Context mContext, Intent intent) {
            this.mContext = mContext;
            this.mWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

        }

        @Override
        public void onCreate() {
            mCursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                            QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                    QuoteColumns.ISCURRENT + " = ?",
                    new String[]{"1"},
                    null);
        }

        @Override
        public void onDataSetChanged() {
            if (mCursor != null) {
                mCursor.close();
            }
            mCursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                            QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                    QuoteColumns.ISCURRENT + " = ?",
                    new String[]{"1"},
                    null);
        }

        @Override
        public void onDestroy() {
            if (mCursor != null) {
                mCursor.close();
            }
        }

        @Override
        public int getCount() {
            return mCursor.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.list_item_quote);

            if (mCursor.moveToPosition(position)) {
                String symbol = mCursor.getString(1);
                String bidprice = mCursor.getString(2) ;
                remoteViews.setTextViewText(R.id.stock_symbol, symbol);
                remoteViews.setTextViewText(R.id.bid_price, bidprice );
                remoteViews.setTextViewText(R.id.change, mCursor.getString(3));

                if (mCursor.getInt(mCursor.getColumnIndex("is_up")) == 1) {
                    remoteViews.setInt(R.id.change, "setBackgroundResource",
                            R.drawable.percent_change_pill_green);
                } else {
                    remoteViews.setInt(R.id.change, "setBackgroundResource",
                            R.drawable.percent_change_pill_red);
                }
                final Intent fillInIntent = new Intent();
                fillInIntent.putExtra("symbol",symbol) ;
                fillInIntent.putExtra("bid",bidprice) ;
               // fillInIntent.setData(weatherUri);
                remoteViews.setOnClickFillInIntent(R.id.list_item_layout, fillInIntent);
            }

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
