package com.hallanmedeiros.exp2.data;

import java.util.ArrayList;
import java.util.List;

import machineLearning.Algorithm;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class IrisAlgorithm extends Algorithm {

	private Instances instances;
	private AbstractClassifier classifier;

	@Override
	public boolean fit(double[][] features, double[] labels) {
		
		List<String> tipos = new ArrayList<>();
		tipos.add("Iris-setosa");
		tipos.add("Iris-versicolor");
		tipos.add("Iris-virginica");
		
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute("w"));
		attributes.add(new Attribute("x"));
		attributes.add(new Attribute("y"));
		attributes.add(new Attribute("z"));
		attributes.add(new Attribute("class", tipos));
		
		this.instances = new Instances("dataset", attributes, 5);
		this.instances.setClassIndex(4);
		
		for (int i = 0; i < features.length-1; i++) {
				Instance novo = new DenseInstance(5);
				novo.setDataset(this.instances);
				
		        novo.setValue(0, features[i][0]);
		        novo.setValue(1, features[i][1]);
		        novo.setValue(2, features[i][2]);
		        novo.setValue(3, features[i][3]);
		        novo.setValue(4, labels[i] );
		        
		        this.instances.add(novo);
		}		
		
		this.classifier = new NaiveBayes();
		try {
			this.classifier.buildClassifier(this.instances);
		} catch (Exception e) {
			throw new RuntimeException(e);
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
