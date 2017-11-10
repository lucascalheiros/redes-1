import java.util.HashMap;

/*
 * Essa Thread tem como função cuidar do sistema de turno do jogo
 * Garantir que caso algum cliente saia não haja inconsistências
*/
public class TurnManager {
	public static HashMap<Integer, TurnManager> mapTurnMessage = new HashMap<Integer, TurnManager>();
	private char message = ' ';
	private boolean full = false;
	private boolean turn = false;

	public TurnManager(int id) {
		mapTurnMessage.put(id, this);

	}

	public char readMessage() {
		if (turn) {
			if (full) {
				char tmp = this.message;
				this.message = ' ';
				this.turn = false;
				this.full = false;
				return tmp;
			}
		}
		return ' ';
	}

	public boolean putMessage(char message) {
		if (turn) {
			if (!full) {
				full = true;
				this.message = message;
				return true;
			}
		}

		return false;
	}

	public void setTurn() {
		this.turn = true;
	}

}
