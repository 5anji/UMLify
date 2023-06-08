package com.interpretation;

import net.sourceforge.plantuml.SourceStringReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UMLbuilder {

    public static void drawDiagram (String request) throws IOException {

        SourceStringReader reader = new SourceStringReader(request);

        File output = new File("diagram.png");
        OutputStream png = new FileOutputStream(output);
        reader.generateImage(png);
        png.close();
        
        System.out.println("\nUML diagram generated successfully.");
    }

}
