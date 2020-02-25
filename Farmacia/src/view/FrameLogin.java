package view;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class FrameLogin extends JFrame implements ActionListener {
	
	private Image Seringa =  new ImageIcon(getClass().getResource("../vaccine.png")).getImage().getScaledInstance(75,75,Image.SCALE_SMOOTH); 
	private JLabel lblLogin = new JLabel("Usuário");
	private JLabel seringaLabel;
	private JLabel lblSenha = new JLabel("Senha");
	private JTextField txtUsuario = new JTextField(5);
	private JPasswordField txtSenha = new JPasswordField(5);
	private JButton btnLog = new JButton("Entrar");
	private JButton btnCancel = new JButton("Cancelar");
	private JLabel frameDetail;
	
	
	public FrameLogin() {
		
		initFrame();
		initComponents();
		
	}
	
	public void initFrame() {
		
		setTitle("Login");
		setSize(600, 500);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		
	}
	
	public void initComponents() {
		
		
		seringaLabel = new JLabel(new ImageIcon(Seringa));
		seringaLabel.setSize(75, 75);
		seringaLabel.setLocation((getWidth() / 2) - (seringaLabel.getWidth() / 2),380);
		seringaLabel.setVisible(true);
		
		add(seringaLabel);
		
		
		frameDetail = new JLabel();
		frameDetail.setLocation(0, 372);
		frameDetail.setSize(getWidth(), 100);
		frameDetail.setOpaque(true);
		frameDetail.setBackground(new Color(0,110,152));
		
		add(frameDetail);
		
		
		
		txtUsuario.setSize(200, 25);
		txtUsuario.setLocation((getWidth() / 2) - (txtUsuario.getWidth() / 2 ), 80);
		txtUsuario.setOpaque(true);
		txtUsuario.setBackground(new Color(232,231,231));
		txtUsuario.setBorder(null);
		
		lblLogin.setSize(100,25);
		lblLogin.setLocation(txtUsuario.getWidth() - 50, 80);
		
		add(txtUsuario);
		add(lblLogin);
		
		txtSenha.setSize(200 , 25);
		txtSenha.setLocation( (getWidth() / 2) - (txtSenha.getWidth() / 2), txtUsuario.getHeight() + 100);
		txtSenha.setOpaque(true);
		txtSenha.setBackground(new Color(232, 231, 231));
		txtSenha.setBorder(null);
		
		lblSenha.setSize(100,25);
		lblSenha.setLocation(txtSenha.getWidth() - 50, txtSenha.getY());
		
		
		add(txtSenha);
		add(lblSenha);
		
		btnLog.setSize((txtSenha.getWidth() / 2) - 15, txtSenha.getHeight());
		btnLog.setLocation(txtSenha.getX(), txtSenha.getY() + 50);
		btnLog.setOpaque(true);
		btnLog.setBackground(new Color(232, 231, 231));
		btnLog.setBorder(null);
		
		
		btnCancel.setSize(txtSenha.getWidth() / 2, txtSenha.getHeight());
		btnCancel.setLocation(txtSenha.getX() + (txtSenha.getWidth() / 2), txtSenha.getY() + 50);
		btnCancel.setOpaque(true);
		btnCancel.setBackground(new Color(232, 231, 231));
		btnCancel.setBorder(null);
		add(btnLog);
		add(btnCancel);
		
		btnLog.addActionListener(this);
		
		btnCancel.addActionListener(this);
		
		repaint();

		
	

	
	}
	

	class  Documento extends PlainDocument{
		
	
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnLog)) {
			
			String senha;
			senha = new String(txtSenha.getPassword());
			
			if(txtUsuario.getText().equals("admin") && senha.equals("1234")) {
				
				new InterfaceGrafica();
				dispose();
				
			}else {
				JOptionPane.showMessageDialog(null, "Credenciais Incorretas!");
				
				txtUsuario.setText("");
				txtSenha.setText("");
			}
			
			
			
		}
		
		
		if(e.getSource().equals(btnCancel)) {
			
			dispose();
		}
		
		
	} 
	

}
