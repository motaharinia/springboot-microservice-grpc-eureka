package ir.micser.login.persistence.orm.adminuserorganizationunit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس ریپازیتوری اطلاعات تماس ادمین
 */
@Repository
public interface AdminUserOrganizationUnitRepository extends JpaRepository<AdminUserOrganizationUnit, Integer>, JpaSpecificationExecutorWithProjection<AdminUserOrganizationUnit> {
 List<AdminUserOrganizationUnit> findAllByParent(AdminUserOrganizationUnit adminUserOrganizationUnit);
}
