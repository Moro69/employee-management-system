package com.moro.model.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Smth {

    private Date firstRequestDate;
    private int requestsNumber;

    public void incrRequestsNumber() {
        requestsNumber++;
    }
}
