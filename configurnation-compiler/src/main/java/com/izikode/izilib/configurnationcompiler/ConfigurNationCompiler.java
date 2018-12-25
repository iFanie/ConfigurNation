package com.izikode.izilib.configurnationcompiler;

import com.izikode.izilib.configurnationannotations.ConfigurMember;
import com.izikode.izilib.configurnationannotations.ConfigurNation;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

public class ConfigurNationCompiler extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new TreeSet<>(Arrays.asList(
                ConfigurNation.class.getCanonicalName(),
                ConfigurMember.class.getCanonicalName()
        ));
    }

    private Filer filer;
    private Messager messager;
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Collection<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ConfigurNation.class);

        info(String.format(Locale.ENGLISH, "Found %1$d elements", elements.size()));

        if (elements.size() < 1) {
            return false;
        }

        List<TypeElement> types = ElementFilter.typesIn(elements);

        info(String.format(Locale.ENGLISH, "Found %1$d types", types.size()));

        if (types.size() < 1) {
            return false;
        }

        boolean notConsumed = true;

        for (TypeElement type : types) {
            PackageElement packageElement = (PackageElement) type.getEnclosingElement();

            String classPackage = packageElement.getQualifiedName().toString();
            String className = type.getAnnotation(ConfigurNation.class).name();
            int prefsMode = type.getAnnotation(ConfigurNation.class).mode();

            info(String.format(Locale.ENGLISH, "Found %1$s.%2$s", classPackage, className));

            List<? extends Element> members = elementUtils.getAllMembers(type);

            info(String.format(Locale.ENGLISH, "Found %1$d members for %2$s", members.size(), className));

            List<Element> annotatedMembers = new ArrayList<>();

            for (Element member : members) {
                if (member.getAnnotation(ConfigurMember.class) != null) {
                    annotatedMembers.add(member);
                }
            }

            String sourceCode = getSource(classPackage, className, prefsMode, annotatedMembers);
            String fileName = classPackage + "." + className;

            info(String.format(Locale.ENGLISH, "Generating source for %1$s", fileName));

            notConsumed = !writeClass(type, fileName, sourceCode);
        }

        return notConsumed;
    }

    private static final String nl = "\r\n";
    private static final String tb = "\t";

    private String getSource(String classPackage, String className, Integer prefsMode, List<Element> classMembers) {
        StringBuilder fieldDeclarations = new StringBuilder();
        StringBuilder fieldInitializations = new StringBuilder();

        for (Element member : classMembers) {
            String memberName = member.getSimpleName().toString();
            String memberType = member.asType().toString().replaceAll("\\(\\)", "");

            if (memberType.equals("boolean")) {
                memberType = "java.lang.Boolean";
            } else if (memberType.equals("float")) {
                memberType = "java.lang.Float";
            } else if (memberType.equals("int")) {
                memberType = "java.lang.Integer";
            } else if (memberType.equals("long")) {
                memberType = "java.lang.Long";
            }

            info(String.format(Locale.ENGLISH, "Found %1$s.%2$s : %3$s", className, memberName, memberType));

            fieldDeclarations
                    .append(tb).append("private com.izikode.izilib.configurnation.Field<").append(memberType).append("> _field_").append(memberName).append(" = null;").append(nl)
                    .append(nl)
                    .append(tb).append("@android.support.annotation.NonNull").append(nl)
                    .append(tb).append("public com.izikode.izilib.configurnation.Field<").append(memberType).append("> ").append(memberName).append("() {").append(nl)
                    .append(tb).append(tb).append("return _field_").append(memberName).append(";").append(nl)
                    .append(tb).append("}").append(nl)
                    .append(nl);

            fieldInitializations
                    .append(tb).append(tb).append("_field_").append(memberName).append(" = new com.izikode.izilib.configurnation.Field<>(prefs, \"").append(memberName).append("\", ").append(memberType).append(".class);").append(nl);
        }

        return (new StringBuilder()
            .append("package ").append(classPackage).append(";").append(nl)
            .append(nl)
            .append("public class ").append(className).append(" {").append(nl)
            .append(nl)
            .append(tb).append("private android.content.Context context = null;").append(nl)
            .append(nl)
            .append(tb).append("private final String name;").append(nl)
            .append(tb).append("private final Integer mode;").append(nl)
            .append(nl)
            .append(tb).append("public ").append(className).append("(@android.support.annotation.NonNull android.content.Context context) {").append(nl)
            .append(tb).append(tb).append("this.context = context;").append(nl)
            .append(nl)
            .append(tb).append(tb).append("name = \"").append(className).append("\";").append(nl)
            .append(tb).append(tb).append("mode = ").append(prefsMode).append(";").append(nl)
            .append(nl)
            .append(tb).append(tb).append("init();").append(nl)
            .append(tb).append("}").append(nl)
            .append(nl)
            .append(tb).append("private android.content.SharedPreferences prefs = null;").append(nl)
            .append(nl)
            .append(fieldDeclarations)
            .append(nl)
            .append(tb).append("private void init() {").append(nl)
            .append(tb).append(tb).append("prefs = context.getSharedPreferences(name, mode);").append(nl)
            .append(nl)
            .append(fieldInitializations)
            .append(tb).append("}").append(nl)
            .append(nl)
            .append("}").append(nl)
        ).toString();
    }

    private boolean writeClass(TypeElement type, String fileName, String sourceCode) {
        try {

            JavaFileObject fileObject = filer.createSourceFile(fileName);
            Writer writer = fileObject.openWriter();

            writer.write(sourceCode);
            writer.close();

            return true;

        } catch (IOException exception) {

            error(exception, type);
            return false;

        }
    }

    private void info(String string) {
        messager.printMessage(Diagnostic.Kind.NOTE, string);
    }

    private void error(Exception exception, TypeElement type) {
        messager.printMessage(Diagnostic.Kind.ERROR, exception.toString(), type);
    }

}
