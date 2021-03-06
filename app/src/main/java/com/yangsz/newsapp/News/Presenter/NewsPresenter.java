package com.yangsz.newsapp.News.Presenter;

import com.yangsz.newsapp.Bean.NewsBean;
import com.yangsz.newsapp.Http.Api;
import com.yangsz.newsapp.News.Model.INewsModel;
import com.yangsz.newsapp.News.Model.IOnLoadListener;
import com.yangsz.newsapp.News.Model.NewsModel;
import com.yangsz.newsapp.News.View.INewsView;
import com.yangsz.newsapp.Page;

public class NewsPresenter implements INewsPresenter, IOnLoadListener {
    private INewsModel iNewsModel;
    private INewsView iNewsView;

    public NewsPresenter(INewsView iNewsView){
        this.iNewsView=iNewsView;
        this.iNewsModel=new NewsModel();
    }

    @Override
    public void success(NewsBean newsBean){
        iNewsView.hideDialog();
        if(newsBean!=null){
            iNewsView.showNews(newsBean);
        }
    }

    @Override
    public void fail(String error){
        iNewsView.hideDialog();
        iNewsView.showErrorMsg(error);
    }

    @Override
    public void loadNews(int type,int startPage){
        iNewsView.showDialog();
        switch (type){
            case Page.NEWS_TYPE_TOP:
                iNewsModel.loadNews("headline",startPage, Api.HEADLINE_ID,this);
                break;
            case Page.NEWS_TYPE_NBA:
                iNewsModel.loadNews("list",startPage, Api.NBA_ID,this);
                break;
            case Page.NEWS_TYPE_JOKES:
                iNewsModel.loadNews("list",startPage, Api.JOKE_ID,this);
                break;
        }
    }
}
