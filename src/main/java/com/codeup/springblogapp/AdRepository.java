package com.codeup.springblogapp;

import com.codeup.springblogapp.model.AdBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<AdBean, Long> {
}
