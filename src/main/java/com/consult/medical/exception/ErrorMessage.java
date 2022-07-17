package com.consult.medical.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Data
public class ErrorMessage {
  private int statusCode;
  private Date timestamp;
  private String message;
  private String description;
}
