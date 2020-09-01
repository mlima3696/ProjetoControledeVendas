/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            
            JOptionPane.showMessageDialog(null, "Produto cadastardo com sucesso");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
        }
    }
}
