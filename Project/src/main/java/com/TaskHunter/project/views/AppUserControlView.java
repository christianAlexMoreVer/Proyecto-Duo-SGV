package com.TaskHunter.project.views;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.query.Query;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;


@Route(value = "AppUserControl")
@PageTitle("AppUserControl")
@PWA(name = "AppUserControl", shortName = "AppUserControl")
public class AppUserControlView extends VerticalLayout{
	

    private Grid<AppUser> grid = new Grid<>(AppUser.class);
    private TextField filterText = new TextField();
    
    public AppUserControlView(Query service) {
    	
    	filterText.setPlaceholder("Filter by username");
        filterText.setClearButtonVisible(true);
        
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));
    	
    	grid.setColumns("idAppUser", "email", "password", "userName", "photo", "rol");
    	
    	

        add(filterText, grid);

        setSizeFull();
        
        updateList(service);
    }
    
    public void updateList(Query service) {
    	grid.setItems(service.getUserByUserNameLike(filterText.getValue()));
    }
    


}
