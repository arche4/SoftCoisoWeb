/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author manue
 */
public class JPAFactory {
     private static final EntityManagerFactory FACTORY;
    private static final String UP = "com.co.softcoisoweb_SoftCoisoWeb_war_V.1PU";
    
    static {        
        FACTORY = Persistence.createEntityManagerFactory(UP);
    }

    public static EntityManagerFactory getFACTORY() {
        return FACTORY;
    }
}
