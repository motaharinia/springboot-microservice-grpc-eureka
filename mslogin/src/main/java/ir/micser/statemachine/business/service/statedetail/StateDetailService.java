package ir.micser.statemachine.business.service.statedetail;

import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import ir.micser.statemachine.business.service.state.StateDetailModel;
import ir.micser.statemachine.business.service.state.StateModel;
import ir.micser.statemachine.persistence.orm.state.State;
import ir.micser.statemachine.persistence.orm.statedetail.StateDetail;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس مدیریت جزییات وضعیت ها
 */
public interface StateDetailService {

    /**
     * ثبت جزییات وضعیت
     *
     * @param stateDetailModel
     * @return
     * @throws UtilityException
     * @throws IllegalAccessException
     * @throws BusinessException
     * @throws InvocationTargetException
     */
    StateDetail create(StateDetailModel stateDetailModel, State state) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException;

    List<StateDetail> findByState(State state) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException;

}
