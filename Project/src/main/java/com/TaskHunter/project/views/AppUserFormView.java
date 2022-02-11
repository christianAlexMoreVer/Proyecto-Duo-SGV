package com.TaskHunter.project.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class AppUserFormView extends FormLayout{
	
	private TextField firstName = new TextField("Email");
	private TextField lastName = new TextField("Password");
	private TextField userName = new TextField("User name");

	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	
	

}
