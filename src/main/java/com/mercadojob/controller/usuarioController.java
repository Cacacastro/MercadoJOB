package com.mercadojob.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadojob.db.localidadeDAL;
import com.mercadojob.db.usuarioDAL;
import com.mercadojob.entity.localidade;
import com.mercadojob.entity.usuario;

@RestController
public class usuarioController {
	@RequestMapping(value="/api/usuarios/listar")	
	public ResponseEntity <Object> listarTodos()
	{
		usuarioDAL dal = new usuarioDAL();
		Map<String,usuario> mappessoas = new HashMap<>();
		List<usuario> todos = dal.getUsuarios("", false);
		for(usuario u : todos) {
			u.setSenha("");
		   mappessoas.put(""+u.getId(), u);
		}
		return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);	
	}
	
	@RequestMapping(value="/api/usuarios/apagar")
	public ResponseEntity <Object> apagar(@RequestParam(value="id") int id)
	{
		String retorno="Erro ao apagar";
		usuarioDAL dal = new usuarioDAL();
		if(dal.apagar(id))
			retorno="Apagado com sucesso!";
		return new ResponseEntity<>(retorno,HttpStatus.OK); 
	}
	
    
    @RequestMapping(value="/api/usuarios/buscar")	
    public ResponseEntity <Object> buscar(@RequestParam(value="id") int id)
    {   usuario u = null;

    	usuarioDAL dal = new usuarioDAL();
    	u = dal.getUsuario(id);

        return new ResponseEntity<>(u,HttpStatus.OK);	
    }

    
    @RequestMapping(value="/api/usuarios/listarFiltro")	
    public ResponseEntity <Object> listarFiltro(@RequestParam(value="chave") String  
                                  chave, @RequestParam(required=false) String filtro)
    {
      localidadeDAL dal = new localidadeDAL();
      Map<String,localidade> mappessoas = new HashMap<>();
      if(chave.equals("MINHACHAVEVALIDA"))
      {
         List<localidade> todos = dal.getLocalidades("", false);
         for(localidade p : todos)
         if(filtro==null || p.getCidade().toUpperCase().contains(filtro.toUpperCase()))
              mappessoas.put(""+p.getId(), p);
      }
      return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);		
    }
    
    @RequestMapping(value="/api/usuarios/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> registrar(@RequestBody usuario u) 
    { 
      usuarioDAL dal = new usuarioDAL();
      String retorno="Erro!";
      if (u.getId()==0) 
      {
         dal.salvar(u);
         retorno = "Gravado com sucesso!";
      }
      else  {
    	  dal.alterar(u);
    	  retorno = "Alterado com sucesso";
      }
      return new ResponseEntity<>(retorno,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/api/usuario/entrar")
    public ResponseEntity<Object> entrar(@RequestBody usuario u)
    {
    	String retorno = "";
    	
    	usuario user = null;
    	usuarioDAL dal = new usuarioDAL();
    	
    	user = dal.getUsuario(u.getEmail());
    	
    	if(user != null)
    	{
    		if(user.getEmail().equals(u.getEmail()) && user.getSenha().equals(u.getSenha()))
    		{
    			if(user.isAdm())
    				retorno = "adm";
    			else
    				retorno = "logado";
    		}
    		else
    			retorno = "email ou senha incorretos";
    	}
    	else
    		retorno = "Usuário não encontrado";
    	
    	return new ResponseEntity<>(retorno,HttpStatus.CREATED);
    }
	
	
}
