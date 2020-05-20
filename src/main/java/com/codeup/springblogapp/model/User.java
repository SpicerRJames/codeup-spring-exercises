package com.codeup.springblogapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    // Setting the Primary key for users table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // username (can't be null, has to be unique)
    @Column(nullable = false, unique = true)
    private String username;

    // email
    @Column(nullable = false)
    private String email;

    // password
    @Column
    private String password;

    // One-to-many, b/c each user can have multiple ads
    // @One[user]toMany[Ads]
    // User ---> Ads
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ad> ads;

    // Set up another relationship, but for posts
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(User copy){
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}


//package com.codeup.springblogapp.model;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    //username (Can't be null, has to be unique
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    //email (Can't be null, can be unique
//    @Column(nullable = false)
//    private String email;
//
//    //password
//    @Column
//    private String password;
//
//    //one-to-many, cause user can have multiple ads
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
////    private List<Ad> ads;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//
//    private List<Post>posts;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
////    public List<Ad> getAds() {
////        return ads;
////    }
////
////    public void setAds(List<Ad> ads) {
////        this.ads = ads;
////    }
//
//    public User() {
//    }
//
//    public User(User copy){
//        id = copy.id;
//        email = copy.email;
//        username = copy.username;
//        password = copy.password;
//    }
//
//}
