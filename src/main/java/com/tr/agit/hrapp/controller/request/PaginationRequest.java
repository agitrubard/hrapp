package com.tr.agit.hrapp.controller.request;

import java.io.Serializable;
import java.util.Objects;

public class PaginationRequest implements Serializable {

    private static final long serialVersionUID = -4209060708801047985L;
    private int page;
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PaginationRequest{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaginationRequest)) return false;
        PaginationRequest that = (PaginationRequest) o;
        return getPage() == that.getPage() &&
                getLimit() == that.getLimit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage(), getLimit());
    }
}