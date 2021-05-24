package com.mercadojob.db;

import com.mercadojob.util.Conexao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mercadojob.entity.usuario;

public class usuarioDAL {
	public boolean salvar (usuario u)
    {
		String sql="insert into usuario values (default, '#1','#2', '#3', '#4', '#5')";
        sql=sql.replace("#1", ""+u.isAdm());
        sql=sql.replace("#2", u.getNome());
        sql = sql.replace("#3", u.getEmail());
        sql = sql.replace("#4", u.getSenha());
        sql = sql.replace("#5", u.getTelefone());
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
	
	
	public boolean alterar (usuario u)
    {   
		String sql = "update usuario set user_adm='#1', user_nome='#2', user_email='#3', user_senha='#4', user_telefone='#5'  where user_id = " + u.getId();
		sql=sql.replace("#1", ""+u.isAdm());
        sql=sql.replace("#2", u.getNome());
        sql = sql.replace("#3", u.getEmail());
        sql = sql.replace("#4", u.getSenha());
        sql = sql.replace("#5", u.getTelefone());
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
	
	public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from usuario where user_id="+id);
        con.fecharConexao();
        return flag;                      
    }
	
	
	public usuario getUsuario(int id)
    {   usuario u = new usuario();
        String sql="select * from usuario where user_id="+id;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setId(id);
              u.setAdm(rs.getBoolean("user_adm"));
              u.setNome(rs.getString("user_nome"));
              u.setEmail(rs.getString("user_email"));
              u.setSenha(rs.getString("user_senha"));
              u.setTelefone(rs.getString("user_telefone"));
          }
          else
        	  u = null;

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return u;
    }
	
	
	public usuario getUsuario(String email)
    {   usuario u = new usuario();
        String sql="select * from usuario where user_email='#1'";
        sql = sql.replace("#1", email);
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setId(rs.getInt("user_id"));
              u.setAdm(rs.getBoolean("user_adm"));
              u.setNome(rs.getString("user_nome"));
              u.setEmail(rs.getString("user_email"));
              u.setSenha(rs.getString("user_senha"));
              u.setTelefone(rs.getString("user_telefone"));
          }
          else
        	  u = null;

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return u;
    }
	
	public ArrayList <usuario> getUsuarios(String filtro, boolean flag)
    {   ArrayList <usuario> lista=new ArrayList();
        String sql="select * from usuario";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by user_nome";
        else
            sql+=" order by user_id";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new usuario(rs.getInt("user_id"), rs.getBoolean("user_adm"), rs.getString("user_nome"), rs.getString("user_email"), rs.getString("user_senha"), rs.getString("user_telefone")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
}
