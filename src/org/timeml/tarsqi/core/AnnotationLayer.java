package org.timeml.tarsqi.core;

import java.util.ArrayList;
import java.util.List;
import org.timeml.tarsqi.core.annotations.Annotation;
import org.timeml.tarsqi.core.annotations.TreeAnnotation;

/**
 * Class that stores annotations and that allows you to group annotations. An
 * annotation can be for a single component, for example the sectioner will
 * write to its own layer. There are also two layers that correspond to the
 * classical layers used by Tarsqi: TARSQI_TAGS and SOURCE_TAGS. Both contain
 * a variety of tags, which for the TARSQI_TAGS layer includes all tags from
 * Tarsqi components (docelement, s, lex, TIMEX3, EVENT, ALINK, SLINK and
 * TLINK).
 *
 * A layer can be of two types: "flat" and "tree". With the former the layer has
 * a flat list of annotations in its annotations field, with the latter we have
 * one instance of TreeAnnotation in the annotations field and that one instance
 * is the top of a tree.
 */
public class AnnotationLayer {

	public static final String SOURCE_TAGS = "SOURCE_TAGS";
	public static final String TARSQI_TAGS = "TARSQI_TAGS";
	public static final String STANFORD_DEPENDENCIES = "STANFORD_DEPENDENCIES";

	/**
	 * Name of the annotation layer. Could be the name of the component that
	 * created the annotation or one of TARSQI_TAGS and SOURCE_TAGS.
	 */
	public String name;

	/** Type of the annotation layer. Value is either "flat" or "tree". */
	public String type;

	/** List of annotations. Ordering does not matter. */
	public List<Annotation> annotations;

	public AnnotationLayer(String name) {
		this.name = name;
		this.type = "flat";
		this.annotations = new ArrayList<>();
	}

	public AnnotationLayer(String name, String type) {
		this.name = name;
		this.type = type;
		this.annotations = new ArrayList<>();
	}

	public List<Annotation> getAnnotations() {
		return this.annotations;
	}

	public int size() {
		if (this.type.equals("tree")) {
			return this.annotations.get(0).treeSize();
		} else {
			return this.annotations.size();
		}
	}

	public void addAnnotation(Annotation annotation) {
		this.annotations.add(annotation);
	}

	@Override
	public String toString() {
		return String.format(
				"<AnnotationLayer %s with %d tags>",
				this.name, this.size());
	}

	public void prettyPrint() {
		System.out.println(String.format("<AnnotationLayer %s>", this.name));
		if (this.type.equals("flat")) {
			for (Annotation annotation : this.annotations)
				System.out.println("   " + annotation);
		} else if (this.type.equals("tree")) {
			TreeAnnotation top = (TreeAnnotation) this.annotations.get(0);
			top.prettyPrint();
		}
	}
}
