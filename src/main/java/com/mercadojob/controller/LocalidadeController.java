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
import com.mercadojob.entity.Localidade;



@RestController
public class LocalidadeController {
	@RequestMapping(value="/api/localidades/listar")	
	public ResponseEntity <Object> listarTodos()
	{
		LocalidadeDAL dal = new LocalidadeDAL();
		Map<String,Localidade> mappessoas = new HashMap<>();
		List<Localidade> todos = dal.getLocalidades("", true);
		for(Localidade l : todos)
		   mappessoas.put(""+l.getId(), l);
		return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);	
	}
	@RequestMapping(value="/api/localidades/apagar")
	public ResponseEntity <Object> apagar(@RequestParam(value="id") int id)
	{
		String retorno="Erro ao apagar";
		LocalidadeDAL dal = new LocalidadeDAL();
		if(dal.apagar(id))
			retorno="Apagado com sucesso!";
		return new ResponseEntity<>(retorno,HttpStatus.OK); 
	}
	
    
    @RequestMapping(value="/api/localidades/buscar")	
    public ResponseEntity <Object> buscar(@RequestParam(value="id") int id)
    {
    	Localidade l = null;
    	LocalidadeDAL dal = new LocalidadeDAL();
    	l = dal.getLocalidade(id);
        return new ResponseEntity<>(l,HttpStatus.OK);	
    }

    
    @RequestMapping(value="/api/localidades/listarFiltro")	
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
    
    @RequestMapping(value = "/api/localidades/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<Object> cadPessoa(@RequestBody Localidade l) 
    { 
      LocalidadeDAL dal = new LocalidadeDAL();
      String retorno="Erro!";
      if (l.getId()==0) 
      {
         dal.salvar(l);
         retorno = "Gravado com sucesso!";
      }
      else  {
    	  dal.alterar(l);
    	  retorno = "Alterado com sucesso";
      }
      return new ResponseEntity<>(retorno,HttpStatus.CREATED);
    }
}
