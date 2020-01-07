package io.nakong.modules.collect.entity;

/**
 * @author tom
 */
public class CommonSingleEntity implements  java.io.Serializable{

    private int dateInt ;
    private Float value;


    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
