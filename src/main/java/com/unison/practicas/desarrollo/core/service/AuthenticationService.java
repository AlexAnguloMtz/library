package com.unison.practicas.desarrollo.core.service;

import com.unison.practicas.desarrollo.core.dto.Auth;
import com.unison.practicas.desarrollo.core.model.SessionData;
import com.unison.practicas.desarrollo.core.repository.SessionRepository;

import java.util.Optional;

public class AuthenticationService {

    private final SessionRepository sessionRepository;

    public AuthenticationService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Auth getAuthentication(String sessionId) {
        Optional<SessionData> sessionOptional = sessionRepository.getSessionById(sessionId);
        if (sessionOptional.isEmpty()) {
            throw new RuntimeException("Invalid session");
        }

        SessionData sessionData = sessionOptional.get();

        return toAuthentication(sessionData);
    }

    private Auth toAuthentication(SessionData sessionData) {
        return null;
    }

}