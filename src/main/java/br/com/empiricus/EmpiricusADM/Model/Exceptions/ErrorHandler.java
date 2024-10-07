package br.com.empiricus.EmpiricusADM.Model.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorHandler  {
   private String field;
   private String message;


}
