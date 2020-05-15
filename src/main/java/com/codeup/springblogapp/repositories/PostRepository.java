package com.codeup.springblogapp.repositories;
import com.codeup.springblogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);
    long deleteByTitle(String title);
    void deleteById(long id);

//    long findById(long id);
}
