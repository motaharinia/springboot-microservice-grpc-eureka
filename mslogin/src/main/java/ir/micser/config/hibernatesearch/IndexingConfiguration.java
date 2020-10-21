package ir.micser.config.hibernatesearch;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-01<br>
 * Time: 16:49:12<br>
 * Description:کلاس تنظیمات ایندکسینگ هایبرنیت سرچ
 */

@Configuration
@Transactional
public class IndexingConfiguration {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public FullTextEntityManager getFullTextEntityManager() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        return fullTextEntityManager;
    }
}
