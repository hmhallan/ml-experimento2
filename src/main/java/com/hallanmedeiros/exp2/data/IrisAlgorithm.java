package com.hallanmedeiros.exp2.data;

import java.util.ArrayList;
import java.util.Arrays;

import machineLearning.Algorithm;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class IrisAlgorithm extends Algorithm {

	private Instances instances;
	private AbstractClassifier classifier;

	@Override
	public boolean fit(double[][] features, double[] labels) {

//		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
//		attributes.add(new Attribute("w"));
//		attributes.add(new Attribute("x"));
//		attributes.add(new Attribute("y"));
//		attributes.add(new Attribute("z"));
//		attributes.add(new Attribute("class", new FastVector<>(Attribute.NOMINAL)));
//		
//		this.instances = new Instances("dataset", attributes, 150);
//		this.instances.setClassIndex(4);
//		
//		for (int i = 0; i < features.length-1; i++) {
//				Instance novo = new DenseInstance(5);
//				novo.setDataset(this.instances);
//				
//				System.out.println(Arrays.toString(features[i]));
//				System.out.println(LabelsIris.values()[(int)labels[i]].name());
//				
//		        novo.setValue(0, features[i][0]);
//		        novo.setValue(1, features[i][1]);
//		        novo.setValue(2, features[i][2]);
//		        novo.setValue(3, features[i][3]);
//		        novo.setValue(4, labels[i] );
////		        novo.setValue(4, LabelsIris.values()[(int)labels[i]].name() );
//		        
//		        this.instances.add(novo);
//		}		
//		
//		this.classifier = new NaiveBayes();
//		try {
//			this.classifier.buildClassifier(this.instances);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		
		CSVLoader csv = new CSVLoader();
        try {
            csv.setSource(this.getClass().getResourceAsStream("/iris.csv"));
            this.instances = csv.getDataSet();

            //indice do atributo que é 'classe'
            this.instances.setClassIndex(4);

            this.classifier = new NaiveBayes();
            this.classifier.buildClassifier(this.instances);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
		
		
		return true;             
	}


	@Override
	public double predict(double[] features) {		
		
		Instance novo = new DenseInstance(5);
		novo.setDataset(this.instances);

        novo.setValue(0, features[0]);
        novo.setValue(1, features[1]);
        novo.setValue(2, features[2]);
        novo.setValue(3, features[3]);
		
        //classifica a flor q chegou
        try {
			return classifier.classifyInstance(novo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void save() {
		// unimplemented for this application		
	}

	@Override
	public void read() {
		// unimplemented for this application
	}	
}
