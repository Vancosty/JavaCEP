package cep;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCEP;
	private JTextField txtendereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUF;
	private JLabel lblStatus;

	/**
	 * Inicia programa
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * cria o Frame.
	 */
	public Cep() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtCEP = new JTextField();
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCEP.setBounds(83, 30, 86, 18);
		contentPane.add(txtCEP);
		txtCEP.setColumns(10);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 30, 75, 17);
		contentPane.add(lblNewLabel);

		JLabel txtEndereco = new JLabel("Endere\u00E7o");
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEndereco.setBounds(10, 70, 133, 18);
		contentPane.add(txtEndereco);

		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 110, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 151, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("UF");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(331, 151, 46, 14);
		contentPane.add(lblNewLabel_4);

		txtendereco = new JTextField();
		txtendereco.setBounds(83, 72, 235, 18);
		contentPane.add(txtendereco);
		txtendereco.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBairro.setBounds(83, 110, 235, 18);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCidade.setBounds(83, 152, 238, 18);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		cboUF = new JComboBox();
		cboUF.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboUF.setBounds(366, 149, 46, 22);
		contentPane.add(cboUF);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(10, 187, 122, 24);
		contentPane.add(btnLimpar);

		JButton btnCEP = new JButton("Buscar");
		btnCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCEP.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "informe o CEP");
					txtCEP.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnCEP.setBounds(206, 28, 89, 23);
		contentPane.add(btnCEP);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);

			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/about.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(335, 11, 48, 48);
		contentPane.add(btnSobre);

		// atxy2k txtCEP
		RestrictedTextField validar = new RestrictedTextField(txtCEP);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(176, 28, 20, 20);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	}// fim

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCEP.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUF.setSelectedItem(element.getText());
				}

				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = (element.getText());
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = (element.getText());
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if(resultado.equals("1")) {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
						
					}else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
						txtCEP.setText(null);
					}
				}
			}
			txtendereco.setText(tipoLogradouro + " " + logradouro);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void limpar() {
		txtCEP.setText(null);
		txtendereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUF.setSelectedItem(null);
		txtCEP.requestFocus();
		lblStatus.setIcon(null);
		
	}
}
