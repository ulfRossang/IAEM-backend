package se.handelsbanken.iaem.model;

import java.util.List;

public class PagedResult<T> {
    public int total;
    public List<T> items;

    public PagedResult() {}

    public PagedResult(int total, List<T> items) {
        this.total = total;
        this.items = items;
    }
}
