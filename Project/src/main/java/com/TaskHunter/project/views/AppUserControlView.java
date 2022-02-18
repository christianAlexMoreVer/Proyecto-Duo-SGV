package com.TaskHunter.project.views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.transform.stream.StreamSource;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.mutation.Mutation;
import com.TaskHunter.project.query.Query;
import com.TaskHunter.project.utils.LocalStorage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Route(value = "AppUserControl")
@PageTitle("AppUserControl")
public class AppUserControlView extends VerticalLayout{

    private Grid<AppUser> grid = new Grid<>(AppUser.class);
    private TextField filterText = new TextField();
    
    public AppUserControlView(LocalStorage localStorage, Mutation mutationService, Query service) {
    	
    	grid.setVisible(false);
    	checkLogin(localStorage);


    	AppUserFormView form = new AppUserFormView(this, mutationService, service  );
    	
    	
    	
    	HorizontalLayout mainContent = new HorizontalLayout(grid, form );
    	
    	filterText.setPlaceholder("Filter by username");
        filterText.setClearButtonVisible(true);
        
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));
        
        Button addAppUserBtn = new Button("Add new AppUser");
        addAppUserBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setAppUser(new AppUser());
        });
    	
    	grid.setColumns("idAppUser", "email", "password", "userName", "rol");
    	
    	mainContent.setSizeFull();
    	form.setAppUser(null);
    	HorizontalLayout toolbar = new HorizontalLayout(filterText, addAppUserBtn);

        add(toolbar, mainContent);

        
        updateList(service);
        
        
        grid.asSingleSelect().addValueChangeListener(event ->
        form.setAppUser(grid.asSingleSelect().getValue()));
    }
    
    public void updateList(Query service) {
    	grid.setItems(service.getUserByUserNameLike(filterText.getValue()));
    }
    
    public void checkLogin(LocalStorage localStorage) {
    	
    	try {
    		String login = localStorage.getLogin();
    		
    		System.out.println(login);
    		
    		if(Objects.isNull(login)) {
    			System.out.println("soy null bb");
    			UI.getCurrent().navigate("");
    			UI.getCurrent().close();
    			System.out.println("me hacen uwu");
    		}else {
    			grid.setVisible(true);
    		}
    		
    		
    	} catch (Exception e) {
    		UI.getCurrent().navigate("");
		}
    	
    }
}

