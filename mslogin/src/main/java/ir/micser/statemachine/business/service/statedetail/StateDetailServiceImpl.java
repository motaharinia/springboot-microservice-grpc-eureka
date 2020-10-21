package ir.micser.statemachine.business.service.statedetail;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import ir.micser.statemachine.business.service.state.StateDetailModel;
import ir.micser.statemachine.persistence.orm.state.State;
import ir.micser.statemachine.persistence.orm.statedetail.StateDetail;
import ir.micser.statemachine.persistence.orm.statedetail.StateDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت جزییات وضعیت ها
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class StateDetailServiceImpl implements StateDetailService {

    /**
     * ریپازیتوری جزییات وضعیت
     */
    private StateDetailRepository stateDetailRepository;


    @Override
    public StateDetail create(StateDetailModel stateDetailModel , State state) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        StateDetail stateDetail = new StateDetail();

        stateDetail.setEntityId(stateDetailModel.getEntityId());
        stateDetail.setEntityNameEnum(stateDetailModel.getEntityNameEnum());
        stateDetail.setGrpcNameEnum(stateDetailModel.getGrpcNameEnum());
        stateDetail.setState(state);

        stateDetailRepository.save(stateDetail);

        return stateDetail;
    }

    @Override
    public List<StateDetail> findByState(State state) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        return stateDetailRepository.findByStateEqualsOOrderByState(state);
    }


}
