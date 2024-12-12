package com.ecomm.productsvc.domain.models.external;

public class Rating {
    private Double rate;
    private Integer count;

    public Rating(Double rating, int ratingCount) {
        this.rate = rating;
        this.count = ratingCount;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}