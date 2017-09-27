package com.crud.app.login;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
        import org.apache.wicket.authroles.authorization.strategies.role.Roles;
        import org.apache.wicket.request.Request;
public class BasicAuthenticationSession extends AuthenticatedWebSession {
    public BasicAuthenticationSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        //user is authenticated if both username and password are equal to 'test'
        return username.equals(password) && username.equals("test");
    }

    @Override
    public Roles getRoles() {
        return null;
    }
}