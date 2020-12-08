package com.triplemovie.pjt;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

	public static void main(String[] args) {
		String url = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=184157";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements dl = doc.select("dl.info_spec");

		Element d_genre = dl.select("dd").get(0).select("span").get(0);
		String genre = d_genre.text();

		Element d_country = dl.select("dd").get(0).select("span").get(1);
		String country = d_country.text();

		Element d_showTime = dl.select("dd").get(0).select("span").get(2);
		String showTime = d_showTime.text();

		Element d_director = dl.select("dd").get(1).select("a").get(0);
		String director = d_director.text();

		Elements d_actor = dl.select("dd").get(2).select("p").get(0).select("a");
		String actor = d_actor.text();

		Element d_grade = dl.select("dd").get(3).select("p").get(0).select("a").get(0);
		String grade = d_grade.text();

		Element img = doc.select("div.poster").get(0).select("img").get(0);
		String imgurl = img.getElementsByAttribute("src").attr("src");
	}
}
