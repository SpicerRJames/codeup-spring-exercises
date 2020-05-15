//package com.codeup.springblogapp.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="books")
//public class Books {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    //nullable
//    @Column(nullable = false, length = 100)
//    private String title;
//
//    @Column(nullable = false)//should add a length here next time //columnDefinition = "TEXT" will ensure that is is infinit
//    private String description;
//
//
//    @ManyToOne
//
//
//
//    public Post() {
//    }
//
//    public Post(long id, String title, String description) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
////    public long createPost(Post post){
////        this.id = post.getId();
////        this.title = post.getTitle();
////        this.description = post.getDescription();
////        return this.id;
////    }
//
//}
