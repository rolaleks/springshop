package ru.geekbrains.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public class ProductSearch {

    private BigDecimal minCost;

    private BigDecimal maxCost;

    private Integer page = 1;

    private Integer pageSize = 5;

    private Integer totalPages;

    private String title;

    public ProductSearch() {
    }

    public ProductSearch(BigDecimal minCost, BigDecimal maxCost, String title) {
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.title = title;
    }

    public BigDecimal getMinCost() {
        return minCost;
    }

    public void setMinCost(BigDecimal minCost) {
        this.minCost = minCost;
    }

    public BigDecimal getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(BigDecimal maxCost) {
        this.maxCost = maxCost;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page != null ? page : this.page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize != null ? pageSize : this.pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Pageable getPageable() {
        return PageRequest.of(getPage() - 1, getPageSize());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
