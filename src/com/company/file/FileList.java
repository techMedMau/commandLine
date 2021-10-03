package com.company.file;

public class FileList {
    private File[] files;
    private int count;

    public FileList(){
        this.files = new File[2];
        this.count = 0;
    }

    public void add(File file) {
        if (count == this.files.length) {
            doubleArr();
        }
        this.files[count++] = file;
    }

    public File get(int index) {
        if (index < 0 || index >= this.count) {
            return null;
        }
        return this.files[index];
    }

    public int size(){
        return this.count;
    }

    private void doubleArr(){
        File[] tmpArr = new File[this.files.length*2];
        for(int i = 0; i < this.files.length; i++){
            tmpArr[i] = this.files[i];
        }
        this.files = tmpArr;
    }

    public FileList forEach(Consumer consumer){
        for(int i = 0; i < this.count; i++){
            consumer.accept(this.files[i]);
        }
        return this;
    }
}