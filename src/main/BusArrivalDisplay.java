package main;

import model.Route;
import model.Stop;
import model.StopTime;
import model.Trip;
import utils.FileReader;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusArrivalDisplay {

    private static final Logger LOGGER = Logger.getLogger(BusArrivalDisplay.class.getName());
    private static final String DATA_DIR = "src/data/";
    private static final String STOP_TIMES_FILE = DATA_DIR + "stop_times.txt";
    private static final String TRIPS_FILE = DATA_DIR + "trips.txt";
    private static final String ROUTES_FILE = DATA_DIR + "routes.txt";
    private static final String STOPS_FILE = DATA_DIR + "stops.txt";

    private final Map<Integer, List<StopTime>> stopTimesByStopId = new HashMap<>();
    private final Map<String, Trip> tripsByTripId = new HashMap<>();
    private final Map<Integer, Route> routesById = new HashMap<>();
    private Stop stop;

    public void loadDataFromFiles(int stopId) {
        LocalTime currentTime = LocalTime.now();
        Set<String> passingTrips = getArrivalsForStop(stopId, currentTime);
        Set<Integer> crossingRoutes = readTripsFromFile(passingTrips);
        readRoutesFromFile(crossingRoutes);
        getStopById(stopId);
    }

    private Set<String> getArrivalsForStop(int stopId, LocalTime currentTime) {
        Set<String> passingTripIDs = new HashSet<>();
        List<String[]> stopTimesData = FileReader.readFile(STOP_TIMES_FILE);

        for (String[] row : stopTimesData) {
            try {
                LocalTime arrivalTime = LocalTime.parse(row[1]);
                int rowStopId = Integer.parseInt(row[3]);

                if (rowStopId == stopId && !arrivalTime.isBefore(currentTime) && arrivalTime.isBefore(currentTime.plusHours(2))) {
                    StopTime stopTime = new StopTime(row[0], arrivalTime, rowStopId);
                    stopTimesByStopId.computeIfAbsent(stopId, _ -> new ArrayList<>()).add(stopTime);
                    passingTripIDs.add(row[0]);
                }
            } catch (Exception e) {
                logParsingError("stop times", row, e);
            }
        }
        return passingTripIDs;
    }

    private Set<Integer> readTripsFromFile(Set<String> passingTripIDs) {
        Set<Integer> crossingRoutes = new HashSet<>();
        List<String[]> tripsData = FileReader.readFile(TRIPS_FILE);

        for (String[] row : tripsData) {
            try {
                if (passingTripIDs.contains(row[2])) {
                    Trip trip = new Trip(row[2], Integer.parseInt(row[0]));
                    tripsByTripId.put(trip.getTripId(), trip);
                    crossingRoutes.add(trip.getRouteId());
                }
            } catch (Exception e) {
                logParsingError("trips", row, e);
            }
        }
        return crossingRoutes;
    }

    private void readRoutesFromFile(Set<Integer> crossingRoutes) {
        List<String[]> routes = FileReader.readFile(ROUTES_FILE);

        for (String[] row : routes) {
            try {
                int routeId = Integer.parseInt(row[0]);
                if (crossingRoutes.contains(routeId) && !routesById.containsKey(routeId)) {
                    routesById.put(routeId, new Route(routeId));
                }
            } catch (Exception e) {
                logParsingError("routes", row, e);
            }
        }
    }

    private void getStopById(int stopId) {
        List<String[]> stops = FileReader.readFile(STOPS_FILE);

        for (String[] row : stops) {
            try {
                if (Integer.parseInt(row[0]) == stopId) {
                    stop = new Stop(stopId, row[2]);
                    return;
                }
            } catch (Exception e) {
                logParsingError("stops", row, e);
            }
        }
    }

    private void logParsingError(String fileType, String[] row, Exception e) {
        LOGGER.log(Level.SEVERE, String.format("Error parsing %s data: %s", fileType, Arrays.toString(row)), e);
    }

    public void displayNextArrivals(int stopId, int numOfBuses, String timeFormat) {
        LOGGER.log(Level.INFO, String.format("Loading data for stop ID: %d", stopId));
        loadDataFromFiles(stopId);

        List<StopTime> times = stopTimesByStopId.getOrDefault(stopId, Collections.emptyList());
        times.sort(Comparator.comparing(StopTime::getArrivalTime));

        Map<Integer, List<LocalTime>> timesByRoute = new HashMap<>();
        for (StopTime time : times) {
            Trip trip = tripsByTripId.get(time.getTripId());
            if (trip != null) {
                timesByRoute.computeIfAbsent(trip.getRouteId(), _ -> new ArrayList<>()).add(time.getArrivalTime());
            }
        }

        LOGGER.log(Level.INFO, String.format("Printing next buses for stop ID: %d", stopId));
        if (stop != null) {
            System.out.printf("Station: %s%n", stop.getStopName());
        } else {
            System.out.printf("No information available for stop ID: %d%n", stopId);
            return;
        }

        for (Map.Entry<Integer, List<LocalTime>> entry : timesByRoute.entrySet()) {
            Route route = routesById.get(entry.getKey());
            List<LocalTime> arrivalTimes = entry.getValue();
            arrivalTimes.sort(Comparator.naturalOrder());

            StringBuilder timesString = new StringBuilder(String.format("%d arrives %s", route.getRouteId(), timeFormat.equals("relative") ? "in: " : "at: "));

            int count = 0;
            for (LocalTime time : arrivalTimes) {
                if (count >= numOfBuses) break;
                if (count > 0) timesString.append(", ");
                timesString.append(formatTime(time, timeFormat));
                count++;
            }

            System.out.println(timesString);
        }
    }

    public String formatTime(LocalTime time, String format) {
        if ("relative".equals(format)) {
            long minutes = ChronoUnit.MINUTES.between(LocalTime.now(), time);
            return String.format("%dmin", minutes);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return time.format(formatter);
        }
    }
}
