/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.service;

import es.logongas.iothome.modelo.Device;
import es.logongas.iothome.modelo.Measure;
import es.logongas.ix3.service.NamedSearch;
import es.logongas.ix3.service.CRUDService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author logongas
 */
public interface MeasureCRUDService extends CRUDService<Measure,Integer> {
    @NamedSearch(parameterNames = {"device"})
    Measure getLast(Device device);

    @NamedSearch(parameterNames = {"device","startDateTime","endDateTime","startMinuteFilter","endMinuteFilter","startHourFilter","endHourFilter","startWeekDayFilter","endWeekDayFilter","startMonthDayFilter","endMonthDayFilter"})
    List<Measure> getGroupByMinute(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter);
    
    @NamedSearch(parameterNames = {"device","startDateTime","endDateTime","startMinuteFilter","endMinuteFilter","startHourFilter","endHourFilter","startWeekDayFilter","endWeekDayFilter","startMonthDayFilter","endMonthDayFilter"})
    List<Measure> getGroupByHour(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter);

    @NamedSearch(parameterNames = {"device","startDateTime","endDateTime","startMinuteFilter","endMinuteFilter","startHourFilter","endHourFilter","startWeekDayFilter","endWeekDayFilter","startMonthDayFilter","endMonthDayFilter"})    
    List<Measure> getGroupByDay(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter);

    @NamedSearch(parameterNames = {"device","startDateTime","endDateTime","startMinuteFilter","endMinuteFilter","startHourFilter","endHourFilter","startWeekDayFilter","endWeekDayFilter","startMonthDayFilter","endMonthDayFilter"})    
    List<Measure> getGroupByWeek(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter);

}
