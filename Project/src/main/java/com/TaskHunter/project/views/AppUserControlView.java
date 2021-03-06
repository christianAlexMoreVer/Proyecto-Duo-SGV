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
import com.TaskHunter.project.utils.AudioPlayer;
import com.TaskHunter.project.utils.LocalStorage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
	final Button print = new Button("Report");
	private Boolean musicPlay = false;

    public AppUserControlView(LocalStorage localStorage, Mutation mutationService, Query service) {

    	grid.setVisible(false);
    	checkLogin(localStorage);

    	AppUserFormView form = new AppUserFormView(this, mutationService, service);
		AudioPlayer player = new AudioPlayer(service);

		player.setVisible(true);
		HorizontalLayout music = new HorizontalLayout(player);
		music.setHeight("0");
		music.setWidth("0");

    	HorizontalLayout mainContent = new HorizontalLayout(grid, form);
    	
    	filterText.setPlaceholder("Filter by username");
        filterText.setClearButtonVisible(true);
        
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));

		Button navigateVideoGameControlBtn = new Button("Tabla VideoGame");
		navigateVideoGameControlBtn.addClickListener(e -> {
			UI.getCurrent().navigate("VideoGameControl");
		});

		Button navigateMusicControlBtn = new Button("Tabla Music");
		navigateMusicControlBtn.addClickListener(e -> {
			UI.getCurrent().navigate("MusicControl");
		});
        
        Button addAppUserBtn = new Button("Add new AppUser");
        addAppUserBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setAppUser(new AppUser());
        });

		Button AudioControlBtn = new Button(new Icon(VaadinIcon.PLAY));
		AudioControlBtn.addClickListener(e -> {
			if (musicPlay == true){
				player.stop();
				musicPlay = false;
				AudioControlBtn.setIcon(new Icon(VaadinIcon.PLAY));
			}else {
				player.play();
				musicPlay = true;
				AudioControlBtn.setIcon(new Icon(VaadinIcon.PAUSE));
			}
		});

		print.addClickListener(e -> {

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(

					service.getVideogamesInCollection()

					, false);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("1", "1");

			JasperReport compileReport;
			try {
				compileReport = JasperCompileManager
						.compileReport(new FileInputStream("src/main/resources/static/report/report.jrxml"));
				JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters,
						beanCollectionDataSource);

				JasperExportManager.exportReportToHtmlFile(jasperPrint, "src/main/resources/static/report/report.html");
				JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/static/report/report.pdf");
				// JasperExportManager.exportReportToPdfFile(jasperPrint,
				// System.currentTimeMillis() + ".pdf");

				byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

				UI.getCurrent().getPage().open("/report/report.pdf");


			} catch (FileNotFoundException | JRException e1) {

				e1.printStackTrace();
			}

		});


		grid.setColumns("idAppUser", "email", "password", "userName", "rol");
    	
    	mainContent.setSizeFull();
    	form.setAppUser(null);
    	HorizontalLayout toolbar = new HorizontalLayout(filterText,navigateVideoGameControlBtn, navigateMusicControlBtn, addAppUserBtn, print, AudioControlBtn, music);

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
    		
    		if(Objects.isNull(login)) {
    			UI.getCurrent().navigate("");
    			UI.getCurrent().close();
    		}else {
    			grid.setVisible(true);
    		}
    		
    		
    	} catch (Exception e) {
    		UI.getCurrent().navigate("");
		}
    	
    }
}

