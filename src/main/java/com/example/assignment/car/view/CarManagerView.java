package com.example.assignment.car.view;

import com.example.assignment.car.entity.CarEntity;
import com.example.assignment.car.service.CarService;
import com.example.assignment.core.component.MenuComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route
public class CarManagerView extends VerticalLayout {

    private CarEntity selectedCar;
    private VerticalLayout form;
    private TextField brand;
    private TextField type;
    private TextField doors;
    private TextField prodYear;
    private Binder<CarEntity> binder;

    @Autowired
    private CarService service;

    @PostConstruct
    public void init() {
        add(new MenuComponent());
        Grid<CarEntity> grid = new Grid<>();
        grid.setItems(service.findAll());
        grid.addColumn(CarEntity::getId).setHeader("ID");
        grid.addColumn(CarEntity::getBrand).setHeader("Brand");
        grid.addColumn(CarEntity::getType).setHeader("Type");
        grid.addColumn(CarEntity::getDoors).setHeader("Number of Doors");
        grid.addColumn(CarEntity::getProdYear).setHeader("Production Year");
        addButtonBar(grid);
        add(grid);
        addForm(grid);
    }

    private void addForm(Grid<CarEntity> grid) {
        form = new VerticalLayout();
        binder = new Binder<>(CarEntity.class);

        brand = new TextField();
        form.add(new Text("Brand"), brand);
        type = new TextField();
        form.add(new Text("Type"), type);
        doors = new TextField();
        form.add(new Text("Number of Doors"), doors);
        prodYear = new TextField();
        form.add(new Text("Production Year"), prodYear);

        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.addClickListener(buttonClickEvent -> {
            if (selectedCar.getId() != null) {
                service.update(selectedCar);
            } else {
                service.create(selectedCar);
            }
            grid.setItems(service.findAll());
            form.setVisible(false);
        });
        form.add(saveBtn);
        add(form);
        form.setVisible(false);
        binder.bindInstanceFields(this);
    }

    private void addButtonBar(Grid<CarEntity> grid) {
        HorizontalLayout buttonBar = new HorizontalLayout();

        Button deleteBtn = new Button();
        deleteBtn.setEnabled(false);
        deleteBtn.setText("Delete");
        deleteBtn.setIcon(VaadinIcon.TRASH.create());
        deleteBtn.addClickListener(buttonClickEvent -> {
           service.deleteById(selectedCar.getId());
           grid.setItems(service.findAll());
           selectedCar = null;
           deleteBtn.setEnabled(false);
           form.setVisible(false);
           Notification.show("Successfully deleted");
        });
        grid.asSingleSelect().addValueChangeListener(event -> {
           selectedCar = event.getValue();
           deleteBtn.setEnabled(selectedCar != null);
           form.setVisible(selectedCar != null);
           binder.setBean(selectedCar);
        });

        Button addBtn = new Button();
        addBtn.setText("Add");
        addBtn.addClickListener(buttonClickEvent -> {
           selectedCar = new CarEntity();
           binder.setBean(selectedCar);
           form.setVisible(true);
        });
        addBtn.setIcon(VaadinIcon.PLUS.create());
        buttonBar.add(deleteBtn, addBtn);
        add(buttonBar);
    }
}
