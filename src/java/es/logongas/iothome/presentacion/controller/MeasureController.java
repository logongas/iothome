/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.presentacion.controller;

import es.logongas.iothome.dao.MeasureDAO;
import es.logongas.iothome.modelo.Device;
import es.logongas.iothome.modelo.Measure;
import es.logongas.ix3.core.BusinessException;
import es.logongas.ix3.dao.DAOFactory;
import es.logongas.ix3.dao.metadata.MetaDataFactory;
import es.logongas.ix3.web.json.JsonFactory;
import es.logongas.ix3.web.json.JsonWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author logongas
 */
@Controller
public class MeasureController {

    @Autowired
    DAOFactory daoFactory;
    @Autowired
    MetaDataFactory metaDataFactory;
    @Autowired
    ConversionService conversionService;
    @Autowired
    JsonFactory jsonFactory;
    private static final Log log = LogFactory.getLog(MeasureController.class);

    @RequestMapping(value = {"/Measure/last"}, method = RequestMethod.GET, produces = "application/json")
    public void last(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        try {
            MeasureDAO measureDAO = (MeasureDAO)daoFactory.getDAO(Measure.class);
            JsonWriter jsonWriter;
            Device device=(Device)daoFactory.getDAO(Device.class).read(Integer.parseInt(httpRequest.getParameter("idDevice")));
            
            Object result = measureDAO.getLast(device);

            if (result != null) {
                jsonWriter = jsonFactory.getJsonWriter(null);
            } else {
                jsonWriter = jsonFactory.getJsonWriter(Measure.class);
            }
            String jsonOut = jsonWriter.toJson(result);

            noCache(httpServletResponse);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonOut);
        } catch (BusinessException ex) {
            try {
                String jsonOut = jsonFactory.getJsonWriter().toJson(ex.getBusinessMessages());

                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonOut);
            } catch (Exception ex2) {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                httpServletResponse.setContentType("text/plain");
                try {
                    ex.printStackTrace(httpServletResponse.getWriter());
                } catch (Exception ex3) {
                    log.error("Falló al imprimir la traza", ex3);
                }
            }
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (Exception ex2) {
                log.error("Falló al imprimir la traza", ex2);
            }
        }
    }
    
        private void noCache(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Cache-Control", "no-cache");
    }

}
