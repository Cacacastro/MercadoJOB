package com.mercadojob.db;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mercadojob.entity.Categoria;
import com.mercadojob.util.Conexao;

public class CategoriaDAL {
	public boolean salvar (Categoria c)
    {
        ArrayList <Categoria> cat = getCategorias("",true);
        int i = cat.get(cat.size()-1).getId()+1;
        String sql="insert into categoria (cat_id,cat_nome) values ('"+i+"', '"
                +c.getNome()+"')";
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Categoria c)
    {   
        String sql = "update categoria set cat_nome='"+c.getNome()+"' where cat_id="+c.getId();
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from categoria where cat_id="+id);
        con.fecharConexao();
        return flag;                      
    }
    public Categoria getCategoria(int id)
    {   Categoria t=new Categoria();
        String sql="select * from categoria where cat_id="+id;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              t.setId(id);
              t.setNome(rs.getString("cat_nome"));
          }

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return t;
    }
    public ArrayList <Categoria> getCategorias(String filtro,boolean flag)
    {   ArrayList <Categoria> lista= new ArrayList();
        String sql="select * from categoria";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by cat_nome";
        else
            sql+=" order by cat_id";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
