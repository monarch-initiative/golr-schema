package org.bbop.schema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class GOlrFieldTest {

  @Test
  public void twoSameGOlrFieldsMustBeEqual() {
    GOlrField g1 = new GOlrField();
    GOlrField g2 = new GOlrField();
    g1.id = "id";
    g2.id = "id";
    g1.type = "type";
    g2.type = "type";
    g1.property = new ArrayList<String>();
    g2.property = new ArrayList<String>();
    assertEquals(g1, g2);
  }

  @Test
  public void twoDifferentGolrFieldMustNotBeEqual() {
    GOlrField g1 = new GOlrField();
    GOlrField g2 = new GOlrField();
    g1.id = "id";
    g2.id = "id";
    g1.type = "type";
    g2.type = "type";
    g1.property = new ArrayList<String>();
    g2.property = new ArrayList<String>();
    g1.searchable = "true";
    g2.searchable = "false";
    assertNotEquals(g1, g2);
  }

}
