BusArrivalDisplay

The BusArrivalDisplay is a plain Java application designed to display the next bus arrivals for a specific stop. 
It utilizes Lombok for boilerplate code reduction and reads data from text files to determine the arrival times of buses.

Features

Data Loading: Load data from stop_times.txt, trips.txt, routes.txt, and stops.txt.
Next Arrivals: Display the next bus arrivals for a specified stop.
Time Formatting: Display arrival times either in absolute or relative time format.
Logging: Comprehensive logging for error handling and debugging.

Prerequisites

Java 8 or higher: Ensure you have Java Development Kit (JDK) installed.
Lombok: Lombok must be set up correctly in your development environment.

Setup

Clone the repository:

git clone [from this link](https://github.com/beslagicadin/RidangoBusArrivalDisplay.git)

cd RidangoBusArrivalDisplay

Ensure Lombok is correctly configured:

IntelliJ IDEA: Enable annotation processing in Preferences > Build, Execution, Deployment > Compiler > Annotation Processors.
Eclipse: Install the Lombok plugin and enable annotation processing in Preferences > Java Compiler > Annotation Processing.

Place your data files:

Ensure the stop_times.txt, trips.txt, routes.txt, and stops.txt files are located in the src/data/ directory.

Usage

Running the Application
You can run the BussArrivalScheduleApp class which contains the main method to start the application.

Example of how to get the next arrivals:

public class Main {
    public static void main(String[] args) {
        BussArrivalScheduleApp app = new BussArrivalScheduleApp();
        int stopId = 123; // replace with actual stop ID
        int numOfBuses = 5; // replace with the number of bus arrivals you want to be displayed
        String timeFormat = "relative"; // or "absolute" for different display type of ETA
        app.getNextArrivals(stopId, numOfBuses, timeFormat);
    }
}

Example Output

"relative"->
Loading data for stop ID: 123
Station: Main Street
1 arrives in: 5min, 15min, 25min
2 arrives in: 10min, 20min, 30min

"absolute"->
Loading data for stop ID: 123
Station: Main Street
1 arrives at: 16:20, 16:30, 16:40
2 arrives at: 16:25, 16:35, 16:45

Logging

The application uses Java's built-in logging framework.
Logs are displayed in the console, showing information and errors encountered during execution.

Acknowledgements

Data files structure is inspired by the [GTFS (General Transit Feed Specification)](https://developers.google.com/transit/gtfs) standard.

