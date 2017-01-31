package com.example.nytimes;

/**
 * Created by Surya on 1/28/2017.
 */
public class DataModel {

    String section;
    String url;
    String title;

    public String getAbstract_desc() {
        return abstract_desc;
    }

    public void setAbstract_desc(String abstract_desc) {
        this.abstract_desc = abstract_desc;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String abstract_desc;
    String byline;
    String published_date ;
}

