package com.TaskHunter.project.views;


import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.validation.constraints.NotBlank;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.mutation.Mutation;
import com.TaskHunter.project.query.Query;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class AppUserFormView extends FormLayout {

	@NotBlank
	private EmailField email = new EmailField("Email");

	private PasswordField password = new PasswordField("Password (max 16 | min 8)");
	private TextField userName = new TextField("User name (max 16 | min 2)");
	private Span errorMessageField;

	private Button save = new Button("Save");
	private Button delete = new Button("Delete");

	private Binder<AppUser> binder = new Binder<>(AppUser.class);

	private AppUserControlView mainView;

	private Pattern pattern = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public AppUserFormView(AppUserControlView mainView, Mutation service, Query queryService) {

		this.mainView = mainView;

		errorMessageField = new Span();
		errorMessageField.setVisible(false);

		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		VerticalLayout error = new VerticalLayout(errorMessageField);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		email.getElement().setAttribute("name", "email");
		email.setErrorMessage("Please enter a valid email address");

		add(email, password, userName, buttons, error);

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
			if (appUser.getIdAppUser() == 0) {
				setRequiredIndicatorVisible(email, password, userName);
			}
		}
	}

	private void update(Mutation service, Query queryService) {
		AppUser user = binder.getBean();

		if (user.getIdAppUser() == 0) {

			errorMessageField.removeAll();
			errorMessageField.setVisible(false);

			int errores = 0;

			Matcher mather = pattern.matcher(email.getValue());

			if (email.isEmpty() || password.isEmpty() || userName.isEmpty()) {
				errores++;
				errorMessageField.add("Falta alg�n campo por rellenar | ");
			}

			if (mather.find() == true && (!queryService.getExistEmail(email.getValue()))) {
				System.out.println("El email ingresado es v�lido.");
			} else {
				errorMessageField.add("El email ingresado no es v�lido o ya existe | ");
				errores++;
			}

			if (password.getValue().length() < 8 || password.getValue().length() > 16) {
				errorMessageField.add("La contrase�a no tiene la longitud adecuada | ");
				errores++;
			}

			if (queryService.getExistUserName(userName.getValue())
					|| (userName.getValue().length() < 2 || userName.getValue().length() > 16)) {
				errorMessageField.add("El nombre de usuario ingresado no es v�lido o ya existe ");
				errores++;
			}

			if (errores == 0) {
				service.InsertAppUserWithOutImage(email.getValue(), password.getValue(), userName.getValue());
				errorMessageField.removeAll();
				mainView.updateList(queryService);
				setAppUser(null);
			}

			else {

				errorMessageField.setVisible(true);
			}

		} else {
			
			
			
			errorMessageField.removeAll();
			errorMessageField.setVisible(false);
			
			Matcher mather = pattern.matcher(email.getValue());

			int errores = 0;
			
			Optional<AppUser> beforeUser = queryService.getUser(user.getIdAppUser());
			
			if (email.isEmpty()) {
				errores--;
				
			}
			
			if (password.isEmpty()) {
				errores--;
				
			}
			
			if (userName.isEmpty()) {
			
				errores--;
				
			}

			if (mather.find() == true && (!queryService.getExistEmail(email.getValue()))) {
				System.out.println("El email ingresado es v�lido.");
			} else {
				if(user.getemail().equals(beforeUser.get().getemail())) {
					System.out.println("El email ingresado es v�lido.");
				}else {
					System.out.println(beforeUser.get().getemail());
					errorMessageField.add("El email ingresado no es v�lido | ");
					errores++;
				}
				
			}

			if (password.getValue().length() < 8 || password.getValue().length() > 16) {
				if(user.getPassword().equals(beforeUser.get().getPassword())) {
					System.out.println("La password esta ok.");
				}else {
					errorMessageField.add("La contrase�a no tiene la longitud adecuada | ");
					errores++;
				}
				
			}

			if ((userName.getValue().length() < 2 || userName.getValue().length() > 16) || queryService.getExistUserName(userName.getValue())) {
				
				if(user.getuserName().equals(beforeUser.get().getuserName())) {
					System.out.println("Username ok");
				}else {
					errorMessageField.add("El nombre de usuario ingresado no es v�lido o ya existe ");
					errores++;
				}
			}

						
			
			System.out.println(errores);
			
			if(errores <= 0) {
				try {
					service.UpdateAppUserWithOutImage(user.getIdAppUser(), email.getValue(), password.getValue(),
							userName.getValue());
					errorMessageField.removeAll();
					mainView.updateList(queryService);
					setAppUser(null);
				} catch (Exception e) {
					errorMessageField.setVisible(true);
				}
				

			}
			else {
				errorMessageField.setVisible(true);
			}

			
		}
	}

	private void delete(Mutation service, Query queryService) {
		AppUser user = binder.getBean();
		service.DeleteAppUser(user.getIdAppUser());
		mainView.updateList(queryService);
		setAppUser(null);
	}

	private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
		Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
	}

}
