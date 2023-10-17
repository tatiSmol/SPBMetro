import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {
    List<Station> firstR, secondR, thirdR;
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    Station alpha, beta, gamma, delta, epsilon, zeta;

    @Override
    protected void setUp() throws Exception {
        Line l1 = new Line(1, "L1");
        Line l2 = new Line(2, "L2");
        Line l3 = new Line(3, "L3");

        stationIndex = new StationIndex();
        Stream.of(l1, l2, l3).forEach(stationIndex::addLine);

        alpha = new Station("Alpha", l1);
        beta = new Station("Beta", l1);
        gamma = new Station("Gamma", l1);
        delta = new Station("Delta", l2);
        epsilon = new Station("Epsilon", l2);
        zeta = new Station("Zeta", l3);

        Stream.of(alpha, beta, gamma, delta, epsilon, zeta)
                .peek(station -> station.getLine().addStation(station)).
                forEach(stationIndex::addStation);


        stationIndex.addConnection(Stream.of(beta, epsilon).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(delta, zeta).collect(Collectors.toList()));

        stationIndex.getConnectedStations(beta);
        stationIndex.getConnectedStations(delta);

        routeCalculator = new RouteCalculator(stationIndex);

        firstR = Stream.of(alpha, beta, gamma).collect(Collectors.toList());
        secondR = Stream.of(alpha, beta, epsilon).collect(Collectors.toList());
        thirdR = Stream.of(beta, epsilon, delta, zeta).collect(Collectors.toList());
    }

        /*
        L1          L2              L3
        alpha       delta     >>>   zeta
        beta   >>>  epsilon
        gamma
        */

    public void testGetShortestRoute() {
        List<Station> noTransfers = routeCalculator.getShortestRoute(alpha, gamma);
        List<Station> shortestWay = firstR;
        assertEquals(noTransfers, shortestWay);

        List<Station> oneTransfer = routeCalculator.getShortestRoute(alpha, epsilon);
        List<Station> littleLongerWay = secondR;
        assertEquals(oneTransfer, littleLongerWay);

        List<Station> twoTransfers = routeCalculator.getShortestRoute(beta, zeta);
        List<Station> longestWay = thirdR;
        assertEquals(twoTransfers, longestWay);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(thirdR);
        double expected = 9.5;

        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
