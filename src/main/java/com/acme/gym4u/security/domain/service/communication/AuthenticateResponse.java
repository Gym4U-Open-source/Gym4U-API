package com.acme.gym4u.security.domain.service.communication;

import com.acme.gym4u.security.resource.AuthenticateResource;
import com.acme.gym4u.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }
    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}
