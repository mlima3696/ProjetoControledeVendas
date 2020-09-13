/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class VendasDAO {
    private Connection con;
    
    public VendasDAO(){
    this.con = new ConnectionFactory().getConnection();
    }
    
    // Cadastrar Venda
    public void cadastrarVendas(Vendas obj) {
        try {
            String sql = "insert into tb_vendas (cliente_id,data_venda,total_venda,observacoes) values (?,?,?,?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, obj.getCliente().getId());
            pst.setString(2, obj.getData_venda());
            pst.setDouble(3, obj.getTotal_venda());
            pst.setString(4, obj.getObs());
            
            pst.execute();
            pst.close();
            
            //JOptionPane.showMessageDialog(null, "Venda registrada com sucesso");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
        }
        
    }
    
    // Retorna a ultima venda
    public int retornaUltimaVenda(){
    
        try {
            int idvenda=0;
            
            String sql="select max(id) id from tb_vendas";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
            Vendas p = new Vendas();
            
            p.setId(rs.getInt("id"));
            
            idvenda=p.getId();
            }
            return idvenda;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
