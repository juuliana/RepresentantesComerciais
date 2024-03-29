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
import Entidades.Telefone;
import javax.swing.JFormattedTextField;

public class RegistroDAO {
	
	public void cadastrarRegistro(JTextField assunto, JTextField nome, JFormattedTextField contato, JTextField hora, JFormattedTextField data, JTextField telefone) {
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
	          System.out.println("Data inválida!");
	        }
                
                Object[] options = { "Confirmar", "Cancelar" };
                int opcao = JOptionPane.showOptionDialog(null, "Você confirma o cadastro?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                if (opcao == 0){
                    EntityManagerFactory emf
	                = Persistence.createEntityManagerFactory("eduvale_pu");
                    EntityManager em = emf.createEntityManager();

                    em.getTransaction().begin();
                    Telefone tel = em.find(Telefone.class, Integer.parseInt(telefone.getText()));
                    reg.setTelefone(tel);
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
                    telefone.setText(null);
                }else{
                    JOptionPane.showMessageDialog(null, "O registro não foi cadastrado!");
                }
	        
        }
	}
}
