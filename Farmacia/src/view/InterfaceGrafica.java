package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.dao.ClienteDAO;
import controller.dao.FabricanteDAO;
import controller.dao.FornecedorDAO;
import controller.dao.FuncionarioDAO;
import controller.dao.ProdutoDAO;
import controller.viacep.ViaCEP;
import controller.viacep.ViaCEPEvents;
import controller.viacep.ViaCEPException;
import model.Cliente;
import model.Fabricante;
import model.Fornecedor;
import model.Funcionario;
import model.Produto;

public class InterfaceGrafica extends JFrame implements MouseListener,ViaCEPEvents{
	
	JLabel cadastro;
	JLabel menu;
	
	int STATE = 0;
	final int EDITING = 2;
	final int INSERTING = 1;
	private Font fonte = new Font("Century Gothic", 1,32);
	String[] tableOptions = {"Cliente","Fornecedor","Fabricante","Produto","Funcionario"};
	String[] typesOptions = {"Medicamento","Cosmético","Genérico","Nutrição Esportiva"};
	JComboBox optionsBox;
	JComboBox typesBox;
	JLabel insertIcon;
	JLabel editIcon;
	JLabel queryIcon;
	JLabel clientIcon;
	JLabel produtosIcon;
	JLabel fornecedorIcon;
	JLabel fabricanteLabel;
	JTextField fabricanteField;
	JTextField fornecedorField;
	JLabel fornecedorLabel;
	JLabel fabricanteIcon;
	JLabel funcionarioIcon;
	private JPanel pnlContainer;
	private JPanel editContainer;
	private JPanel clientContainer;
	private JPanel fornecedorContainer;
	private JPanel fabricanteContainer;
	private JPanel produtoContainer;
	private JPanel funcionarioContainer;
	private ArrayList<String> fabricantes;
	private ArrayList<String> fornecedores;
	private JPanel queryContainer;
	JButton editCliente;
	JButton editFuncionario;
	JButton editProduto;
	JButton editFabricante;
	JButton editFornecedor;
	JLabel lupaIcon;
	JTable tableQuery;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();
	private DefaultTableModel modelFornecedor = new DefaultTableModel();
	private DefaultTableModel modelFabricante = new DefaultTableModel();
	private DefaultTableModel modelProduto = new DefaultTableModel();
	private DefaultTableModel modelFuncionario = new DefaultTableModel();
	JLabel searchId;
	JTextField nomeCliente;
	JTextField nomeFornecedor;
	JTextField nomeFabricante;
	JTextField nomeProduto;
	JLabel nomeLabel;
	JTextField buscaId;
	JLabel buscaIdLabel;
	JLabel logLabel;
	JTextField numLog;
	JLabel numLogLabel;
	JLabel searchIdFornecedor;
	JLabel searchIdFabricante;
	JLabel searchIdFuncionario;
	JLabel searchIdProduto;
	JFormattedTextField cepCliente;
	JLabel cepLabel;
	ViaCEP cAPI;
	String cep;
	JLabel bairroLabel;
	JLabel estadoLabel;
	JLabel ufLabel;
	JLabel municipioLabel;
	JLabel complementoLabel;
	JLabel precoLabel;
	JLabel rgLabel;
	JTextField rgField;
	JTextField complemento;
	JFormattedTextField precoProduto;
	JButton submitCliente;
	JButton deleteCliente;
	JButton submitFornecedor;
	JButton deleteFornecedor;
	JButton submitFabricante;
	JButton deleteFabricante;
	JButton submitProduto;
	JButton deleteProduto;
	JButton submitFuncionario;
	JButton deleteFuncionario;
	JTextField nomeFuncionario;
	JButton refresh;
	JFormattedTextField cpfField;
	JFormattedTextField dataNasc;
	JLabel dataNascLabel;
	JLabel cpfLabel;
	JLabel generoLabel;
	ButtonGroup genero;
	JRadioButton genero1;
	JRadioButton genero2;
	String sexo;
	
	private Image Insert = new ImageIcon(getClass().getResource("../botao-inserir.png")).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
	
	private Image Edit = new ImageIcon(getClass().getResource("../new-file.png")).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
	
	private Image Delete = new ImageIcon(getClass().getResource("../delete.png")).getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
	
