/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author logongas
 */
public class Device {
    private int idDevice;
    private String name;
    private String streamName0;
    private String streamName1;
    private String streamName2;
    private String streamName3;
    private String streamName4;
    private String streamName5;
    private String streamName6;
    private String streamName7;
    private String streamName8;
    private String streamName9;
    
    
    public List<Measure> getMeasures(Date start,Date finish,Group groupBy) {
        return null;
    }
    
    public Measure getLastMeasure() {
        return null;
    }    

    /**
     * @return the idDevice
     */
    public int getIdDevice() {
        return idDevice;
    }

    /**
     * @param idDevice the idDevice to set
     */
    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the streamName0
     */
    public String getStreamName0() {
        return streamName0;
    }

    /**
     * @param streamName0 the streamName0 to set
     */
    public void setStreamName0(String streamName0) {
        this.streamName0 = streamName0;
    }

    /**
     * @return the streamName1
     */
    public String getStreamName1() {
        return streamName1;
    }

    /**
     * @param streamName1 the streamName1 to set
     */
    public void setStreamName1(String streamName1) {
        this.streamName1 = streamName1;
    }

    /**
     * @return the streamName2
     */
    public String getStreamName2() {
        return streamName2;
    }

    /**
     * @param streamName2 the streamName2 to set
     */
    public void setStreamName2(String streamName2) {
        this.streamName2 = streamName2;
    }

    /**
     * @return the streamName3
     */
    public String getStreamName3() {
        return streamName3;
    }

    /**
     * @param streamName3 the streamName3 to set
     */
    public void setStreamName3(String streamName3) {
        this.streamName3 = streamName3;
    }

    /**
     * @return the streamName4
     */
    public String getStreamName4() {
        return streamName4;
    }

    /**
     * @param streamName4 the streamName4 to set
     */
    public void setStreamName4(String streamName4) {
        this.streamName4 = streamName4;
    }

    /**
     * @return the streamName5
     */
    public String getStreamName5() {
        return streamName5;
    }

    /**
     * @param streamName5 the streamName5 to set
     */
    public void setStreamName5(String streamName5) {
        this.streamName5 = streamName5;
    }

    /**
     * @return the streamName6
     */
    public String getStreamName6() {
        return streamName6;
    }

    /**
     * @param streamName6 the streamName6 to set
     */
    public void setStreamName6(String streamName6) {
        this.streamName6 = streamName6;
    }

    /**
     * @return the streamName7
     */
    public String getStreamName7() {
        return streamName7;
    }

    /**
     * @param streamName7 the streamName7 to set
     */
    public void setStreamName7(String streamName7) {
        this.streamName7 = streamName7;
    }

    /**
     * @return the streamName8
     */
    public String getStreamName8() {
        return streamName8;
    }

    /**
     * @param streamName8 the streamName8 to set
     */
    public void setStreamName8(String streamName8) {
        this.streamName8 = streamName8;
    }

    /**
     * @return the streamName9
     */
    public String getStreamName9() {
        return streamName9;
    }

    /**
     * @param streamName9 the streamName9 to set
     */
    public void setStreamName9(String streamName9) {
        this.streamName9 = streamName9;
    }
    
    
}
