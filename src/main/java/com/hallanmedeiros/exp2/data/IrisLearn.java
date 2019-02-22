package com.hallanmedeiros.exp2.data;

import machineLearning.Learn;

public class IrisLearn extends Learn {

	@Override
	protected void addAlgorithms() {		
		this.classifier.getAlgorithms().add(new IrisAlgorithm());
	}

}