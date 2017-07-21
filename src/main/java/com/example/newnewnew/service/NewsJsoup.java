package com.example.newnewnew.service;


import com.example.newnewnew.model.BookModel;
import com.example.newnewnew.model.NewsModel;
import com.example.newnewnew.repository.NewsRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class NewsJsoup {

/*     Вывел и сделал одно подключение для всех (точнее только для получения Title и Href. Картинка получается в своем методе)     */

    Document doc = Jsoup.connect("http://m.ufa1.ru/text/subject/gorod_online/").userAgent("Mozilla/5.0").timeout(60000).get();
    Elements allRes = doc.getElementsByAttributeValue("class", "widget_theme_news");

    public NewsJsoup() throws IOException {
    }

/*  / Вывел и сделал одно подключение для всех (точнее только для получения Title и Href. Картинка получается в своем методе)  */


    public List <String> arrayCrawlerHref () throws IOException {

/*      Document doc = Jsoup.connect("http://m.ufa1.ru/text/subject/gorod_online/").userAgent("Mozilla/5.0").timeout(60000).get();
        Elements allRes = doc.getElementsByAttributeValue("class", "widget_theme_news");     -Подключение вынесено в самом классе    */

        List<String> myArrayHref = new ArrayList<>();

            for (Element result : allRes) {

                Element open = result;

                String str1 = open.child(2).attr("href");
                myArrayHref.add(str1);
            }

            //System.out.println(myArray.get(0));

          /*  Это просто чтобы посмотреть что в массиве в реальном времени

           for (int i = 1; i < myArrayHref.size(); i++){
            System.out.println("http://www.ufa1.ru"+myArrayHref.get(i)+" "+" "+" "+" "+i);
            }
            */
        return myArrayHref;
    }

////////////////////////////////////////////////////////////////////////////////////////////////

        public List <String> arrayCrawlerText () throws IOException{

/*            Document doc = Jsoup.connect("http://m.ufa1.ru/text/subject/gorod_online/").userAgent("Mozilla/5.0").timeout(10000).get();
            Elements allRes = doc.getElementsByAttributeValue("class", "widget_theme_news");  -Подключение вынесено в самом классе */

            List<String> myArrayText = new ArrayList<>();

            for (Element result : allRes) {

                //Element open = result.child(0);
                Element open = result;

                String str2 = open.child(1).text();
                myArrayText.add(str2);
            }

            //System.out.println(myArray.get(0));

           /*  Это просто чтобы посмотреть что в массиве в реальном времени

            for (int i = 1; i < myArrayText.size(); i++){
                System.out.println(myArrayText.get(i)+" "+" "+" "+" "+i);
            } */
            return myArrayText;
        }

//////////////////////////////////////////////////////////////////////////////////////////////////

    public List <String> arrayCrawlerImage () throws  IOException{

        /*     производим подключение внутри метода, так как присутствует   doc.select("img[src$=.jpg]") которого нет у других  */

            Document doc = Jsoup.connect("http://m.ufa1.ru/text/subject/gorod_online/").userAgent("Mozilla/5.0").timeout(60000).get();
            Elements allRes = doc.select("img[src$=.jpg]");

            List<String> myArrayImage = new ArrayList<>();

            for (Element result : allRes) {

                //Element open = result.child(0);
                Element open = result;

                String str3 = open.attr("src");
                myArrayImage.add(str3);
            }

            //System.out.println(myArray.get(0));

           /*  Это просто чтобы посмотреть что в массиве в реальном времени

           for (int i = 1; i < myArrayImage.size(); i++){
               System.out.println(myArrayImage.get(i)+" "+" "+" "+" "+i);
            }
            */
            return myArrayImage;

        }

///////////////////////////////////////////////////     МЕТОД САМОГО СОХРАНЕНИЯ href/title/image   //////////////////////////////////////////////
    @Scheduled(initialDelay=30000, fixedRate = 3600000)  // Даза данных обновляется каждый час (3600 секунд)
    public void saveNews () throws IOException {

       /* System.out.println(arrayCrawlerText().get(5));*/

        for (int i = 0; i < arrayCrawlerText().size(); i++) {

            /////////////////////////////////////////////////////////   Блок для пропуска или продолжения поиска новостей


            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();


            Query queryRequest = session.getNamedQuery("stopSearchNews")
                    .setParameter(0, arrayCrawlerText().get(i));

            List <NewsModel> newsModels = (List<NewsModel>) queryRequest.list();

            session.getTransaction().commit();
            session.close();

            if (newsModels.isEmpty()){

                System.out.println("Добавлена новая новость");

                SessionFactory sessionFactory1 = new Configuration().configure().buildSessionFactory();
                Session sessionSave = sessionFactory1.openSession();
                sessionSave.beginTransaction();

                Query querySave2 = sessionSave.getNamedQuery("saveAllParserDB")

                        .setParameter(0, arrayCrawlerText().get(i))
                        .setParameter(1, arrayCrawlerHref().get(i))
                        .setParameter(2, arrayCrawlerImage().get(i));

                querySave2.executeUpdate();

                sessionSave.getTransaction().commit();
                sessionSave.close();
            }

            else {

                    System.out.println("Данная новость уже существует");
                }
            }
        }
    /////////////////////////////////////////////////////////   Блок для пропуска или продолжения поиска новостей

    ////////////////////          /     МЕТОД САМОГО СОХРАНЕНИЯ href/title/image   /////////////////////////////////////


    @Autowired
    private NewsRepository newsRepository;

    public Collection<NewsModel> readAllListNews (){

        List <NewsModel> listNews = new ArrayList<NewsModel>();

        for (NewsModel newsModel : newsRepository.findAll()){

            listNews.add(newsModel);

        }
        return listNews;
    }


}


