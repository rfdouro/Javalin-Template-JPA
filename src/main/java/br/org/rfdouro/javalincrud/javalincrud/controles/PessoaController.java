/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.javalincrud.javalincrud.controles;

import br.org.rfdouro.javalincrud.javalincrud.modelos.Pessoa;
import br.org.rfdouro.javalincrud.javalincrud.persistencia.JPAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import io.javalin.Handler;
import io.javalin.apibuilder.CrudHandler;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author romulo.douro
 */
public class PessoaController implements CrudHandler {

 public static Handler all = ctx -> {
  EntityManager em = (EntityManager) ctx.req.getAttribute("EM");
  Query q = em.createQuery("select P from Pessoa P");
  List l = q.getResultList();
  ctx.json(l);
 };

 public static Handler get = ctx -> {
  EntityManager em = (EntityManager) ctx.req.getAttribute("EM");
  Pessoa p = em.find(Pessoa.class, Long.parseLong(ctx.pathParam("id")));
  ctx.json(p);
 };

 @Override
 public void getAll(Context cntxt) {
  EntityManager em = (EntityManager) cntxt.req.getAttribute("EM");
  Query q = em.createQuery("select P from Pessoa P");
  List l = q.getResultList();
  cntxt.json(l);
 }

 @Override
 public void getOne(Context cntxt, String string) {
  EntityManager em = (EntityManager) cntxt.req.getAttribute("EM");
  Pessoa p = em.find(Pessoa.class, Long.parseLong(cntxt.pathParam("id")));
  cntxt.json(p);
 }

 @Override
 public void create(Context cntxt) {
  EntityManager em = (EntityManager) cntxt.req.getAttribute("EM");
  Pessoa p = new Pessoa();
  String nome = (cntxt.formParam("nome") != null) ? cntxt.formParam("nome") : cntxt.queryParam("nome");
  p.setNome(nome);
  em.persist(p);
  cntxt.json("inserido");
 }

 /**
  * Método PATCH do HTTP
  * @param cntxt
  * @param string 
  */
 @Override
 public void update(Context cntxt, String string) {
  EntityManager em = (EntityManager) cntxt.req.getAttribute("EM");
  Pessoa p = em.find(Pessoa.class, Long.parseLong(cntxt.pathParam("id")));
  String nome = (cntxt.formParam("nome") != null) ? cntxt.formParam("nome") : cntxt.queryParam("nome");
  p.setNome(nome);
  em.merge(p);
  cntxt.json("alterado");
 }

 @Override
 public void delete(Context cntxt, String string) {
  EntityManager em = (EntityManager) cntxt.req.getAttribute("EM");
  Pessoa p = em.find(Pessoa.class, Long.parseLong(cntxt.pathParam("id")));
  em.remove(p);
  cntxt.json("excluído");
 }

}
