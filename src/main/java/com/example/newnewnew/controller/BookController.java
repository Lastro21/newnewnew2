package com.example.newnewnew.controller;

import com.example.newnewnew.model.BookModel;
import com.example.newnewnew.model.User;
import com.example.newnewnew.service.BookService;
import com.example.newnewnew.service.ChangeValuesUsers;
import com.example.newnewnew.service.NewsJsoup;
import com.example.newnewnew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.invoke.empty.Empty;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Component
public class BookController {


    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsJsoup newsJsoup;



    @RequestMapping(value = "/allbooks")
    public ModelAndView showAllBooks(){
        ModelAndView modelAndView = new ModelAndView();



        /*     Сразу же передаем во вьюшку поток наших новостей, которые состоят из href/title/image     */

        modelAndView.addObject("getArrayNews", newsJsoup.readAllListNews());

        /*   /  Сразу же передаем во вьюшку поток наших новостей, которые состоят из href/title/image     */

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        if (auth.getPrincipal().toString() == "anonymousUser"){

            modelAndView.addObject("DefaultUserInformation", "pink");
            modelAndView.addObject("background_color", "silver");
            modelAndView.addObject("background_block", "yellowgreen");
            modelAndView.addObject("font_color", "white");
            modelAndView.addObject("block_widht", "300");
            modelAndView.addObject("block_height", "200");


            modelAndView.addObject("enter_to_system", "Войти в систему");
        }
        else {

            modelAndView.addObject("DefaultUserInformation", user.getBackground_color());
            modelAndView.addObject("background_color", user.getBackground_color());
            modelAndView.addObject("background_block", user.getBackground_block());
            modelAndView.addObject("font_color", user.getFont_color());
            modelAndView.addObject("block_widht", user.getBlock_widht());
            modelAndView.addObject("block_height", user.getBlock_height());



            modelAndView.addObject("enter_to_system", "Сменить пользователя");
        }


        modelAndView.addObject("getArrayBooks",bookService.redAllListBooks());
        modelAndView.setViewName("/books/allbooks");




        return modelAndView;
    }




    ////////////// Получаем параметры пользователя для ANGULARJS  /////////////////////

    List<Collection<BookModel>> booksAllArrayFields = new ArrayList<Collection<BookModel>>();

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @RequestMapping("/getAllBooksForAngular")
    @Scheduled(initialDelay=5000, fixedRate = 120000)
    public List<Collection<BookModel>> showAllBooksForAngular(){


        System.out.println("[InitDelayTimeReport] Now: " + dateFormat.format(new Date()));

        booksAllArrayFields = new ArrayList<Collection<BookModel>>();

        if (booksAllArrayFields.isEmpty()) {

            booksAllArrayFields.add(bookService.redAllListBooks());
        }

        return booksAllArrayFields;
    }

    //////////////          / Получаем параметры пользователя для ANGULARJS  /////////////////////





    @RequestMapping ("/newbook")
    public ModelAndView newBook(){
       ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/books/newbooksimple");
        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }



    @RequestMapping(value = "/savebook", method = RequestMethod.POST)
    public void saveBook (@ModelAttribute BookModel bookModel, BindingResult bindingResult, HttpServletResponse response) throws IOException {
        bookService.createBook(bookModel);
        response.sendRedirect("allbooks");
    }

    @RequestMapping ("/updatebook")
    public ModelAndView updateBook(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("for_update", bookService.updateBook(id));
        modelAndView.setViewName("/books/new-and-edit");
        return modelAndView;
    }



    @RequestMapping ("/deletebook")
    public void deeleteBook(@RequestParam Long id, HttpServletResponse response) throws IOException {
        bookService.deleteBook(id);
        response.sendRedirect("/allbooks");
    }




    @RequestMapping(value = "/changeValuesUser", method = RequestMethod.POST)
    public void changeValuesUser (@RequestParam String background_color,
                                  @RequestParam String background_block,
                                  @RequestParam String font_color,
                                  @RequestParam String block_widht,
                                  @RequestParam String block_height,
                                  HttpServletResponse response) throws IOException {



        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ChangeValuesUsers changeValuesUsers = new ChangeValuesUsers();

        if (auth.getPrincipal().toString() != "anonymousUser") {

            changeValuesUsers.changeBackground_color  (background_color, user.getId());
            changeValuesUsers.changeBackground_block  (background_block, user.getId());
            changeValuesUsers.changeFont_color        (font_color, user.getId());
            changeValuesUsers.changeBlock_widht       (block_widht, user.getId());
            changeValuesUsers.changeBlock_height      (block_height, user.getId());

        }
        response.sendRedirect("allbooks");
    }
}
