
package me.alam.sadar.stockhawk.datamodel ;

import android.os.Parcel;
import android.os.Parcelable;

public class Meta implements Parcelable {


    private String uri;
    private String ticker;
    private String companyName;
    private String exchangeName;
    private String unit;
    private String timestamp;
    private String firstTrade;
    private String lastTrade;
    private String currency;
    private String previousClosePrice;


    /**
     * 
     * @return
     *     The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 
     * @return
     *     The ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * 
     * @param ticker
     *     The ticker
     */
    public void setTicker(String ticker) {
        this.ticker = ticker.toUpperCase();
    }

    /**
     * 
     * @return
     *     The companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 
     * @param companyName
     *     The Company-Name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 
     * @return
     *     The exchangeName
     */
    public String getExchangeName() {
        return exchangeName;
    }

    /**
     * 
     * @param exchangeName
     *     The Exchange-Name
     */
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    /**
     * 
     * @return
     *     The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The firstTrade
     */
    public String getFirstTrade() {
        return firstTrade;
    }

    /**
     * 
     * @param firstTrade
     *     The first-trade
     */
    public void setFirstTrade(String firstTrade) {
        this.firstTrade = formatDate(firstTrade);
    }

    /**
     * 
     * @return
     *     The lastTrade
     */
    public String getLastTrade() {
        return lastTrade;
    }

    /**
     * 
     * @param lastTrade
     *     The last-trade
     */
    public void setLastTrade(String lastTrade) {
        this.lastTrade = formatDate(lastTrade);
    }

    /**
     * 
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 
     * @return
     *     The previousClosePrice
     */
    public String getPreviousClosePrice() {
        return previousClosePrice;
    }

    /**
     * 
     * @param previousClosePrice
     *     The previous_close_price
     */
    public void setPreviousClosePrice(String previousClosePrice) {
        this.previousClosePrice = previousClosePrice;
    }

    private String formatDate(String date){
        StringBuilder builder = new StringBuilder() ;
        builder.append(date.substring(6))
                .append("/")
                .append(date.substring(4,6))
                .append("/")
                .append(date.substring(0,4));

        return builder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uri);
        dest.writeString(ticker);
        dest.writeString(companyName);
        dest.writeString(exchangeName);
        dest.writeString(unit);
        dest.writeString(timestamp);
        dest.writeString(firstTrade);
        dest.writeString(lastTrade);
        dest.writeString(currency);
        dest.writeString(previousClosePrice);
    }

    public Meta(Parcel in){
        uri = in.readString();
        ticker = in.readString() ;
        companyName  = in.readString() ;
        exchangeName  = in.readString() ;
        unit   = in.readString() ;
        timestamp  = in.readString() ;
        firstTrade  = in.readString() ;
        lastTrade  = in.readString() ;
        currency = in.readString() ;
        previousClosePrice = in.readString() ;
    }

    public Meta(){}

    public static final Parcelable.Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}
