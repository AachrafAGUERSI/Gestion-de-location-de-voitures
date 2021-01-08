import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class BDD extends JDialog {


	private JLabel loginLabel, passLabel, pass2Label;
	private JPasswordField login;
	private JPasswordField  pass, pass2;
	public static String adr;
	public BDD(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}
	
	
	public  BDD(String adr){
		this.adr =adr;
	}
	private void information(){

		
	
		//Le login
		JPanel panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setPreferredSize(new Dimension(220, 60));
		panLogin.setBorder(BorderFactory.createTitledBorder("Nom de la base de donnée"));
		loginLabel = new JLabel("Base de donnée : ");
		login = new JPasswordField("Location");
		login.setPreferredSize(new Dimension(90, 25));
		panLogin.add(loginLabel);
		panLogin.add(login);
		
		//Le pass
		JPanel panPass = new JPanel();
		panPass.setBackground(Color.white);
		panPass.setPreferredSize(new Dimension(220, 60));
		panPass.setBorder(BorderFactory.createTitledBorder("Nom de l'utilisateur"));
		passLabel = new JLabel("L'utilisateur : ");
		pass = new JPasswordField("postgres");
		pass.setPreferredSize(new Dimension(90, 25));
		panPass.add(passLabel);
		panPass.add(pass);
		
		//Le pass 2
		JPanel panPass2 = new JPanel();
		panPass2.setBackground(Color.white);
		panPass2.setPreferredSize(new Dimension(220, 60));
		panPass2.setBorder(BorderFactory.createTitledBorder("Le mot de passe"));
		pass2Label = new JLabel("Passe : ");
		pass2 = new JPasswordField("postgres");
		pass2.setPreferredSize(new Dimension(90, 25));
		panPass2.add(pass2Label);
		panPass2.add(pass2);

		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panLogin);
		content.add(panPass);
		content.add(panPass2);
		

		System.out.println(login.getText());
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				

				
		new Connect(login.getText(),pass.getText(), pass2.getText());
     	JOptionPane confirmation = new JOptionPane();
		confirmation.showMessageDialog(null, "Informations enregistrès ", "Confirmation", JOptionPane.INFORMATION_MESSAGE, null);
		 
		setVisible(false);
		
			}
		}
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
		JLabel icon = new JLabel(new ImageIcon("admin.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		this.getContentPane().add(panIcon, BorderLayout.EAST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}

}
