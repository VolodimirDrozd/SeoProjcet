package com.okhelps.seo.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Category")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category
{

    @Id
    private int id;
    private String parent_id;
    private String name;
    private String url;
    private String title;
    private String description;
    private String keywords;
    private String h1;
    private String h2_1;
    private String h2_2;
    private String h2_3;
    private String h2_4;
    private String h2_5;
    private String h2_6;
    private String article;
    private int projectId;

    public int getProjectId()
    {
        return projectId;
    }

    public void setProjectId(int projectId)
    {
        this.projectId = projectId;
    }

    @JsonIgnore
    private List<Category> children = new ArrayList<Category>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getParentId()
    {
        return parent_id;
    }

    public void setParentId(String parent_id)
    {
        this.parent_id = parent_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getKeywords()
    {
        return keywords;
    }

    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getH1()
    {
        return h1;
    }

    public void setH1(String h1)
    {
        this.h1 = h1;
    }

    public String getH2_1()
    {
        return h2_1;
    }

    public void setH2_1(String h2_1)
    {
        this.h2_1 = h2_1;
    }

    public String getH2_2()
    {
        return h2_2;
    }

    public void setH2_2(String h2_2)
    {
        this.h2_2 = h2_2;
    }

    public String getH2_3()
    {
        return h2_3;
    }

    public void setH2_3(String h2_3)
    {
        this.h2_3 = h2_3;
    }

    public String getH2_4()
    {
        return h2_4;
    }

    public void setH2_4(String h2_4)
    {
        this.h2_4 = h2_4;
    }

    public String getH2_5()
    {
        return h2_5;
    }

    public void setH2_5(String h2_5)
    {
        this.h2_5 = h2_5;
    }

    public String getH2_6()
    {
        return h2_6;
    }

    public void setH2_6(String h2_6)
    {
        this.h2_6 = h2_6;
    }

    public String getArticle()
    {
        return article;
    }

    public void setArticle(String article)
    {
        this.article = article;
    }

    public List<Category> getChildren()
    {
        return children;
    }

}
