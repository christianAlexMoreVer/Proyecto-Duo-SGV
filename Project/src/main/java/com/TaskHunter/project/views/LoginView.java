package com.TaskHunter.project.views;

import java.util.Optional;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.services.AppUserServiceImpl;
import com.TaskHunter.project.query.Query;
import com.TaskHunter.project.utils.LocalStorage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.Objects;

@Route(value = "")
@PageTitle("Login")

public class LoginView extends VerticalLayout {

	static Boolean logged;

	private final LoginForm login = new LoginForm(); 

	public LoginView(LocalStorage localStorage, Query service){
		
	
		
		LoginOverlay loginOverlay = new LoginOverlay();
		loginOverlay.setTitle("Hunt The Game");
		loginOverlay.setDescription("Admin panel");
		loginOverlay.setI18n(createLoginI18n());
		
		loginOverlay.setForgotPasswordButtonVisible(false);
		
		
		addClassName("login-view");
		
		setSizeFull(); 
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		
	
		
	
		loginOverlay.addLoginListener( event -> {
			
			

			Optional<AppUser> us = service.getLogin(event.getUsername(), event.getPassword());
			
			
			if(Objects.isNull(us)) {
				loginOverlay.setError(true);
			}else {
				if(us.get().getRol() == 1) {
					loginOverlay.setError(false);
					
					localStorage.setLogin("true");
					// loginOverlay.getUI().ifPresent(ui -> ui.navigate("AppUserControl"));
					UI.getCurrent().navigate("AppUserControl");
					
				}else {
					loginOverlay.setError(true);
				}
				
			}
			
			
			
		});

		add(loginOverlay);
		loginOverlay.setOpened(true);
		
				
	}
	
	private LoginI18n createLoginI18n(){
		LoginI18n i18n = LoginI18n.createDefault();
		
		
	    i18n.getForm().setUsername("Email"); 
	    i18n.getErrorMessage().setTitle("Incorrect email or password");
	    
	    return i18n;
	}

	public static Boolean getLogged() {
		return logged;
	}

	public static void setLogged(Boolean logged) {
		LoginView.logged = logged;
	}
	
	
	
}

