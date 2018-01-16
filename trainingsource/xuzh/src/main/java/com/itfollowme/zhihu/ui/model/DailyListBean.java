package com.itfollowme.zhihu.ui.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by notre on 2018/1/15.
 */

public class DailyListBean implements Serializable{
    private Date date;

    private List<StoryBean> stories;

    private List<TopStoryBean> topStories;

    public List<StoryBean> getStories() {
        return stories;
    }
    public void setStories(List<StoryBean> stories) {
        this.stories = stories;
    }

    public List<TopStoryBean> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStoryBean> topStories) {
        this.topStories = topStories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //stories;
    /*
    *       "images": [
        "https:\/\/pic3.zhimg.com\/v2-9daa6b3fd2959ed37e773802727972b2.jpg"
      ],
      "type": 0,
      "id": 9666020,
      "ga_prefix": "011515",
      "title": "王思聪现实中是一个什么样的人？"*/
    public static class StoryBean{
        private List<String> images;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    /*
    *   "image": "https:\/\/pic4.zhimg.com\/v2-25e726517fb933035b902036d237c613.jpg",
      "type": 0,
      "id": 9666000,
      "ga_prefix": "011514",
      "title": "为什么请来了 Jessie J，《歌手》总导演洪涛还是哭红了眼？"*/
    public static class TopStoryBean{
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
