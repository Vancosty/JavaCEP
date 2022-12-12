package cep;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP - Ver 1.0");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 54, 162, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("WEB service: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 95, 85, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblWebService = new JLabel("https://www.republicavirtual.com.br");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.republicavirtual.com.br/");
			}
		});
		lblWebService.setForeground(new Color(0, 51, 255));
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWebService.setBounds(100, 91, 237, 23);
		getContentPane().add(lblWebService);
		
		JButton btfGit = new JButton("");
		btfGit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/Vancosty?tab=repositories");
			}
		});
		btfGit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btfGit.setBorderPainted(false);
		btfGit.setToolTipText("Projeto");
		btfGit.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		btfGit.setBackground(SystemColor.control);
		btfGit.setBounds(35, 177, 48, 48);
		getContentPane().add(btfGit);

	}// fim do construtor
	
	private void link (String site) {
	
		Desktop desktop = Desktop.getDesktop();
		try {
		   URI uri =  new URI(site);
		   desktop.browse(uri);
		}catch ( Exception e) {
			System.out.println(e);
	
	}
	}
}
