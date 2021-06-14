package com.example.assignment.manufacturer.view;

import com.example.assignment.car.entity.CarEntity;
import com.example.assignment.car.service.CarService;
import com.example.assignment.core.component.MenuComponent;
import com.example.assignment.manufacturer.entity.ManufacturerEntity;
import com.example.assignment.manufacturer.service.ManufacturerService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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
public class ManufacturerManagerView extends VerticalLayout {

    private ManufacturerEntity selectedCar;
    private VerticalLayout form;
    private TextField name;
    private ComboBox<CarEntity> car;
    private Binder<ManufacturerEntity> binder;

    @Autowired
    private ManufacturerService service;
    @Autowired
    private CarService carService;

    @PostConstruct
    public void init() {
        add(new MenuComponent());
        Grid<ManufacturerEntity> grid = new Grid<>();
        grid.setItems(service.findAll());
        grid.addColumn(ManufacturerEntity::getId).setHeader("ID");
        grid.addColumn(ManufacturerEntity::getName).setHeader("Name");
        grid.addColumn(manufacturerEntity -> {
            if (manufacturerEntity.getCar() != null) {
                return manufacturerEntity.getCar().getBrand() + " " + manufacturerEntity.getCar().getType() + " " + manufacturerEntity.getCar().getDoors() + " " + manufacturerEntity.getCar().getProdYear();
            }
            return "";
        }).setHeader("Car");

    }

    private void addForm(Grid<ManufacturerEntity> grid) {
        form = new VerticalLayout();
        binder = new Binder<>(ManufacturerEntity.class);
        name = new TextField();
        form.add(new Text("Name"), name);
        car = new ComboBox<>();
        car.setItems(carService.findAll());
        car.setItemLabelGenerator(carEntity -> carEntity.getBrand() + " " + carEntity.getType() + " " + carEntity.getDoors() + " " + carEntity.getProdYear());
        form.add(new Text("Car"), car);

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


    private void addButtonBar(Grid<ManufacturerEntity> grid) {
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
            selectedCar = new ManufacturerEntity();
            binder.setBean(selectedCar);
            form.setVisible(true);
        });
        addBtn.setIcon(VaadinIcon.PLUS.create());
        buttonBar.add(deleteBtn, addBtn);
        add(buttonBar);
    }
}