	private Image Client = new ImageIcon(getClass().getResource("../user.png")).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);

	private Image Suplements = new ImageIcon(getClass().getResource("../suplements.png")).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);


	private Image Employer = new ImageIcon(getClass().getResource("../employee.png")).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
	
	
	private Image Lupa = new ImageIcon(getClass().getResource("../loupe.png")).getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
	
	private Image Query = new ImageIcon(getClass().getResource("../search-query.png")).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
	
	private Image Refresh = new ImageIcon(getClass().getResource("../refresh.png")).getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
	
	private Image Supplyer = new ImageIcon(getClass().getResource("../fornecedor.png")).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
	
	private Image Manufacturing = new ImageIcon(getClass().getResource("../fabricante.png")).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
	
	
	public InterfaceGrafica() {
		
		configWindow();
		initComponents();
		
	}
	
	
	public MaskFormatter Mascara(String Mascara, String Type){
        
        MaskFormatter F_Mascara = new MaskFormatter();
        try{
            F_Mascara.setMask(Mascara); 
            F_Mascara.setPlaceholderCharacter('_'); 
            
            if(Type.equals("num")) {
            	F_Mascara.setValidCharacters("0123456789");

            }
        }
        catch (Exception excecao) {
        excecao.printStackTrace();
        } 
        return F_Mascara;
	} 
	
	public void configWindow() {
		
		
		setTitle("Farmacia");
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		

		
	}

	
	
	public void initComponents() {
		
		
		
		cAPI = new ViaCEP();
		
		cadastro = new JLabel();
		cadastro.setSize(300,70);
		cadastro.setLocation((getWidth() / 2) - (cadastro.getWidth() / 2)  , 10 );
		cadastro.setFont(fonte);
		cadastro.setText("Farmácia");
		
		add(cadastro);
		
		insertIcon = new JLabel(new ImageIcon(Insert));
		insertIcon.setSize(50,50);
		insertIcon.setLocation(60 - insertIcon.getWidth() / 2, 135);
		insertIcon.addMouseListener(this);
		insertIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(insertIcon);
		
		editIcon = new JLabel(new ImageIcon(Edit));
		editIcon.setSize(50,50);
		editIcon.setLocation(insertIcon.getX(), insertIcon.getY() + editIcon.getHeight()*2);
		editIcon.addMouseListener(this);
		editIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		add(editIcon);
		repaint();
	
		queryIcon = new JLabel(new ImageIcon(Query));
		queryIcon.setSize(50,50);
		queryIcon.setLocation(insertIcon.getX(), editIcon.getY() + queryIcon.getHeight()*2);
		queryIcon.addMouseListener(this);
		queryIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		add(queryIcon);
		repaint();
		
		
		
		

		
		menu = new JLabel();
		menu.setSize(120, getHeight());
		menu.setOpaque(true);
		menu.setLocation(0 , 0);
		menu.setBackground(new Color(0, 110, 152));
		
		add(menu);
		

		
		
		
		pnlContainer = new JPanel();
		pnlContainer.setLayout(null);
		pnlContainer.setBackground(Color.white);
		pnlContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		pnlContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		editContainer = new JPanel();
		editContainer.setLayout(null);
		editContainer.setBackground(Color.white);
		editContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		editContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		clientContainer = new JPanel();
		clientContainer.setLayout(null);
		clientContainer.setBackground(Color.white);
		clientContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		clientContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		fornecedorContainer = new JPanel();
		fornecedorContainer.setLayout(null);
		fornecedorContainer.setBackground(Color.white);
		fornecedorContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		fornecedorContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		
		fabricanteContainer = new JPanel();
		fabricanteContainer.setLayout(null);
		fabricanteContainer.setBackground(Color.white);
		fabricanteContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		fabricanteContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		
		produtoContainer = new JPanel();
		produtoContainer.setLayout(null);
		produtoContainer.setBackground(Color.white);
		produtoContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		produtoContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		funcionarioContainer = new JPanel();
		funcionarioContainer.setLayout(null);
		funcionarioContainer.setBackground(Color.white);
		funcionarioContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		funcionarioContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		
		
		queryContainer = new JPanel();
		queryContainer.setLayout(null);
		queryContainer.setBackground(Color.white);
		queryContainer.setSize(this.getWidth() - menu.getWidth(),getHeight());
		queryContainer.setLocation(menu.getX() + menu.getWidth(), 0);
		
		
		
		
		clientIcon = new JLabel(new ImageIcon(Client));
		clientIcon.setSize(100,100);
		clientIcon.setLocation((clientIcon.getWidth() / 2), insertIcon.getY());
		clientIcon.addMouseListener(this);
		clientIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		clientIcon.setToolTipText("Cadastrar Cliente");
		
		produtosIcon = new JLabel(new ImageIcon(Suplements));
		produtosIcon.setSize(100,100);
		produtosIcon.setLocation(clientIcon.getX() + (clientIcon.getWidth() * 2), clientIcon.getY());
		produtosIcon.addMouseListener(this);
		produtosIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		produtosIcon.setToolTipText("Cadastrar Produto");
		
		
		fornecedorIcon = new JLabel(new ImageIcon(Supplyer));
		fornecedorIcon.setSize(100,100);
		fornecedorIcon.setLocation(produtosIcon.getX() + (clientIcon.getWidth() * 2), clientIcon.getY());
		fornecedorIcon.addMouseListener(this);
		fornecedorIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fornecedorIcon.setToolTipText("Cadastrar Fornecedor");
		
		
		
		funcionarioIcon = new JLabel(new ImageIcon(Employer));
		funcionarioIcon.setSize(100,100);
		funcionarioIcon.setLocation(clientIcon.getX(), clientIcon.getY() + (clientIcon.getHeight() * 2));
		funcionarioIcon.addMouseListener(this);
		funcionarioIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		funcionarioIcon.setToolTipText("Cadastrar Funcionario");
		
		
		fabricanteIcon = new JLabel(new ImageIcon(Manufacturing));
		fabricanteIcon.setSize(100,100);
		fabricanteIcon.setLocation(produtosIcon.getX(), funcionarioIcon.getY());
		fabricanteIcon.addMouseListener(this);
		fabricanteIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fabricanteIcon.setToolTipText("Cadastrar Fabricante");
		
		
		
		editCliente = new JButton("Editar Cliente");
		editCliente.setSize(200,80);
		editCliente.setBorder(new LineBorder(Color.black,1,true));
		editCliente.setBackground(null);
		editCliente.setLocation(editCliente.getWidth() / 2, clientIcon.getY());
		editCliente.addMouseListener(this);
		editCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		editFuncionario = new JButton("Editar Funcionario");
		editFuncionario.setSize(200,80);
		editFuncionario.setBorder(new LineBorder(Color.black,1,true));
		editFuncionario.setBackground(null);
		editFuncionario.setLocation(editCliente.getX() + (editCliente.getWidth() / 2 + editCliente.getWidth()), clientIcon.getY());
		editFuncionario.addMouseListener(this);
		editFuncionario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		editProduto = new JButton("Editar Produto");
		editProduto.setSize(200,80);
		editProduto.setBorder(new LineBorder(Color.black,1,true));
		editProduto.setBackground(null);
		editProduto.setLocation(editCliente.getWidth() / 2, editCliente.getY() + (editFuncionario.getHeight() + editFuncionario.getHeight() / 2));
		editProduto.addMouseListener(this);
		editProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		editFornecedor = new JButton("Editar Fornecedor");
		editFornecedor.setSize(200,80);
		editFornecedor.setBorder(new LineBorder(Color.black,1,true));
		editFornecedor.setBackground(null);
		editFornecedor.setLocation(editFuncionario.getX(), editProduto.getY());
		editFornecedor.addMouseListener(this);
		editFornecedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		editFabricante = new JButton("Editar Fabricante");
		editFabricante.setSize(200,80);
		editFabricante.setBorder(new LineBorder(Color.black,1,true));
		editFabricante.setBackground(null);
		editFabricante.setLocation(editCliente.getWidth() / 2, editProduto.getY() + (editFuncionario.getHeight() + editFuncionario.getHeight() / 2));
		editFabricante.addMouseListener(this);
		editFabricante.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		
		pnlContainer.add(clientIcon);
		pnlContainer.add(produtosIcon);
		pnlContainer.add(fabricanteIcon);
		pnlContainer.add(fornecedorIcon);
		pnlContainer.add(funcionarioIcon);
		
		editContainer.add(editCliente);
		editContainer.add(editFuncionario);
		editContainer.add(editProduto);
		editContainer.add(editFornecedor);
		editContainer.add(editFabricante);
		editContainer.repaint();
		
		
		
		nomeCliente = new JTextField();
		nomeCliente.setOpaque(true);
		nomeCliente.setBackground(new Color(232, 231, 231));
		nomeCliente.setBorder(null);
		nomeCliente.setSize(200,25);
		nomeCliente.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);
		nomeCliente.setVisible(true);
		
		
		nomeLabel = new JLabel("Nome");
		nomeLabel.setSize(100,25);
		nomeLabel.setLocation(nomeCliente.getX() - (nomeLabel.getWidth() / 2), nomeCliente.getY());
		nomeLabel.setVisible(true);
		
		cpfLabel = new JLabel("CPF");
		cpfLabel.setSize(50,25);
		cpfLabel.setLocation((nomeCliente.getX() + nomeCliente.getWidth() + (cpfLabel.getWidth() / 2) ), nomeLabel.getY());
		
		cpfField = new JFormattedTextField( Mascara("###.###.###-##","num"));
		cpfField.setOpaque(true);
		cpfField.setBackground(new Color(232, 231, 231));
		cpfField.setBorder(null);
		cpfField.setSize(100,25);
		cpfField.setLocation((cpfLabel.getX() + cpfLabel.getWidth()) , cpfLabel.getY());
		
		
		dataNascLabel = new JLabel("Data de Nascimento");
		dataNascLabel.setSize(125,25);
		dataNascLabel.setLocation(nomeLabel.getX(), nomeCliente.getY() + ((dataNascLabel.getHeight() * 2) + (dataNascLabel.getHeight() / 2)));
		
		dataNasc = new JFormattedTextField(Mascara("##/##/####" , "num"));
		dataNasc.setOpaque(true);
		dataNasc.setBackground(new Color(232, 231, 231));
		dataNasc.setBorder(null);
		dataNasc.setSize(70,25);
		dataNasc.setLocation((dataNascLabel.getX() + dataNascLabel.getWidth()), dataNascLabel.getY());
		
		
		generoLabel = new JLabel("Gênero");
		generoLabel.setSize(80,25);
		generoLabel.setLocation((dataNasc.getX() + dataNasc.getWidth()) + (dataNasc.getWidth() / 2), dataNascLabel.getY());
		
		
		genero1 = new JRadioButton("Masculino");
		genero1.setSize(100, 25);
		genero1.setBackground(Color.WHITE);
		genero1.setLocation(generoLabel.getX() + generoLabel.getWidth(), generoLabel.getY());
		

		genero2 = new JRadioButton("Feminino");
		genero2.setSize(100, 25);
		genero2.setBackground(Color.WHITE);
		genero2.setLocation(genero1.getX() + genero1.getWidth(), generoLabel.getY());
		
		genero = new ButtonGroup();
		genero.add(genero1);
		genero.add(genero2);
		
		cepCliente = new JFormattedTextField(Mascara("#####-###","num"));
		cepCliente.setSize(80,25);
		cepCliente.setOpaque(true);
		cepCliente.setBackground(new Color(232, 231, 231));
		cepCliente.setBorder(null);
		cepCliente.setLocation(nomeCliente.getX(), dataNascLabel.getY() + ((cepCliente.getHeight() * 2)  +  (cepCliente.getHeight() / 2)));
		cepCliente.setVisible(true);
		
		
		cepLabel = new JLabel("CEP");
		cepLabel.setSize(50,25);
		cepLabel.setLocation(cepCliente.getX() - (cepCliente.getWidth() / 2), cepCliente.getY());
		cepLabel.setVisible(true);
		

		lupaIcon = new JLabel(new ImageIcon(Lupa));
		lupaIcon.setSize(25,25);
		lupaIcon.setLocation((cepCliente.getX() + cepCliente.getWidth()) + lupaIcon.getWidth(), cepLabel.getY());
		lupaIcon.addMouseListener(this);
		lupaIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lupaIcon.setVisible(true);
		
		
		logLabel = new JLabel();
		logLabel.setSize(400,25);
		logLabel.setLocation(nomeCliente.getX(),cepCliente.getY() + logLabel.getHeight() + (logLabel.getHeight() / 2));
		logLabel.setVisible(true);

		
		bairroLabel = new JLabel();
		bairroLabel.setSize(300,25);
		bairroLabel.setLocation(nomeCliente.getX(),logLabel.getY() + logLabel.getHeight() + (logLabel.getHeight() / 3));
		bairroLabel.setVisible(true);
		
		
		
		
		ufLabel = new JLabel();
		ufLabel.setSize(50,25);
		ufLabel.setLocation(logLabel.getX() + (logLabel.getWidth() / 2),logLabel.getY());
		ufLabel.setVisible(true);
		
		
				
		municipioLabel = new JLabel();
		municipioLabel.setSize(200,25);
		municipioLabel.setLocation(logLabel.getX() + (logLabel.getWidth() / 2),bairroLabel.getY());
		municipioLabel.setVisible(true);
		
		numLogLabel = new JLabel("Nº");
		numLogLabel.setSize(25,25);
		numLogLabel.setLocation(cepLabel.getX(), bairroLabel.getY() + numLogLabel.getHeight());
		numLogLabel.setVisible(true);
		
		numLog = new JTextField();
		numLog.setSize(50, 25);
		numLog.setLocation(bairroLabel.getX(), numLogLabel.getY());
		numLog.setOpaque(true);
		numLog.setBackground(new Color(232, 231, 231));
		numLog.setBorder(null);
		numLog.setVisible(true);
		
		
		complementoLabel = new JLabel("Complemento");
		complementoLabel.setSize(100,25);
		complementoLabel.setLocation((numLogLabel.getX() + numLogLabel.getWidth()) + complementoLabel.getWidth() , numLogLabel.getY());
		complementoLabel.setVisible(true);
		
		
		
		complemento = new JTextField();
		complemento.setSize(75, 25);
		complemento.setOpaque(true);
		complemento.setBackground(new Color(232, 231, 231));
		complemento.setBorder(null);
		complemento.setLocation(complementoLabel.getX() + (complemento.getWidth() + ((complemento.getWidth() / 2) - 15)), complementoLabel.getY() );
		complemento.setVisible(true);
		
		
		submitCliente = new JButton("Cadastrar");
		submitCliente.setBackground(null);
		submitCliente.setBorder(new LineBorder(Color.black,1,true));
		submitCliente.setSize(100, 50);
		submitCliente.setLocation(nomeLabel.getX(),getHeight() - (submitCliente.getHeight() * 2));
		submitCliente.addMouseListener(this);
		submitCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		deleteCliente = new JButton(new ImageIcon(Delete));
		deleteCliente.setBackground(Color.red);
		deleteCliente.setBorder(null);
		deleteCliente.setSize(100, 50);
		deleteCliente.setLocation((submitCliente.getX() + submitCliente.getWidth()) + (deleteCliente.getWidth() / 2), submitCliente.getY());
		deleteCliente.addMouseListener(this);
		deleteCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		submitFornecedor = new JButton("Cadastrar");
		submitFornecedor.setBackground(null);
		submitFornecedor.setBorder(new LineBorder(Color.black,1,true));
		submitFornecedor.setSize(100, 50);
		submitFornecedor.setLocation(submitCliente.getX(),submitCliente.getY());
		submitFornecedor.addMouseListener(this);
		submitFornecedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		
		deleteFornecedor = new JButton(new ImageIcon(Delete));
		deleteFornecedor.setBackground(Color.red);
		deleteFornecedor.setBorder(null);
		deleteFornecedor.setSize(100, 50);
		deleteFornecedor.setLocation(deleteCliente.getX(), deleteCliente.getY());
		deleteFornecedor.addMouseListener(this);
		deleteFornecedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		submitFabricante = new JButton("Cadastrar");
		submitFabricante.setBackground(null);
		submitFabricante.setBorder(new LineBorder(Color.black,1,true));
		submitFabricante.setSize(100, 50);
		submitFabricante.setLocation(submitCliente.getX(),submitCliente.getY());
		submitFabricante.addMouseListener(this);
		submitFabricante.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		
		deleteFabricante = new JButton(new ImageIcon(Delete));
		deleteFabricante.setBackground(Color.red);
		deleteFabricante.setBorder(null);
		deleteFabricante.setSize(100, 50);
		deleteFabricante.setLocation(deleteCliente.getX(), deleteCliente.getY());
		deleteFabricante.addMouseListener(this);
		deleteFabricante.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		submitProduto = new JButton("Cadastrar");
		submitProduto.setBackground(null);
		submitProduto.setBorder(new LineBorder(Color.black,1,true));
		submitProduto.setSize(100, 50);
		submitProduto.setLocation(submitCliente.getX(),submitCliente.getY());
		submitProduto.addMouseListener(this);
		submitProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		
		deleteProduto = new JButton(new ImageIcon(Delete));
		deleteProduto.setBackground(Color.red);
		deleteProduto.setBorder(null);
		deleteProduto.setSize(100, 50);
		deleteProduto.setLocation(deleteCliente.getX(), deleteCliente.getY());
		deleteProduto.addMouseListener(this);
		deleteProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		submitFuncionario = new JButton("Cadastrar");
		submitFuncionario.setBackground(null);
		submitFuncionario.setBorder(new LineBorder(Color.black,1,true));
		submitFuncionario.setSize(100, 50);
		submitFuncionario.setLocation(submitCliente.getX(),submitCliente.getY());
		submitFuncionario.addMouseListener(this);
		submitFuncionario.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		
		deleteFuncionario = new JButton(new ImageIcon(Delete));
		deleteFuncionario.setBackground(Color.red);
		deleteFuncionario.setBorder(null);
		deleteFuncionario.setSize(100, 50);
		deleteFuncionario.setLocation(deleteCliente.getX(), deleteCliente.getY());
		deleteFuncionario.addMouseListener(this);
		deleteFuncionario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		refresh = new JButton(new ImageIcon(Refresh));
		refresh.setBackground(new Color(0 , 110, 152));
		refresh.setSize(100, 50);
		refresh.setLocation(submitCliente.getX(), submitCliente.getY());
		refresh.addMouseListener(this);
		refresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		buscaIdLabel = new JLabel("Buscar ID");
		buscaIdLabel.setSize(75,25);
		buscaIdLabel.setLocation(nomeLabel.getX(), nomeLabel.getY());
		
		
		buscaId = new JTextField();
		buscaId.setOpaque(true);
		buscaId.setBackground(new Color(232, 231, 231));
		buscaId.setBorder(null);
		buscaId.setSize(50,25);
		buscaId.setLocation((buscaIdLabel.getX() + buscaIdLabel.getWidth()), nomeCliente.getY());
		
		
		searchId = new JLabel(new ImageIcon(Lupa));
		searchId.setSize(25,25);
		searchId.setLocation((buscaId.getX() + buscaId.getWidth()) + 10, buscaId.getY());
		searchId.addMouseListener(this);
		searchId.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		searchIdFornecedor = new JLabel(new ImageIcon(Lupa));
		searchIdFornecedor.setSize(25,25);
		searchIdFornecedor.setLocation((buscaId.getX() + buscaId.getWidth()) + 10, buscaId.getY());
		searchIdFornecedor.addMouseListener(this);
		searchIdFornecedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		

		searchIdFabricante = new JLabel(new ImageIcon(Lupa));
		searchIdFabricante.setSize(25,25);
		searchIdFabricante.setLocation((buscaId.getX() + buscaId.getWidth()) + 10, buscaId.getY());
		searchIdFabricante.addMouseListener(this);
		searchIdFabricante.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		searchIdProduto = new JLabel(new ImageIcon(Lupa));
		searchIdProduto.setSize(25,25);
		searchIdProduto.setLocation((buscaId.getX() + buscaId.getWidth()) + 10, buscaId.getY());
		searchIdProduto.addMouseListener(this);
		searchIdProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		searchIdFuncionario = new JLabel(new ImageIcon(Lupa));
		searchIdFuncionario.setSize(25,25);
		searchIdFuncionario.setLocation((buscaId.getX() + buscaId.getWidth()) + 10, buscaId.getY());
		searchIdFuncionario.addMouseListener(this);
		searchIdFuncionario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		nomeFornecedor = new JTextField();
		nomeFornecedor.setOpaque(true);
		nomeFornecedor.setBackground(new Color(232, 231, 231));
		nomeFornecedor.setBorder(null);
		nomeFornecedor.setSize(200,25);
		nomeFornecedor.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);
		nomeFornecedor.setVisible(true);
		
		
		nomeFabricante = new JTextField();
		nomeFabricante.setOpaque(true);
		nomeFabricante.setBackground(new Color(232, 231, 231));
		nomeFabricante.setBorder(null);
		nomeFabricante.setSize(200,25);
		nomeFabricante.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);
		nomeFabricante.setVisible(true);
		
		
		nomeProduto = new JTextField();
		nomeProduto.setOpaque(true);
		nomeProduto.setBackground(new Color(232, 231, 231));
		nomeProduto.setBorder(null);
		nomeProduto.setSize(200,25);
		nomeProduto.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);
		nomeProduto.setVisible(true);
		
		
		
		
		precoLabel = new JLabel("Preço");
		precoLabel.setSize(100,25);
		precoLabel.setLocation(nomeLabel.getX(), nomeProduto.getY() + ((dataNascLabel.getHeight() * 2) + (dataNascLabel.getHeight() / 2)));
		
		precoProduto = new JFormattedTextField(Mascara("R$##.##","num"));
		precoProduto.setSize(100, 25);
		precoProduto.setLocation(nomeProduto.getX(),precoLabel.getY());
		precoProduto.setOpaque(true);
		precoProduto.setBackground(new Color(232, 231, 231));
		precoProduto.setBorder(null);
		
		fornecedorLabel = new JLabel("Fornecedor");
		fornecedorLabel.setSize(dataNascLabel.getWidth() - 50,25 );
		fornecedorLabel.setLocation(nomeLabel.getX(), dataNascLabel.getY() + ((cepCliente.getHeight() * 2)  +  (cepCliente.getHeight() / 2)));

		fornecedorField = new JTextField("");
		fornecedorField.setSize(buscaId.getWidth(),buscaId.getHeight());
		fornecedorField.setLocation((fornecedorLabel.getX() + fornecedorLabel.getWidth()),fornecedorLabel.getY());
		fornecedorField.setOpaque(true);
		fornecedorField.setBackground(new Color(232, 231, 231));
		fornecedorField.setBorder(null);
		
		fabricanteLabel = new JLabel("Fabricante");
		fabricanteLabel.setSize(dataNascLabel.getWidth() - 50,25 );
		fabricanteLabel.setLocation(nomeLabel.getX(), fornecedorLabel.getY() + ((cepCliente.getHeight() * 2)  +  (cepCliente.getHeight() / 2)));

		fabricanteField = new JTextField("");
		fabricanteField.setSize(buscaId.getWidth(),buscaId.getHeight());
		fabricanteField.setLocation((fabricanteLabel.getX() + fabricanteLabel.getWidth()),fabricanteLabel.getY());
		fabricanteField.setOpaque(true);
		fabricanteField.setBackground(new Color(232, 231, 231));
		fabricanteField.setBorder(null);
		
		
		nomeFuncionario = new JTextField();
		nomeFuncionario.setOpaque(true);
		nomeFuncionario.setBackground(new Color(232, 231, 231));
		nomeFuncionario.setBorder(null);
		nomeFuncionario.setSize(200,25);
		nomeFuncionario.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);
		nomeFuncionario.setVisible(true);
		
		rgLabel = new JLabel("RG");
		rgLabel.setSize(cpfLabel.getWidth(),25);
		rgLabel.setLocation(nomeLabel.getX(), generoLabel.getY());
		
		rgField = new JFormattedTextField(Mascara("##.###.###-#","num"));
		rgField.setSize(cpfField.getSize());
		rgField.setOpaque(true);
		rgField.setBackground(new Color(232, 231, 231));
		rgField.setBorder(null);
		rgField.setLocation(nomeFuncionario.getX(), rgLabel.getY());
		rgField.setVisible(true);
		
		
		
		
		optionsBox = new JComboBox<>(tableOptions);
		optionsBox.setSize(100,25);
		optionsBox.setLocation(cadastro.getX() + cadastro.getWidth(), cadastro.getY());
		optionsBox.setBackground(null);
		optionsBox.setBorder(null);
		optionsBox.setForeground(new Color(0, 110, 152));
		optionsBox.setRenderer(new MyComboBoxRenderer("Selecione..."));
		optionsBox.setSelectedIndex(-1);
		optionsBox.addItemListener(new ItemListener() {
		
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					
					
					if(e.getItem().equals("Cliente")) {
						repaint();
						tableQuery.setModel(modelo);
						tableQuery.setEnabled(false);
						barraRolagem = new JScrollPane(tableQuery);
						barraRolagem.setVisible(false);

						barraRolagem.setSize(600, 200);
						barraRolagem.setLocation((queryContainer.getWidth() / 2) - (barraRolagem.getWidth() / 2),80);
						tableQuery.getColumnModel().getColumn(0).setPreferredWidth(10);
						tableQuery.getColumnModel().getColumn(1).setPreferredWidth(120);
						tableQuery.getColumnModel().getColumn(2).setPreferredWidth(50);
						tableQuery.getColumnModel().getColumn(3).setPreferredWidth(5);
						tableQuery.getColumnModel().getColumn(4).setPreferredWidth(50);
						tableQuery.getColumnModel().getColumn(5).setPreferredWidth(5);
						tableQuery.getColumnModel().getColumn(6).setPreferredWidth(5);
						
						
						
						tableQuery.setVisible(true);
						barraRolagem.setVisible(true);

												
						
						consultarCliente(modelo);
						queryContainer.add(barraRolagem);
						optionsBox.setEnabled(false);	
						repaint();
					}
					
					if(e.getItem().equals("Fornecedor")) {
						repaint();
									
						tableQuery.setModel(modelFornecedor);
						tableQuery.setEnabled(false);;
						barraRolagem = new JScrollPane(tableQuery);
						barraRolagem.setSize(200, 200);
						barraRolagem.setLocation((queryContainer.getWidth() / 2) - (barraRolagem.getWidth() / 2),80);
						tableQuery.getColumnModel().getColumn(0).setPreferredWidth(10);
						tableQuery.getColumnModel().getColumn(1).setPreferredWidth(120);
						
						
						
						tableQuery.setVisible(true);
						barraRolagem.setVisible(true);

												
						consultarFornecedor(modelFornecedor);
						queryContainer.add(barraRolagem);
						optionsBox.setEnabled(false);
						repaint();

					}
					
					if(e.getItem().equals("Fabricante")) {
						repaint();

						tableQuery.setModel(modelFabricante);
						tableQuery.setEnabled(false);;
						barraRolagem = new JScrollPane(tableQuery);
						barraRolagem.setSize(200, 200);
						barraRolagem.setLocation((queryContainer.getWidth() / 2) - (barraRolagem.getWidth() / 2),80);
						tableQuery.getColumnModel().getColumn(0).setPreferredWidth(10);
						tableQuery.getColumnModel().getColumn(1).setPreferredWidth(120);
						
						
						
						tableQuery.setVisible(true);
						barraRolagem.setVisible(true);

												
						consultarFabricante(modelFabricante);
						queryContainer.add(barraRolagem);
						optionsBox.setEnabled(false);
						repaint();

					
					
					}
					
					if(e.getItem().equals("Produto")) {
						repaint();

						tableQuery.setModel(modelProduto);
						tableQuery.setEnabled(false);
						barraRolagem = new JScrollPane(tableQuery);
						barraRolagem.setSize(600, 200);
						barraRolagem.setLocation((queryContainer.getWidth() / 2) - (barraRolagem.getWidth() / 2),80);
						tableQuery.getColumnModel().getColumn(0).setPreferredWidth(10);
						tableQuery.getColumnModel().getColumn(1).setPreferredWidth(120);
						tableQuery.getColumnModel().getColumn(2).setPreferredWidth(10);
						tableQuery.getColumnModel().getColumn(3).setPreferredWidth(50);
						tableQuery.getColumnModel().getColumn(4).setPreferredWidth(50);
						tableQuery.getColumnModel().getColumn(5).setPreferredWidth(90);
						
						
						
						
						tableQuery.setVisible(true);
						barraRolagem.setVisible(true);

												
						consultarProduto(modelProduto);
						queryContainer.add(barraRolagem);
						optionsBox.setEnabled(false);
						repaint();

					
					
					}

					
					if(e.getItem().equals("Funcionario")) {
						repaint();

						tableQuery.setModel(modelFuncionario);
						tableQuery.setEnabled(false);
						barraRolagem = new JScrollPane(tableQuery);
						barraRolagem.setSize(600, 200);
						barraRolagem.setLocation((queryContainer.getWidth() / 2) - (barraRolagem.getWidth() / 2),80);
						tableQuery.getColumnModel().getColumn(0).setPreferredWidth(10);
						tableQuery.getColumnModel().getColumn(1).setPreferredWidth(120);
						tableQuery.getColumnModel().getColumn(2).setPreferredWidth(70);
						tableQuery.getColumnModel().getColumn(3).setPreferredWidth(70);
						tableQuery.getColumnModel().getColumn(4).setPreferredWidth(10);
						
						
						
						
						tableQuery.setVisible(true);
						barraRolagem.setVisible(true);

												
						consultarFuncionario(modelFuncionario);
						queryContainer.add(barraRolagem);
						optionsBox.setEnabled(false);
						repaint();

					
					
					}
					
				}
			}
		});
		
		
		
		typesBox = new JComboBox<>(typesOptions);
		typesBox.setSize(125,25);
		typesBox.setLocation((nomeProduto.getX() + nomeProduto.getWidth() + (typesBox.getWidth() / 2)), nomeProduto.getY());
		typesBox.setBackground(null);
		typesBox.setBorder(null);
		typesBox.setForeground(new Color(0, 110, 152));
		typesBox.setRenderer(new MyComboBoxRenderer("Tipo..."));
		typesBox.setSelectedIndex(-1);
		
		
		
		
		tableQuery = new JTable();
		
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Gênero");
		modelo.addColumn("CEP");
		modelo.addColumn("numLog");
		modelo.addColumn("Complemento");
		
		modelFornecedor.addColumn("Id");
		modelFornecedor.addColumn("Nome");
		
		
		modelFabricante.addColumn("Id");
		modelFabricante.addColumn("Nome");
		
		modelProduto.addColumn("Id");
		modelProduto.addColumn("Nome");
		modelProduto.addColumn("Preço");
		modelProduto.addColumn("Fornecedor");
		modelProduto.addColumn("Fabricante");
		modelProduto.addColumn("Tipo");

		modelFuncionario.addColumn("Id");
		modelFuncionario.addColumn("Nome");
		modelFuncionario.addColumn("RG");
		modelFuncionario.addColumn("CPF");
		modelFuncionario.addColumn("Gênero");

		queryContainer.add(optionsBox);
		queryContainer.add(refresh);
		
		
		
		
		repaint();
		
		
		
	}
	
	public static void consultarCliente(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ClienteDAO clientObject = new ClienteDAO();
		
		for(Cliente c : clientObject.select()) {
			modelo.addRow(new Object[]{c.getIdCliente(), c.getNomeCliente(), c.getCpfCliente(),c.getGeneroCliente(),c.getCepCliente(),c.getNumLog(),c.getComplementoLog()});
		}
	}
	
	public static void consultarFornecedor(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		FornecedorDAO fornecedorObject = new FornecedorDAO();
		
		for(Fornecedor f : fornecedorObject.select()) {
			modelo.addRow(new Object[]{f.getIdFornecedor(),f.getNomeFornecedor()});
		}
	}
	
	public static void consultarFabricante(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		FabricanteDAO fabricanteObject = new FabricanteDAO();
		
		for(Fabricante f : fabricanteObject.select()) {
			modelo.addRow(new Object[]{f.getIdFabricante(),f.getNomeFabricante()});
		}
	}
	
	public static void consultarFuncionario(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		FuncionarioDAO funcionarioObject = new FuncionarioDAO();
		
		for(Funcionario f : funcionarioObject.select()) {
			modelo.addRow(new Object[]{f.getIdFuncionario(),f.getNomeFuncionario(),f.getRgFuncionario(),f.getCpfFuncionario(),f.getGeneroFuncionario()});
		}
	}
	
	
	
	
	public static void consultarProduto(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ProdutoDAO produtoObject = new ProdutoDAO();
		
		for(Produto p : produtoObject.select()) {
			Fornecedor fornecedor = new Fornecedor();
			FornecedorDAO fornDAO = new FornecedorDAO();
			
			fornecedor = fornDAO.select(p.getIdFornecedor());
			
			Fabricante fabricante = new Fabricante();
			FabricanteDAO fabDAO = new FabricanteDAO();
			
			fabricante = fabDAO.select(p.getIdFabricante());
			
			
			modelo.addRow(new Object[]{p.getIdProduto(),p.getNomeProduto(),p.getPrecoProduto(),fornecedor.getNomeFornecedor(),fabricante.getNomeFabricante(),p.getTipoProduto()});
		}
	}
	
	
	public void clearClient() {

		nomeCliente.setText("");
		cpfField.setText("");
		dataNasc.setText("");
		cepCliente.setText("");
		dataNasc.setText("");
		cepCliente.setText("");
		logLabel.setText("");
		clientContainer.remove(logLabel);
		clientContainer.remove(numLogLabel);
		clientContainer.remove(complementoLabel);
		numLog.setText("");
		clientContainer.remove(numLog);
		municipioLabel.setText("");
		clientContainer.remove(municipioLabel);
		complemento.setText("");
		clientContainer.remove(complemento);
		bairroLabel.setText("");
		clientContainer.remove(bairroLabel);
		ufLabel.setText("");
		clientContainer.remove(ufLabel);
		genero.clearSelection();
		repaint();
	
	}
	
	public void clearProduto(){
		nomeProduto.setText("");
		fornecedorField.setText("");
		typesBox.setSelectedIndex(-1);
		fabricanteField.setText("");
		precoProduto.setText("");
	}
	
	public void clearFuncionario() {
		nomeFuncionario.setText("");
		cpfField.setText("");
		rgField.setText("");
		genero.clearSelection();
	}
	
	public void removeAll() {
		remove(clientContainer);
		remove(fornecedorContainer);
		remove(fabricanteContainer);
		remove(editContainer);
		remove(pnlContainer);
		remove(queryContainer);
		remove(produtoContainer);
		remove(funcionarioContainer);
		repaint();
	}
	
	public void realocate() {
		
		cpfLabel.setLocation((nomeCliente.getX() + nomeCliente.getWidth() + (cpfLabel.getWidth() / 2) ), nomeLabel.getY());
		
		cpfField.setLocation((cpfLabel.getX() + cpfLabel.getWidth()) , cpfLabel.getY());

		dataNascLabel.setLocation(nomeLabel.getX(), nomeCliente.getY() + ((dataNascLabel.getHeight() * 2) + (dataNascLabel.getHeight() / 2)));

		dataNasc.setLocation((dataNascLabel.getX() + dataNascLabel.getWidth()), dataNascLabel.getY());

		generoLabel.setLocation((dataNasc.getX() + dataNasc.getWidth()) + (dataNasc.getWidth() / 2), dataNascLabel.getY());

		genero1.setLocation(generoLabel.getX() + generoLabel.getWidth(), generoLabel.getY());
		
		genero2.setLocation(genero1.getX() + genero1.getWidth(), generoLabel.getY());
		
		cepCliente.setLocation(nomeCliente.getX(), dataNascLabel.getY() + ((cepCliente.getHeight() * 2)  +  (cepCliente.getHeight() / 2)));
		
		cepLabel.setLocation(cepCliente.getX() - (cepCliente.getWidth() / 2), cepCliente.getY());
		
		lupaIcon.setLocation((cepCliente.getX() + cepCliente.getWidth()) + lupaIcon.getWidth(), cepLabel.getY());
		
		logLabel.setLocation(nomeCliente.getX(),cepCliente.getY() + logLabel.getHeight() + (logLabel.getHeight() / 2));
		
		bairroLabel.setLocation(nomeCliente.getX(),logLabel.getY() + logLabel.getHeight() + (logLabel.getHeight() / 3));
		
		ufLabel.setLocation(logLabel.getX() + (logLabel.getWidth() / 2),logLabel.getY());
		
		municipioLabel.setLocation(logLabel.getX() + (logLabel.getWidth() / 2),bairroLabel.getY());
		
		numLogLabel.setLocation(cepLabel.getX(), bairroLabel.getY() + numLogLabel.getHeight());
		
		numLog.setLocation(bairroLabel.getX(), numLogLabel.getY());
		
		complementoLabel.setLocation((numLogLabel.getX() + numLogLabel.getWidth()) + complementoLabel.getWidth() , numLogLabel.getY());
		
		complemento.setLocation(complementoLabel.getX() + (complemento.getWidth() + ((complemento.getWidth() / 2) - 15)), complementoLabel.getY() );

		typesBox.setLocation((nomeProduto.getX() + nomeProduto.getWidth() + (typesBox.getWidth() / 2)), nomeProduto.getY());


		precoLabel.setLocation(nomeLabel.getX(), nomeProduto.getY() + ((dataNascLabel.getHeight() * 2) + (dataNascLabel.getHeight() / 2)));
			
		precoProduto.setLocation(nomeProduto.getX(),precoLabel.getY());
		
		fornecedorLabel.setLocation(nomeLabel.getX(), precoLabel.getY() + ((cepCliente.getHeight() * 2)  +  (cepCliente.getHeight() / 2)));


		fornecedorField.setLocation((fornecedorLabel.getX() + fornecedorLabel.getWidth()),fornecedorLabel.getY());

		
		fabricanteLabel.setLocation(nomeLabel.getX(), fornecedorLabel.getY() + ((cepCliente.getHeight() * 2)  +  (cepCliente.getHeight() / 2)));

		fabricanteField.setLocation((fabricanteLabel.getX() + fabricanteLabel.getWidth()),fabricanteLabel.getY());

		rgLabel.setLocation(nomeLabel.getX(), nomeFuncionario.getY() + ((dataNascLabel.getHeight() * 2) + (dataNascLabel.getHeight() / 2)));		

		rgField.setLocation(nomeFuncionario.getX(), rgLabel.getY());


		
	}
	
	public void cReAdd(){
		
		
		clientContainer.add(nomeCliente);
		clientContainer.add(nomeLabel);
		clientContainer.add(cepCliente);
		clientContainer.add(cepLabel);
		clientContainer.add(lupaIcon);
		clientContainer.add(submitCliente);
		clientContainer.add(cpfLabel);
		clientContainer.add(cpfField);
		clientContainer.add(dataNascLabel);
		clientContainer.add(dataNasc);
		clientContainer.add(generoLabel);
		clientContainer.add(genero1);
		clientContainer.add(genero2);
		
		
	}
	
	public void fornReAdd() {
		fornecedorContainer.add(nomeLabel);
		fornecedorContainer.add(nomeFornecedor);
		
		fornecedorContainer.add(submitFornecedor);
	
	}
	
	public void fabReAdd() {
		fabricanteContainer.add(nomeLabel);
		fabricanteContainer.add(submitFabricante);
		fabricanteContainer.add(nomeFabricante);
	}
	
	public void prodReAdd() {
		produtoContainer.add(nomeLabel);
		produtoContainer.add(submitProduto);
		produtoContainer.add(typesBox);
		produtoContainer.add(precoLabel);
		produtoContainer.add(nomeProduto);
		produtoContainer.add(precoProduto);
		produtoContainer.add(fornecedorLabel);
		produtoContainer.add(fornecedorField);
		produtoContainer.add(fabricanteField);
		produtoContainer.add(fabricanteLabel);
	}
	
	public void funcReAdd() {
		funcionarioContainer.add(nomeLabel);
		funcionarioContainer.add(nomeFuncionario);
		funcionarioContainer.add(cpfField);
		funcionarioContainer.add(cpfLabel);
		funcionarioContainer.add(generoLabel);
		funcionarioContainer.add(genero1);
		funcionarioContainer.add(genero2);
		funcionarioContainer.add(submitFuncionario);
		funcionarioContainer.add(rgLabel);
		funcionarioContainer.add(rgField);
	}
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		try {
			if(e.getSource().equals(submitCliente) ) {
				submitCliente.setBackground(new Color(0, 110, 152));
				submitCliente.setForeground(Color.white);
				
			}
			
			if(e.getSource().equals(submitFornecedor)) {
				
				submitFornecedor.setBackground(new Color(0, 110, 152));
				submitFornecedor.setForeground(Color.white);
				
			}
			
			if(e.getSource().equals(submitProduto)) {
				
				submitProduto.setBackground(new Color(0, 110, 152));
				submitProduto.setForeground(Color.white);
				
			}
			
			
			if(e.getSource().equals(submitFabricante)) {
				
				submitFabricante.setBackground(new Color(0, 110, 152));
				submitFabricante.setForeground(Color.white);
				
			}
			
			if(e.getSource().equals(submitFuncionario)) {
				
				submitFuncionario.setBackground(new Color(0, 110, 152));
				submitFuncionario.setForeground(Color.white);
				
			}
			
			if(e.getSource().equals(editCliente)) {
				editCliente.setBackground(new Color(0, 110, 152));
				editCliente.setForeground(Color.white);
			}
			

			if(e.getSource().equals(editFabricante)) {
				editFabricante.setBackground(new Color(0, 110, 152));
				editFabricante.setForeground(Color.white);
			}
			
			if(e.getSource().equals(editFornecedor)) {
				editFornecedor.setBackground(new Color(0, 110, 152));
				editFornecedor.setForeground(Color.white);
			}
			
			if(e.getSource().equals(editFuncionario)) {
				editFuncionario.setBackground(new Color(0, 110, 152));
				editFuncionario.setForeground(Color.white);
			}
			
			if(e.getSource().equals(editProduto)) {
				editProduto.setBackground(new Color(0, 110, 152));
				editProduto.setForeground(Color.white);
			}
			
			
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource().equals(submitCliente)) {
				submitCliente.setBackground(null);
				submitCliente.setForeground(Color.black);
			}
			
			if(e.getSource().equals(submitFornecedor)) {
				submitFornecedor.setBackground(null);
				submitFornecedor.setForeground(Color.black);
			}

			if(e.getSource().equals(submitFabricante)) {
				submitFabricante.setBackground(null);
				submitFabricante.setForeground(Color.black);
			}

			if(e.getSource().equals(submitProduto)) {
				submitProduto.setBackground(null);
				submitProduto.setForeground(Color.black);
			}
			
			
			if(e.getSource().equals(submitFuncionario)) {
				submitFuncionario.setBackground(null);
				submitFuncionario.setForeground(Color.black);
			}
			
			
			if(e.getSource().equals(editCliente)) {
				editCliente.setBackground(null);
				editCliente.setForeground(Color.black);
			}
			
			if(e.getSource().equals(editFabricante)) {
				editFabricante.setBackground(null);
				editFabricante.setForeground(Color.black);
			}
			
			if(e.getSource().equals(editFornecedor)) {
				editFornecedor.setBackground(null);
				editFornecedor.setForeground(Color.black);
			}
			
			if(e.getSource().equals(editFuncionario)) {
				editFuncionario.setBackground(null);
				editFuncionario.setForeground(Color.black);
			}
			
			if(e.getSource().equals(editProduto)) {
				editProduto.setBackground(null);
				editProduto.setForeground(Color.black);
			}
			
			
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
		}	
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	
		if(e.getSource().equals(insertIcon)) {
			repaint();
			removeAll();
			add(pnlContainer);
			cadastro.setText("Cadastrar:");
			STATE = INSERTING;
			repaint();
		}
		
		if(e.getSource().equals(editIcon)) {
			repaint();
			buscaId.setText("");
			cadastro.setText("Editar:");
			removeAll();
			STATE = EDITING;
			add(editContainer);
			repaint();
		}
		
		if(STATE == INSERTING) {
			clientContainer.remove(buscaId);
			clientContainer.remove(buscaIdLabel);
			clientContainer.remove(searchId);
			
			nomeCliente.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);
			nomeLabel.setLocation(nomeCliente.getX() - (nomeLabel.getWidth() / 2), nomeCliente.getY());
			
			cReAdd();
			repaint();

			
			realocate();
			repaint();
		
			
			fornecedorContainer.remove(buscaId);
			fornecedorContainer.remove(buscaIdLabel);
			fornecedorContainer.remove(searchIdFornecedor);
			
			nomeFornecedor.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);
			
			
			repaint();
			
			
			
			fabricanteContainer.remove(buscaId);
			fabricanteContainer.remove(buscaIdLabel);
			fabricanteContainer.remove(searchIdFabricante);
			
			nomeFabricante.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);

			
			repaint();

			produtoContainer.remove(buscaId);
			produtoContainer.remove(buscaIdLabel);
			produtoContainer.remove(searchIdProduto);
			nomeProduto.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);

			repaint();
			
			funcionarioContainer.remove(buscaId);
			funcionarioContainer.remove(buscaIdLabel);
			funcionarioContainer.remove(searchIdFuncionario);
			nomeFuncionario.setLocation(nomeCliente.getWidth() / 2, cadastro.getY() + 100);

			
		}
		
		if(e.getSource().equals(clientIcon)) {
			repaint();
			cadastro.setText("Cliente");
			submitCliente.setText("Cadastrar");
			clientContainer.remove(deleteCliente);
			clearClient();
			STATE = INSERTING;
			removeAll();
			add(clientContainer);
			
				
			repaint();
		}
		
		if(e.getSource().equals(fornecedorIcon)) {
			repaint();
			nomeFornecedor.setText("");
			cadastro.setText("Fornecedor");
			submitFornecedor.setText("Cadastrar");
			fornReAdd();
			fornecedorContainer.remove(deleteFornecedor);
			STATE = INSERTING;
			removeAll();
			add(fornecedorContainer);
		}
		
		if(e.getSource().equals(fabricanteIcon)) {
			removeAll();			
			repaint();
			nomeFabricante.setText("");
			cadastro.setText("Fabricante");
			submitFabricante.setText("Cadastrar");
			fabReAdd();
			fabricanteContainer.remove(deleteFabricante);
			STATE = INSERTING;
			add(fabricanteContainer);
			repaint();
		}
	
		if(e.getSource().equals(produtosIcon)) {
			removeAll();			
			repaint();
			cadastro.setText("Produto");
			clearProduto();
			submitProduto.setText("Cadastrar");
			produtoContainer.remove(deleteProduto);
			prodReAdd();
			STATE = INSERTING;
			add(produtoContainer);
			repaint();
		}
		
		if(e.getSource().equals(funcionarioIcon)) {
			removeAll();
			repaint();
			cadastro.setText("Funcionário");
			clearFuncionario();
			submitFuncionario.setText("Cadastrar");
			funcionarioContainer.remove(deleteFuncionario);
			STATE = INSERTING;
			funcReAdd();
			add(funcionarioContainer);
			repaint();
		}
		
		if(e.getSource().equals(lupaIcon)) {
			
			cep = String.valueOf(cepCliente.getText());
			cep = cep.replace("-", "");
			
			
			try {
				cAPI.buscar(this.cep);
				
				logLabel.setText(cAPI.getLogradouro());
				clientContainer.add(logLabel);
				
				bairroLabel.setText(cAPI.getBairro());
				clientContainer.add(bairroLabel);
				
				
				
				ufLabel.setText(cAPI.getUf());
				clientContainer.add(ufLabel);
				
				
				municipioLabel.setText(cAPI.getLocalidade());
				clientContainer.add(municipioLabel);
				
				clientContainer.add(numLogLabel);
				clientContainer.add(numLog);
				
				clientContainer.add(complemento);
				clientContainer.add(complementoLabel);
				
				repaint();
			} catch (ViaCEPException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
				}
		
		if(e.getSource().equals(submitCliente) && submitCliente.getText().equals("Cadastrar")) {
			
			if(genero1.isSelected()) {
				
				sexo = "m";
				
			}else if(genero2.isSelected()) {
				
				sexo = "f";
			}else {
				JOptionPane.showMessageDialog(null, "Insira todos os campos!");
			}
			
			
			Cliente cliente = new Cliente();
			cliente.setNomeCliente(nomeCliente.getText());
			cliente.setCpfCliente(cpfField.getText());
			cliente.setDataNasc(dataNasc.getText());
			cliente.setCepCliente(cepCliente.getText());
			cliente.setLogCliente(logLabel.getText());
			cliente.setNumLog(numLog.getText());
			cliente.setMunicipioCliente(municipioLabel.getText());
			cliente.setComplementoLog(complemento.getText());
			cliente.setBairroCliente(bairroLabel.getText());
			cliente.setUfCliente(ufLabel.getText());
			cliente.setGeneroCliente(sexo);
			
			
			ClienteDAO clienteObject = new ClienteDAO();
			clienteObject.insert(cliente);
			
			clearClient();	
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
		}
		
		if(e.getSource().equals(editCliente)) {
			removeAll();
			repaint();
			add(clientContainer);
			
			if(STATE == EDITING) {	
			
				
			submitCliente.setText("Atualizar");
			clearClient();
			clientContainer.removeAll();
			clientContainer.add(buscaId);
			clientContainer.add(buscaIdLabel);
			clientContainer.add(searchId);
			
			
			
			
			nomeLabel.setLocation(buscaIdLabel.getX(), buscaIdLabel.getY() + (nomeLabel.getHeight() * 2));
			nomeCliente.setLocation(buscaId.getX(), nomeLabel.getY());
			realocate();
			
			
			
			
			
			complemento.setVisible(true);
			complementoLabel.setVisible(true);
			numLog.setVisible(true);
			numLogLabel.setVisible(true);
			estadoLabel.setVisible(true);
			municipioLabel.setVisible(true);
			}
			repaint();
			
			
			
		}
		
		if(e.getSource().equals(editFornecedor)) {
			removeAll();
			
			
			if(STATE == EDITING) {
				
				
				nomeLabel.setLocation(buscaIdLabel.getX(), buscaIdLabel.getY() + (nomeLabel.getHeight() * 2));
				nomeFornecedor.setLocation(buscaId.getX(), nomeLabel.getY());
				
				submitFornecedor.setText("Atualizar");
				nomeFornecedor.setText("");
				fornecedorContainer.removeAll();
				fornecedorContainer.add(buscaId);
				fornecedorContainer.add(buscaIdLabel);
				fornecedorContainer.add(searchIdFornecedor);
				
				
				repaint();
			
			}
			
			add(fornecedorContainer);
		}
		

		if(e.getSource().equals(editFabricante)) {
			removeAll();
			
			
			if(STATE == EDITING) {
				
				
				nomeLabel.setLocation(buscaIdLabel.getX(), buscaIdLabel.getY() + (nomeLabel.getHeight() * 2));
				nomeFabricante.setLocation(buscaId.getX(), nomeLabel.getY());
				
				submitFabricante.setText("Atualizar");
				nomeFabricante.setText("");
				fabricanteContainer.removeAll();
				fabricanteContainer.add(buscaId);
				fabricanteContainer.add(buscaIdLabel);
				fabricanteContainer.add(searchIdFabricante);
				
				repaint();
			
			}
			
			add(fabricanteContainer);
		}
		
		if(e.getSource().equals(editProduto)) {
			removeAll();
			
			
			if(STATE == EDITING) {
				
				
				nomeLabel.setLocation(buscaIdLabel.getX(), buscaIdLabel.getY() + (nomeLabel.getHeight() * 2));
				nomeProduto.setLocation(buscaId.getX(), nomeLabel.getY());
				realocate();
			
				
				submitProduto.setText("Atualizar");
				clearProduto();
				produtoContainer.removeAll();
				produtoContainer.add(buscaId);
				produtoContainer.add(searchIdProduto);
				produtoContainer.add(buscaIdLabel);
				
				repaint();
			
			}
			
			add(produtoContainer);
		}
		
		if(e.getSource().equals(editFuncionario)) {
			removeAll();
			
			
			if(STATE == EDITING) {
				
				
				nomeLabel.setLocation(buscaIdLabel.getX(), buscaIdLabel.getY() + (nomeLabel.getHeight() * 2));
				nomeFuncionario.setLocation(buscaId.getX(), nomeLabel.getY());
				realocate();
				generoLabel.setLocation((dataNasc.getX() + dataNasc.getWidth()) + (dataNasc.getWidth() / 2), rgField.getY());

				genero1.setLocation(generoLabel.getX() + generoLabel.getWidth(), generoLabel.getY());
				
				genero2.setLocation(genero1.getX() + genero1.getWidth(), generoLabel.getY());

				
				submitFuncionario.setText("Atualizar");
				clearFuncionario();
				funcionarioContainer.removeAll();
				funcionarioContainer.add(buscaId);
				funcionarioContainer.add(searchIdFuncionario);
				funcionarioContainer.add(buscaIdLabel);
				
				repaint();
			
			}
			
			add(funcionarioContainer);
		}
		

		if(e.getSource().equals(searchId)) {
			
			
			ClienteDAO clienteObject = new ClienteDAO();
			Cliente client = new Cliente();
			client = clienteObject.select(Integer.parseInt(buscaId.getText()));
			
			
			nomeCliente.setText(client.getNomeCliente());
			cpfField.setText(client.getCpfCliente());
			dataNasc.setText(client.getDataNasc());
			
			if(client.getGeneroCliente().equals("m")) {
				genero1.setSelected(true);
			}else if(client.getGeneroCliente().equals("f")){
				genero2.setSelected(true);
			}else {
				JOptionPane.showMessageDialog(null, "Erro ao consultar ID");
			}
			
			cepCliente.setText(client.getCepCliente());
			municipioLabel.setText(client.getMunicipioCliente());
			logLabel.setText(client.getLogCliente());
			numLog.setText(client.getNumLog());
			bairroLabel.setText(client.getBairroCliente());
			ufLabel.setText(client.getUfCliente());
			complemento.setText(client.getComplementoLog());
			
			
			
			clientContainer.add(logLabel);
			clientContainer.add(bairroLabel);
			clientContainer.add(ufLabel);
			clientContainer.add(municipioLabel);
			clientContainer.add(numLogLabel);
			clientContainer.add(numLog);
			clientContainer.add(complemento);
			clientContainer.add(complementoLabel);
			clientContainer.add(deleteCliente);

			repaint();
		
			
			cReAdd();
			repaint();
			
			
			
			
		}
		
		
		if(e.getSource().equals(submitCliente) && submitCliente.getText().equals("Atualizar")) {
		
			ClienteDAO clienteObject = new ClienteDAO();
			Cliente client = new Cliente();
			client = clienteObject.select(Integer.parseInt(buscaId.getText()));
	
			
			client.setNomeCliente(nomeCliente.getText());
			client.setCpfCliente(cpfField.getText());
			client.setDataNasc(dataNasc.getText());
			
			if(genero1.isSelected()) {
				sexo = "m";
			}else if(genero2.isSelected()) {
				sexo ="f";
			}else {
				JOptionPane.showMessageDialog(null, "Insira todos os campos!");
			}
			
			client.setGeneroCliente(sexo);
			client.setCepCliente(cepCliente.getText());
			client.setMunicipioCliente(municipioLabel.getText());
			client.setLogCliente(logLabel.getText());
			client.setNumLog(numLog.getText());
			client.setBairroCliente(bairroLabel.getText());
			client.setUfCliente(ufLabel.getText());
			client.setComplementoLog(complemento.getText());
			
			
			clienteObject.update(client);
			JOptionPane.showMessageDialog(null, "Dados de "+client.getNomeCliente()+" alterados com suceso!");
			buscaId.setText("");
			clearClient();
			repaint();
			
			
		}
		
		if(e.getSource().equals(deleteCliente)) {
			ClienteDAO clienteObject = new ClienteDAO();
			Cliente client = new Cliente();
			client = clienteObject.select(Integer.parseInt(buscaId.getText()));
	
			int verify = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o cliente: "+client.getNomeCliente());
			
			
			if(verify == 0) {
				clienteObject.delete(client);
				
				JOptionPane.showMessageDialog(null, "Cliente Deletado!");

				buscaId.setText("");
				clearClient();
				repaint();
			}
			
			
		}
		
		if(e.getSource().equals(searchIdFornecedor)) {
			
			FornecedorDAO fornecedorObject = new FornecedorDAO();
			Fornecedor fornecedor = new Fornecedor();
			fornecedor = fornecedorObject.select(Integer.parseInt(buscaId.getText()));
			
			
			nomeFornecedor.setText(fornecedor.getNomeFornecedor());
			
			
			fornReAdd();
			fornecedorContainer.add(deleteFornecedor);
			repaint();
		}
		
		
		if(e.getSource().equals(searchIdFabricante)) {
			
			FabricanteDAO fabricanteObject = new FabricanteDAO();
			Fabricante fabricante = new Fabricante();
			fabricante = fabricanteObject.select(Integer.parseInt(buscaId.getText()));
			
			nomeFabricante.setText(fabricante.getNomeFabricante());
			
			
			fabReAdd();
			fabricanteContainer.add(deleteFabricante);
			repaint();
		}
		
		if(e.getSource().equals(searchIdProduto)) {
			
			ProdutoDAO produtoObject = new ProdutoDAO();
			Produto produto = new Produto();
			produto = produtoObject.select(Integer.parseInt(buscaId.getText()));
			
			nomeProduto.setText(produto.getNomeProduto());
			fornecedorField.setText(String.valueOf(produto.getIdFornecedor()));
			fabricanteField.setText(String.valueOf(produto.getIdFornecedor()));
			precoProduto.setText(String.format("%.2f", produto.getPrecoProduto()).replace(",", "."));
			
			System.out.println(produto.getTipoProduto());
			
			if(produto.getTipoProduto().equals("Medicamento")) {
				typesBox.setSelectedIndex(0);
				
			}else if(produto.getTipoProduto().equals("Cosmético")) {
				typesBox.setSelectedIndex(1);
				
			}else if(produto.getTipoProduto().equals("Genérico")) {
				typesBox.setSelectedIndex(2);
				
			}else if(produto.getTipoProduto().equals("Nutrição Esportiva")) {
				typesBox.setSelectedIndex(3);
				
			}
			
			prodReAdd();
			produtoContainer.add(deleteProduto);
			repaint();
		}
		

		if(e.getSource().equals(searchIdFuncionario)) {
			
			FuncionarioDAO funcionarioObject = new FuncionarioDAO();
			Funcionario funcionario = new Funcionario();
			funcionario = funcionarioObject.select(Integer.parseInt(buscaId.getText()));

			nomeFuncionario.setText(funcionario.getNomeFuncionario());
			rgField.setText(funcionario.getRgFuncionario());
			cpfField.setText(funcionario.getCpfFuncionario());
			
			
			if(funcionario.getGeneroFuncionario().equals("m")) {
				genero1.setSelected(true);
			}else {
				genero2.setSelected(true);
			}
			
			
			
			funcReAdd();
			funcionarioContainer.add(deleteFuncionario);
			repaint();
		}
		
		if(e.getSource().equals(submitProduto) && submitProduto.getText().equals("Atualizar")) {
			
			ProdutoDAO produtoObject = new ProdutoDAO();
			Produto produto = new Produto();
			produto = produtoObject.select(Integer.parseInt(buscaId.getText()));
			
			String a;
			a = precoProduto.getText();
			a = a.replace("R$", "");
			produto.setNomeProduto(nomeProduto.getText());
			produto.setPrecoProduto(Double.parseDouble(a));
			produto.setIdFabricante(Integer.parseInt(fabricanteField.getText()));
			produto.setIdFornecedor(Integer.parseInt(fornecedorField.getText()));
			produto.setTipoProduto(typesOptions[typesBox.getSelectedIndex()]);
			
			
			
			produtoObject.update(produto);
			
			JOptionPane.showMessageDialog(null, "Dados de "+produto.getNomeProduto()+" alterados com sucesso");
			
			buscaId.setText("");
			clearProduto();
			
			repaint();
		}
		
		
		
		if(e.getSource().equals(submitFornecedor) && submitFornecedor.getText().equals("Atualizar")) {
			
			FornecedorDAO fornecedorObject = new FornecedorDAO();
			Fornecedor fornecedor = new Fornecedor();
			fornecedor = fornecedorObject.select(Integer.parseInt(buscaId.getText()));
			
			fornecedor.setNomeFornecedor(nomeFornecedor.getText());
			
			fornecedorObject.update(fornecedor);
			
			JOptionPane.showMessageDialog(null, "Dados de "+fornecedor.getNomeFornecedor()+" alterados com sucesso");
			
			buscaId.setText("");
			nomeFornecedor.setText("");
			
			repaint();
		}
		
		if(e.getSource().equals(deleteFornecedor)) {
			
			FornecedorDAO fornecedorObject = new FornecedorDAO();
			Fornecedor fornecedor = new Fornecedor();
			fornecedor = fornecedorObject.select(Integer.parseInt(buscaId.getText()));
			
			
			int verify = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o fornecedor: "+fornecedor.getNomeFornecedor());
			
			
			if(verify == 0) {
				fornecedorObject.delete(fornecedor);
				
				JOptionPane.showMessageDialog(null, "Fornecedor Deletado!");

				buscaId.setText("");
				nomeFornecedor.setText("");
				repaint();
			}				
		}
		
		

		if(e.getSource().equals(submitFabricante) && submitFabricante.getText().equals("Atualizar")) {
			
			FabricanteDAO fabricanteObject = new FabricanteDAO();
			Fabricante fabricante = new Fabricante();
			fabricante = fabricanteObject.select(Integer.parseInt(buscaId.getText()));

			fabricante.setNomeFabricante(nomeFabricante.getText());
			
			fabricanteObject.update(fabricante);
			
			JOptionPane.showMessageDialog(null, "Dados de "+fabricante.getNomeFabricante()+" alterados com sucesso");
			
			buscaId.setText("");
			nomeFabricante.setText("");
			
			repaint();
		}
		
		if(e.getSource().equals(deleteFabricante)) {
			
			FabricanteDAO fabricanteObject = new FabricanteDAO();
			Fabricante fabricante = new Fabricante();
			fabricante = fabricanteObject.select(Integer.parseInt(buscaId.getText()));
			
			
			int verify  = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o fornecedor: "+fabricante.getNomeFabricante());
			
			
			if( verify == 0) {
				fabricanteObject.delete(fabricante);
				
				JOptionPane.showMessageDialog(null, "Fabricante Deletado!");

				buscaId.setText("");
				nomeFornecedor.setText("");
				repaint();
			}
			
		}	
			
		if(e.getSource().equals(deleteProduto)) {
			
			ProdutoDAO produtoObject = new ProdutoDAO();
			Produto produto = new Produto();
			produto = produtoObject.select(Integer.parseInt(buscaId.getText()));
			
			
			int verify  = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o produto: "+produto.getNomeProduto());
			
			
			if( verify == 0) {
				produtoObject.delete(produto);
				
				JOptionPane.showMessageDialog(null, "Produto Deletado!");

				buscaId.setText("");
				clearProduto();
				repaint();
			}
			
		}
		
		if(e.getSource().equals(submitFuncionario) && submitFuncionario.getText().equals("Atualizar")) {
			
			FuncionarioDAO funcionarioObject = new FuncionarioDAO();
			Funcionario funcionario = new Funcionario();
			funcionario = funcionarioObject.select(Integer.parseInt(buscaId.getText()));
			
			funcionario.setNomeFuncionario(nomeFuncionario.getText());
			funcionario.setCpfFuncionario(cpfField.getText());
			funcionario.setRgFuncionario(rgField.getText());
			
			
			if(genero1.isSelected()) {
				sexo="m";
			}
			else if (genero2.isSelected()){
				sexo="f";
			}
			
			funcionario.setGeneroFuncionario(sexo);
			
			funcionarioObject.update(funcionario);
			
			JOptionPane.showMessageDialog(null,"Dados de " +funcionario.getNomeFuncionario() +" atualizados com sucesso");
			clearFuncionario();
		}
		
		if(e.getSource().equals(deleteFuncionario)) {
			
			FuncionarioDAO funcionarioObject = new FuncionarioDAO();
			Funcionario funcionario = new Funcionario();
			funcionario = funcionarioObject.select(Integer.parseInt(buscaId.getText()));
			
			
			int verify  = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o funcionário: "+funcionario.getNomeFuncionario());
			
			
			if( verify == 0) {
				funcionarioObject.delete(funcionario);
				
				JOptionPane.showMessageDialog(null, "Funcionário Deletado!");

				buscaId.setText("");
				clearFuncionario();
				repaint();
			}
			
			clearFuncionario();
			
		}
		
		
		
		if(e.getSource().equals(queryIcon)) {
			removeAll();
			add(queryContainer);
			cadastro.setText("Consultar:");
			
			
			
			
		}
		
		
		
		if(e.getSource().equals(refresh)) {
		
			queryContainer.remove(barraRolagem);
			repaint();
		
			optionsBox.setRenderer(new MyComboBoxRenderer("Selecione..."));
			optionsBox.setEnabled(true);
			optionsBox.setSelectedIndex(-1);
			
		}
		
		
		
		if(e.getSource().equals(submitFornecedor) && submitFornecedor.getText().equals("Cadastrar")) {
			
			Fornecedor f = new Fornecedor();
			FornecedorDAO fornecedorObject1 = new FornecedorDAO();
			
			f.setNomeFornecedor(nomeFornecedor.getText());
			fornecedorObject1.insert(f);
			
			fornReAdd();
			JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso");
			nomeFornecedor.setText("");
			
			repaint();

		}
		
		
		if(e.getSource().equals(submitFabricante) && submitFabricante.getText().equals("Cadastrar")) {
			
			Fabricante f = new Fabricante();
			FabricanteDAO fabricanteObject1 = new FabricanteDAO();
			
			f.setNomeFabricante(nomeFabricante.getText());
			fabricanteObject1.insert(f);
			
			fabReAdd();
			JOptionPane.showMessageDialog(null, "Fabricante cadastrado com sucesso");
			nomeFabricante.setText("");
			
			repaint();

		}
		
		if(e.getSource().equals(submitProduto) && submitProduto.getText().equals("Cadastrar")) {
			prodReAdd();
			Produto p = new Produto();
			ProdutoDAO produtoObject = new ProdutoDAO();
			String a;
			a = precoProduto.getText();
			a = a.replace("R$", "");
			p.setNomeProduto(nomeProduto.getText());
			p.setPrecoProduto(Double.parseDouble(a));
			p.setIdFabricante(Integer.parseInt(fabricanteField.getText()));
			p.setIdFornecedor(Integer.parseInt(fornecedorField.getText()));
			p.setTipoProduto(typesOptions[typesBox.getSelectedIndex()]);
			produtoObject.insert(p);
			
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
			
			clearProduto();
			
			repaint();

		}
		
		
		if(e.getSource().equals(submitFuncionario) && submitFuncionario.getText().equals("Cadastrar")) {
			funcReAdd();
			Funcionario f = new Funcionario();
			FuncionarioDAO funcionarioObject = new FuncionarioDAO();
			
			
			
			f.setCpfFuncionario(cpfField.getText());
			f.setNomeFuncionario(nomeFuncionario.getText());
			
			if(genero1.isSelected()) {
				sexo="m";
			}else if(genero2.isSelected()) {
				sexo="f";
			}
			
			f.setGeneroFuncionario(sexo);
			f.setRgFuncionario(rgField.getText());
			
			funcionarioObject.insert(f);
			
			JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso");
			
			clearFuncionario();
		}
		
	}

		
	
		
	
	
	
	


	@Override
	public void onCEPSuccess(ViaCEP cep) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCEPError(String cep) {
		// TODO Auto-generated method stub
	
	}
	
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
	
}
