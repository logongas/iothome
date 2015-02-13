/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.service.impl;

import es.logongas.iothome.dao.MeasureDAO;
import es.logongas.iothome.modelo.Device;
import es.logongas.iothome.modelo.Measure;
import es.logongas.iothome.service.MeasureCRUDService;
import es.logongas.ix3.service.impl.CRUDServiceImpl;
import java.util.Date;
import java.util.List;

/**
 *
 * @author logongas
 */
public class MeasureCRUDServiceImpl extends CRUDServiceImpl<Measure, Integer> implements MeasureCRUDService {

    private MeasureDAO getMeasureDAO() {
        return (MeasureDAO) getDAO();
    }
    
    @Override
    public Measure getLast(Device device) {
        return getMeasureDAO().getLast(device);
    }

    @Override
    public List<Measure> getGroupByMinute(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        return getMeasureDAO().getGroupByMinute(device, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }

    @Override
    public List<Measure> getGroupByHour(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        return getMeasureDAO().getGroupByHour(device, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }

    @Override
    public List<Measure> getGroupByDay(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        return getMeasureDAO().getGroupByDay(device, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }

    @Override
    public List<Measure> getGroupByWeek(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        return getMeasureDAO().getGroupByWeek(device, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }
    
}
