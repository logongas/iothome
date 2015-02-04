/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.security;

import es.logongas.ix3.security.authentication.Principal;
import java.io.Serializable;

/**
 *
 * @author logongas
 */
public class Usuario implements Principal {

    private final int sid;
    private final String name; 

    public Usuario(int sid, String name) {
        this.sid = sid;
        this.name = name;
    }

    @Override
    public Serializable getSid() {
        return sid;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
