import javafx.scene.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;


public class ConnectFourController {

	
	private static final int DISK_SIZE = 28;
	private static final int START = 0;
	//create game logic for this graphic,
	private ConnectFourLogic l = new ConnectFourLogic(START);


	@FXML
	private GridPane buttonPane;

	@FXML
	private GridPane pane;

	@FXML
	void fivePressed(ActionEvent event) {
		buttonPressed(5);
	}

	@FXML
	void fourPressed(ActionEvent event) {
		buttonPressed(4);
	}

	@FXML
	void newGame(ActionEvent event) {
		//get first child, the grid lines to save and then re-paste without the disks
		Node node = pane.getChildren().get(0);
		pane.getChildren().clear();
		pane.getChildren().add(0,node);
		//need to reset game logic for new game
		l = new ConnectFourLogic(START);
	}


	@FXML
	void onePressed(ActionEvent event) {
		buttonPressed(1);
	}

	@FXML
	void sixPressed(ActionEvent event) {
		buttonPressed(6);
	}

	@FXML
	void threePressed(ActionEvent event) {
		buttonPressed(3);
	}

	@FXML
	void twoPressed(ActionEvent event) {
		buttonPressed(2);
	}

	@FXML
	void zeroPressed(ActionEvent event) {
		buttonPressed(0);
	}

	//when button n is pressed
	private void buttonPressed(int n) {
		boolean validTurn;
		Circle c1 = new Circle (pane.getPrefWidth()/DISK_SIZE);
		//check if this button is a valid turn and if so add disk in logic board
		validTurn = l.checkTurn(n);
		//if valid turn add disk to graphic board.
		if (validTurn) {
			if (l.getTurn() % 2 == 0)
				c1.setFill(Color.RED);
			else
				c1.setFill(Color.BLUE);
			pane.add(c1, n, l.getRow());
			//check if this turn leads to a winner or tie
			l.playTurn();
		}
	}
}






