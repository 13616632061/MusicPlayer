package lyf.com.musicplayer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2019/7/18.
 */

public class MusicInfo implements Parcelable{

    String name;
    String picture;
    String url;
    String psesoner;
    String count;

    public MusicInfo() {
    }

    protected MusicInfo(Parcel in) {
        name = in.readString();
        picture = in.readString();
        url = in.readString();
        psesoner = in.readString();
        count = in.readString();
    }

    public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
        @Override
        public MusicInfo createFromParcel(Parcel in) {
            return new MusicInfo(in);
        }

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPsesoner() {
        return psesoner;
    }

    public void setPsesoner(String psesoner) {
        this.psesoner = psesoner;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(picture);
        dest.writeString(url);
        dest.writeString(psesoner);
        dest.writeString(count);
    }
}
