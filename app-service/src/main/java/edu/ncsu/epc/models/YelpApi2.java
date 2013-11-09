
package edu.ncsu.epc.models;

import org.scribe.model.Token;
import org.scribe.builder.api.DefaultApi10a;

/**
 * Service provider for "2-legged" OAuth10a for Yelp API (version 2).
 */
public class YelpApi2 extends DefaultApi10a {

    public String getAccessTokenEndpoint() {
    return null;
  }

    public String getAuthorizationUrl(Token arg0) {
    return null;
  }

    public String getRequestTokenEndpoint() {
    return null;
  }

}