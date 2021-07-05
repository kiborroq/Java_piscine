package edu.school21.processors;

import com.google.auto.service.AutoService;
import edu.school21.annotations.HtmlForm;
import edu.school21.annotations.HtmlInput;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.naming.spi.DirectoryManager;
import javax.tools.Diagnostic;
import java.io.*;
import java.util.Set;
import java.util.StringJoiner;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("edu.school21.annotations.HtmlForm")
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {
    Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            if (elem.getKind() == ElementKind.CLASS) {
                HtmlForm htmlForm = elem.getAnnotation(HtmlForm.class);

                StringJoiner fileContent = new StringJoiner("\n");
                fileContent.add(getFormOpennedTag(htmlForm));
                for (Element e : elem.getEnclosedElements()) {
                    if (e.getKind() == ElementKind.FIELD && e.getAnnotation(HtmlInput.class) != null)
                        fileContent.add(getFormRow(e.getAnnotation(HtmlInput.class)));
                }
                fileContent.add(getFormClosedTag());
                try {
                    File file = new File(htmlForm.fileName());
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file);
                    fw.write(String.valueOf(fileContent));
                    fw.close();
                } catch (IOException e) {
                    messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                }
            }
            else
                messager.printMessage(Diagnostic.Kind.ERROR, elem.getKind() + " isn't expected.");
        }
        return false;
    }

    private CharSequence getFormClosedTag() {
        return "<input type = \"submit\" value = \"Send\">\n" +
                "</form>";
    }

    private String getFormOpennedTag(HtmlForm htmlForm) {
        return String.format("<form action = \"%s\" method = \"%s\">", htmlForm.action(), htmlForm.method());
    }

    private String getFormRow(HtmlInput htmlInput) {
        return String.format("<input type = \"%s\" name = \"%s\" placeholder = \"%s\">",
                htmlInput.type(), htmlInput.name(), htmlInput.placeholder());
    }
}
