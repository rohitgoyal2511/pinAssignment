package com.assignment.decomodigitial.entity;

import com.assignment.decomodigitial.common.Constant;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Entity
@Table
public class PIN {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Size(max = Constant.ID_SIZE)
    private String pinId;
    private String msisdn;
    private String pin;
    private boolean verified = false;
    private long sentAt;
    private long modifiedAt;
    private int validationTry = 0;

}
