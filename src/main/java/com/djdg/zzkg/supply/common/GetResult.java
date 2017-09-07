package com.djdg.zzkg.supply.common;

import java.io.Serializable;

public class GetResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private long totalpage;
    private long currPage;
    private long total;
    private long count;
    private boolean hasNext;

    private T data;

    public GetResult() {
    }


    public long getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(long totalpage) {
        this.totalpage = totalpage;
    }

    public long getCurrPage() {
        return currPage;
    }

    public void setCurrPage(long currPage) {
        this.currPage = currPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}