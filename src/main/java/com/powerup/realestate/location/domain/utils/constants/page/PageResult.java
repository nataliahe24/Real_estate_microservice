package com.powerup.realestate.location.domain.utils.constants.page;

import java.util.List;

public class PageResult<T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalPages;
    private int totalElements;

    public PageResult(List<T> content, int page, int size, int totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements/size);
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

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "content=" + content +
                ", page=" + page +
                ", size=" + size +
                ", totalPages=" + totalPages +
                '}';
    }
}
