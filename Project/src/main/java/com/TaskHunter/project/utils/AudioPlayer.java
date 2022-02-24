package com.TaskHunter.project.utils;

import com.TaskHunter.project.query.Query;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

@Tag("audio")
public class AudioPlayer extends Component {

    public AudioPlayer(Query serviceGraphqlQuery){
        getElement().setAttribute("controls",true);
        this.setSource("http://localhost:8013/mediaFiles/"+(serviceGraphqlQuery.getMusicBackgroundInUse().getBackgroundMusic()));
    }

    public void setSource(String path){
        getElement().setProperty("src",path);
    }

    public void play() {
        getElement().callJsFunction("play");
    }

    public void stop() {
        getElement().callJsFunction("pause");
        getElement().setProperty("currentTime", 0);
    }

}