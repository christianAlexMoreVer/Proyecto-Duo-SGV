package com.TaskHunter.project.views;

import com.TaskHunter.project.entity.models.Music;
import com.TaskHunter.project.entity.models.VideoGame;
import com.TaskHunter.project.mutation.Mutation;
import com.TaskHunter.project.query.Query;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

public class MusicFormView extends FormLayout {

    @NotBlank
    private IntegerField inUse = new IntegerField("1 la cacion está en uso 0 no está en uso.");

    private String musicBase64 = null;
    private boolean musicUpload = false;
    private MemoryBuffer memoryBuffer = new MemoryBuffer();
    @NotBlank
    private Upload musicFile = new Upload(memoryBuffer);

    private Span errorMessageField;
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<Music> binder = new Binder<>(Music.class);

    private MusicControlView mainView;

    public MusicFormView(MusicControlView mainView, Mutation service, Query queryService){

        this.mainView = mainView;

        errorMessageField = new Span();
        errorMessageField.setVisible(false);

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        VerticalLayout error = new VerticalLayout(errorMessageField);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        musicFile.setDropAllowed(true);
        musicFile.setAcceptedFileTypes("audio/mpeg");

        musicFile.addFileRejectedListener(event -> {
            String errorMessage = "No has introducido una archivo de audio mp3";

            Notification notification = Notification.show(
                    errorMessage,
                    5000,
                    Notification.Position.MIDDLE
            );
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

        musicFile.addFinishedListener(event -> {
            try {
                Base64.Encoder dec = Base64.getEncoder();
                musicBase64 = dec.encodeToString(memoryBuffer.getInputStream().readAllBytes());
                musicUpload = true;
            } catch (IOException e) {
                System.out.println("Ha fallado la conversion de la imagen a base64");
                e.printStackTrace();
            }
        });

        add(inUse,musicFile,buttons, error);

        binder.bindInstanceFields(this);
        save.addClickListener(event -> update(service, queryService));
        delete.addClickListener(event -> delete(service, queryService));

    }

    public void setMusic(Music music) {
        binder.setBean(music);
        if (music == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    private void delete(Mutation service, Query queryService){
        Music music = binder.getBean();
        service.DeleteMusic(music.getIdMusic());
        mainView.updateList(queryService);
        setMusic(null);
    }

    private void update(Mutation service, Query queryService){
        Music music = binder.getBean();

        if (music.getIdMusic() == 0) {

            errorMessageField.removeAll();
            errorMessageField.setVisible(false);

            int errores = 0;

            if (inUse.isEmpty()) {
                errores++;
                errorMessageField.add("Falta algún campo por rellenar | ");
            }

            if (errores == 0) {
                try {
                    service.InsertMusic(memoryBuffer.getFileName().replace(" ",""), musicBase64);
                    errorMessageField.removeAll();
                    mainView.updateList(queryService);
                    musicUpload = false;
                    memoryBuffer.getInputStream().reset();
                    setMusic(null);
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

            Music beforeMusic = queryService.getMusic(music.getIdMusic());

            if (inUse.isEmpty()) {
                errores--;
            }

            if (musicUpload == false){
                errores--;
            }

            if(errores <= 0) {
                if (inUse.getValue() == 1){
                    try {
                        Music musicaEnUso = queryService.getMusicBackgroundInUse();
                        musicaEnUso.setInUse(0);
                        service.UpdateMusic((int) music.getIdMusic(), memoryBuffer.getFileName().replace(" ",""), musicBase64, 1 );
                        service.UpdateMusicWithoutImage(musicaEnUso.getIdMusic(), musicaEnUso.getBackgroundMusic(), 0);
                        errorMessageField.removeAll();
                        mainView.updateList(queryService);
                        setMusic(null);
                        musicUpload = false;
                        memoryBuffer.getInputStream().reset();
                    } catch (Exception e) {
                        errorMessageField.setVisible(true);
                    }
                } else  {
                    try {
                        service.UpdateMusic((int) music.getIdMusic(), memoryBuffer.getFileName().replace(" ",""), musicBase64, 0 );
                        errorMessageField.removeAll();
                        mainView.updateList(queryService);
                        setMusic(null);
                        musicUpload = false;
                        memoryBuffer.getInputStream().reset();
                    } catch (Exception e) {
                        errorMessageField.setVisible(true);
                    }
                }


            }
            else {
                errorMessageField.setVisible(true);
            }


        }
    }
}
