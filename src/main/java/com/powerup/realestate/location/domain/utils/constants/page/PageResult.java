package com.powerup.realestate.location.domain.utils.constants.page;

import lombok.Getter;

import java.util.List;
@Getter
public class PageResult<T> {
    final List<T> content;
    final int page;
    final int size;
    final int totalPages;
    final int totalElements;

    public PageResult(List<T> content, int page, int size, int totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements/size);
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
