package ru.geekbrains.persist.repl;

import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.persist.enity.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepl implements Serializable {

    private Long id;

    private String title;

    private BigDecimal cost;

    private String description;

    private Integer category_id;

    private List<PhotoPathRepl> photoPathList;

    private List<PhotoRawRepl> photoRawList;

    private MultipartFile[] newPhotos;

    public ProductRepl() {
    }

    public ProductRepl(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.description = product.getDescription();
        this.photoPathList = product.getPhotoPathList().stream()
                .map(PhotoPathRepl::new)
                .collect(Collectors.toList());

        this.photoRawList = product.getPhotoRawList().stream()
                .map(PhotoRawRepl::new)
                .collect(Collectors.toList());
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


    public List<PhotoPathRepl> getPhotoPathList() {
        return photoPathList;
    }

    public void setPhotoPathList(List<PhotoPathRepl> photoPathList) {
        this.photoPathList = photoPathList;
    }

    public List<PhotoRawRepl> getPhotoRawList() {
        return photoRawList;
    }

    public void setPhotoRawList(List<PhotoRawRepl> photoRawList) {
        this.photoRawList = photoRawList;
    }

    public MultipartFile[] getNewPhotos() {
        return newPhotos;
    }

    public void setNewPhotos(MultipartFile[] newPhotos) {
        this.newPhotos = newPhotos;
    }

    public Integer getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Integer categoryId) {
        this.category_id = categoryId;
    }


}
