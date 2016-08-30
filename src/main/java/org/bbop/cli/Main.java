package org.bbop.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

  public static void main(String[] args) {
    final String configShortOpt = "c";
    final String outputShortOpt = "o";

    // create the command line parser
    CommandLineParser parser = new DefaultParser();

    // create the Options
    Options options = new Options();
    options.addOption(Option.builder(configShortOpt).required().hasArg()
        .argName("SOLR-CONFIG.yaml").optionalArg(false).longOpt("solr-config")
        .desc("Required path to the Solr YAML config file.").hasArgs().build());
    options.addOption(outputShortOpt, "output", true,
        "Output path to the Solr schema.xml. Will print to stdout if empty.");

    Optional<String> outputPath = Optional.empty();
    try {
      // parse the command line arguments
      CommandLine line = parser.parse(options, args);
      List<String> configs = Arrays.asList(line.getOptionValue(configShortOpt).trim().split(" "));
      if (line.hasOption(outputShortOpt)) {
        outputPath = Optional.of(line.getOptionValue(outputShortOpt).trim());
      }

      SolrSchemaGenerator solrSchemaGenerator = new SolrSchemaGenerator(configs, outputPath);
      solrSchemaGenerator.generate();

    } catch (ParseException exp) {
      System.out.println("Unexpected exception:" + exp.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("ant", options);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Couldn't write into " + outputPath);
    }
  }

}
