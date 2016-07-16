
package me.alam.sadar.stockhawk.datamodel;


import android.os.Parcel;
import android.os.Parcelable;



public class Quote implements Parcelable{



    private String date;

    private String open;

    private String high;

    private String low;

    private String close;

    private String volume;


    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The Date
     */
    public void setDate(String date) {
        this.date = formatDate(date);
    }

    /**
     * 
     * @return
     *     The open
     */
    public String getOpen() {
        return open;
    }

    /**
     * 
     * @param open
     *     The Open
     */
    public void setOpen(String open) {
        this.open = open;
    }

    /**
     * 
     * @return
     *     The high
     */
    public String getHigh() {
        return high;
    }

    /**
     * 
     * @param high
     *     The High
     */
    public void setHigh(String high) {
        this.high = high;
    }

    /**
     * 
     * @return
     *     The low
     */
    public String getLow() {
        return low;
    }

    /**
     * 
     * @param low
     *     The Low
     */
    public void setLow(String low) {
        this.low = low;
    }

    /**
     * 
     * @return
     *     The close
     */
    public String getClose() {
        return close;
    }

    /**
     * 
     * @param close
     *     The Close
     */
    public void setClose(String close) {
        this.close = close;
    }

    /**
     * 
     * @return
     *     The volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * 
     * @param volume
     *     The Volume
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }


    @Override
    public int describeContents() {
        return 0;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(open);
        dest.writeString(high);
        dest.writeString(low);
        dest.writeString(close);
        dest.writeString(volume);
    }

    public Quote(Parcel in){
        date = in.readString();
        open = in.readString();
        high = in.readString();
        low = in.readString();
        close = in.readString();
        volume = in.readString();
    }

    public Quote(){}

    public static final Parcelable.Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel source) {
            return new Quote(source) ;
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };
}
