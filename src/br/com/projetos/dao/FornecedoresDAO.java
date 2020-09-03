/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
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
public class FornecedoresDAO {
    
     private Connection con;
    
    public FornecedoresDAO(){
    this.con = new ConnectionFactory().getConnection();
    }
    
    //Metodo cadastrar Cliente
    public void cadastarFornecedores(Fornecedores obj){
        try {
            //1 passo - criar o comando sql
              String sql = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
            //2 passo - conectar no banco de dados e organizar o comando sql
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCnpj());
            pst.setString(3, obj.getEmail());
            pst.setString(4, obj.getTelefone());
            pst.setString(5, obj.getCelular());
            pst.setString(6, obj.getCep());
            pst.setString(7, obj.getEndereco());
            pst.setInt(8, obj.getNumero());
            pst.setString(9, obj.getComplemento());
            pst.setString(10, obj.getBairro());
            pst.setString(11, obj.getCidade());
            pst.setString(12, obj.getEstado());
            
            //3 passo - executar o comando sql
            pst.execute();
            pst.close();
           
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            System.out.println(e);
        }
    } 
    
     //Metodo listarFornecedores
     public List<Fornecedores> listarFornecedores() {
        try {

            //1 passo criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));             
                obj.setCnpj(rs.getString("cnpj"));
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

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
     
     // Metodo excluir Fornecedores
     
      public void excluirFornecedores(Fornecedores obj){
        try {
            // 1 passo comando sql
            String sql = "delete from tb_fornecedores where id=?";
            
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
      
      // Metodo Alterar Fornecedores
      
      public void alterarFornecedores(Fornecedores obj){
        try {
            String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id =?";
            
           PreparedStatement pst = con.prepareStatement(sql);
           
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCnpj());
            pst.setString(3, obj.getEmail());
            pst.setString(4, obj.getTelefone());
            pst.setString(5, obj.getCelular());
            pst.setString(6, obj.getCep());
            pst.setString(7, obj.getEndereco());
            pst.setInt(8, obj.getNumero());
            pst.setString(9, obj.getComplemento());
            pst.setString(10, obj.getBairro());
            pst.setString(11, obj.getCidade());
            pst.setString(12, obj.getEstado());
            
            pst.setInt(13, obj.getId());
            
            pst.execute();
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
    
      
        // Metodo ListarFornecedores por nome - retorna uma lista
    public List<Fornecedores> buscaFornecedoresPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nome);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }
    }
    
    //Metodo consultaFornecedoresPorNome
    public Fornecedores consultaPorNome(String nome){
        try {
            String sql = "select * from tb_fornecedores where nome=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            
            ResultSet rs = pst.executeQuery();
            
             Fornecedores obj = new Fornecedores();
            if(rs.next()){
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
            }
            
            return obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
            return null;
        }  
    }
    
}
