package com.example.newnewnew.repository;


import com.example.newnewnew.model.BookModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository <BookModel, Long> {



}
