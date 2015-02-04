/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.logongas.iothome.security;

import es.logongas.ix3.core.BusinessException;
import es.logongas.ix3.security.authentication.AuthenticationProvider;
import es.logongas.ix3.security.authentication.Credential;
import es.logongas.ix3.security.authentication.Principal;
import es.logongas.ix3.security.authentication.impl.CredentialImplLoginPassword;
import java.io.Serializable;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author logongas
 */
public class AuthenticationProviderImplSimple implements AuthenticationProvider {

    private static final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    private static final Usuario logongas = new Usuario(1, "Lorenzo");
    private static final Usuario sergio = new Usuario(2, "Sergio");

    @Override
    public Principal authenticate(Credential credential) throws BusinessException {
        CredentialImplLoginPassword credentialImplLoginPassword = (CredentialImplLoginPassword) credential;

        System.out.println( passwordEncryptor.encryptPassword(credentialImplLoginPassword.getPassword()));
        
        if (credentialImplLoginPassword.getLogin().equals("logongas")) {
            if (passwordEncryptor.checkPassword(credentialImplLoginPassword.getPassword(), "csVxBd20o+5z/nl4Y4RpTeXwuH1Ffh9Sylq2y0smCIEuRJUAJs5BEDyCE1K0k35i")) {
                return logongas;
            } else {
                return null;
            }
        } else if (credentialImplLoginPassword.getLogin().equals("sergio")) {
            if (passwordEncryptor.checkPassword(credentialImplLoginPassword.getPassword(), "csVxBd20o+5z/nl4Y4RpTeXwuH1Ffh9Sylq2y0smCIEuRJUAJs5BEDyCE1K0k35i")) {
                return sergio;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public Principal getPrincipalBySID(Serializable sid) throws BusinessException {
        if ((Integer)sid==1) {
            return logongas;
        } else if ((Integer)sid==2) {
            return sergio;
        } else {
            return null;
        }
    }

}
