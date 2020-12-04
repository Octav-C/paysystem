package com.paysys.participant.application.port.in;

public interface LogInUseCase {
    Boolean processLogOn(String email, String password);
}
