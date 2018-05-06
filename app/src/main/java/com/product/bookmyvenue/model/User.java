package com.product.bookmyvenue;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class User implements Parcelable {

    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    };
    private String name;
    private String pic;
    private String address;
    private String mobile;
    private Float rate;
    private Integer rateCount;
    private Integer visit;
    private String email;
    private String createdOn;
    private String lastLogin;
    private String geo;
    private Integer posts;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected User(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.pic = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.rate = ((Float) in.readValue((Float.class.getClassLoader())));
        this.rateCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.visit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.lastLogin = ((String) in.readValue((String.class.getClassLoader())));
        this.geo = ((String) in.readValue((String.class.getClassLoader())));
        this.posts = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(pic);
        dest.writeValue(address);
        dest.writeValue(mobile);
        dest.writeValue(rate);
        dest.writeValue(rateCount);
        dest.writeValue(visit);
        dest.writeValue(email);
        dest.writeValue(createdOn);
        dest.writeValue(lastLogin);
        dest.writeValue(geo);
        dest.writeValue(posts);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}