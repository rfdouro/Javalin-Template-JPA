/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.javalincrud.javalincrud.util;

import br.org.rfdouro.javalincrud.javalincrud.persistencia.JPAUtil;
import io.javalin.Handler;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author romulo.douro
 */
public class Filtros {

 public static Handler handleInsereEntityManager = ctx -> {
  EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
  EntityManager em = emf.createEntityManager();
  em.getTransaction().begin();
  ctx.req.setAttribute("EM", em);
 };

 public static Handler handleFechaEntityManager = ctx -> {
  EntityManager em = (EntityManager) ctx.req.getAttribute("EM");
  if (em != null && em.isOpen()) {
   try {
    em.getTransaction().commit();
   } catch (Exception ex) {
    em.getTransaction().rollback();
   } finally {
    try {
     em.close();
     em.clear();
    } catch (Exception exf) {

    }
   }
  }
 };
}
