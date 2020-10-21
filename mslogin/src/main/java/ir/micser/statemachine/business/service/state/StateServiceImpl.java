package ir.micser.statemachine.business.service.state;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import ir.micser.statemachine.business.service.statedetail.StateDetailService;
import ir.micser.statemachine.persistence.orm.state.State;
import ir.micser.statemachine.persistence.orm.state.StateRepository;
import ir.micser.statemachine.persistence.orm.statedetail.StateDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت وضعیت ها
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StateServiceImpl implements StateService {

    /**
     * ریپازیتوری وضعیت
     */
    private StateRepository stateRepository;

    /**
     * سرویس مقادیر ثابت
     */
    private StateDetailService stateDetailService;


    /**
     * متد سازنده
     */
    @Autowired
    public StateServiceImpl(StateDetailService stateDetailService) {
        this.stateDetailService = stateDetailService;
    }

    @Override
    public State createOrUpdate(StateModel stateModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {

        State state = stateRepository.findByReferenceCodeEquals(stateModel.getReferenceCode());
        if (ObjectUtils.isEmpty(state)) {
            state = new State();
            state.setDateStart(new Date());
            state.setReferenceCode(stateModel.getReferenceCode());
            state.setServiceNameEnum(stateModel.getServiceNameEnum());
            state.setMethodTypeEnum(stateModel.getMethodTypeEnum());
            state.setFailed(stateModel.getFailed());
            state.setRolledBack(stateModel.getRolledBack());
            stateRepository.save(state);
        }

        //ثبت جزییات وضعیت
        stateDetailService.create(stateModel.getStateDetailModel(), state);

        return state;
    }

    @Override
    public void rollback(String referenceCode) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        State state = stateRepository.findByReferenceCodeEquals(referenceCode);

        List<StateDetail> stateDetailList = stateDetailService.findByState(state);
    }

}
