package ir.micser.statemachine.business.service.state;

import ir.micser.statemachine.persistence.orm.state.State;

import javax.persistence.*;

public class StateDetailModel {

    /**
     * شناسه
     */
    private Integer id;

    /**
     * نام grpc
     */
    private GRPCNameEnum grpcNameEnum;

    /**
     * نام انتیتی
     */
    private EntityNameEnum entityNameEnum;


    /**
     *آیدی انتیتی
     */
    private Integer entityId;


    /**
     * وضعیت
     */
    //private State state;


    //-------------getter,setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GRPCNameEnum getGrpcNameEnum() {
        return grpcNameEnum;
    }

    public void setGrpcNameEnum(GRPCNameEnum grpcNameEnum) {
        this.grpcNameEnum = grpcNameEnum;
    }

    public EntityNameEnum getEntityNameEnum() {
        return entityNameEnum;
    }

    public void setEntityNameEnum(EntityNameEnum entityNameEnum) {
        this.entityNameEnum = entityNameEnum;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

}
