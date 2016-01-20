/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.annotation.compiletime.process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("impatient.java.compiletime.annotation.Assignment")
public class AssignmentProcess extends AbstractProcessor {

    private TypeElement assignmentElement;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        Elements elementUtils = processingEnv.getElementUtils();
        assignmentElement = elementUtils.getTypeElement("impatient.java.compiletime.annotation.Assignment");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(assignmentElement);

        boolean isElementProcessed = false;
        for (Element element : elements) {
            processAssignment(element);
            isElementProcessed = true;
        }
        return isElementProcessed;
    }

    private void processAssignment(Element element) {

        List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
        for (AnnotationMirror mirror : annotations) {
            if (mirror.getAnnotationType().asElement().equals(assignmentElement)) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> values = mirror.getElementValues();
                System.out.println("Print Assignee : " + getAnnotationValue(values, "assignee")); 

            }
        }

    }

    private Object getAnnotationValue(Map<? extends ExecutableElement, ? extends AnnotationValue> values,
            String annotationFiledName) {

        for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : values.entrySet()) {
            if (entry.getKey().getSimpleName().contentEquals(annotationFiledName)) {
                return entry.getValue().getValue();
            }
        }
        return null;
    }
}
