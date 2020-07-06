package ru.geekbrains.persist.repl;

import ru.geekbrains.persist.enity.PhotoPath;

import java.io.Serializable;

public class PhotoPathRepl implements Serializable {


    private Long id;

    private String path;

    private String name;

    public PhotoPathRepl() {
    }

    public PhotoPathRepl(PhotoPath photoPath) {
        this.id = photoPath.getId();
        this.name = photoPath.getName();
        this.path = photoPath.getPath();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
