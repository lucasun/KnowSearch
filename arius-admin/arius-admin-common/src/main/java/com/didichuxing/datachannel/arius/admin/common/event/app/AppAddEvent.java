package com.didichuxing.datachannel.arius.admin.common.event.app;

import com.didichuxing.datachannel.arius.admin.common.bean.entity.app.App;

@Deprecated
public class AppAddEvent extends AppEvent {

    private App app;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AppAddEvent(Object source, App app) {
        super(source);
        this.app = app;
    }

    public App getApp() {
        return app;
    }
}