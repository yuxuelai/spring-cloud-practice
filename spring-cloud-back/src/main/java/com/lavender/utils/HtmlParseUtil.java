package com.lavender.utils;


import com.lavender.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {

    public static void main(String[] args) throws IOException {

        new HtmlParseUtil ().getJingDongInfo ("vue").forEach (System.out::println);

    }

    public List<Content> getJingDongInfo(String keyword) throws IOException {
        // 获取网页
        String url = "https://search.jd.com/Search?keyword="+keyword;
        // 解析网页 js 返回js的浏览器的document对象
        Document document = Jsoup.parse (new URL (url), 30000);

        Element element = document.getElementById ("J_goodsList");

        ArrayList<Content> goodsList = new ArrayList<> ();
//        System.out.println (element.html ());
        // 获取所有的li元素
        Elements li = element.getElementsByTag ("li");

        // 获取元素中的内容
        for (Element element1 : li) {

            // 懒加载 - 图片延迟加载
            String img1 = element1.getElementsByTag ("img").eq (0).attr ("data-lazy-img");
            String price = element1.getElementsByClass ("p-price").eq (0).text ();
            String title = element1.getElementsByClass ("p-name").eq (0).text ();

//            System.out.println ("-==================================");
//            System.out.println (img1);
//            System.out.println (price);
//            System.out.println (title);
            goodsList.add (new Content (img1, price, title));


        }
        return goodsList;
    }
}
