package com.example.generateprojection.helper;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZhiHuDB {

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<StoriesDTO> stories;
    @SerializedName("top_stories")
    private List<TopStoriesDTO> topStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesDTO> getStories() {
        return stories;
    }

    public void setStories(List<StoriesDTO> stories) {
        this.stories = stories;
    }

    public List<TopStoriesDTO> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStoriesDTO> topStories) {
        this.topStories = topStories;
    }

    public static class StoriesDTO {
        @SerializedName("image_hue")
        private String imageHue;
        @SerializedName("title")
        private String title;
        @SerializedName("url")
        private String url;
        @SerializedName("hint")
        private String hint;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        @SerializedName("type")
        private Integer type;
        @SerializedName("id")
        private Integer id;
        @SerializedName("images")
        private List<String> images;

        public String getImageHue() {
            return imageHue;
        }

        public void setImageHue(String imageHue) {
            this.imageHue = imageHue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesDTO {
        @SerializedName("image_hue")
        private String imageHue;
        @SerializedName("hint")
        private String hint;
        @SerializedName("url")
        private String url;
        @SerializedName("image")
        private String image;
        @SerializedName("title")
        private String title;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        @SerializedName("type")
        private Integer type;
        @SerializedName("id")
        private Integer id;

        public String getImageHue() {
            return imageHue;
        }

        public void setImageHue(String imageHue) {
            this.imageHue = imageHue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
