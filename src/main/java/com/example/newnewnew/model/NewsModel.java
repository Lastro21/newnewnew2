package com.example.newnewnew.model;


import javax.persistence.*;




@Entity
@Table (name = "news")
@NamedNativeQueries({
    @NamedNativeQuery(name = "saveAllParserDB", query = "INSERT INTO news (title, href, image) VALUES (?, ?, ?)", resultClass = NewsModel.class),
    @NamedNativeQuery(name = "stopSearchNews", query = "SELECT * FROM  news  WHERE title = ?", resultClass = NewsModel.class)

})
public class NewsModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "id")
        private long id;


        @Column (name = "title")
        private String title;


        @Column (name = "href")
        private String href;


        @Column (name = "image")
        private String image;


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

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "News{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", href='" + href + '\'' +
                    ", image='" + image + '\'' +
                    '}';
        }
}

