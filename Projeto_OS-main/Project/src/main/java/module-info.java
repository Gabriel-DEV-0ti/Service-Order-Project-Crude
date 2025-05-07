module VdeVigilancia.Projeto_OS {
    requires javafx.fxml;
    requires javafx.controls;

    requires java.desktop;
    requires com.dlsc.formsfx;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires java.sql;

    opens VdeVigilancia.Projeto_OS to javafx.fxml;
    exports VdeVigilancia.Projeto_OS;
    exports VdeVigilancia.Projeto_OS.cliente;
    exports VdeVigilancia.Projeto_OS.dao;
}