package com.example.newnewnew.service;

import com.example.newnewnew.model.BookModel;
import com.example.newnewnew.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Collection <BookModel> redAllListBooks (){

        List <BookModel> listBooks = new ArrayList<BookModel>();

        for (BookModel bookModel : bookRepository.findAll()){

            listBooks.add(bookModel);

        }
        return listBooks;
    }


    public void createBook (BookModel bookModel){

        bookRepository.save(bookModel);
    }

    public BookModel updateBook (long id){

        return bookRepository.findOne(id);
    }

    public void deleteBook(long id){

        bookRepository.delete(id);
    }
}
