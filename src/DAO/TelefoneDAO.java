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
                
                Object[] options = { "Confirmar", "Cancelar" };
                int opcao = JOptionPane.showOptionDialog(null, "Você confirma o cadastro?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                
                if (opcao == 0){
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
                }else{
                    JOptionPane.showMessageDialog(null, "O telefone não foi cadastrado!");
                }
            }
	}
        
        
        public void deletarTelefone(JTextField id) {
            
            if(JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir esse Telefone de seu cadastro?", "Alerta de exclusão", JOptionPane.YES_NO_OPTION)==0){

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("eduvale_pu");
		EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();
                Telefone tel = em.find(Telefone.class, Integer.parseInt(id.getText()));
                em.remove(tel);
                em.getTransaction().commit();
                emf.close();
                
                JOptionPane.showMessageDialog(null, "Telefone excluído com sucesso!");
                id.setText(null);
            }
        }  
}
