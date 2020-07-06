package ru.geekbrains.persist.enity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 5, max = 32)
    @Column(nullable = false)
    private String title;

    @DecimalMin("0.01")
    @DecimalMax("10000000")
    @Column(nullable = false)
    private BigDecimal cost;

    @Column(length = 512)
    private String description;

    @Column
    private Integer category_id;

    @ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(name = "product_raw_photos",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<PhotoRaw> photoRawList;

    @ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(name = "product_path_photos",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<PhotoPath> photoPathList;

    public Product() {
    }

    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Integer categoryId) {
        this.category_id = categoryId;
    }

    public List<PhotoRaw> getPhotoRawList() {
        return photoRawList;
    }

    public void setPhotoRawList(List<PhotoRaw> photoRawList) {
        this.photoRawList = photoRawList;
    }

    public List<PhotoPath> getPhotoPathList() {
        return photoPathList;
    }

    public void setPhotoPathList(List<PhotoPath> photoPathList) {
        this.photoPathList = photoPathList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
