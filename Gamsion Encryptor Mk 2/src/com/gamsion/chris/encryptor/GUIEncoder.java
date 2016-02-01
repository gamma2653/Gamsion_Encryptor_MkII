package com.gamsion.chris.encryptor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUIEncoder extends JFrame {
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension defaultsize = toolkit.getScreenSize();
	private static final long serialVersionUID = 1L;
	JLabel label = new JLabel();
	JButton encodeButton = new JButton("Encode");
	JButton decodeButton = new JButton("Decode");
	JButton copyButton = new JButton("Copy To Clipboard");
	JButton pasteButton = new JButton("Paste from Clipboard");
	JButton clearButton = new JButton("Clear");
	JButton encryptButton = new JButton("Encrypt");
	JButton decryptButton = new JButton("Decrypt");
	JButton readmeButton = new JButton("Open Readme");
	JTextField text = new JTextField(defaultsize.width / 27);
	JTextField password = new JTextField(defaultsize.width / 64);
	InputStream inputStream = getClass().getClassLoader()
			.getResourceAsStream("com/gamsion/chris/encryptor/docs/Readme.txt");
	Scanner s = new Scanner(inputStream).useDelimiter("\\A");
	String message =  s.hasNext() ? s.next() : "";
	private class handleEncode implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			text.setText(Encrypt.encode(text.getText()));
		}
	}

	private class handleDecode implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			text.setText(Encrypt.decode(text.getText()));
		}
	}

	private class handleCopyToClipboard implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			text.selectAll();
			text.copy();
		}
	}

	private class handlePasteFromClipboard implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			text.selectAll();
			text.paste();
		}
	}

	private class handleClearClipboard implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			text.selectAll();
			text.setText("");
		}
	}

	private class handleEncrypt implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			text.setText(Encrypt.encrypt(text.getText(), password.getText()));
		}
	}

	private class handleDecrypt implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			text.setText(Encrypt.decrypt(text.getText(), password.getText()));
		}
	}

	private class handleOpenReadme implements ActionListener {
		public void actionPerformed(ActionEvent event) {
		    
		    JOptionPane.showMessageDialog(GUIEncoder.this, message);
		    
		}
	}
	

	public GUIEncoder(String name) {
		setTitle(name);

		handleEncode encode = new handleEncode();
		handleDecode decode = new handleDecode();
		handleCopyToClipboard copyclip = new handleCopyToClipboard();
		handlePasteFromClipboard pasteclip = new handlePasteFromClipboard();
		handleClearClipboard clear = new handleClearClipboard();
		handleEncrypt encrypt = new handleEncrypt();
		handleDecrypt decrypt = new handleDecrypt();
		handleOpenReadme readme = new handleOpenReadme();
		encodeButton.addActionListener(encode);
		decodeButton.addActionListener(decode);
		copyButton.addActionListener(copyclip);
		pasteButton.addActionListener(pasteclip);
		clearButton.addActionListener(clear);
		encryptButton.addActionListener(encrypt);
		decryptButton.addActionListener(decrypt);
		readmeButton.addActionListener(readme);
		add(text);
		add(encodeButton);
		add(decodeButton);
		add(copyButton);
		add(pasteButton);
		add(clearButton);
		add(password);
		add(encryptButton);
		add(decryptButton);
		add(readmeButton);
		password.setText("Password");
		setLayout(new FlowLayout());
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(defaultsize.width / 2 + 40,
				defaultsize.height / 4));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		JOptionPane.showMessageDialog(GUIEncoder.this, message);
	}

	public static void main(String[] args) {
		new GUIEncoder("Gamsion Encryptor Mk II");

	}
}
