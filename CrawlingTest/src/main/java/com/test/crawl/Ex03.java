package com.test.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex03 {
	public static void main(String[] args) {
		try {

			Document doc = Jsoup.connect("http://localhost:8090/crawl/Ex05.jsp").get();
			
			//System.out.println(doc.html());
			
			Elements list = doc.select("#list1 > li");
			
			for (Element item : list) {
				System.out.println(item.text());
			}
			
			System.out.println("end");
			
		} catch (Exception e) {
			System.out.println("Ex03.main");
			e.printStackTrace();
		}
	}

}
