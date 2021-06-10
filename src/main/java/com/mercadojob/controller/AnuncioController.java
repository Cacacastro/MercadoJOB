package com.mercadojob.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mercadojob.db.AnuncioDAL;
import com.mercadojob.db.LocalidadeDAL;
import com.mercadojob.entity.Anuncio;
import com.mercadojob.entity.Localidade;

@RestController
public class AnuncioController {
	
	@RequestMapping(value="/api/anuncios/upload", method = RequestMethod.POST)
	public ResponseEntity<Object> fotos(@RequestParam("file") MultipartFile file) throws Exception 
	{
		String retorno = "";
		String nome = file.getOriginalFilename();
		byte[] foto = new byte[(int)file.getSize()];
		
		try {
			file.getInputStream().read(foto);
			String path = new FileSystemResource("").getFile().getAbsolutePath();
			FileOutputStream arquivo = new FileOutputStream(new File(path + "/src/main/resources/static/assets/img/anuncios/" + nome));
			arquivo.write(foto);
            arquivo.close();
            retorno = "sucesso";
		} catch (IOException e) {
			retorno = "erro ao enviar";
		}
		
		return new ResponseEntity<>(retorno,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/anuncios/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<Object> cadAnuncio(@RequestBody Anuncio a) 
    { 
      AnuncioDAL dal = new AnuncioDAL();
      String retorno="Erro!";
      if (a.getId()==0) 
      {
    	 if(verificaUser(a.getUsuario().getId())) {
    		 dal.salvar(a);
         	retorno = "Gravado com sucesso!";
    	 }
    	 else
    		 retorno = "Falha ao registrar o usuário já tem anúncio cadastrado! Somente um anuncio por usuário é permitido no momento";
      }
      else  {
    	  dal.alterar(a);
    	  retorno = "Alterado com sucesso";
      }
      return new ResponseEntity<>(retorno,HttpStatus.CREATED);
    }
	
	
	
	public boolean verificaUser(int codUser) 
	{
		AnuncioDAL dal = new AnuncioDAL();
		boolean retorno = false;
		ArrayList<Anuncio> a = dal.getAnuncios("user_id = " + codUser, false);
		
		if(a.size() == 0)
			retorno = true;
		
		return retorno;
	}
	
	@RequestMapping(value = "/api/anuncios/obteranuncio")
	public ResponseEntity<Object> anuncioUser(int codUser) 
	{
		AnuncioDAL dal = new AnuncioDAL();
		Anuncio retorno = null;
		ArrayList<Anuncio> a = dal.getAnuncios("user_id = " + codUser, false);
		
		if(a.size() == 1)
			retorno = a.get(0);
		
		return new ResponseEntity<>(retorno,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/api/anuncios/buscar")	
    public ResponseEntity <Object> buscar(int id)
    {
    	Anuncio a = null;
    	AnuncioDAL dal = new AnuncioDAL();
    	a = dal.getAnuncio(id);
    	a.getUsuario().setSenha("");
        return new ResponseEntity<>(a,HttpStatus.OK);	
    }
	
	
	@RequestMapping(value="/api/anuncios/listar")	
	public ResponseEntity <Object> listarTodos()
	{
		AnuncioDAL dal = new AnuncioDAL();
		Map<String,Anuncio> mappessoas = new HashMap<>();
		List<Anuncio> todos = dal.getAnuncios("", true);
		for(Anuncio a : todos) {
			a.getUsuario().setSenha("");
			mappessoas.put(""+a.getId(), a);
		}
		return new ResponseEntity<>(mappessoas.values(),HttpStatus.OK);	
	}
	
}
