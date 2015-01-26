/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.dao;

import es.logongas.iothome.modelo.Device;
import es.logongas.iothome.modelo.Measure;
import es.logongas.ix3.dao.GenericDAO;

/**
 *
 * @author logongas
 */
public interface MeasureDAO extends GenericDAO<Measure,Integer> {
    
    Measure getLast(Device device);
    
}
