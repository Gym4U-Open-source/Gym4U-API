package com.acme.gym4u.security.domain.service.communication;

import com.acme.gym4u.security.resource.UserResource;
import com.acme.gym4u.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }
    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}
