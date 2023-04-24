package com.charity.charityapi.handler;

import java.util.Date;
import lombok.Builder;
import lombok.Value;

/**
 * Class for response object of any Exception in our app.
 */
@Value
@Builder
public class ErrorMessage {
  int status;
  Date date;
  String description;
  String url;
}
