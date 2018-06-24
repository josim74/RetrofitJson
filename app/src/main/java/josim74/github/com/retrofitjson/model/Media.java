package josim74.github.com.retrofitjson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JosimUddin on 24/06/2018.
 */

public class Media {
    @SerializedName("m")
    @Expose
    private String m;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "Media{" +
                "m='" + m + '\'' +
                '}';
    }
}
