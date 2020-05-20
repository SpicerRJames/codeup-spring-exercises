package com.codeup.springblogapp;

import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.AdRepository;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.servlet.http.HttpSession;


import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBlogAppApplication.class)
@AutoConfigureMockMvc
public class AdsIntegrationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    AdRepository adsDao;

    @Autowired
    PostRepository postRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {

        testUser = userDao.findByUsername("testUser");

        // Creates the test user if not exists
        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = userDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "test")
                .param("password", "aaaa"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }

//    @Test
//    public void testShowAd() throws Exception {
//
//        Ad existingAd = adsDao.findAll().get(0);
//
//        // Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
//        this.mvc.perform(get("/ads/" + existingAd.getId()))
//                .andExpect(status().isOk())
//                // Test the dynamic content of the page
//                .andExpect(content().string(containsString(existingAd.getDescription())));
//    }

//    @Test
//    public void testShowPost() throws Exception {
//
//        Post existingPost = postRepo.findAll().get(0);
//
//        // Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
//        this.mvc.perform(get("/posts/" + existingPost.getId()))
//                .andExpect(status().isOk())
//                // Test the dynamic content of the page
//                .andExpect(content().string(containsString(existingPost.getBody())));
//    }
//
//
//    @Test
//    public void testAdsIndex() throws Exception {
//        Ad existingAd = adsDao.findAll().get(0);
//
//        // Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
//        this.mvc.perform(get("/ads"))
//                .andExpect(status().isOk())
//                // Test the static content of the page
//                .andExpect(content().string(containsString("Ad List")))
//                // Test the dynamic content of the page
//                .andExpect(content().string(containsString(existingAd.getTitle())));
//    }

    @Test
    public void testCreateAd() throws Exception {
        // Makes a Post request to /ads/create and expect a redirection to the Ad
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        // Add all the required parameters to your request like this
                        .param("title", "test")
                        .param("body", "for sale"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testShowAd() throws Exception {

        Post existingPost = postRepo.findAll().get(0);

        // Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getBody())));
    }

    @Test
    public void testPostIndex() throws Exception {
        Post existingPost = postRepo.findAll().get(0);

        // Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                // Test the static content of the page
                .andExpect(content().string(containsString("Recent Blog Post")))
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }

//    @Test
//    public void testEditPost() throws Exception {
//        // Gets the first Ad for tests purposes
////        Ad existingAd = adsDao.findAll().get(0);
//        Post existingPost = postRepo.findAll().get(0);
//
//        // Makes a Post request to /ads/{id}/edit and expect a redirection to the Ad show page
//        this.mvc.perform(
//                post("/posts/" + existingPost.getId() + "/edit").with(csrf())
//                        .session((MockHttpSession) httpSession)
//                        .param("title", "edited title")
//                        .param("description", "edited description"))
//                .andExpect(status().is3xxRedirection());
//
//        // Makes a GET request to /ads/{id} and expect a redirection to the Ad show page
//        this.mvc.perform(get("/posts/" + existingPost.getId()))
//                .andExpect(status().isOk())
//                // Test the dynamic content of the page
//                .andExpect(content().string(containsString("edited title")))
//                .andExpect(content().string(containsString("edited description")));
//    }

    @Test
    public void testDeleteAd() throws Exception {
        // Creates a test Ad to be deleted
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "ad to be deleted1")
                        .param("description", "won't last long"))
                .andExpect(status().is3xxRedirection());

        // Get the recent Ad that matches the title
//        Ad existingAd = adsDao.findByTitle("ad to be deleted");
        Post existingPost = postRepo.findByTitle("ad to be deleted1");

        // Makes a Post request to /ads/{id}/delete and expect a redirection to the Ads index
        this.mvc.perform(
                post("/posts/" + existingPost.getId() + "/delete").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("id", String.valueOf(existingPost.getId())))
                .andExpect(status().is3xxRedirection());
    }

}
