package com.djdg.zzkg.supply.db.entity;

import com.djdg.zzkg.supply.common.BaseEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * UserTest:刘敏华 shadow.liu@hey900.com
 * Date: 2017/9/7
 * Time: 15:07
 */

@Entity
@Table(name = "user_test")
public class UserTest extends BaseEntity {


    @Id
    @SequenceGenerator(name = "supplySeq", sequenceName = "supply_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "supplySeq")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
