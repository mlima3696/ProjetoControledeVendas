/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class ProdutosDAO {
    
    private Connection con;
    
    public ProdutosDAO(){
    this.con = new ConnectionFactory().getConnection();
    }
    
    // Metodo cadastrarProdutos
    public void cadastrarProdutos(Produtos obj) {
        try {
            String sql = "insert into tb_produtos (descricao,preco,qtd_estoque,for_id) values (?,?,?,?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1,obj.getDescricao());
            pst.setDouble(2, obj.getPreco());
            pst.setInt(3,obj.getQtd_estoque());
            
            //Objeto de fornecedor
            pst.setInt(4, obj.getFornecedor().getId());
            
            pst.execute();
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
        }
        
    }
        
        // Metodo listar Produtos
        public List<Produtos>  listarProdutos(){
            try {
                // 1 passo criar a lista
                List<Produtos> lista = new ArrayList<>();
                
                // 2 passo - criar o sql, organizar e executar
                
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        + "inner join tb_fornecedores as f on(p.for_id = f.id)";
                //2 Passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
                lista.add(obj);
                }
                
                return lista;
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro " + e);
                return null;
            }
        }
        
        //Metodo Alterar Produtos
        public void alterarProdutos(Produtos obj){
            try {
                
                String sql = "update tb_produtos set descricao=?, preco=?, qtd_estoque=?,for_id=? where id=?";
                
                //2 Passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, obj.getDescricao());
                pst.setDouble(2, obj.getPreco());
                pst.setInt(3, obj.getQtd_estoque());
                
                pst.setInt(4, obj.getFornecedor().getId());
                
                pst.setInt(5, obj.getId());
                
                pst.execute();
                pst.close();
                
                JOptionPane.showMessageDialog(null, "Produto Alterado com sucesso! ");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro " + e);
            }
        }
        
        // Metodo Excluir Produtos
        public void excluirProdutos(Produtos obj){
            try {
                String sql = "delete from tb_produtos where id=?";
                // 2 passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, obj.getId());
                pst.execute();
                pst.close();
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro: " + e);
            }
        }
       
        // Metodo listar Produtos Por Nome
        public List<Produtos>  listarProdutosPorNome(String nome){
            try {
                // 1 passo criar a lista
                List<Produtos> lista = new ArrayList<>();
                
                // 2 passo - criar o sql, organizar e executar
                
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        + "inner join tb_fornecedores as f on(p.for_id = f.id) where p.descricao like ?";
                //3 Passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, nome);
                
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
                lista.add(obj);
                }
                
                return lista;
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro " + e);
                return null;
            }
        }
        
        // Metodo consultaProduto Por Nome
        public Produtos consultaPorNome(String nome){
            try {
                // 1 passo - criar o sql, organizar e executar
                
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        + "inner join tb_fornecedores as f on(p.for_id = f.id) where p.descricao = ?";
                //2 Passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, nome);
                
                ResultSet rs = pst.executeQuery();
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                if(rs.next()){
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                }
                
                return obj;
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro " + e);
                return null;
            }
        }
        
        // Metodo buscaProduto Por Codigo
        public Produtos buscaPorCodigo(int id){
            try {
                // 1 passo - criar o sql, organizar e executar
                
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        + "inner join tb_fornecedores as f on(p.for_id = f.id) where p.id = ?";
                //String sql = "select*tb_produtos where id = ?";
                //2 Passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                
                ResultSet rs = pst.executeQuery();
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                if(rs.next()){
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                }
                
                return obj;
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado! ");
                JOptionPane.showMessageDialog(null, "Erro " + e);
                return null;
            }
        }  
        
        // Metodo que da baixa em estoque
        public void baixaEstoque(int id,int qtd_nova){
            try {
                String sql="update tb_produtos set qtd_estoque=? where id=?";
                
                //2 passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setInt(1, qtd_nova);
                pst.setInt(2, id);
                
                pst.execute();
                pst.close();;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }
        
        //Metodo que retorna o estoque atual de um produto
        public int retornaEstoqueAtual(int id){
            try {
                int qtd_estoque=0;
                String sql="select qtd_estoque from tb_produtos where id=?";
                
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){               
                qtd_estoque=(rs.getInt("qtd_estoque"));
                }
                return qtd_estoque;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        public void adicionarEstoque(int id, int qtd_novo){
            try {
                String sql = "update tb_produtos set qtd_estoque= ? where id=?";
                
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setInt(1, qtd_novo);
                pst.setInt(2, id);
                pst.execute();
                pst.close();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Aconteceu um ERRO: " + e);
                System.out.println(e);
            }
        }
        
}
