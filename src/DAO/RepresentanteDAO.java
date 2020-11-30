package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entidades.Representante;
import javax.swing.JTable;

public class RepresentanteDAO {
	
		public void cadastrarRepresentante(JTextField nome, JTextField cpf, JTextField empresa) {
	
			Representante rep = new Representante();
			
			rep.setNome(nome.getText());
			rep.setCpf(cpf.getText());
			rep.setEmpresa(empresa.getText());
			
			if ((rep.getNome().isEmpty())||(rep.getCpf().isEmpty())||(rep.getEmpresa().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
			}else{

		        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eduvale_pu");
		        EntityManager em = emf.createEntityManager();
		        
		        em.getTransaction().begin();
		        em.persist(rep); 
		        em.getTransaction().commit();
		        em.close();
		        emf.close();
		        
		        JOptionPane.showMessageDialog(null, "Representante cadastrado com sucesso!");
		        
		        nome.setText(null);
		        cpf.setText(null);
		        empresa.setText(null);
	        }
	}
}