//package com.codeup.springblogapp.model;
//
//        import javax.persistence.*;
//import com.codeup.springblogapp.model.User;
//@Entity
//@Table(name="books")
//public class AdBean {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false, length = 100)
//    private String title;
//
//    @Column(nullable = false)
//    private String description;
//
//
//    @Column(nullable = false)
//    private User user;
//
//    public AdBean(){}
//
//    public AdBean(String description, String title, User user) {
//        this.user = user;
//        this.description = description;
//        this.title= title;
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
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
