package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entidades.Representante;
import Entidades.Telefone;
import javax.swing.JFormattedTextField;

public class TelefoneDAO {
	
	public void cadastrarTelefone(JFormattedTextField numero, JComboBox<?> tipo, JTextField representante) {
		Telefone t = new Telefone();

        t.setNumero(numero.getText());
        t.setTipo(tipo.getSelectedIndex());
        
        if ((t.getNumero().isEmpty())) {
        	JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
        }else {
        	EntityManagerFactory emf
	           = Persistence.createEntityManagerFactory("eduvale_pu");
	        EntityManager em = emf.createEntityManager();
	        
	        em.getTransaction().begin();
	        Representante r = em.find(Representante.class, Integer.parseInt(representante.getText()));
	        r.getTelefones().add(t);
	        em.persist(t); 
	        em.getTransaction().commit();
	        em.close();
	        emf.close();
	        
	        JOptionPane.showMessageDialog(null, "Telefone cadastrado com sucesso!");
	        
	        numero.setText(null);
	        tipo.setToolTipText(null);
	        representante.setText(null);
        }
	}
}
