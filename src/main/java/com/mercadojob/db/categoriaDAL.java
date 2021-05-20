package com.mercadojob.db;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mercadojob.entity.categoria;
import com.mercadojob.util.Conexao;

public class categoriaDAL {
	public boolean salvar (categoria c)
    {
        ArrayList <categoria> cat = getCategorias("",true);
        int i = cat.get(cat.size()-1).getId()+1;
        String sql="insert into categoria (cat_id,cat_nome) values ('"+i+"', '"
                +c.getNome()+"')";
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (categoria c)
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
    public categoria getCategoria(int id)
    {   categoria t=new categoria();
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
    public ArrayList <categoria> getCategorias(String filtro,boolean flag)
    {   ArrayList <categoria> lista= new ArrayList();
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
             lista.add(new categoria(rs.getInt("cat_id"),rs.getString("cat_nome")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
