package ma.enset.javafx_jdbc_orm.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.javafx_jdbc_orm.dao.CategoryDaoImp;
import ma.enset.javafx_jdbc_orm.dao.ProductDaoImpl;
import ma.enset.javafx_jdbc_orm.dao.entities.Category;
import ma.enset.javafx_jdbc_orm.dao.entities.Product;
import ma.enset.javafx_jdbc_orm.service.CatalogueService;
import ma.enset.javafx_jdbc_orm.service.CatalogueServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class productController implements Initializable {
    @FXML private TextField textNom;
    @FXML private TextField textRef;
    @FXML private TextField textPrix;
    @FXML private TextField textSearch;
    @FXML private ComboBox<Category> comboCategory;
    @FXML private TableView<Product> tableViewProducts;
    @FXML private TableColumn<Long,Product> columnId;
    @FXML private TableColumn<String,Product> columnName;
    @FXML private TableColumn<String,Product> columnRef;
    @FXML private TableColumn<Float,Product> columnPrice;
    @FXML private TableColumn<Category,Product> columnCategory;

    private CatalogueService catalogueService;
    ObservableList<Product> data = FXCollections.observableArrayList();
    private Product selectedProduct;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewProducts.setItems(data);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        catalogueService = new CatalogueServiceImpl(new ProductDaoImpl(),new CategoryDaoImp());
        comboCategory.getItems().addAll(catalogueService.getAllCategories());
        loadData();
        //observable, String oldValue, String newValue
        textSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            data.clear();
            data.addAll(catalogueService.searchProductByQuery(newValue));
        });

    }
    private void loadData(){
        data.clear();
        data.addAll(catalogueService.getAllProducts());
    }
    public  void addProduct(){
        Product p = new Product();
        p.setName(textNom.getText());
        p.setReference(textRef.getText());
        p.setPrice(Float.parseFloat(textPrix.getText()));
        p.setCategory(comboCategory.getSelectionModel().getSelectedItem());
        catalogueService.addProduct(p);
        loadData();
    }
    public void deleteProduct(){
        Product p = tableViewProducts.getSelectionModel().getSelectedItem();
        catalogueService.deleteProduct(p);
        loadData();
    }
    public void modifyProduct(){
        selectedProduct.setName(textNom.getText());
        selectedProduct.setPrice(Float.parseFloat(textPrix.getText()));
        selectedProduct.setReference(textRef.getText());
        selectedProduct.setCategory(comboCategory.getSelectionModel().getSelectedItem());
        catalogueService.updateProduct(selectedProduct);
        loadData();
    }

    public void editProduct() {
        selectedProduct = tableViewProducts.getSelectionModel().getSelectedItem();
        textNom.setText(selectedProduct.getName());
        textPrix.setText(String.valueOf(selectedProduct.getPrice()));
        textRef.setText(selectedProduct.getReference());
        /*comboCategory.setSelectionModel(new SingleSelectionModel<Category>() {
            @Override
            protected Category getModelItem(int index) {
                return selectedProduct.getCategory();
            }

            @Override
            protected int getItemCount() {
                return 1;
            }
        });*/
    }
}
