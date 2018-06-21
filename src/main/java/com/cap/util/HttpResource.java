package com.cap.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
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
    public static ConcurrentHashMap<String, String> articleMap = new ConcurrentHashMap<String, String>();

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
//            System.out.println("----------------------------");
//            System.out.println(map);
//            System.out.println("----------------------------");
            String url = null;
            String address = null;
            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            for (Map.Entry<String, String> set : entrySet){
                System.out.println(set.getKey());
                System.out.println(set.getValue());
                address = set.getValue();
                if(!address.startsWith("/")){
                    address = "/" + address;
                }
                url = "https://www.sanwen.net" + address;
                System.out.println("URL"  + url);
                HttpGet httpGet2 = new HttpGet(url);
                CloseableHttpResponse response2 = httpClient.execute(httpGet2);
                HttpEntity entity2 = response2.getEntity();
                String result2 = EntityUtils.toString(entity2, "UTF-8");
                Document document2 = Jsoup.parse(result2);
                Elements elements2 = document2.getElementsByClass("mod").addClass("article");
                if(elements2.size() > 0){
//                    elements2.get(0).
                    articleMap.put(set.getKey(), elements2.get(0).toString());
                }
            }
            System.out.println(articleMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getNews();
//        String str = "fafa";
//        if(!str.startsWith("/")){
//            str = "/" + str;
//        }
//        System.out.println(str);
//        System.out.println(str.startsWith("/"));
    }
}
