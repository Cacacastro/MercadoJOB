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

import com.mercadojob.db.categoriaDAL;
import com.mercadojob.entity.categoria;

@RestController
public class categoriaController {

	@RequestMapping(value="/listarCat")	
	public ResponseEntity <Object> listarTodos()
	{
		categoriaDAL dal = new categoriaDAL();
		Map<String,categoria> mappessoas = new HashMap<>();
		List<categoria> todos = dal.getCategorias("", false);
		for(categoria c : todos)
		   mappessoas.put(""+c.getId(), c);
		return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);		
	}
    
    @RequestMapping(value="/buscarCat")	
    public ResponseEntity <Object> buscar(@RequestParam(value="id") int id)
    {   categoria c=null;

    	categoriaDAL dal = new categoriaDAL();
        for (categoria pe : dal.getCategorias("", false))
             if (pe.getId()==id) c=pe;

        return new ResponseEntity<>(c,HttpStatus.OK);	
    }

    @RequestMapping(value="/apagarCat")	
    public ResponseEntity <Object> apagar(@RequestParam(value="id") int id)
    {
    	categoriaDAL dal = new categoriaDAL();
    	String retorno="problemas ao apagar";
    	for (categoria pe : dal.getCategorias("", false))
    	  if (pe.getId()==id){ 
                	dal.apagar(pe.getId()); 
                	retorno="Excluído com sucesso";
             break;}
    	return new ResponseEntity<>(retorno,HttpStatus.OK); 	
    }
    
    @RequestMapping(value="/listarFiltroCat")	
    public ResponseEntity <Object> listarFiltro(@RequestParam(value="chave") String  
                                  chave, @RequestParam(required=false) String filtro)
    {
      categoriaDAL dal = new categoriaDAL();
      Map<String,categoria> mappessoas = new HashMap<>();
      if(chave.equals("MINHACHAVEVALIDA"))
      {
         List<categoria> todos = dal.getCategorias("", false);
         for(categoria p : todos)
         if(filtro==null || p.getNome().toUpperCase().contains(filtro.toUpperCase()))
              mappessoas.put(""+p.getId(), p);
      }
      return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);		
    }
    
    @RequestMapping(value = "/cadastrarCat", method = RequestMethod.POST)
    public ResponseEntity<Object> cadPessoa(@RequestBody categoria pessoa) 
    { 
      categoriaDAL dal = new categoriaDAL();
      String retorno="Gravado com sucesso";
      if (pessoa.getId()==0) 
      {
         dal.salvar(pessoa);
      }
      else  //alteração
      {  int i=0;
         retorno="Alterado com sucesso";
         while(dal.getCategoria(i).getId()!=pessoa.getId()) i++;
         if(i<dal.getCategorias("", false).size()) dal.alterar(pessoa);
         else retorno="Erro ao alterar";
      }
      return new ResponseEntity<>(retorno,HttpStatus.CREATED);
    }



}
