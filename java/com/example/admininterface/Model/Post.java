package com.example.admininterface.Model;

public class Post {
    private String
            postid;

    public String getUdp() {
        return udp;
    }

    public void setUdp(String udp) {
        this.udp = udp;
    }

    private String udp;

    private String postImage;
    private String description;
    private String publisher;

    public Post(String postid, String postImage, String description, String publisher) {
        this.postid =postid;
        this.postImage = postImage;
        this.description = description;
        this.publisher = publisher;
    }
    public Post(){

    }


    public String getPostID() {
        return postid;
    }

    public void setPostID(String postid) {
        this.postid =postid;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
