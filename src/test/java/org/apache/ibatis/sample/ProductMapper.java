package org.apache.ibatis.sample;

import java.util.List;

public interface ProductMapper {
  int insertProduct(Product product);

  Product queryProduct(Long productId);

  List<Product> queryByPage(Product product);
}
