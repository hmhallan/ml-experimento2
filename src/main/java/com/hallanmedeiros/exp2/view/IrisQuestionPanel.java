package com.hallanmedeiros.exp2.view;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import formsManagement.MainForm;
import formsManagement.QuestionPanel;

public class IrisQuestionPanel extends QuestionPanel {
	
	private static final long serialVersionUID = 1L;

	private JLabel txtrQuestion1;
	private JLabel txtrQuestion2;
	private JLabel txtrQuestion3;
	private JLabel txtrQuestion4;

	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private JTextField field4;
	
	public IrisQuestionPanel(MainForm mainForm) {
		this.mainForm = mainForm;
		
		txtrQuestion1 = new JLabel();
		txtrQuestion1.setOpaque(false);  
		txtrQuestion1.setBounds(10, 40, 620, 48);
		add(txtrQuestion1);
		
		this.field1 = new JTextField();
		this.field1.setBounds(10, 80, 200, 24);
		add(this.field1);
		
		txtrQuestion2 = new JLabel();
		txtrQuestion2.setOpaque(false);
		txtrQuestion2.setBounds(10, 120, 620, 48);
		add(txtrQuestion2);
		
		this.field2 = new JTextField();
		this.field2.setBounds(10, 160, 200, 24);
		add(this.field2);
		
		txtrQuestion3 = new JLabel();
		txtrQuestion3.setOpaque(false);
		txtrQuestion3.setBounds(10, 200, 620, 48);
		add(txtrQuestion3);
		
		this.field3 = new JTextField();
		this.field3.setBounds(10, 240, 200, 24);
		add(this.field3);
		
		txtrQuestion4 = new JLabel();
		txtrQuestion4.setOpaque(false);
		txtrQuestion4.setBounds(10, 280, 620, 48);
		add(txtrQuestion4);
		
		this.field4 = new JTextField();
		this.field4.setBounds(10, 320, 200, 24);
		add(this.field4);
	}
	
	public void setQuestionLabel(int index, String question) {
		switch (index) {
		case 1:
            txtrQuestion1.setText(question);
            break;
        case 2:
        	txtrQuestion2.setText(question);
            break;
        case 3:
        	txtrQuestion3.setText(question);
            break;
        case 4:
        	txtrQuestion4.setText(question);
            break;
		}
	}

	@Override
	protected boolean checkAnswerRules() {
		try {
			Double.parseDouble( this.field1.getText() );
			Double.parseDouble( this.field2.getText() );
			Double.parseDouble( this.field3.getText() );
			Double.parseDouble( this.field4.getText() );
			return true;
		}
		catch(NumberFormatException ex) {
			return false;
		}
	}

	@Override
	protected void collectData() {
		
		//usar this.index?
		//preencher o "this.data" (array)
		
		this.data[ 0 ] = Double.parseDouble( this.field1.getText() );
		this.data[ 1 ] = Double.parseDouble( this.field2.getText() );
		this.data[ 2 ] = Double.parseDouble( this.field3.getText() );
		this.data[ 3 ] = Double.parseDouble( this.field4.getText() );
		
	}
	
}