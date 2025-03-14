package com.powerup.realestate.category.domain.utils.page;

import java.util.List;

public class PageResult <T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalpages;
    private int totalelements;

    public PageResult(List<T> content, int page, int size, int totalelements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalelements = totalelements;
        this.totalpages = (int) Math.ceil((double) totalelements/size);
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public int getTotalelements() {
        return totalelements;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "content=" + content +
                ", page=" + page +
                ", size=" + size +
                ", totalpages=" + totalpages +
                '}';
    }
}
