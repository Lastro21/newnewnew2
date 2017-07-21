package com.example.newnewnew.repository;

import com.example.newnewnew.model.NewsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository <NewsModel, Long> {


}
