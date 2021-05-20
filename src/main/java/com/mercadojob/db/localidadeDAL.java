package com.mercadojob.db;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mercadojob.entity.localidade;
import com.mercadojob.util.Conexao;

public class localidadeDAL {
	
	public boolean salvar (localidade l)
    {
		String sql="insert into localidade values (default,'#1','#2', '#3')";
        sql=sql.replace("#1", l.getCidade());
        sql=sql.replace("#2", l.getEstado());
        sql = sql.replace("#3", l.getUF());

        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
	
	
	public boolean alterar (localidade l)
    {   
		String sql = "update localidade set loc_cidade='#1', loc_estado='#2', loc_uf='#3' where loc_id = " + l.getId();
        //String sql = "update localidades set loc_cidade='"+ l.getCidade()+"' loc_estaodo='" + l.getEstado() + "' loc_uf='" + l.getUF() + "' where loc_id="+l.getId();
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
	
	public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from localidade where loc_id="+id);
        con.fecharConexao();
        return flag;                      
    }
	
	public localidade getLocalidade(int id)
    {   localidade l = new localidade();
        String sql="select * from localidade where loc_id="+id;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              l.setId(id);
              l.setCidade(rs.getString("loc_cidade"));
              l.setEstado(rs.getString("loc_estado"));
              l.setUF(rs.getString("loc_uf"));
          }

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return l;
    }
	
	public ArrayList <localidade> getLocalidades(String filtro,boolean flag)
    {   ArrayList <localidade> lista=new ArrayList();
        String sql="select * from localidade";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by loc_cidade";
        else
            sql+=" order by loc_id";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new localidade(rs.getInt("loc_id"),rs.getString("loc_cidade"), rs.getString("loc_estado"), rs.getString("loc_uf")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
}
