package ir.micser.statemachine.persistence.orm.statedetail;


import com.motaharinia.msjpautility.entity.GenericEntity;
import ir.micser.statemachine.business.service.state.EntityNameEnum;
import ir.micser.statemachine.business.service.state.GRPCNameEnum;
import ir.micser.statemachine.persistence.orm.state.State;

import javax.persistence.*;
import java.io.Serializable;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس انتیتی جزییات وضعیت
 */
@Entity
@Table(name = "state_detail")
public class StateDetail extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * نام grpc
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "grpc_name_enum")
    private GRPCNameEnum grpcNameEnum;

    /**
     * نام انتیتی
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "entity_name_enum")
    private EntityNameEnum entityNameEnum;


    /**
     *آیدی انتیتی
     */
    @Column(name = "entity_id")
    private Integer entityId;


    /**
     *آیدی وضعیت
     */
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State stateId) {
        this.state = stateId;
    }
}
