package com.nowui.cloud.cms.article.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleCategoryMapper;
import com.nowui.cloud.cms.article.repository.ArticleCategoryRepository;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Date;

/**
 * 文章分类业务实现
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
@Service
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategoryMapper, ArticleCategory, ArticleCategoryRepository, ArticleCategoryView> implements ArticleCategoryService {

    @Override
        public Integer countForAdmin(String appId, String articleCategoryName, String articleCategoryCode) {
            Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                    .and(ArticleCategoryView.ARTICLE_CATEGORY_NAME).regex(".*?" + articleCategoryName + ".*")
                    .and(ArticleCategoryView.ARTICLE_CATEGORY_CODE).regex(".*?" + articleCategoryCode + ".*")
                    .and(ArticleCategoryView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<ArticleCategoryView> listForAdmin(String appId, String articleCategoryName, String articleCategoryCode, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                    .and(ArticleCategoryView.ARTICLE_CATEGORY_NAME).regex(".*?" + articleCategoryName + ".*")
                    .and(ArticleCategoryView.ARTICLE_CATEGORY_CODE).regex(".*?" + articleCategoryCode + ".*")
                    .and(ArticleCategoryView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, ArticleCategoryView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<ArticleCategoryView> articleCategoryViewList = list(query, sort, pageIndex, pageSize);

            return articleCategoryViewList;
        }

}