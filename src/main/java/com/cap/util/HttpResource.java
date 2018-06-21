package com.cap.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.midi.Soundbank;

import com.cap.entity.Article;

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
 * @Description https://www.sanwen.net/sanwen/  爬取散文网文章
 */
@Component
public class HttpResource {

    public static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    public static List<Article> getNews(String requestUrl){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(requestUrl);
        List<Article> list = new ArrayList<Article>();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            Document document =Jsoup.parse(result);
            Elements elements = document.getElementsByClass("article-list");
            //选出文章列表和文章具体地址
            for (Element element: elements) {
                Elements elements2 = element.getElementsByTag("a");
                for (Element link : elements2) {
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    map.put(linkText, linkHref);
                }
            }

            String url = null;
            String address = null;
            String title = null;
            Set<Map.Entry<String, String>> entrySet = map.entrySet();

            for (Map.Entry<String, String> set : entrySet){
                Article article = new Article();
                //标题
                title = set.getKey();
                article.setTitle(title);
                address = set.getValue();
                if(!address.startsWith("/")){
                    address = "/" + address;
                }
                url = "https://www.sanwen.net" + address;
//                System.out.println("URL"  + url);
                HttpGet httpGet2 = new HttpGet(url);
                CloseableHttpResponse response2 = httpClient.execute(httpGet2);
                HttpEntity entity2 = response2.getEntity();
                String result2 = EntityUtils.toString(entity2, "UTF-8");
                Document document2 = Jsoup.parse(result2);
                Elements elements2 = document2.getElementsByClass("mod").addClass("article");
                if(elements2.size() > 0){
                    //段落
                    Elements elements4 = elements2.get(0).getElementsByTag("p");
                    String content = null;
                    for (Element element : elements4){
//                        System.out.println(element.outerHtml());
                        content += element.outerHtml();
                    }
                    article.setContent(content);

                    //作者
                    Elements elements3 = elements2.get(0).getElementsByTag("a");
                    for (Element link : elements3){
//                        System.out.println(link.text());
                        article.setAuthor(link.text());
                    }
                }
                list.add(article);
            }
//            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Article> iterator = list.iterator();
        while (iterator.hasNext()){
            Article article = iterator.next();
            if(article.getContent() == null || article.getTitle() == null){
                iterator.remove();
            }else {
                article.setContent(article.getContent().substring(4));
                article.setArticleAbstract(article.getContent().replace("<p>", "").replace("</p>", "。").substring(0,280));
            }
        }
        return list;
    }

//    public static void main(String[] args) {
//        getNews();
//    }
}
