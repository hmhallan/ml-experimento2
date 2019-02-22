package formsManagement;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.hallanmedeiros.exp2.data.LabelsIris;
import com.hallanmedeiros.exp2.view.IrisQuestionPanel;

import machineLearning.Application;
import machineLearning.Classify;

public class MainForm extends JFrame{

	Panel mainPanel;
	Panel questionPanelsPane;
	JTextArea intructions;
	Label title;
	
	int numberOfQuestionForms;
	int numberOfQuestions;
	double[] dataToPredict;
	
	Application application;
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String args[]) {
		new MainForm();
	}
	
	public MainForm () {
		configureCloseAction();
		defineDataInfo();
		setInstrucionsPanelComponents();
		setQuestionPanelsComponents();
		checkNumberOfPages();
		setFormInfo();	
		runApplication();		
	}

	private void configureCloseAction() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		       System.exit(0);
		    }
		});
	}
		
	private void defineDataInfo() {
		this.setNumberOfQuestionForms(1);
		this.setNumberOfQuestions(4);
		dataToPredict = new double[this.getNumberOfQuestions()];
	}

	private void setFormInfo() {
		this.setTitle("Iris - Indentificar flor");
		this.setInstructions("Preencha os campos a seguir. Use somente números, com separador ponto (.) para casas decimais");
	}

	private void runApplication() {
		this.application = new Application(this.getNumberOfQuestions());		
		this.application.setNumberOfQuestionForms(this.getNumberOfQuestionForms());
	}
   
	public void setApplication(Application application) {
		this.application = application;
	}

	public int getNumberOfQuestionForms() {
		return numberOfQuestionForms;
	}

	public void setNumberOfQuestionForms(int numberOfQuestionForms) {
		this.numberOfQuestionForms = numberOfQuestionForms;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	protected void setInstrucionsPanelComponents() {  // TODO - User can create another instruction panel
		this.setVisible(true);
		this.setBounds(100, 100, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		mainPanel = new Panel();
		
		mainPanel.setBounds(10, 10, 680, 590);
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		
		title = new Label();
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 22));
		title.setBounds(182, 35, 251, 66);
		mainPanel.add(title);
		
		intructions = new JTextArea();
		intructions.setLineWrap(true);
		intructions.setFont(new Font("Tahoma", Font.PLAIN, 11));
		intructions.setEditable(false);
		intructions.setBounds(10, 153, 594, 206);
		intructions.setOpaque(false);  
	    mainPanel.add(intructions);
		
		Button button = new Button("Iniciar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTest();
			}
		});
		button.setBounds(273, 500, 70, 22);
		mainPanel.add(button);
		this.repaint();
	}
	
	private void setQuestionPanelsComponents() { // TODO: This can be edited
		questionPanelsPane = new Panel();
		questionPanelsPane.setVisible(false);
		questionPanelsPane.setBounds(10, 10, 640, 590);
		getContentPane().add(questionPanelsPane);
		questionPanelsPane.setLayout(new CardLayout(0, 0));

		addQuestionPanels();	
	}	

	private void addQuestionPanels() {
		IrisQuestionPanel iqp = new IrisQuestionPanel(this);
		iqp.setData(dataToPredict);
		iqp.setIsFirstPanel(true);
		iqp.setIndex(0);
		questionPanelsPane.add("Questions1", iqp);
		iqp.setQuestionLabel(1, "Largura da Sépala");
		iqp.setQuestionLabel(2, "Altura da Sépala");
		iqp.setQuestionLabel(3, "Largura da Pétala");
		iqp.setQuestionLabel(4, "Altura da Pétala");
	}
	
	private void checkNumberOfPages() {
		if (this.numberOfQuestionForms != questionPanelsPane.getComponentCount()) {
			JOptionPane.showMessageDialog(this, "The number of pages configured doesnt match the configured",
				    "Atenção", JOptionPane.ERROR_MESSAGE);
			throw new UnsupportedOperationException("The number of pages configured doesnt match the configured");
		}
	}
	
	protected void startTest() {
		if (application.getOperationMode() instanceof  machineLearning.Learn ) {
			if (application.train()) {
				Classify classify = new Classify();
				classify.setClassifier((application.getOperationMode().getClassifier()));
				application.setOperationMode(classify);
			}
		} 

	    mainPanel.setVisible(false);
	    questionPanelsPane.setVisible(true);				
	}
		
	public void setInstructions(String text) {
		intructions.setText(text);		
	}
	
	public void setTitle(String text) {
		title.setText(text);		
	}
	
	public void finish() {
		double prediction = application.classify(dataToPredict);
		setFinishMessage(prediction);
	}

	private void setFinishMessage(double prediction) {
		String message = "indefinido";
		if (prediction >= 0) {
			message = LabelsIris.values()[(int)prediction].name();
		}
		JOptionPane.showMessageDialog(this, "A flor é: " + message,
				"Resultado!", JOptionPane.INFORMATION_MESSAGE);
	}
		
}