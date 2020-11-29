package ui;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sun.java.swing.plaf.motif.MotifButtonListener;

import Exceptions.QueueEmptyException;
import Exceptions.UserNotExistException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BAnk;
import model.User;

public class uiController {
	
    @FXML
    private ComboBox<String> sortComboBox;
	
	@FXML
    private Button sortButton;

	@FXML
	private Button confirmButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField IDTextField;

	@FXML
	private Label nameActualClientPrioQueueLabel;

	@FXML
	private Label nameActualClientQueueLabel;

	@FXML
	private Label idActualClientPrioQueueLabel;

	@FXML
	private Label idActualClientQueueLabel;

	@FXML
	private Label numClientsQueueLabel;

	@FXML
	private Label numClientsPriorityQueueLabel;

	@FXML
	private Label choiceLabel;

	@FXML
	private TextField amountTextField;

	@FXML
	private ComboBox<String> typePayComboBox;

	@FXML
	private ComboBox<String> operationComboBox;

	@FXML
	private TextField idSearchTextField;

	@FXML
	private Label nameSearchedLabel;

	@FXML
	private Label idSearchedLabel;

	@FXML
	private Label accountNumSearchedLabel;

	@FXML
	private Label debitCardSearchedLabel;

	@FXML
	private TableView<User> dataTableView;

	@FXML
	private TableColumn<User, String> nameTableCol;

	@FXML
	private TableColumn<User, String> idTableCol;

	@FXML
	private TableColumn<User, LocalDate> dateJoinTableCol;

	@FXML
	private TableColumn<User, LocalDate> datePayTableCol;

	@FXML
	private TableColumn<User, Double> debitTableCol;

	@FXML
	private TableColumn<User, Double> creditTableCol;


	private BAnk bank;

	public uiController() {
		bank = new BAnk();
	}

	public void initialize() {
		operationComboBox.getItems().add(0, "Make a deposit");
		operationComboBox.getItems().add(1, "Make a withdraw");
		operationComboBox.getItems().add(2, "Cancel an account");
		operationComboBox.getItems().add(3, "Pay card");

		typePayComboBox.getItems().add(0, "Cash");
		typePayComboBox.getItems().add(1, "DebitCard");
		
		sortComboBox.getItems().add("Name");
		sortComboBox.getItems().add("ID");
		sortComboBox.getItems().add("Account Number");
		sortComboBox.getItems().add("Debit Card");

		nameTableCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		idTableCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
		creditTableCol.setCellValueFactory(new PropertyValueFactory<>("creditCard"));
		debitTableCol.setCellValueFactory(new PropertyValueFactory<>("debitCard"));
		dateJoinTableCol.setCellValueFactory(new PropertyValueFactory<>("dateOfPayCC"));
		datePayTableCol.setCellValueFactory(new PropertyValueFactory<>("dateOfJoin"));
	}

	@FXML
	public void actualizeQueues(Event event) {

		User ActualUser = bank.peekQueue();
		if(ActualUser!=null) {
			nameActualClientQueueLabel.setText(ActualUser.getName());
			idActualClientQueueLabel.setText(ActualUser.getID());
			numClientsQueueLabel.setText(bank.getQueueSize()+"");
		}else {
			nameActualClientQueueLabel.setText("--------------");
			idActualClientQueueLabel.setText("--------------");
			numClientsQueueLabel.setText("---");
		}
		ActualUser = bank.peekPrioQueue();
		if(ActualUser!=null) {
			nameActualClientPrioQueueLabel.setText(ActualUser.getName());
			idActualClientPrioQueueLabel.setText(ActualUser.getID());
			numClientsPriorityQueueLabel.setText(bank.getPrioQueueSize()+"");
		}else {
			nameActualClientPrioQueueLabel.setText("--------------");
			idActualClientPrioQueueLabel.setText("--------------");
			numClientsPriorityQueueLabel.setText("---");
		}
	}

	@FXML
	public void attendPriorityQueue(ActionEvent event) {
		try {
			bank.attendPrioQueueUser();;
		} catch (QueueEmptyException e) {
			JOptionPane.showMessageDialog(null, "The queue is empty");
		}
		actualizeQueues(event);
	}

	@FXML
	public void attendQueue(ActionEvent event) {
		try {
			bank.attendQueueUser();
		} catch (QueueEmptyException e) {
			JOptionPane.showMessageDialog(null, "The queue is empty");
		}
		actualizeQueues(event);
	}

	@FXML
	public void newEntry(ActionEvent event) {
		String id = IDTextField.getText();
		IDTextField.setText("");
		nameTextField.setText("");
		try {
			bank.newEntry(id);
			JOptionPane.showMessageDialog(null, "Added correctly to the queue");
		} catch (UserNotExistException e) {
			JOptionPane.showMessageDialog(null, "The user isn´t on the data base");
		}

	}

	@FXML
	public void operation(ActionEvent event) {

		String operation = operationComboBox.getSelectionModel().getSelectedItem();
		String typePay = typePayComboBox.getSelectionModel().getSelectedItem();

		if(operation==null||typePay==null) {
			JOptionPane.showMessageDialog(null, "An operation and a type of pay needs to be choosen!");
			return;
		}

		switch(operation) {
		case "Make a deposit":
			break;
		case "Make a withdraw":
			break;
		case "Cancel an account":
			break;
		case "Pay card":
			break;

		}

		switch(typePay) {
		case "Cash":
			break;
		case "DebitCard":
			break;
		}


		idSearchTextField.setText("");
		accountNumSearchedLabel.setText("---------------------------------------");
		debitCardSearchedLabel.setText("---------------------------------------");
		idSearchedLabel.setText("---------------------------------------");
		nameSearchedLabel.setText("---------------------------------------");

		operationComboBox.getSelectionModel().select("");
		typePayComboBox.getSelectionModel().select("");

		operationComboBox.setDisable(true);
		typePayComboBox.setDisable(true);
		amountTextField.setDisable(true);
		amountTextField.setText("");
		confirmButton.setDisable(true);

	}

	@FXML
	void searchClient(ActionEvent event) {
		String id = idSearchTextField.getText();
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "The user isn´t on the data base");
		}else {
			User u = bank.searchUser(id);
			accountNumSearchedLabel.setText(u.getBankAccountNum());
			debitCardSearchedLabel.setText(u.getDebitCard()+"");
			idSearchedLabel.setText(u.getID());
			nameSearchedLabel.setText(u.getName());
			operationComboBox.setDisable(false);
		}

	}

	@FXML
	void choiceEvent(ActionEvent event) {

		if(operationComboBox.getSelectionModel().getSelectedItem().equals("Cancel an account")) {
			typePayComboBox.setDisable(true);
			amountTextField.setDisable(true);
		}else {
			typePayComboBox.setDisable(false);
			amountTextField.setDisable(false);
		}
		confirmButton.setDisable(false);
	}

	public void actualizeTable(Event event) {
		ObservableList<User> data = FXCollections.observableArrayList(bank.getDatabase());
		dataTableView.getItems().addAll(data);
	}
	
	public void sortTable(ActionEvent event) {
		
		String order = sortComboBox.getSelectionModel().getSelectedItem();
		
		ArrayList<User> sorted = bank.getDataBasesortedBy(order);
		
		ObservableList<User> data = FXCollections.observableArrayList(sorted);
		
		dataTableView.getItems().addAll(data);
	}
	
	public void sortComboBoxAction(ActionEvent event) {
		sortButton.setDisable(false);
	}
}
