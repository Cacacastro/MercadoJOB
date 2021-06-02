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

import com.mercadojob.db.LocalidadeDAL;
import com.mercadojob.db.UsuarioDAL;
import com.mercadojob.entity.Localidade;
import com.mercadojob.entity.Usuario;

@RestController
public class UsuarioController {
	@RequestMapping(value="/api/usuarios/listar")	
	public ResponseEntity <Object> listarTodos()
	{
		UsuarioDAL dal = new UsuarioDAL();
		Map<String,Usuario> mappessoas = new HashMap<>();
		List<Usuario> todos = dal.getUsuarios("", false);
		for(Usuario u : todos) {
			u.setSenha("");
		   mappessoas.put(""+u.getId(), u);
		}
		return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);	
	}
	
	@RequestMapping(value="/api/usuarios/apagar")
	public ResponseEntity <Object> apagar(@RequestParam(value="id") int id)
	{
		String retorno="Erro ao apagar";
		UsuarioDAL dal = new UsuarioDAL();
		if(dal.apagar(id))
			retorno="Apagado com sucesso!";
		return new ResponseEntity<>(retorno,HttpStatus.OK); 
	}
	
    
    @RequestMapping(value="/api/usuarios/buscar")	
    public ResponseEntity <Object> buscar(@RequestParam(value="id") int id)
    {   Usuario u = null;

    	UsuarioDAL dal = new UsuarioDAL();
    	u = dal.getUsuario(id);

        return new ResponseEntity<>(u,HttpStatus.OK);	
    }

    
    @RequestMapping(value="/api/usuarios/listarFiltro")	
    public ResponseEntity <Object> listarFiltro(@RequestParam(value="chave") String  
                                  chave, @RequestParam(required=false) String filtro)
    {
      LocalidadeDAL dal = new LocalidadeDAL();
      Map<String,Localidade> mappessoas = new HashMap<>();
      if(chave.equals("MINHACHAVEVALIDA"))
      {
         List<Localidade> todos = dal.getLocalidades("", false);
         for(Localidade p : todos)
         if(filtro==null || p.getCidade().toUpperCase().contains(filtro.toUpperCase()))
              mappessoas.put(""+p.getId(), p);
      }
      return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);		
    }
    
    @RequestMapping(value="/api/usuarios/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> registrar(@RequestBody Usuario u) 
    { 
      UsuarioDAL dal = new UsuarioDAL();
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
    public ResponseEntity<Object> entrar(@RequestBody Usuario u)
    {
    	String retorno = "";
    	
    	Usuario user = null;
    	UsuarioDAL dal = new UsuarioDAL();
    	
    	user = dal.getUsuario(u.getEmail());
    	
    	if(user != null)
    	{
    		if(user.getEmail().equals(u.getEmail()) && user.getSenha().equals(u.getSenha()))
    		{
    			user.setSenha("");
    			return new ResponseEntity<>(user,HttpStatus.CREATED);
    		}
    		else
    			retorno = "email ou senha incorretos";
    	}
    	else
    		retorno = "Usuário não encontrado";
    	
    	return new ResponseEntity<>(retorno,HttpStatus.CREATED);
    }
	
	
}
