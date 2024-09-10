package br.com.empiricus.EmpiricusADM.Service.Exceptions;

public class MismatchId extends RuntimeException {
    public MismatchId(String message) {
        super(message);
    }
}
