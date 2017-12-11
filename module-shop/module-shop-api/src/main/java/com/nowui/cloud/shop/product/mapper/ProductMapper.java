package com.nowui.cloud.shop.product.mapper;
import com.nowui.cloud.mapper.BaseMapper;
import com.nowui.cloud.shop.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author ZhongYongQiang
 */
@Mapper
@Component(value = "productMapper")
public interface ProductMapper extends BaseMapper<Product> {

}
