package com.tr.agit.hrapp.controller.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PaginationRequest implements Serializable {

    private static final long serialVersionUID = -4209060708801047985L;
    private int page;
    private int limit;
}