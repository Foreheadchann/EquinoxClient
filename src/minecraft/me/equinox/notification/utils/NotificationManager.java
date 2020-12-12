package me.equinox.notification.utils;

import me.equinox.notification.NotificationHandler;

import java.util.concurrent.LinkedBlockingDeque;

public class NotificationManager {
    private static final LinkedBlockingDeque<NotificationHandler> pending = new LinkedBlockingDeque<>();
    private static NotificationHandler currentlyDisplayed = null;

    public static void displayNotification(NotificationHandler notification) {
        pending.add(notification);
    }

    public static void notificationUpdater() {
        if(currentlyDisplayed != null && !currentlyDisplayed.isBeingDisplayed()) { currentlyDisplayed = null; }
        if(currentlyDisplayed == null && !pending.isEmpty()) { currentlyDisplayed = pending.poll(); currentlyDisplayed.display(); }
    }

    public static void render() {
        try { notificationUpdater(); } catch(Throwable thrown) { thrown.printStackTrace(); }
        if(currentlyDisplayed != null)
            currentlyDisplayed.render();
    }
}
