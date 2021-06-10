package com.mercadojob.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mercadojob.entity.Anuncio;
import com.mercadojob.util.Conexao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class RelatoriosController {

    @Autowired
    private ResourceLoader resourceLoader;
    
    
    @RequestMapping(value="/geraRelatorio", method=RequestMethod.GET)
    public ResponseEntity<byte[]> geraRelatorio (@RequestParam(value="relatorio") String relatorio) throws IOException
    {
        // path referencia o caminho relativo (realpath) para a pasta que se encontra os .jasper
        String path = resourceLoader.getResource("classpath:reports/rel_anuncios.jasper").getURI().getPath();
        byte[] contents = gerarRelatorioPDF("select * from anuncio inner join categoria on categoria.cat_id = anuncio.cat_id inner join localidade on localidade.loc_id = anuncio.loc_id inner join usuario on usuario.user_id = anuncio.user_id order by categoria.cat_nome", path);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        //headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }
    
    private byte[] gerarRelatorioPDF(String sql, String relat)
    {   byte[] pdf;
        try { //sql para obter os dados para o relatorio
            JasperPrint jasperprint=null;
            ResultSet rs = new Conexao().consultar(sql);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            jasperprint = JasperFillManager.fillReport(relat, null, jrRS);
            pdf=JasperExportManager.exportReportToPdf(jasperprint);

        } catch (JRException erro) {
            pdf=null;
        }
        return pdf;
    }
}
