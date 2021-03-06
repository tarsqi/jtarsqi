package org.timeml.tarsqi.components;

import org.timeml.tarsqi.io.ThymeFile;
import org.timeml.tarsqi.tools.stanford.StanfordNLP;
import edu.stanford.nlp.pipeline.Annotation;

import java.util.List;
import org.timeml.tarsqi.core.TarsqiDocument;
import org.timeml.tarsqi.io.TarsqiReader;
import org.timeml.tarsqi.tools.stanford.StanfordResult;


public class Coref {

	static final String THYME_CORPUS =  "/DATA/resources/corpora/thyme/THYME-corpus-processed/";
	static final String INPUT = THYME_CORPUS + "train/ttk-output/thyme/ID009_clinic_025";

	static final String THYME = "/DATA/resources/corpora/thyme/THYME-corpus";
	static List<ThymeFile> data;

	public static void main(String[] args) {
		
		TarsqiDocument doc = new TarsqiReader().readTarsqiFile(INPUT);
		doc.prettyPrint();
		runStanford(doc.text);
		
		/*
		System.out.println();
		int start, end;
		data = ThymeReader.load(THYME + "/TextData/dev");
		System.out.println(data);
		System.out.println(data.get(0).file);
		start = 410; end = 521;
		System.out.println(data.get(0).text.substring(start, end));
		//runStanford(data.get(0).text.substring(start, end));
		*/
	}

	public static void runStanford(String input) {
		System.out.println(input);
		StanfordNLP stan = new StanfordNLP("depparse");
		StanfordResult result = stan.processString(input);
		stan.show(result.annotation);
		//Annotation doc = stan.document;
		//System.out.println(doc);
	}
	
}
