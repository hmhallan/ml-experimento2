package com.hallanmedeiros.exp2.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import machineLearning.DataDAO;

public class IrisDAO extends DataDAO {
	private String filePath;
	
	public IrisDAO() {
		this.filePath = "/iris.csv";
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public double[][] load() {
		String line = "";
        String cvsSplitBy = ",";        
        
        ArrayList<String[]> csvData = new ArrayList<String[]>();
        
        try (BufferedReader br = new BufferedReader(
        					new InputStreamReader(
        							this.getClass().getResourceAsStream(filePath)))) {

        	//pula primeira linha
        	line = br.readLine();
        	
        	while ((line = br.readLine()) != null) {             
                String[] dataSample = line.split(cvsSplitBy);
                csvData.add(dataSample);
            }
        	
        	features = new double[csvData.size()][csvData.get(0).length -1]; 
        	labels = new double[csvData.size()]; 
     	
        	for (int i = 0; i < csvData.size() - 1; i++) {
            	for (int j = 0; j < csvData.get(i).length - 1; j++) {            		
            		features[i][j] = Double.parseDouble(csvData.get(i)[j]); 	
            	}
            	labels[i] = Integer.parseInt(csvData.get(i)[csvData.get(i).length - 1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return features;
	}	
}