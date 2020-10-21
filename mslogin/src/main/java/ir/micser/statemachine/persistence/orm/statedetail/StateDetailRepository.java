package ir.micser.statemachine.persistence.orm.statedetail;


import ir.micser.statemachine.persistence.orm.state.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;

//https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
//https://attacomsian.com/blog/spring-data-jpa-specifications
//https://github.com/pramoth/specification-with-projection
//https://stackoverflow.com/questions/22171822/spring-data-jpa-specification-to-select-specific-columns

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس ریپازیتوری وضعیت
 */
@Repository
public interface StateDetailRepository extends JpaRepository<StateDetail, Integer>, JpaSpecificationExecutorWithProjection<StateDetail> {

    List<StateDetail> findByStateEqualsOOrderByState(State state);
}
