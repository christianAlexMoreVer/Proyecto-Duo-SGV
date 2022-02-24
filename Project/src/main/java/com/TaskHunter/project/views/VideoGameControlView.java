package com.TaskHunter.project.views;

import com.TaskHunter.project.entity.models.AppUser;
import com.TaskHunter.project.entity.models.VideoGame;
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

@Route(value = "VideoGameControl")
@PageTitle("VideoGameControl")
public class VideoGameControlView extends VerticalLayout {

    private Grid<VideoGame> grid = new Grid<>(VideoGame.class);
    private TextField filterText = new TextField();
    private Boolean musicPlay = false;

    public VideoGameControlView(LocalStorage localStorage, Mutation mutationService, Query service){

        grid.setVisible(false);
        checkLogin(localStorage);

        VideoGameFormView form = new VideoGameFormView(this, mutationService, service);
        AudioPlayer player = new AudioPlayer(service);

        player.setVisible(true);
        HorizontalLayout music = new HorizontalLayout(player);
        music.setHeight("0");
        music.setWidth("0");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);

        filterText.setPlaceholder("Filter by name");
        filterText.setClearButtonVisible(true);

        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));

        Button navigateAppUserControlBtn = new Button("Tabla AppUser");
        navigateAppUserControlBtn.addClickListener(e -> {
            UI.getCurrent().navigate("AppUserControl");
        });

        Button navigateMusicControlBtn = new Button("Tabla Music");
        navigateMusicControlBtn.addClickListener(e -> {
            UI.getCurrent().navigate("MusicControl");
        });

        Button addVideoGameBtn = new Button("Add new VideoGame");
        addVideoGameBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setVideoGame(new VideoGame());
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

        grid.setColumns("idVideoGame", "name", "photo");

        mainContent.setSizeFull();
        form.setVideoGame(null);
        HorizontalLayout toolbar = new HorizontalLayout(filterText,navigateAppUserControlBtn,navigateMusicControlBtn ,addVideoGameBtn, AudioControlBtn, music);

        add(toolbar, mainContent);

        updateList(service);

        grid.asSingleSelect().addValueChangeListener(event -> form.setVideoGame(grid.asSingleSelect().getValue()));

    }

    public void updateList(Query service) {
        grid.setItems(service.getVideogameByName(filterText.getValue()));
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
