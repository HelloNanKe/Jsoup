package com.zt.apple.jsoup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHtml();
    }

    private void getHtml() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //还是一样先从一个URL加载一个Document对象。
                            Document doc = Jsoup.connect("http://list.youku.com/albumlist/show?id=966411&ascending=1&page=2").get();

                            //“椒麻鸡”和它对应的图片都在<div class="pic">中
                            Elements elements = doc.getElementsByTag("ul");
                            for(int i=0;i<elements.size();i++){

                                //使用Element.select(String selector)查找元素，使用Node.attr(String key)方法取得一个属性的值
                                if(elements.get(i).select("a").attr("href")!=null){
                                    Log.i("mytag", "title:" + elements.get(i).select("a").attr("href"));
                                }
                            }
                        }catch(Exception e) {
                            Log.i("mytag", e.toString());
                        }
                    }
                }).start();


                }






}
