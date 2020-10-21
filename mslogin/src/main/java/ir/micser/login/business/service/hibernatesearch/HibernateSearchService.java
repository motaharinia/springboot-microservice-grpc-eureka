package ir.micser.login.business.service.hibernatesearch;

import com.motaharinia.msutility.json.PrimitiveResponse;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;
import java.util.Set;

public interface HibernateSearchService {

    QueryBuilder getQueryBuilder(Class entityClass) throws InterruptedException;

    List<Integer> indexIdListBy(Class entityClass, Query luceneQuery, String sortString) throws Exception;

    List<Object[]> indexArrayListBy(Class entityName, Query luceneQuery, String projection, String sort, Integer pagingRows, Integer pagingNo) throws Exception;

    PrimitiveResponse rebuildIndexer(Set<String> entitySet) throws Exception;

}
