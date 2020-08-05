/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class ClientesDAO {
    
    private Connection con;
    public ClientesDAO(){
    this.con= new ConnectionFactory().getConnection();
    }
    
    //Metodo cadastrarCliente
    public void cadastarCliente(Clientes obj){
        try {
            //1 passo - criar o comando sql
              String sql = "insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //2 passo - conectar no banco de dados e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getRg());
            pst.setString(3, obj.getCpf());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getTelefone());
            pst.setString(6, obj.getCelular());
            pst.setString(7, obj.getCep());
            pst.setString(8, obj.getEndereco());
            pst.setInt(9, obj.getNumero());
            pst.setString(10, obj.getComplemento());
            pst.setString(11, obj.getBairro());
            pst.setString(12, obj.getCidade());
            pst.setString(13, obj.getEstado());
            
            //3 passo - executar o comkando sql
            pst.execute();
            pst.close();
           
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            System.out.println(e);
        }
    }
     //Metodo Alterar
    public void alterarCliente(Clientes obj){
        try {
            String sql = "update tb_clientes set nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id =?";
            
           PreparedStatement pst = con.prepareStatement(sql);
           
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getRg());
            pst.setString(3, obj.getCpf());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getTelefone());
            pst.setString(6, obj.getCelular());
            pst.setString(7, obj.getCep());
            pst.setString(8, obj.getEndereco());
            pst.setInt(9, obj.getNumero());
            pst.setString(10, obj.getComplemento());
            pst.setString(11, obj.getBairro());
            pst.setString(12, obj.getCidade());
            pst.setString(13, obj.getEstado());
            
            pst.setInt(14, obj.getId());
            
            pst.execute();
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
     //MetodoExcluir
    public void excluirCliente(Clientes obj){
        try {
            // 1 passo comando sql
            String sql = "delete from tb_clintes where id=?";
            
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, obj.getId());
            
            //3 passo - executar o comando sql
            pst.execute();
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
     //MetodoExcluir
    public List<Clientes> listarCliente(){
        try {
            //1 passo criar a lista
            List<Clientes>lista = new ArrayList<>();
            
            //2 passo - criar o sql, organizar e executar
            String sql = "select*from tb_clientes";
            PreparedStatement pst = con.prepareStatement(sql);
            
            // Toda a vez que for fazer um select´e preciso o ResultSet
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                lista.add(obj);
                
            }
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
            return null;
        }
    }
   
    
}
