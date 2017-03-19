package br.com.cristiana.mytravelsdiary.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristiana on 14/03/2017.
 */

public class Login implements Parcelable {

    @SerializedName("usuario")
    private String user;
    @SerializedName("senha")
    private String Password;

    public Login(){}

    protected Login(Parcel in) {
        user = in.readString();
        Password = in.readString();
    }
    public Login(String user, String password) {
        this.user = user;
        Password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    //Serialização do arquivo
    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(user);
        parcel.writeString(Password);
    }


}
