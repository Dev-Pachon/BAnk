package ui;

import javax.swing.JOptionPane;

import com.sun.java.swing.plaf.motif.MotifButtonListener;

import Exceptions.QueueEmptyException;
import Exceptions.UserNotExistException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.BAnk;
import model.PayType;
import model.TransactionType;
import model.User;

public class uiController {

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
	private TableView<?> dataTableView;

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
		
		

		accountNumSearchedLabel.setText("---------------------------------------");
		debitCardSearchedLabel.setText("---------------------------------------");
		idSearchedLabel.setText("---------------------------------------");
		nameSearchedLabel.setText("---------------------------------------");
		
		operationComboBox.setDisable(false);
		typePayComboBox.setDisable(false);
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
			operationComboBox.setDisable(true);
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
}
