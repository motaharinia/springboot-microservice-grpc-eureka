package ir.micser.statemachine.business.service.state;

import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.filter.SearchFilterModel;
import ir.micser.login.presentation.adminuser.AdminUserModel;
import ir.micser.statemachine.persistence.orm.state.State;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس مدیریت وضعیت ها
 */
public interface StateService {

    /**
     * ثبت وضعیت
     * @param stateModel
     * @return
     * @throws UtilityException
     * @throws IllegalAccessException
     * @throws BusinessException
     * @throws InvocationTargetException
     */
    State createOrUpdate(StateModel stateModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException;

    /**
     * رول بک وضعیت
     * @param referenceCode
     * @throws UtilityException
     * @throws IllegalAccessException
     * @throws BusinessException
     * @throws InvocationTargetException
     */
    void rollback(String referenceCode) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException;



}
