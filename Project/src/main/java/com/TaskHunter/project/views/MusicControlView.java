package com.TaskHunter.project.views;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.Music;
import com.TaskHunter.project.mutation.Mutation;
import com.TaskHunter.project.query.Query;
import com.TaskHunter.project.utils.AudioPlayer;
import com.TaskHunter.project.utils.LocalStorage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Objects;


@Route(value = "MusicControl")
@PageTitle("MusicControl")
public class MusicControlView extends VerticalLayout {

    private Grid<Music> grid = new Grid<>(Music.class);
    private TextField filterText = new TextField();
    private Boolean musicPlay = false;

    public MusicControlView(LocalStorage localStorage, Mutation mutationService, Query service){

        grid.setVisible(false);
        checkLogin(localStorage);

        MusicFormView form = new MusicFormView(this, mutationService, service);
        AudioPlayer player = new AudioPlayer(service);

        player.setVisible(true);
        HorizontalLayout music = new HorizontalLayout(player);
        music.setHeight("0");
        music.setWidth("0");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);

        filterText.setPlaceholder("Filter by backgroundMusic");
        filterText.setClearButtonVisible(true);

        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));

        Button navigateAppUserControlBtn = new Button("Tabla AppUser");
        navigateAppUserControlBtn.addClickListener(e -> {
            UI.getCurrent().navigate("AppUserControl");
        });

        Button navigateVideoGameControlBtn = new Button("Tabla VideoGame");
        navigateVideoGameControlBtn.addClickListener(e -> {
            UI.getCurrent().navigate("VideoGameControl");
        });

        Button addMusicBtn = new Button("Add new Music");
        addMusicBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setMusic(new Music());
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

        grid.setColumns("idMusic", "backgroundMusic", "inUse");


        form.setVisible(false);
        mainContent.setSizeFull();
        HorizontalLayout toolbar = new HorizontalLayout(filterText,navigateAppUserControlBtn,navigateVideoGameControlBtn,addMusicBtn, AudioControlBtn, music);

        add(toolbar, mainContent);

        updateList(service);

        grid.asSingleSelect().addValueChangeListener(event -> form.setMusic(grid.asSingleSelect().getValue()));
    }

    public void updateList(Query service) {
        grid.setItems(service.getMusicBackgroundByNameContaining(filterText.getValue()));
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
