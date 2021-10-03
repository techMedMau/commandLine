package com.company.file;

import com.company.FileEntity;
import com.company.directory.Directory;

public class File extends FileEntity {

    public File(String name){
        super(name);
    }
    public File(String name, Directory parnet){
        super(name, parnet);
    }

    @Override
    public String toString(){
        return getName();
    }
}
