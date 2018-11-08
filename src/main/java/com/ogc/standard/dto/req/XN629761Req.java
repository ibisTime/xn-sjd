package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN629761Req {

    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
