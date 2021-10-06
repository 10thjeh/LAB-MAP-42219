package umn.ac.id.uts_lab;

import android.os.Parcel;
import android.os.Parcelable;

public class Sound implements Parcelable {
    String name, description;
    int sound_id;

    protected Sound(Parcel in) {
        name = in.readString();
        description = in.readString();
        sound_id = in.readInt();
    }

    public static final Creator<Sound> CREATOR = new Creator<Sound>() {
        @Override
        public Sound createFromParcel(Parcel in) {
            return new Sound(in);
        }

        @Override
        public Sound[] newArray(int size) {
            return new Sound[size];
        }
    };

    public int getSound_id() {
        return sound_id;
    }

    public Sound(){}

    public Sound(String name, String description, int sound_id) {
        this.name = name;
        this.description = description;
        this.sound_id = sound_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(sound_id);
    }
}
