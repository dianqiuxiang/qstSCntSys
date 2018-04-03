package com.qst.scnt.model;

public class ParameterInfo {
    /**
     *
     */
    private Integer id;

    /**
     *参数名称
     */
    private String parameterName;

    /**
     *参数值
     */
    private String value;

    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *参数名称
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     *参数名称
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName == null ? null : parameterName.trim();
    }

    /**
     *参数值
     */
    public String getValue() {
        return value;
    }

    /**
     *参数值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}