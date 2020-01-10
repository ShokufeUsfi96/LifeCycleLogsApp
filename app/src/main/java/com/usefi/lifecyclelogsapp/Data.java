
package com.usefi.lifecyclelogsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("timings")
    @Expose
    private com.usefi.lifecyclelogsapp.Timings timings;
    @SerializedName("date")
    @Expose
    private com.usefi.lifecyclelogsapp.Date date;
    @SerializedName("meta")
    @Expose
    private com.usefi.lifecyclelogsapp.Meta meta;

    public com.usefi.lifecyclelogsapp.Timings getTimings() {
        return timings;
    }

    public void setTimings(com.usefi.lifecyclelogsapp.Timings timings) {
        this.timings = timings;
    }

    public com.usefi.lifecyclelogsapp.Date getDate() {
        return date;
    }

    public void setDate(com.usefi.lifecyclelogsapp.Date date) {
        this.date = date;
    }

    public com.usefi.lifecyclelogsapp.Meta getMeta() {
        return meta;
    }

    public void setMeta(com.usefi.lifecyclelogsapp.Meta meta) {
        this.meta = meta;
    }

}
