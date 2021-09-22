package com.assignment.decomodigitial.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class PIN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pinId;
    private String msisdn;
    private String pin;
    private boolean verified = false;
    private long sentAt;
    private long modifiedAt;
    private int retryCount = 0;
    private int validationTry = 0;

}
