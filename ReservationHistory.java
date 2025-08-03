import java.util.LinkedList;

public class ReservationHistory {
    private LinkedList<String> history;

    public ReservationHistory() {
        this.history = new LinkedList<>();
    }

    // Getters and Setters
    public LinkedList<String> getHistory() {
        return history;
    }

    public void setHistory(LinkedList<String> history) {
        this.history = history;
    }

    public void addReservation(String reservationDetails) {
        history.addFirst(reservationDetails);
        System.out.println("Reservation added to history!");
    }

    public void displayHistory() {
        System.out.println("Reservation History:");
        for (String reservation : history) {
            System.out.println(reservation);
        }
    }
}