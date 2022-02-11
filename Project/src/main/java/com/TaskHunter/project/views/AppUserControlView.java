package com.TaskHunter.project.views;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.mutation.Mutation;
import com.TaskHunter.project.query.Query;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
    
   
    
    public AppUserControlView(Mutation mutationService, Query service) {
    	
    	AppUserFormModifyView form = new AppUserFormModifyView(this, mutationService, service  );
    	
    	HorizontalLayout mainContent = new HorizontalLayout(grid, form );
    	
    	filterText.setPlaceholder("Filter by username");
        filterText.setClearButtonVisible(true);
        
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));
    	
    	grid.setColumns("idAppUser", "email", "password", "userName", "rol");
    	
    	mainContent.setSizeFull();
    	form.setAppUser(null);

        add(filterText, mainContent);

        
        updateList(service);
        
        
        grid.asSingleSelect().addValueChangeListener(event ->
        form.setAppUser(grid.asSingleSelect().getValue()));
    }
    
    public void updateList(Query service) {
    	grid.setItems(service.getUserByUserNameLike(filterText.getValue()));
    }
    


}
