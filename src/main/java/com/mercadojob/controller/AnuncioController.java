package com.mercadojob.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
         dal.salvar(a);
         retorno = "Gravado com sucesso!";
      }
      else  {
    	  dal.alterar(a);
    	  retorno = "Alterado com sucesso";
      }
      return new ResponseEntity<>(retorno,HttpStatus.CREATED);
    }
}
