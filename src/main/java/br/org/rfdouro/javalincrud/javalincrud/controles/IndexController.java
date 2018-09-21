/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.javalincrud.javalincrud.controles;

import io.javalin.Handler;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author romulo.douro
 */
public class IndexController {

 public static Handler inicio = ctx -> {
  Map<String, Object> model = new HashMap<>();
  model.put("mensagem", "Usado o template básico do MaterializeCSS");
  ctx.render("/velocity/index/index.vm", model);
 };
}
