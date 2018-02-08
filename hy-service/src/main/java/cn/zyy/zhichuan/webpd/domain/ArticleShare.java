package cn.zyy.zhichuan.webpd.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * The persistent class for the article_share database table.
 * 
 */
@Entity
@Table(name = "article_share")
@NamedQuery(name = "ArticleShare.findAll", query = "SELECT a FROM ArticleShare a")
public class ArticleShare implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int               id;

    @Column(name = "appear_time", nullable = false, length = 32)
    private String            appearTime;

    @Column(name = "article_cat_id", nullable = false)
    private int               articleCatId;

    @Column(name = "article_collect_id", nullable = false)
    private int               articleCollectId;

    @Column(length = 512)
    private String            description;

    @Column(name = "icon_url", length = 512)
    private String            iconUrl;

    @Column(name = "is_del", nullable = false)
    private int               isDel;

    @Column(name = "origin_url", nullable = false, length = 512)
    private String            originUrl;

    @Column(name = "registration_time", nullable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp         registrationTime;

    @Column(nullable = false)
    private int               star;

    @Column(nullable = false)
    private int               state;

    @Column(length = 512)
    private String            title;

    @Column(name = "update_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp         updateTime;

    @Column(nullable = false, length = 512)
    private String            url;

    public ArticleShare()
    {}

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAppearTime()
    {
        return this.appearTime;
    }

    public void setAppearTime(String appearTime)
    {
        this.appearTime = appearTime;
    }

    public int getArticleCatId()
    {
        return this.articleCatId;
    }

    public void setArticleCatId(int articleCatId)
    {
        this.articleCatId = articleCatId;
    }

    public int getArticleCollectId()
    {
        return this.articleCollectId;
    }

    public void setArticleCollectId(int articleCollectId)
    {
        this.articleCollectId = articleCollectId;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getIconUrl()
    {
        return this.iconUrl;
    }

    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    public int getIsDel()
    {
        return this.isDel;
    }

    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
    }

    public String getOriginUrl()
    {
        return this.originUrl;
    }

    public void setOriginUrl(String originUrl)
    {
        this.originUrl = originUrl;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public int getStar()
    {
        return this.star;
    }

    public void setStar(int star)
    {
        this.star = star;
    }

    public int getState()
    {
        return this.state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Timestamp getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

}