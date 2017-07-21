package com.example.newnewnew.model;



import javax.persistence.*;
import java.util.Date;

@Entity

/*@NamedNativeQuery(name = "myTestNativeQuery", query = "SELECT * FROM books WHERE author = ?", resultClass = BookModel.class)*/

@NamedNativeQuery(name = "myTestNativeQuery", query = "SELECT * FROM  books  WHERE author =?", resultClass = BookModel.class)
@Table (name = "books")
public class BookModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "book_name")
    private String book_name;

    @Column (name = "author")
    private  String author;

    @Column (name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchase_date;


    //////////////////////////////////////////////////////////////////


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }
}
