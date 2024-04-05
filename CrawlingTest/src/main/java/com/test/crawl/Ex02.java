package com.test.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex02 {

	public static void main(String[] args) {
		
		try {
			
			String url = "https://www.moviechart.co.kr/rank/boxoffice";
			
			//https://www.moviechart.co.kr/info/movieinfo/detail/20234675
			
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc.html());
			
			//Elements list = doc.select(".listTable .title > a");
			Elements list = doc.select(".listTable tbody tr");
			
			//System.out.println(list.size());
			
			System.out.println("[영화 순위]");
			
			for (Element tr : list) {
				//System.out.println(item.text());
				
				System.out.printf("%-5s %s %s %s"
					,tr.firstElementChild().text()
					,tr.select(".title > a").text()
					,tr.select(".title > a").attr("href")
					,tr.select(".date").text()
				);
				
				System.out.println();
				
				Document subdoc = Jsoup.connect("https://www.moviechart.co.kr" + tr.select(".title > a").attr("href")).get();
				
				//#content > div.info > div > div.movieIner > div.movieIner__text > div > ul > li:nth-child(3) > dl > dt
				String director = subdoc.select("#content > div.info > div > div.movieIner > div.movieIner__text > div > ul > li:nth-child(3) > dl > dt").text();
				
				System.out.println(director);
			}//for
			
		} catch (Exception e) {
			System.out.println("Ex02.main");
			e.printStackTrace();
		}
		
		
	}
	
}














