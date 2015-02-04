/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.dao.impl;

import es.logongas.iothome.dao.MeasureDAO;
import es.logongas.iothome.modelo.Device;
import es.logongas.iothome.modelo.Measure;
import es.logongas.ix3.dao.impl.GenericDAOImplHibernate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author logongas
 */
public class MeasureDAOImplHibernate extends GenericDAOImplHibernate<Measure, Integer> implements MeasureDAO {

    @Override
    public Measure getLast(Device device) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT measure FROM Measure measure WHERE measure.device.idDevice=? ORDER BY time DESC");
        query.setMaxResults(1);
        query.setInteger(0, device.getIdDevice());
        Measure measure = (Measure) query.uniqueResult();
        measure.setDevice(null);
        return measure;
    }

    @Override
    public List<Measure> getGroupByMinute(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {

        String sql = "SELECT "
                + " STR_TO_DATE(DATE_FORMAT(TIME,'%Y%m%d%H%i'),'%Y%m%d%H%i'), ROUND(SUM(stream0)/COUNT(*),2),ROUND(SUM(stream1)/COUNT(*),2),ROUND(SUM(stream2)/COUNT(*),2),ROUND(SUM(stream3)/COUNT(*),2)"
                + " FROM measure"
                + " WHERE idDevice=? " + getWhere(startDateTime, endDateTime)
                + " GROUP BY DATE_FORMAT(TIME,'%Y%m%d%H%i')"
                + " ORDER BY DATE_FORMAT(TIME,'%Y%m%d%H%i') ASC";

        return getMeasures(device, sql, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }

    @Override
    public List<Measure> getGroupByHour(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        String sql = "SELECT "
                + " STR_TO_DATE(DATE_FORMAT(TIME,'%Y%m%d%H'),'%Y%m%d%H'), ROUND(SUM(stream0)/COUNT(*),2),ROUND(SUM(stream1)/COUNT(*),2),ROUND(SUM(stream2)/COUNT(*),2),ROUND(SUM(stream3)/COUNT(*),2)"
                + " FROM measure"
                + " WHERE idDevice=? " + getWhere(startDateTime, endDateTime)                
                + " GROUP BY DATE_FORMAT(TIME,'%Y%m%d%H')"
                + " ORDER BY DATE_FORMAT(TIME,'%Y%m%d%H') ASC";

        return getMeasures(device, sql, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }

    @Override
    public List<Measure> getGroupByDay(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        String sql = "SELECT "
                + " STR_TO_DATE(DATE_FORMAT(TIME,'%Y%m%d'),'%Y%m%d'), ROUND(SUM(stream0)/COUNT(*),2),ROUND(SUM(stream1)/COUNT(*),2),ROUND(SUM(stream2)/COUNT(*),2),ROUND(SUM(stream3)/COUNT(*),2)"
                + " FROM measure"
                + " WHERE idDevice=? " + getWhere(startDateTime, endDateTime)                
                + " GROUP BY DATE_FORMAT(TIME,'%Y%m%d')"
                + " ORDER BY DATE_FORMAT(TIME,'%Y%m%d') ASC";

        return getMeasures(device, sql, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }

    @Override
    public List<Measure> getGroupByWeek(Device device, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        String sql = "SELECT "
                + " date(DATE_SUB(TIME, INTERVAL ( (CONVERT(DATE_FORMAT(TIME,'%w'),UNSIGNED INTEGER) +6) % 7 )  DAY)), ROUND(SUM(stream0)/COUNT(*),2),ROUND(SUM(stream1)/COUNT(*),2),ROUND(SUM(stream2)/COUNT(*),2),ROUND(SUM(stream3)/COUNT(*),2)"
                + " FROM measure"
                + " WHERE idDevice=? " + getWhere(startDateTime, endDateTime)                
                + " GROUP BY DATE_FORMAT(TIME,'%x%v')"
                + " ORDER BY DATE_FORMAT(TIME,'%x%v') ASC";

        return getMeasures(device, sql, startDateTime, endDateTime, startMinuteFilter, endMinuteFilter, startHourFilter, endHourFilter, startWeekDayFilter, endWeekDayFilter, startMonthDayFilter, endMonthDayFilter);
    }

    
    
    private List<Measure> getMeasures(Device device,String sql, Date startDateTime, Date endDateTime, Integer startMinuteFilter, Integer endMinuteFilter, Integer startHourFilter, Integer endHourFilter, Integer startWeekDayFilter, Integer endWeekDayFilter, Integer startMonthDayFilter, Integer endMonthDayFilter) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery(sql);
        int numParameter=0;
        query.setInteger(numParameter++, device.getIdDevice());
        if (startDateTime!=null) {
            query.setTimestamp(numParameter++, startDateTime);
        }
        if (endDateTime!=null) {
            query.setTimestamp(numParameter++, endDateTime);
        }          
        
        List<Object[]> listDatos = query.list();

        List<Measure> measures = new ArrayList<Measure>();
        for (Object[] datos : listDatos) {
            Double rawStream0=(Double) datos[1];
            Double rawStream1=(Double) datos[2];
            Double rawStream2=(Double) datos[3];
            Double rawStream3=(Double) datos[4];
   
            float stream0;
            float stream1;
            float stream2;
            float stream3;
            
            if (rawStream0==null) {
                stream0=0;
            } else {
                stream0=rawStream0.floatValue();
            }
            if (rawStream1==null) {
                stream1=0;
            } else {
                stream1=rawStream1.floatValue();
            }
            if (rawStream2==null) {
                stream2=0;
            } else {
                stream2=rawStream2.floatValue();
            }            
            if (rawStream3==null) {
                stream3=0;
            } else {
                stream3=rawStream3.floatValue();
            }
            
            Measure measure = new Measure(0, (Date) datos[0], null,stream0, stream1, stream2, stream3);

            measures.add(measure);

        }

        return measures;
    }
    
    private String getWhere(Date startDateTime, Date endDateTime) {
        
        
        StringBuilder sb=new StringBuilder();
        sb.append(" AND 1=1 ");
        
        if (startDateTime!=null) {
            sb.append(" AND time>=? ");
        }
        if (endDateTime!=null) {
            sb.append(" AND time<=? ");
        }  
        
        return sb.toString();
        
    }
    
}
