package org.apache.ibatis.sample;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisSimple {

  private static SqlSessionFactory sessionFactory;

  @BeforeClass
  public static void setUp() throws Exception {
    InputStream inputStream = Resources.getResourceAsStream("org/apache/ibatis/sample/mybatis-config.xml");
    sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  }

  @Test
  public void testSelectOne() {
    try(SqlSession sqlSession = sessionFactory.openSession()) {
      Object product = sqlSession.selectOne("queryProduct", 1);
      System.out.println(product);
    }
  }

  @Test
  public void testMapper() {
    try(SqlSession sqlSession = sessionFactory.openSession()) {
      ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
      Product product = productMapper.queryProduct(1L);
      System.out.println("product = " + product);
    }
  }

  @Test
  public void testQueryByPage() throws IOException {
    try(SqlSession sqlSession = sessionFactory.openSession()) {
      ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

      Product query = new Product();
      query.setProductStatus("1");
      List<Product> products = productMapper.queryByPage(query);
      products.forEach(System.out::println);
    }
  }



}
