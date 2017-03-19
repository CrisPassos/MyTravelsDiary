package br.com.cristiana.mytravelsdiary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristiana on 18/03/2017.
 */

public class Travel implements Parcelable{

    private int id;
    private String destiny;
    private String days;
    private String value;
    private String departureDate;
    private String returnDate;
    private String hotel;
    private String touristsHotspots;

    public Travel(){}

    public Travel(int id, String destiny, String days, String value, String departureDate, String returnDate, String hotel, String touristsHotspots) {
        this.id = id;
        this.destiny = destiny;
        this.days = days;
        this.value = value;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.hotel = hotel;
        this.touristsHotspots = touristsHotspots;
    }

    public Travel(String destiny, String days, String value, String departureDate, String returnDate, String hotel, String touristsHotspots) {
        this.destiny = destiny;
        this.days = days;
        this.value = value;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.hotel = hotel;
        this.touristsHotspots = touristsHotspots;
    }

    protected Travel(Parcel in) {
        id = in.readInt();
        destiny = in.readString();
        days = in.readString();
        value = in.readString();
        departureDate = in.readString();
        returnDate = in.readString();
        hotel = in.readString();
        touristsHotspots = in.readString();
    }

    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getTouristsHotspots() {
        return touristsHotspots;
    }

    public void setTouristsHotspots(String touristsHotspots) {
        this.touristsHotspots = touristsHotspots;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(destiny);
        parcel.writeString(days);
        parcel.writeString(value);
        parcel.writeString(departureDate);
        parcel.writeString(returnDate);
        parcel.writeString(hotel);
        parcel.writeString(touristsHotspots);
    }
}
