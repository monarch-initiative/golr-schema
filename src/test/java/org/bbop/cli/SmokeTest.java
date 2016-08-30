package org.bbop.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class SmokeTest {

  private final String obanConfig = this.getClass().getResource("/oban-config.yaml").getFile();
  private final String ontologyConfig = this.getClass().getResource("/ont-config.yaml").getFile();

  @Test
  public void generateSchemaForOban() throws IOException {
    List<String> conf = new ArrayList<String>();
    conf.add(obanConfig);
    SolrSchemaGenerator solrSchemaGenerator = new SolrSchemaGenerator(conf, Optional.empty());
    solrSchemaGenerator.generate();
  }

  @Test
  public void generateSchemaForOntology() throws IOException {
    List<String> conf = new ArrayList<String>();
    conf.add(ontologyConfig);
    SolrSchemaGenerator solrSchemaGenerator = new SolrSchemaGenerator(conf, Optional.empty());
    solrSchemaGenerator.generate();
  }
}
