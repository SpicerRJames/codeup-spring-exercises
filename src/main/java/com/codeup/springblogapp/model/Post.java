package com.codeup.springblogapp.model;

import javax.persistence.*;

@Entity
//Table name for my work is ads, in the walkthrough it is posts.
@Table(name="ads")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //nullable
    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)//should add a length here next time //columnDefinition = "TEXT" will ensure that is is infinit
    private String description;

    //set our relationship between the posts and user
    //many posts can belong to one user
    //posts ---> user
    //many to ----> one
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(long id, String title, String description, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Post(String title, String description, User user) {
        this.user = user;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //    public long createPost(Post post){
//        this.id = post.getId();
//        this.title = post.getTitle();
//        this.description = post.getDescription();
//        return this.id;
//    }

}
