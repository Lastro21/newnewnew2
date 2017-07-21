package com.example.newnewnew;


import com.example.newnewnew.service.NewsJsoup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.*;



@SpringBootApplication
@EnableScheduling
public class NewnewnewApplication  {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(NewnewnewApplication.class, args);

		NewsJsoup newsJsoup = new NewsJsoup();
		newsJsoup.saveNews();

	}
}