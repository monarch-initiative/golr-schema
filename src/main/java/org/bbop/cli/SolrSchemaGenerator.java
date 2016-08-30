package org.bbop.cli;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;
import org.bbop.schema.ConfigManager;
import org.bbop.schema.SolrSchemaXMLWriter;

public class SolrSchemaGenerator {

  private static final Logger LOGGER = Logger.getLogger(SolrSchemaGenerator.class);

  final private List<String> configPath;
  final private Optional<String> outputPath;

  public SolrSchemaGenerator(List<String> configPath, Optional<String> outputPath) {
    this.configPath = configPath;
    this.outputPath = outputPath;
  }

  public void generate() throws IOException {
    ConfigManager confManager = getConfig(configPath);

    // Get the XML from the dumper into a string.
    String configString = null;
    try {
      SolrSchemaXMLWriter ssxw = new SolrSchemaXMLWriter(confManager);
      configString = ssxw.schema();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }

    if (outputPath.isPresent()) {
      Path path = Paths.get(outputPath.get());
      try (BufferedWriter writer = Files.newBufferedWriter(path)) {
        writer.write(configString);
      }
      LOGGER.info("Wrote into " + path);
    } else {
      LOGGER.info("No outputfile specified, printing to stdout");
      System.out.println(configString);
    }


  }

  private ConfigManager getConfig(List<String> configPath) {
    // Try and munge all of the configs together.
    ConfigManager configManger = new ConfigManager();
    for (String fsPath : configPath) {

      LOGGER.info("Trying config found at: " + fsPath);

      // Attempt to parse the given config file.
      try {
        configManger.add(fsPath);
        LOGGER.info("Using config found at: " + fsPath);
      } catch (FileNotFoundException e) {
        LOGGER.info("Failure with config file at: " + fsPath);
        e.printStackTrace();
      }
    }
    return configManger;
  }

}
