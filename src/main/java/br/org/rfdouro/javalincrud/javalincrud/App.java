/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.javalincrud.javalincrud;

import br.org.rfdouro.javalincrud.javalincrud.util.Filtros;
import br.org.rfdouro.javalincrud.javalincrud.controles.IndexController;
import br.org.rfdouro.javalincrud.javalincrud.controles.PessoaController;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;

/**
 *
 * @author romulo.douro
 */
public class App {

 public static void main(String[] args) {
  int port = (args != null && args.length > 0 && args[0] != null) ? Integer.parseInt(args[0]) : 7000;

  Javalin app = Javalin.create()
          .enableCorsForOrigin("*") // enables cors for the specified origin(s)
          .enableStaticFiles("/public")
          //.enableRouteOverview("/routes")
          .start(port);
  app.routes(() -> {
   before(Filtros.handleInsereEntityManager);
   ApiBuilder.after(Filtros.handleFechaEntityManager);
   get("/", IndexController.inicio);
   get("/pessoa/all", PessoaController.all);
   get("/pessoa/get/:id", PessoaController.get);
   ApiBuilder.crud("pessoa/:id", new PessoaController());
  });
 }
}
