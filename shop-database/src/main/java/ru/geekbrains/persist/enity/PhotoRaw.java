package ru.geekbrains.persist.enity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "photos_raw")
public class PhotoRaw implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "data", nullable = false, length = 33554430)
    private byte[] data;

    @Column(name = "content_type")
    private String contentType;

    @Column
    private String name;

    @ManyToMany(mappedBy = "photoRawList")
    private List<Product> products;

    public PhotoRaw() {
    }

    public PhotoRaw(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
