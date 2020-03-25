package dehant.rayan.tpfinal;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {
    private String image;
    private String nom;
    private String continent;
    private String sousContinent;
    private String capitale;
    private int habitants;

    public Country(String n,String cont,String sousCont,String cap,int hab,String image){
        nom=n;
        continent=cont;
        sousContinent=sousCont;
        capitale=cap;
        habitants=hab;
        this.image=image;
    }

    public Country(Parcel in){
        nom=in.readString();
        continent=in.readString();
        sousContinent=in.readString();
        capitale=in.readString();
        image=in.readString();
        habitants=in.readInt();
    }

    public static final Creator<Country> CREATOR=new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest,int flags){
        dest.writeString(nom);
        dest.writeString(continent);
        dest.writeString(sousContinent);
        dest.writeString(capitale);
        dest.writeString(image);
        dest.writeInt(habitants);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getSousContinent() {
        return sousContinent;
    }

    public void setSousContinent(String sousContinent) {
        this.sousContinent = sousContinent;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public int getHabitants() {
        return habitants;
    }

    public void setHabitants(int habitants) {
        this.habitants = habitants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
