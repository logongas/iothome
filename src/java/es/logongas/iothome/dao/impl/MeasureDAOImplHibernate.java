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
import java.io.Serializable;
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
        return measure;
    }

}
