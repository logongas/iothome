/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.modelo;

import java.util.Date;

/**
 *
 * @author logongas
 */
public class Measure {

    private int idMeasure;

    private Date time;
    private Device device;

    private Float stream0;
    private Float stream1;
    private Float stream2;

    /**
     * @return the idMeasure
     */
    public int getIdMeasure() {
        return idMeasure;
    }

    /**
     * @param idMeasure the idMeasure to set
     */
    public void setIdMeasure(int idMeasure) {
        this.idMeasure = idMeasure;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * @return the stream0
     */
    public Float getStream0() {
        return stream0;
    }

    /**
     * @param stream0 the stream0 to set
     */
    public void setStream0(Float stream0) {
        this.stream0 = stream0;
    }

    /**
     * @return the stream1
     */
    public Float getStream1() {
        return stream1;
    }

    /**
     * @param stream1 the stream1 to set
     */
    public void setStream1(Float stream1) {
        this.stream1 = stream1;
    }

    /**
     * @return the stream2
     */
    public Float getStream2() {
        return stream2;
    }

    /**
     * @param stream2 the stream2 to set
     */
    public void setStream2(Float stream2) {
        this.stream2 = stream2;
    }

}
