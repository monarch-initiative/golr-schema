package org.bbop.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class SmokeTest {

  private final String obanConfig = this.getClass().getResource("/oban-config.yaml").getFile();
  private final String ontologyConfig = this.getClass().getResource("/ont-config.yaml").getFile();
  private final String monarchSearchConfig = this.getClass()
      .getResource("/monarch-search-config.yaml").getFile();
  private final String invalidFolderConfig = this.getClass().getResource("/multiple_invalid")
      .getFile();
  private final String validFolderConfig = this.getClass().getResource("/multiple_valid").getFile();

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

  @Test
  public void generateSchemaForMonarchSearch() throws IOException {
    List<String> conf = new ArrayList<String>();
    conf.add(monarchSearchConfig);
    SolrSchemaGenerator solrSchemaGenerator = new SolrSchemaGenerator(conf, Optional.empty());
    solrSchemaGenerator.generate();
  }

  @Test(expected = IllegalStateException.class)
  public void throwExceptionOnInconsistentFields() throws IOException {
    List<String> conf = new ArrayList<String>();
    conf.add(invalidFolderConfig);
    SolrSchemaGenerator solrSchemaGenerator = new SolrSchemaGenerator(conf, Optional.empty());
    solrSchemaGenerator.generate();
  }

  @Test
  public void generateSchemaForFolder() throws IOException {
    List<String> conf = new ArrayList<String>();
    conf.add(validFolderConfig);
    SolrSchemaGenerator solrSchemaGenerator = new SolrSchemaGenerator(conf, Optional.empty());
    solrSchemaGenerator.generate();
  }
}
