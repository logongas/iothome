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
    
    private Double stream0;
    private Double stream1;
    private Double stream2;
    private Double stream3;
    private Double stream4;
    private Double stream5;
    private Double stream6;
    private Double stream7;
    private Double stream8;
    private Double stream9;

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
    public Double getStream0() {
        return stream0;
    }

    /**
     * @param stream0 the stream0 to set
     */
    public void setStream0(Double stream0) {
        this.stream0 = stream0;
    }

    /**
     * @return the stream1
     */
    public Double getStream1() {
        return stream1;
    }

    /**
     * @param stream1 the stream1 to set
     */
    public void setStream1(Double stream1) {
        this.stream1 = stream1;
    }

    /**
     * @return the stream2
     */
    public Double getStream2() {
        return stream2;
    }

    /**
     * @param stream2 the stream2 to set
     */
    public void setStream2(Double stream2) {
        this.stream2 = stream2;
    }

    /**
     * @return the stream3
     */
    public Double getStream3() {
        return stream3;
    }

    /**
     * @param stream3 the stream3 to set
     */
    public void setStream3(Double stream3) {
        this.stream3 = stream3;
    }

    /**
     * @return the stream4
     */
    public Double getStream4() {
        return stream4;
    }

    /**
     * @param stream4 the stream4 to set
     */
    public void setStream4(Double stream4) {
        this.stream4 = stream4;
    }

    /**
     * @return the stream5
     */
    public Double getStream5() {
        return stream5;
    }

    /**
     * @param stream5 the stream5 to set
     */
    public void setStream5(Double stream5) {
        this.stream5 = stream5;
    }

    /**
     * @return the stream6
     */
    public Double getStream6() {
        return stream6;
    }

    /**
     * @param stream6 the stream6 to set
     */
    public void setStream6(Double stream6) {
        this.stream6 = stream6;
    }

    /**
     * @return the stream7
     */
    public Double getStream7() {
        return stream7;
    }

    /**
     * @param stream7 the stream7 to set
     */
    public void setStream7(Double stream7) {
        this.stream7 = stream7;
    }

    /**
     * @return the stream8
     */
    public Double getStream8() {
        return stream8;
    }

    /**
     * @param stream8 the stream8 to set
     */
    public void setStream8(Double stream8) {
        this.stream8 = stream8;
    }

    /**
     * @return the stream9
     */
    public Double getStream9() {
        return stream9;
    }

    /**
     * @param stream9 the stream9 to set
     */
    public void setStream9(Double stream9) {
        this.stream9 = stream9;
    }
}
