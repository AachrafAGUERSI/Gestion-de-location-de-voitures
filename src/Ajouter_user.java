import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Ajouter_user extends JDialog {
	
	private JLabel loginLabel, passLabel, pass2Label, mailLabel;
	private JTextField login,mail;
	private JPasswordField  pass, pass2;
	public static String adr,log=null;
	public static InetAddress ip;
	
	
	public static String getLog() {
		return log;
	}







	public static int getId() {
		return id;
	}







	private static int id =0;
	public Ajouter_user(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}
	
	

	



	public Ajouter_user(String log, int id) {
		// TODO Auto-generated constructor stub
		
		this.log = log;
		this.id = id;
	}







	
	private void information(){

		
	
		//Le login
		JPanel panlogin = new JPanel();
		panlogin.setBackground(Color.white);
		panlogin.setPreferredSize(new Dimension(220, 60));
		panlogin.setBorder(BorderFactory.createTitledBorder("Login du membre"));
		loginLabel = new JLabel("Login : ");
		login = new JTextField(getLog());
		login.setPreferredSize(new Dimension(90, 25));
		panlogin.add(loginLabel);
		panlogin.add(login);
		
		//Le pass
		JPanel panpass = new JPanel();
		panpass.setBackground(Color.white);
		panpass.setPreferredSize(new Dimension(220, 60));
		panpass.setBorder(BorderFactory.createTitledBorder("Passe du membre"));
		passLabel = new JLabel("Mot de passe : ");
		pass = new JPasswordField();
		pass.setPreferredSize(new Dimension(90, 25));
		panpass.add(passLabel);
		panpass.add(pass);
		
		//Le pass 2
		JPanel panpass2 = new JPanel();
		panpass2.setBackground(Color.white);
		panpass2.setPreferredSize(new Dimension(220, 60));
		panpass2.setBorder(BorderFactory.createTitledBorder("Répeter passe du membre"));
		pass2Label = new JLabel("Confirmer : ");
		pass2 = new JPasswordField();
		pass2.setPreferredSize(new Dimension(90, 25));
		panpass2.add(pass2Label);
		panpass2.add(pass2);

		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panlogin);
		content.add(panpass);
		content.add(panpass2);
		

		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
	
		okBouton.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {				

				
				if(pass.getText().equals(pass2.getText()) && log == null) {
		
				try {
				
					
					Statement state = Connect.getInstance()
					.createStatement();
				String requete = "INSERT INTO utilisateurs(log,passe,statut) VALUES ('"+login.getText()+"', '"+pass.getText()+"', 'hors ligne')";
					 int resultat = state.executeUpdate(requete);
							
					
						
					 JOptionPane confirmation = new JOptionPane();
						confirmation.showMessageDialog(null, "L'utilisateur a été ajouter ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

						
			
			 state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 catch (Exception e) {
				e.printStackTrace();
			}
			 setVisible(false);
				} 
				
				else if(pass.getText().equals(pass2.getText()) && log != null) {	
	               try {
					
						
						Statement state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						String query = "UPDATE utilisateurs SET log = '"+login.getText()+"', passe = '"+pass.getText()+"' WHERE id = '"+id+"'";
						
					
						state.executeUpdate(query);

			                        state.close();
			                        JOptionPane confirmation = new JOptionPane();
									confirmation.showMessageDialog(null, "L'utilisateur a été modifier ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

						
							
					
					}  catch (Exception e) {
			            				e.printStackTrace();
			            			}
			            			 setVisible(false);
				}
				
				else {
					
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Les deux mots de passes ne correspondent pas !", "Erreur pass", JOptionPane.ERROR_MESSAGE);

				}
				
		
		/*

				try {
					Class.forName("org.postgresql.Driver");
					
					    String url = "jdbc:postgresql://localhost:5432/Location";
						String user = "postgres";
						String passwd = "postgres";
						
						Connection con = DriverManager.getConnection(url, user, passwd);
						Statement state = con.createStatement();
						String requete = "SELECT pass FROM vehicule";
						ResultSet resultat = state.executeQuery(requete);
					
				
					while(resultat.next()){
						 System.out.println(resultat.getString("pass"));
					}

		                        resultat.close();
		                        state.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}*/	
					
			}}
		
			
		);
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					setVisible(false);
				}catch(NullPointerException n) {
					System.exit(0);	
					
				}
			}			
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		JLabel icon = new JLabel(new ImageIcon("images/admin.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		this.getContentPane().add(panIcon, BorderLayout.EAST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}

}
