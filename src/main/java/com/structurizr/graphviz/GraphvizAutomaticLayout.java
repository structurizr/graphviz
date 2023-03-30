package com.structurizr.graphviz;

import com.structurizr.Workspace;
import com.structurizr.export.Diagram;
import com.structurizr.view.*;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;

/**
 * Applies the graphviz automatic layout to views in a Structurizr workspace.
 *
 * Note: this class assumes that the "dot" command is available.
 */
public class GraphvizAutomaticLayout {

    private static final String DOT_FILE_EXTENSION = ".dot";

    private final File path;

    private RankDirection rankDirection = RankDirection.TopBottom;
    private double rankSeparation = 1.0;
    private double nodeSeparation = 1.0;

    private int margin = 400;
    private boolean changePaperSize = true;

    private Locale locale = Locale.US;

    public GraphvizAutomaticLayout() {
        this(new File("."));
    }

    public GraphvizAutomaticLayout(File path) {
        this.path = path;
    }

    public void setRankDirection(RankDirection rankDirection) {
        this.rankDirection = rankDirection;
    }

    public void setRankSeparation(double rankSeparation) {
        this.rankSeparation = rankSeparation;
    }

    public void setNodeSeparation(double nodeSeparation) {
        this.nodeSeparation = nodeSeparation;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setChangePaperSize(boolean changePaperSize) {
        this.changePaperSize = changePaperSize;
    }

    /**
     * Sets the locale used when writing DOT files.
     *
     * @param locale        a Locale instance
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    private DOTExporter createDOTExporter() {
        DOTExporter exporter = new DOTExporter(rankDirection, rankSeparation, nodeSeparation);
        exporter.setLocale(locale);

        return exporter;
    }

    private void writeFile(Diagram diagram) throws Exception {
        File file = new File(path, diagram.getKey() + DOT_FILE_EXTENSION);
        BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
        writer.write(diagram.getDefinition());
        writer.flush();
        writer.close();
    }

    private SVGReader createSVGReader() {
        return new SVGReader(path, margin, changePaperSize);
    }

    private void runGraphviz(View view) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder().inheritIO();
        processBuilder.command("dot", new File(path, view.getKey() + DOT_FILE_EXTENSION).getAbsolutePath(), "-Tsvg", "-O");
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

    public void apply(CustomView view) throws Exception {
        Diagram diagram = createDOTExporter().export(view);
        writeFile(diagram);
        runGraphviz(view);
        createSVGReader().parseAndApplyLayout(view);
    }

    public void apply(SystemLandscapeView view) throws Exception {
        Diagram diagram = createDOTExporter().export(view);
        writeFile(diagram);
        runGraphviz(view);
        createSVGReader().parseAndApplyLayout(view);
    }

    public void apply(SystemContextView view) throws Exception {
        Diagram diagram = createDOTExporter().export(view);
        writeFile(diagram);
        runGraphviz(view);
        createSVGReader().parseAndApplyLayout(view);
    }

    public void apply(ContainerView view) throws Exception {
        Diagram diagram = createDOTExporter().export(view);
        writeFile(diagram);
        runGraphviz(view);
        createSVGReader().parseAndApplyLayout(view);
    }

    public void apply(ComponentView view) throws Exception {
        Diagram diagram = createDOTExporter().export(view);
        writeFile(diagram);
        runGraphviz(view);
        createSVGReader().parseAndApplyLayout(view);
    }

    public void apply(DynamicView view) throws Exception {
        Diagram diagram = createDOTExporter().export(view);
        writeFile(diagram);
        runGraphviz(view);
        createSVGReader().parseAndApplyLayout(view);
    }

    public void apply(DeploymentView view) throws Exception {
        Diagram diagram = createDOTExporter().export(view);
        writeFile(diagram);
        runGraphviz(view);
        createSVGReader().parseAndApplyLayout(view);
    }

    public void apply(Workspace workspace) throws Exception {
        for (CustomView view : workspace.getViews().getCustomViews()) {
            apply(view);
        }

        for (SystemLandscapeView view : workspace.getViews().getSystemLandscapeViews()) {
            apply(view);
        }

        for (SystemContextView view : workspace.getViews().getSystemContextViews()) {
            apply(view);
        }

        for (ContainerView view : workspace.getViews().getContainerViews()) {
            apply(view);
        }

        for (ComponentView view : workspace.getViews().getComponentViews()) {
            apply(view);
        }

        for (DynamicView view : workspace.getViews().getDynamicViews()) {
            apply(view);
        }

        for (DeploymentView view : workspace.getViews().getDeploymentViews()) {
            apply(view);
        }
    }

}