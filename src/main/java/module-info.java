module fatec.lanchoneteapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jtds;
    requires java.sql;
    requires javafx.graphics;

    opens fatec.lanchoneteapp.run to javafx.fxml;
    opens fatec.lanchoneteapp.adapters.ui to javafx.fxml;
    opens fatec.lanchoneteapp.application.facade to javafx.fxml;
    opens fatec.lanchoneteapp.adapters.ui.cargo to javafx.fxml;
    opens fatec.lanchoneteapp.adapters.ui.categoria to javafx.fxml;
    opens fatec.lanchoneteapp.adapters.ui.cliente to javafx.fxml;
    opens fatec.lanchoneteapp.adapters.ui.fornecedor to javafx.fxml;
    opens fatec.lanchoneteapp.adapters.ui.controller to javafx.fxml;

    exports fatec.lanchoneteapp.run;
    exports fatec.lanchoneteapp.adapters.ui;
    exports fatec.lanchoneteapp.adapters.ui.cliente;
    exports fatec.lanchoneteapp.adapters.repository;
    exports fatec.lanchoneteapp.application.facade;
    exports fatec.lanchoneteapp.application.dto;
    exports fatec.lanchoneteapp.application.exception;
    exports fatec.lanchoneteapp.application.mapper;
    exports fatec.lanchoneteapp.application.repository;
    exports fatec.lanchoneteapp.application.usecase.cadastro;
    exports fatec.lanchoneteapp.application.usecase.pedido;
    exports fatec.lanchoneteapp.application.usecase.auth;
    exports fatec.lanchoneteapp.config;
    exports fatec.lanchoneteapp.adapters.repository.db;
    exports fatec.lanchoneteapp.application.service;
    exports fatec.lanchoneteapp.domain.entity;
    exports fatec.lanchoneteapp.adapters.ui.funcionario;
    opens fatec.lanchoneteapp.adapters.ui.funcionario to javafx.fxml;
    exports fatec.lanchoneteapp.adapters.ui.produto;
    opens fatec.lanchoneteapp.adapters.ui.produto to javafx.fxml;
    exports fatec.lanchoneteapp.adapters.ui.pedido;
    opens fatec.lanchoneteapp.adapters.ui.pedido to javafx.fxml;
    exports fatec.lanchoneteapp.adapters.ui.pedido.itemPedido;
    opens fatec.lanchoneteapp.adapters.ui.pedido.itemPedido to javafx.fxml;
}
