package org.bbop.schema;

import java.util.ArrayList;
import java.util.Map;

public class GOlrField {

  // NOTE: required and searchable are slightly fudged here--they are bool as far as the YAML goes,
  // but they kind of end up strings at the Solr end of things as that's mostly how they go
  // in between.
  public String id;
  public String description;
  public String display_name;
  public String type;
  public String required;
  public String cardinality;
  public ArrayList<String> property;
  public Map<String, Object> property_config; // generic configuration map
  public String boost_weights;
  public String result_weights;
  public String filter_weights;
  public String searchable;
  public String indexed;
  // The processing steps to apply to that property--not yet used
  public ArrayList<String> transform;


  // Define the defaults for optional fields.
  public GOlrField() {
    required = "false";
    cardinality = "single";
    boost_weights = "";
    result_weights = "";
    filter_weights = "";
    searchable = "false";
    indexed = "true";

    // There are no default transformations to make.
    transform = new ArrayList<String>();
  }

  @Override
  public String toString() {
    return "id - " + this.id + " (schema)\n" + "description - " + this.description + "\n"
        + "display_name - " + this.display_name + "\n" + "type - " + this.type + " (schema)\n"
        + "required - " + this.required + " (schema)\n" + "property - " + this.property + "\n"
        + "cardinality - " + this.cardinality + " (schema)\n" + "property_config - "
        + this.property_config + "\n" + "boost_weights - " + this.boost_weights + "\n"
        + "result_weights - " + this.result_weights + "\n" + "filter_weights - "
        + this.filter_weights + "\n" + "searchable - " + this.searchable + " (schema)\n"
        + "indexed - " + this.indexed + " (schema)\n" + "transform - " + this.transform + "\n";
  }

  /*
   * Only the fields which are used to generate the schema.xml are checked
   */
  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    if (!(other instanceof GOlrField))
      return false;
    GOlrField otherGOlrField = (GOlrField) other;
    return this.id.equals(otherGOlrField.id) && this.type.equals(otherGOlrField.type)
        && this.required.equals(otherGOlrField.required)
        && this.cardinality.equals(otherGOlrField.cardinality)
        && this.searchable.equals(otherGOlrField.searchable)
        && this.indexed.equals(otherGOlrField.indexed);
  }
}
