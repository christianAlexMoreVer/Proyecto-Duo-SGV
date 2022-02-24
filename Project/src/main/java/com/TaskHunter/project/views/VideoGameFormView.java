package com.TaskHunter.project.views;

import com.TaskHunter.project.entity.models.VideoGame;
import com.TaskHunter.project.mutation.Mutation;
import com.TaskHunter.project.query.Query;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Stream;

public class VideoGameFormView extends FormLayout {

    @NotBlank
    private TextField name = new TextField("name");
    private String imageBase64 = null;

    private MemoryBuffer memoryBuffer = new MemoryBuffer();
    @NotBlank
    private Upload imageVideoGame = new Upload(memoryBuffer);

    private Span errorMessageField;
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<VideoGame> binder = new Binder<>(VideoGame.class);

    private VideoGameControlView mainView;

    public VideoGameFormView(VideoGameControlView mainView, Mutation service, Query queryService) {

        this.mainView = mainView;

        errorMessageField = new Span();
        errorMessageField.setVisible(false);

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        VerticalLayout error = new VerticalLayout(errorMessageField);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        name.getElement().setAttribute("name", "name");
        name.setErrorMessage("Please enter a name");

        imageVideoGame.setDropAllowed(true);
        imageVideoGame.setAcceptedFileTypes("image/avif","image/bmp","image/gif","image/vnd.microsoft.icon","image/jpeg","image/png","image/svg+xml","image/tiff","image/webp" );

        imageVideoGame.addFileRejectedListener(event -> {
            String errorMessage = "No has introducido una archivo de imagen aceptado";

            Notification notification = Notification.show(
                    errorMessage,
                    5000,
                    Notification.Position.MIDDLE
            );
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

        imageVideoGame.addFinishedListener(event -> {
            try {
                Base64.Encoder dec = Base64.getEncoder();
                imageBase64 = dec.encodeToString(memoryBuffer.getInputStream().readAllBytes());
            } catch (IOException e) {
                System.out.println("Ha fallado la conversion de la imagen a base64");
                e.printStackTrace();
            }
        });

        add(name, imageVideoGame, buttons, error);

        binder.bindInstanceFields(this);
        save.addClickListener(event -> update(service, queryService));
        delete.addClickListener(event -> delete(service, queryService));

    }

    public void setVideoGame(VideoGame game) {
        binder.setBean(game);

        if (game == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
            if (game.getIdVideoGame() == 0) {
                setRequiredIndicatorVisible(name);
            }
        }
    }

    private void delete(Mutation service, Query queryService) {
        VideoGame game = binder.getBean();
        service.DeleteVideoGame(game.getIdVideoGame());
        mainView.updateList(queryService);
        setVideoGame(null);
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

    private void update(Mutation service, Query queryService) {
        VideoGame game = binder.getBean();

        if (game.getIdVideoGame() == 0) {

            errorMessageField.removeAll();
            errorMessageField.setVisible(false);

            int errores = 0;

            if (name.isEmpty()) {
                errores++;
                errorMessageField.add("Falta algún campo por rellenar | ");
            }

            if (false == queryService.getExistVideogame(name.getValue())) {
                System.out.println("El name ingresado es válido.");
            } else {
                errorMessageField.add("El name ingresado no es válido o ya existe | ");
                errores++;
            }

            if (errores == 0) {
                try {
                    service.InsertVideoGame(name.getValue(), memoryBuffer.getFileName(), imageBase64);
                    errorMessageField.removeAll();
                    mainView.updateList(queryService);
                    setVideoGame(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                errorMessageField.setVisible(true);
            }

        } else {

            errorMessageField.removeAll();
            errorMessageField.setVisible(false);

            int errores = 0;

            Optional<VideoGame> beforeGame = queryService.getVideogame(game.getIdVideoGame());

            if (name.isEmpty()) {
                errores--;
            }

            if (false == queryService.getExistVideogame(name.getValue())) {
                System.out.println("El email ingresado es válido.");
            } else {
                if(game.getName().equals(beforeGame.get().getName())) {
                    System.out.println("El email ingresado es válido.");
                }else {
                    System.out.println(beforeGame.get().getName());
                    errorMessageField.add("El name ingresado no es válido | ");
                    errores++;
                }

            }

            if(errores <= 0) {
                try {
                    service.UpdateVideoWithGameImage((int) game.getIdVideoGame(), name.getValue(), memoryBuffer.getFileName(), imageBase64);
                    errorMessageField.removeAll();
                    mainView.updateList(queryService);
                    setVideoGame(null);
                } catch (Exception e) {
                    errorMessageField.setVisible(true);
                }

            }
            else {
                errorMessageField.setVisible(true);
            }


        }
    }

}
