package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entidades.Registro;
import Entidades.Representante;
import javax.swing.JFormattedTextField;

public class RegistroDAO {
	
	public void cadastrarRegistro(JTextField assunto, JTextField nome, JFormattedTextField contato, JTextField hora, JFormattedTextField data, JTextField representante) {
		Registro reg = new Registro();
        
        reg.setAssunto(assunto.getText());
        reg.setNomeContato(nome.getText());
        reg.setTelContato(contato.getText());
        reg.setHora(hora.getText());
        
        if ((reg.getAssunto().isEmpty())||(reg.getNomeContato().isEmpty())||(reg.getTelContato().isEmpty())||(reg.getHora().isEmpty())) {
        	JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
        }else {
        	try{
	            Date formato = new SimpleDateFormat("dd/MM/yyyy").parse(data.getText());
	            SimpleDateFormat dataNova = new SimpleDateFormat("yyyy-MM-dd");
	             
	            String data1 = dataNova.format(formato).toString();
	            reg.setData(dataNova.parse(data1));
	        }
	        catch(ParseException e)
	        {
	          System.out.println("Data não selecionada");
	        }
	       
	        EntityManagerFactory emf
	                = Persistence.createEntityManagerFactory("eduvale_pu");
	        EntityManager em = emf.createEntityManager();
	        
	        em.getTransaction().begin();
	        Representante rep = em.find(Representante.class, Integer.parseInt(representante.getText()));
	        reg.setRepresentante(rep);
	        em.persist(reg);
	        em.getTransaction().commit();
	        em.close();
	        emf.close();
	        
	        JOptionPane.showMessageDialog(null, "Registro realizado com sucesso!");
	        
	        assunto.setText(null);
	        nome.setText(null);
	        contato.setText(null);
	        hora.setText(null);
	        data.setText(null);
	        representante.setText(null);
        }
	}
}
