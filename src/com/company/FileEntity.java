package com.company;

import com.company.directory.Directory;

public class FileEntity {
    private String name;
    private Directory parent;

    public FileEntity(String name){
        this(name, null);
    }
    public FileEntity(String name, Directory parent){
        this.name = name;
        this.parent = parent;
    }

    public String getName(){
        return name;
    }

    public Directory getParent() {
        return this.parent;
    }

    public String getPath(){
        if(parent == null) return getName();
        return this.parent.getPath()+"\\"+getName();
    }
}
