package com.cap.util;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * Created by cmhy on 2018/6/20.
 */
@Component
public class HttpResource {

    public static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    public static String getNews(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://www.sanwen.net/sanwen/love/");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            Document document =Jsoup.parse(result);
            Elements elements = document.getElementsByClass("article-list");
            for (Element element: elements) {
                Elements elements2 = element.getElementsByTag("a");
                for (Element link : elements2) {
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    map.put(linkText, linkHref);
                }
            }
            System.out.println(map);
            String url = "https://www.sanwen.net" + map.get("致深爱过的你的一封信");
            System.out.println(url);
            HttpGet httpGet2 = new HttpGet("https://www.sanwen.net/subject/3945911/");
            CloseableHttpResponse response2 = httpClient.execute(httpGet2);
            HttpEntity entity2 = response2.getEntity();
            String result2 = EntityUtils.toString(entity2, "UTF-8");
            Document document2 = Jsoup.parse(result2);
            Elements elements2 = document2.getElementsByClass("mod").addClass("article");
            for (Element element2: elements2) {
                System.out.println(element2);
            }
//            Element content = document2.getElementById("content");
//            System.out.println(content);
//            System.out.println(result2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getNews();
    }
}
