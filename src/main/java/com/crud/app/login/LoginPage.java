package com.crud.app.login;


import com.crud.app.page.HomePage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginPage extends WebPage{
    /**
     *
     */
    private static final long serialVersionUID = 5946349607750478191L;

    private String username;
    private String password;
    @Override
    protected void onInitialize() {
        super.onInitialize();
        StatelessForm form = new StatelessForm("loginForm") {
            @Override
            protected void onSubmit() {
                boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
                // if authentication succeeds redirect user to the requested page
                if (authResult){
                    setResponsePage(HomePage.class);
                }else{
                    error ("Wrong credentials!");
                }
            }
        };
        form.setDefaultModel(new CompoundPropertyModel(this));
        add(new FeedbackPanel("feedback"));
        form.add(new TextField("username").setRequired(true));
        form.add(new PasswordTextField("password").setRequired(true));
        add(form);
    }
}
