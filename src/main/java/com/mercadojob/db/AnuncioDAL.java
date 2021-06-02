package com.mercadojob.db;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mercadojob.entity.Anuncio;
import com.mercadojob.entity.Localidade;
import com.mercadojob.util.Conexao;

public class AnuncioDAL {
	public boolean salvar (Anuncio a)
    {
		String sql="insert into anuncio values (default, #0, #1, #2, '#3', '#4', #5, '#6', '#7', '#8', '#9')";
        sql = sql.replace("#0", a.getCategoria().getId()+"");
        sql = sql.replace("#1", a.getUsuario().getId()+"");
        sql = sql.replace("#2", a.getLocalidade().getId()+"");
        sql = sql.replace("#3", a.getNome());
        sql = sql.replace("#4", a.getDescCurta());
        sql = sql.replace("#5", a.getValor()+"");
        sql = sql.replace("#6", a.getFoto1());
        sql = sql.replace("#7", a.getFoto2());
        sql = sql.replace("#8", a.getFoto3());
        sql = sql.replace("#9", a.getDescLonga());
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
	
	
	public boolean alterar (Anuncio a)
    {   
		String sql = "update anuncio set cat_id=#0, user_id=#1, loc_id=#2, anun_nome='#3', anun_descCurta='#4', anun_valor=#5, anun_foto1='#6', anun_foto2='#7', anun_foto3='#8', anun_descLonga='#9' where anun_id = " + a.getId();
		sql = sql.replace("#0", a.getCategoria().getId()+"");
        sql = sql.replace("#1", a.getUsuario().getId()+"");
        sql = sql.replace("#2", a.getLocalidade().getId()+"");
        sql = sql.replace("#3", a.getNome());
        sql = sql.replace("#4", a.getDescCurta());
        sql = sql.replace("#5", a.getValor()+"");
        sql = sql.replace("#6", a.getFoto1());
        sql = sql.replace("#7", a.getFoto2());
        sql = sql.replace("#8", a.getFoto3());
        sql = sql.replace("#9", a.getDescLonga());
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
	
	public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from anuncio where anun_id="+id);
        con.fecharConexao();
        return flag;                      
    }
	
	public Anuncio getAnuncio(int id)
    {   Anuncio a = new Anuncio();
        String sql="select * from anuncio where anun_id="+id;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              a.setId(id);
              a.setCategoria(new CategoriaDAL().getCategoria(rs.getInt("cat_id")));
              a.setUsuario(new UsuarioDAL().getUsuario(rs.getInt("user_id")));
              a.setLocalidade(new LocalidadeDAL().getLocalidade(rs.getInt("loc_id")));
              a.setNome(rs.getString("anun_nome"));
              a.setDescCurta(rs.getString("anun_descCurta"));
              a.setValor(rs.getDouble("anun_valor"));
              a.setFoto1(rs.getString("anun_foto1"));
              a.setFoto2(rs.getString("anun_foto2"));
              a.setFoto3(rs.getString("anun_foto3"));
              a.setDescLonga(rs.getString("anun_descLonga"));
          }

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return a;
    }
	
	public ArrayList <Anuncio> getAnuncios(String filtro,boolean flag)
    {   ArrayList <Anuncio> lista=new ArrayList();
        String sql="select * from anuncio";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by anun_id";
        else
            sql+=" order by anun_nome";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(
            		 new Anuncio(
            				 rs.getInt("anun_id"),
            				 new CategoriaDAL().getCategoria(rs.getInt("cat_id")),
            				 new UsuarioDAL().getUsuario(rs.getInt("user_id")),
            				 new LocalidadeDAL().getLocalidade(rs.getInt("loc_id")),
            				 rs.getString("anun_nome"),
            				 rs.getString("anun_descCurta"),
            				 rs.getDouble("anun_valor"),
            				 rs.getString("anun_descLonga"),
            				 rs.getString("anun_foto1"),
            				 rs.getString("anun_foto2"),
            				 rs.getString("anun_foto3")
            		 )
             );
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
}
