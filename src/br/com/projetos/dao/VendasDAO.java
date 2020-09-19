/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetos.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
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
    
    // Metodo que filtra Vendas por Datas
        public List<Vendas>listarVendasporData(LocalDate data_inicio,LocalDate data_fim){
            try {
                // 1 passo criar a lista
                List<Vendas> lista = new ArrayList<>();
                
                // 2 passo - criar o sql, organizar e executar
                
                String sql = "select v.id, v.data_venda, c.nome, v.total_venda, v.observacoes from tb_vendas as v " +
                             " inner join tb_clientes as c on(v.cliente_id = c.id) where v.data_venda between ? and ?";
                //3 Passo - conectar o banco de dados e organizar o comando sql
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setString(1, data_inicio.toString());
                pst.setString(2, data_fim.toString());
                
                ResultSet rs = pst.executeQuery();
                
               
                
                while(rs.next()){
                Vendas obj= new Vendas();
                Cliente c= new Cliente();
                
                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("v.data_venda"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));
                
                obj.setCliente(c);
                
                lista.add(obj);
                }
                
                return lista;
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro " + e);
                return null;
            }
        }
}
