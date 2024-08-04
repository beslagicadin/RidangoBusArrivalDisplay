import main.BusArrivalDisplay;

public class Main {
    public static void main(String[] args) {
        BusArrivalDisplay app = new BusArrivalDisplay();
        //1st param: Stop ID
        //2nd param: Number of arrivals in order in which they will come to the stop
        //3rd param: "relative" or "absolute" display type of ETA


        app.displayNextArrivals(
                2,
                5,
                "relative");

        app.displayNextArrivals(
                2,
                5,
                "absolute");
    }
}
