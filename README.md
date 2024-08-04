<h1>BusArrivalDisplay</h1>
    <p>The BusArrivalDisplay is a plain Java application designed to display the next bus arrivals for a specific stop.<br>
    It utilizes Lombok for boilerplate code reduction and reads data from text files to determine the arrival times of buses.
    </p>
    <h2>Features</h2>
    <ul>
        <li><strong>Data Loading:</strong> Load data from <code>stop_times.txt</code>, <code>trips.txt</code>, <code>routes.txt</code>, and <code>stops.txt</code>.</li>
        <li><strong>Next Arrivals:</strong> Display the next bus arrivals for a specified stop.</li>
        <li><strong>Time Formatting:</strong> Display arrival times either in absolute or relative time format.</li>
        <li><strong>Logging:</strong> Comprehensive logging for error handling and debugging.</li>
    </ul>
    <h2>Directory Structure</h2>
    <pre>
    BusArrivalDisplay/
    ├── src/
    │   ├── app/
    │   │   └── BussArrivalScheduleApp.java
    │   ├── models/
    │   │   ├── Route.java
    │   │   ├── Stop.java
    │   │   ├── StopTime.java
    │   │   └── Trip.java
    │   ├── utils/
    │   │   └── FileReader.java
    │   └── data/
    │       ├── stop_times.txt
    │       ├── trips.txt
    │       ├── routes.txt
    │       └── stops.txt
    ├── README.md
    </pre>
    <h2>Prerequisites</h2>
        <ul>
            <li><strong>Java 22:</strong> Ensure you have Java Development Kit (JDK) installed.</li>
            <li><strong>Lombok:</strong> Lombok must be set up correctly in your development environment.</li>
        </ul>
        <h2>Setup</h2>
        <p>Clone the repository:</p>
        <pre>
    git clone <a href="https://github.com/beslagicadin/RidangoBusArrivalDisplay.git">https://github.com/beslagicadin/RidangoBusArrivalDisplay.git</a>
    cd RidangoBusArrivalDisplay
    </pre>
    <p>Ensure Lombok is correctly configured:</p>
    <ul>
        <li><strong>IntelliJ IDEA:</strong> Enable annotation processing in <code>Preferences &gt; Build, Execution, Deployment &gt; Compiler &gt; Annotation Processors</code>.</li>
        <li><strong>Eclipse:</strong> Install the Lombok plugin and enable annotation processing in <code>Preferences &gt; Java Compiler &gt; Annotation Processing</code>.</li>
    </ul>
    <p>Place your data files:</p>
    <p>Ensure the <code>stop_times.txt</code>, <code>trips.txt</code>, <code>routes.txt</code>, and <code>stops.txt</code> files are located in the <code>src/data/</code> directory.</p>
    <h2>Usage</h2>
    <h3>Running the Application</h3>
    <p>You can run the <code>BussArrivalScheduleApp</code> class which contains the <code>main</code> method to start the application.</p>
    <p>Example of how to get the next arrivals:</p>
    <pre>
public class Main {
    public static void main(String[] args) {
        BusArrivalDisplay app = new BusArrivalDisplay();
        int stopId = 123; // replace with actual stop ID
        int numOfBuses = 5; // replace with the number of bus arrivals you want to be displayed
        String timeFormat = "relative"; // or "absolute" for different display type of ETA
        app.displayNextArrivals(stopId, numOfBuses, timeFormat);
    }
}
    </pre>
    <h3>Example Output</h3><br>
    <p><strong>"relative":</strong></p>
    <pre>
    Loading data for stop ID: 123
    Station: Main Street
    1 arrives in: 5min, 15min, 25min
    2 arrives in: 10min, 20min, 30min
    </pre><br>
    <p><strong>"absolute":</strong></p>
    <pre>
    Loading data for stop ID: 123
    Station: Main Street
    1 arrives at: 16:20, 16:30, 16:40
    2 arrives at: 16:25, 16:35, 16:45
    </pre>
    <h2>Logging</h2>
    <p>The application uses Java's built-in logging framework. Logs are displayed in the console, showing information and errors encountered during execution.</p>
    <h2>Acknowledgements</h2>
    <p>Data files structure is inspired by the <a href="https://developers.google.com/transit/gtfs">GTFS (General Transit Feed Specification)</a> standard.</p>
