package ru.geekbrains.persist.repl;

import ru.geekbrains.persist.enity.PhotoRaw;

import java.io.Serializable;

public class PhotoRawRepl implements Serializable {


    private Long id;


    private byte[] data;


    private String contentType;


    private String name;


    public PhotoRawRepl() {

    }

    public PhotoRawRepl(PhotoRaw photoRaw) {
        this.id = photoRaw.getId();
        this.name = photoRaw.getName();
        this.contentType = photoRaw.getContentType();
        this.data = photoRaw.getData();
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
