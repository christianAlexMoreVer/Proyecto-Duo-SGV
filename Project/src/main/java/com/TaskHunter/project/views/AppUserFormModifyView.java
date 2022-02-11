package com.TaskHunter.project.views;

import java.io.IOException;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.mutation.Mutation;
import com.TaskHunter.project.query.Query;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class AppUserFormModifyView extends FormLayout{
	
	private EmailField email = new EmailField("Email");
	private PasswordField password = new PasswordField("Password");
	private TextField userName = new TextField("User name");

	private Button save = new Button("Update");
	private Button delete = new Button("Delete");
	
	private Binder<AppUser> binder = new Binder<>(AppUser.class);
	
	private AppUserControlView mainView;
	
	
	
	public AppUserFormModifyView(AppUserControlView mainView, Mutation service, Query queryService) {
		
		this.mainView = mainView;
	 

	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	    add(email, password, userName, buttons);
	    
	    binder.bindInstanceFields(this);
	    save.addClickListener(event -> update(service, queryService));
	    delete.addClickListener(event -> delete(service, queryService));
	}

	public void setAppUser(AppUser appUser) {
	    binder.setBean(appUser);

	    if (appUser == null) {
	        setVisible(false);
	    } else {
	        setVisible(true);
	        email.focus();
	    }
	}
	
	private void update(Mutation service, Query queryService) {
		AppUser user = binder.getBean();
	 
		service.UpdateAppUserWithOutImage(user.getIdAppUser() ,email.getValue(), password.getValue(), userName.getValue());
	
	    mainView.updateList(queryService);
	    setAppUser(null);
	}
	
	private void delete(Mutation service, Query queryService) {
	    AppUser user = binder.getBean();
	    service.DeleteAppUser(user.getIdAppUser());
	    mainView.updateList(queryService);
	    setAppUser(null);
	}
}
