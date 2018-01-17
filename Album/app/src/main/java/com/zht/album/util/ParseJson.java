package com.zht.album.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zht.album.model.Meitu_1_Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import static com.github.skykai.App.getContext;

/**
 * Created by masterzht on 2018/1/16.
 */

public class ParseJson {




 /*   static{


    }*/




    public static List<String> ParseJsonWithGson(){

        List<Meitu_1_Bean> meitu_1_bean=null;
        int random=0;


        try {
            InputStream inputStream = getContext().getAssets().open("meitu_1.json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text= new String(buf,"UTF-8");
            //meitu_1_bean = (List<Meitu_1_Bean>) JSON.parseObject(text, Meitu_1_Bean.class);

            Gson gson=new Gson();

            meitu_1_bean= gson.fromJson(text, new TypeToken<List<Meitu_1_Bean>>() {}.getType());

            int max = meitu_1_bean.size();
            int min = 0;
            random= new Random().nextInt(max - min) + min;



        } catch (IOException e) {
            e.printStackTrace();
        }

        return meitu_1_bean.get(random).getImage_urls();


        //随机返回一个包含十个img_url的列表



        /*for(Meitu_1_Bean a:meitu_1_bean) {
            System.out.println(a.getImage_urls());
        }

        for  (int i=0;i<meitu_1_bean.size();i++){
            System.out.println(meitu_1_bean.get(i));
        }*/



    }
}
