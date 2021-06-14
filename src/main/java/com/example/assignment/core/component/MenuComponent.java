package com.example.assignment.core.component;

import com.example.assignment.security.config.SecurityUtils;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MenuComponent extends HorizontalLayout {

    public MenuComponent() {
        Anchor carLink = new Anchor();
        carLink.setHref("/carmanager");
        carLink.setText("Cars");

        Anchor manufacturerLink = new Anchor();
        manufacturerLink.setHref("/manufacturermanager");
        manufacturerLink.setText("Manufacturers");
        add(carLink, manufacturerLink);

        if (SecurityUtils.isAdmin()) {
            Anchor userLink = new Anchor();
            userLink.setHref("/usermanager");
            userLink.setText("Users:");
            add(userLink);
        }
    }
}
