/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.javalincrud.javalincrud.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author romulo.douro
 */
public class JPAUtil {

 private static final String PERSISTENCE_UNIT_NAME = "dbTeste-PU";
 private static EntityManagerFactory factory;

 public static EntityManagerFactory getEntityManagerFactory() {
  if (factory == null || (factory != null && !factory.isOpen())) {
   factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }
  return factory;
 }

 public static void shutdown() {
  if (factory != null) {
   factory.close();
   factory = null;
  }
 }
}
