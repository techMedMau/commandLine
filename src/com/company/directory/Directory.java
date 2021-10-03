package com.company.directory;

import com.company.FileEntity;
import com.company.file.File;
import com.company.file.FileList;

public class Directory extends FileEntity {
    private DirectoryList directories;
    private FileList files;

    public Directory(String name){
        super(name, null);
    }
    public Directory(String name, Directory parent){
        super(name, parent);
    }

    public void add(Directory directory){
        if(this.directories == null){
            this.directories = new DirectoryList();
        }
        this.directories.add(directory);
    }

    public void add(File file){
        if(this.files == null){
            this.files = new FileList();
        }
        this.files.add(file);
    }

    public Directory get(String name){
        if(this.directories == null){
            return null;
        }
        DirectoryList d1 = this.directories.filter(directory -> directory.getName().equals(name));
        if(d1.size() == 0) return null;
        return d1.get(0);
    }

    public void ls(){
        int f = 0;
        int d = 0;
        if(this.directories != null){

            this.directories.forEach(System.out::println);
            d = this.directories.size();
        }
        if(this.files != null){

            this.files.forEach(System.out::println);
            f = this.files.size();
        }
        System.out.println("Total: "+f+" files, "+d+" directories");
    }

    public void search(final String key){
        if(this.directories != null){
            this.directories.forEach(
                    directory -> {
                        if(directory.getName().contains(key)){
                            System.out.println(directory.getPath());
                        }
                        directory.search(key);
                    });
        }
        if(this.files != null){
            this.files.forEach(file -> {
                if(file.getName().contains(key)){
                    System.out.println(file.getPath());
                }
            });
        }
    }

    @Override
    public String toString(){
        return "["+getName()+"]";
    }
}
