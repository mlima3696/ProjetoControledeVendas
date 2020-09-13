/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItemVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class ItensVendasDAO {
    
    private Connection con;
    
    public ItensVendasDAO(){
    this.con=new ConnectionFactory().getConnection();
    }
    
    //Metodo que cadastra Itens
    public void cadastraItens(ItemVendas obj){
        try {
            // 1 passo - Comando sql
            String sql="insert into tb_itensvendas (venda_id,produto_id,qtd,subtotal) values (?,?,?,?)";
            
            // 2 passo - Conectar o banco de dados e organizar o sql
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, obj.getVenda().getId());
            pst.setInt(2, obj.getProduto().getId());
            pst.setInt(3, obj.getQtd());
            pst.setDouble(4, obj.getSubtotal());
            
            pst.execute();
            pst.close();;
            
            //JOptionPane.showMessageDialog(null,"Item de venda registrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
}
