/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
import br.com.projeto.model.ItemVendas;
import br.com.projeto.model.Produtos;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    
    //Metodo que lista Itens de uma venda por Id
   public List<ItemVendas>listarItensPorVenda(int venda_id){
            try {
                // 1 passo criar a lista
                List<ItemVendas> lista = new ArrayList<>();
                
                // 2 passo - criar o sql, organizar e executar
                
                String sql = "select i.id,p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i " +
                             "inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ?";
                //3 Passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setInt(1, venda_id);
                
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                ItemVendas item = new ItemVendas();
                Produtos prod =new Produtos();
                
                item.setId(rs.getInt("i.id"));
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                
                item.setProduto(prod);
                
                
                lista.add(item);
                }
                
                return lista;
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro " + e);
                return null;
            }
        }
    
}
