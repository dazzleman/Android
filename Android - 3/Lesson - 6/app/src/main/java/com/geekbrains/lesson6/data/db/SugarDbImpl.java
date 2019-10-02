package com.geekbrains.lesson6.data.db;


import com.geekbrains.lesson6.domain.model.Article;
import com.geekbrains.lesson6.data.entity.ArticleSugarData;

import java.util.ArrayList;
import java.util.List;


public class SugarDbImpl implements DbProvider<ArticleSugarData, List<Article>> {
    @Override
    public void insert(ArticleSugarData data) {
        data.save();
    }

    @Override
    public void update(ArticleSugarData data) {
        data.update();
    }

    @Override
    public void delete(ArticleSugarData data) {
        data.delete();
    }

    @Override
    public List<Article> select() {
        List<ArticleSugarData> articleSugarDataList = ArticleSugarData.listAll(ArticleSugarData.class);
        List<Article> articleList = new ArrayList<>();
        for (ArticleSugarData articleSugarData : articleSugarDataList) {
            articleList.add(ArticleSugarData.convertToEntity(articleSugarData));
        }
        return articleList;
    }
}
