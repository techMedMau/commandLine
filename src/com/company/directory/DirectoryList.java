package com.company.directory;

public class DirectoryList {
    private Directory[] directories;
    private int count;

    public DirectoryList(){
        this.directories = new Directory[2];
        this.count = 0;
    }

    public void add(Directory directory) {
        if (count == directories.length) {
            doubleArr();
        }
        directories[count++] = directory;
    }

    public Directory get(int index) {
        if (index < 0 || index >= count) {
            return null;
        }
        return directories[index];
    }

    public int size(){
        return this.count;
    }

    private void doubleArr(){
        Directory[] tmpArr = new Directory[this.directories.length*2];
        for(int i = 0; i < this.directories.length; i++){
            tmpArr[i] = this.directories[i];
        }
        this.directories = tmpArr;
    }

    public DirectoryList forEach(Consumer consumer){
        for(int i = 0; i < this.count; i++){
            consumer.accept(this.directories[i]);
        }
        return this;
    }

    public DirectoryList filter(Filter filter){
        DirectoryList directoryList = new DirectoryList();
        for(int i = 0; i < this.count; i++){
            if(filter.filter(directories[i])){
                directoryList.add(this.directories[i]);
            }
        }
        return directoryList;
    }
}

