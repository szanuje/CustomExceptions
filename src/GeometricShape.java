import java.util.*;

class GeometricShape implements GeometricShapeInterface {

    public static void main(String args[]) {
        GeometricShape s = new GeometricShape();

        Point p1 = new Point(3);
        p1.setPosition(0, 1);
        p1.setPosition(1, 1);
        p1.setPosition(2, 1);

        Point p2 = new Point(2);
        p2.setPosition(0, 4);
        p2.setPosition(1, 15);
        //p2.setPosition(2, 15);

        Point p3 = new Point(3);
        p3.setPosition(0, 1);
        p3.setPosition(1, 1);
        p3.setPosition(2, 1);

        Point p4 = new Point(3);
        p4.setPosition(0, 1);
        p4.setPosition(1, 1);
        p4.setPosition(2, 11);

        Point p5 = new Point(3);
        p5.setPosition(0, 1);
        p5.setPosition(1, 11);
        p5.setPosition(2, 1);

        Point p6 = new Point(3);
        p6.setPosition(0, 1);
        p6.setPosition(1, 1);
        p6.setPosition(2, 1);

        try {
            s.add(p1);
        } catch (WrongNumberOfDimensionsException e) {

        }
//        try {
//            s.add(p2);
//        } catch (WrongNumberOfDimensionsException e) {
//
//        }
        try {
            s.add(p3);
        } catch (WrongNumberOfDimensionsException e) {

        }
        try {
            s.add(p4);
        } catch (WrongNumberOfDimensionsException e) {

        }
        try {
            s.add(p5);
        } catch (WrongNumberOfDimensionsException e) {

        }
        try {
            s.add(p6);
        } catch (WrongNumberOfDimensionsException e) {

        }

        System.out.println(s.get());

        try {
            s.addBefore(p3,p2);
        } catch (Exception e) {

        }
        System.out.println(s.get());


        System.out.println(s.getSetOfPoints());

        List<Integer> i = new ArrayList<>();
        i.add(1);
        i.add(1);
        i.add(1);

        try {

            System.out.println(s.getByPosition(i));
        } catch (WrongNumberOfDimensionsException e) {

        }

    }

    private List<BetterPoint> pointList;
    private int dimensions = 0;
    private int counter = 1;

    private class BetterPoint {

        private int count;
        private Point point;

        BetterPoint(Point p) {
            this.point = p;
            this.count = counter;

        }

        private Point getPoint() {
            return point;
        }

        private int getCount() {
            return count;
        }
    }

    public GeometricShape() {

        this.pointList = new ArrayList<>();

    }


    /**
     * Metoda dodaje punkt. Punkt dodawany na koniec kolekcji.
     *
     * @param point dodawany punkt
     * @throws WrongNumberOfDimensionsException wyjątek zgłaszany, gdy nowododawany
     *                                          punkt posiada inną liczbę wymiarów
     *                                          niż te, które już wcześniej przed
     *                                          nim dodano. O poprawnej liczbie
     *                                          wymiarów decyduje <b>pierwszy</b>
     *                                          punkt dodany do kształtu.
     */
    @Override
    public void add(Point point) throws WrongNumberOfDimensionsException {

        int thisDimensions = 0;
        if (pointList.size() > 0) {

            for (int i = 0; i < 100; i++) {
                try {
                    point.getPosition(i);
                } catch (ArrayIndexOutOfBoundsException e) {
                    thisDimensions = i;
                    break;
                }
            }

            if (thisDimensions != dimensions) {
                System.out.println("add - wrong number of dimensions");

                throw new WrongNumberOfDimensionsException(dimensions, thisDimensions);
            } else {
                pointList.add(new BetterPoint(point));
                counter++;

            }

        } else {

            for (int i = 0; i < 100; i++) {
                try {
                    point.getPosition(i);
                } catch (ArrayIndexOutOfBoundsException e) {
                    dimensions = i;
                    pointList.add(new BetterPoint(point));
                    counter++;
                    break;
                }
            }

        }


    }


    /**
     * Metoda usuwa punkt point, o ile taki istnieje. Jeśli w kolekcji punktów jest
     * więcej takich samych jak point, usuwany jest tylko pierwszy z nich.
     *
     * @param point punkt do usunięcia
     * @throws WrongArgumentException wyjątek zgłaszany gdy zlecane jest usunięcie
     *                                punktu, który nie należy do figury.
     */
    @Override
    public void remove(Point point) throws WrongArgumentException {

        boolean b = false;
        for (BetterPoint i : pointList) {
            if (i.getPoint().equals(point)) {
                pointList.remove(i);

                b = true;

                break;
            }
        }

        if (!b) {
            System.out.println("remove - wrong argument exception");

            throw new WrongArgumentException(point);
        }

    }

    /**
     * Metoda dodaje punkt przed punktem beforePoint.
     *
     * @param point       dodawany punkt
     * @param beforePoint punkt, bezpośrednio przed którym nowy należy dodać
     * @throws WrongArgumentException           wyjątek zgłaszany, gdy jako
     *                                          beforePoint przekazany został punkt,
     *                                          który nie został wcześniej dodany do
     *                                          figury.
     * @throws WrongNumberOfDimensionsException wyjątek zgłaszany, gdy liczba
     *                                          wymiarów punktu point lub
     *                                          beforePoint nie jest zgodna z liczbą
     *                                          wymiarów punktów dodanych wcześniej
     *                                          do kształtu.
     */
    @Override
    public void addBefore(Point point, Point beforePoint)
            throws WrongArgumentException, WrongNumberOfDimensionsException {

        boolean b = false;

        int thisDimensions = 0;
        for (int i = 0; i < 100; i++) {
            try {
                point.getPosition(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                thisDimensions = i;
                break;
            }
        }

        int thatDimensions = 0;
        for (int i = 0; i < 100; i++) {
            try {
                beforePoint.getPosition(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                thatDimensions = i;
                break;
            }
        }

        if (thatDimensions != dimensions) {

            System.out.println("addBefore1 - wrong number of dimensions");
            throw new WrongNumberOfDimensionsException(dimensions, thatDimensions);
        }

        if (thisDimensions != dimensions) {

            System.out.println("addBefore - wrong number of dimensions");
            throw new WrongNumberOfDimensionsException(dimensions, thisDimensions);
        }



        for (BetterPoint i : pointList) {
            if (i.getPoint().equals(beforePoint)) {

                pointList.add(pointList.indexOf(i), new BetterPoint(point));
                counter++;

                b = true;
                break;
            }
        }

        if (!b) {
            System.out.println("addBefore - wrong argument exception");
            throw new WrongArgumentException(beforePoint);
        }


    }

    /**
     * Metoda dodaje punkt za punktem afterPoint.
     *
     * @param point      dodawany punkt
     * @param afterPoint punkt, bezpośrednio za którym nowy należy dodać
     * @throws WrongArgumentException           wyjątek zgłaszany, gdy jako
     *                                          afterPoint przekazany został punkt,
     *                                          który nie został wcześniej dodany do
     *                                          figury.
     * @throws WrongNumberOfDimensionsException wyjątek zgłaszany, gdy liczba
     *                                          wymiarów punktu point lub afterPoint
     *                                          nie jest zgodna z liczbą wymiarów
     *                                          punktów dodanych wcześniej do
     *                                          kształtu.
     */
    @Override
    public void addAfter(Point point, Point afterPoint)
            throws WrongNumberOfDimensionsException, WrongArgumentException {

        boolean b = false;

        int thisDimensions = 0;
        for (int i = 0; i < 100; i++) {
            try {
                point.getPosition(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                thisDimensions = i;
                break;
            }
        }

        int thatDimensions = 0;
        for (int i = 0; i < 100; i++) {
            try {
                afterPoint.getPosition(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                thatDimensions = i;
                break;
            }
        }

        if (thatDimensions != dimensions) {

            System.out.println("addAfter - wrong number of dimensions");
            throw new WrongNumberOfDimensionsException(dimensions, thatDimensions);
        }


        if (thisDimensions != dimensions) {

            System.out.println("addAfter - wrong number of dimensions");
            throw new WrongNumberOfDimensionsException(dimensions, thisDimensions);
        }




        for (int i = pointList.size() - 1; i >= 0; i--) {
            if (pointList.get(i).getPoint().equals(afterPoint)) {
                pointList.add(i + 1, new BetterPoint(point));
                counter++;

                b = true;
                break;
            }
        }

        if (!b) {
            System.out.println("addAfter - wrong argument exception");
            throw new WrongArgumentException(afterPoint);
        }

    }

    /**
     * Metoda usuwa punkt przed punktem beforePoint.
     *
     * @param beforePoint punkt istniejący bezpośrednio przed beforePoint należy
     *                    usunąć.
     * @return Gdy punkt odniesienia istniał oraz istniał punkt do usunięcia,
     * zwracana jest referencja do usuniętego punktu.
     * @throws NoSuchPointException             wyjątek zgłaszany, gdy punkt
     *                                          beforePoint jest pierwszym z punktów
     *                                          figury i innego punktu przed nim nie
     *                                          ma.
     * @throws WrongArgumentException           wyjątek zgłaszany, gdy punkt
     *                                          beforePoint nie został wcześniej
     *                                          dodany do figury.
     * @throws WrongNumberOfDimensionsException wyjątek zgłaszany, gdy liczba
     *                                          wymiarów punktu beforePoint nie jest
     *                                          zgodna z liczbą wymiarów punktów
     *                                          dodanych wcześniej do kształtu.
     */
    @Override
    public Point removeBefore(Point beforePoint)
            throws NoSuchPointException, WrongNumberOfDimensionsException, WrongArgumentException {

        if (pointList.get(0).getPoint().equals(beforePoint)) {
            System.out.println("removeBefore - no such point");
            throw new NoSuchPointException(beforePoint);
        }

        int thisDimensions = 0;
        for (int i = 0; i < 100; i++) {
            try {
                beforePoint.getPosition(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                thisDimensions = i;
                break;
            }
        }

        if (thisDimensions != dimensions) {

            System.out.println("removeBefore - wrong number of dimensions");
            throw new WrongNumberOfDimensionsException(dimensions, thisDimensions);
        }

        boolean b = false;

        Point p = null;
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).getPoint().equals(beforePoint)) {
                if (i > 0) {
                    p = pointList.get(i - 1).getPoint();
                    pointList.remove(i - 1);

                    b = true;
                    break;
                } else {
                    break;
                }
            }
        }
        if (!b) {
            System.out.println("removeBefore - wrong argument exception");
            throw new WrongArgumentException(beforePoint);
        }

        return p;
    }

    /**
     * Metoda usuwa punkt za punktem afterPoint.
     *
     * @param afterPoint punkt istniejący bezpośrednio po afterPoint należy usunąć.
     * @return Gdy punkt odniesienia istniał oraz istniał punkt do usunięcia,
     * zwracana jest referencja do usuniętego punktu.
     * @throws NoSuchPointException             wyjątek zgłaszany, gdy punkt
     *                                          beforePoint jest pierwszym z punktów
     *                                          figury i innego punktu przed nim nie
     *                                          ma.
     * @throws WrongArgumentException           wyjątek zgłaszany, gdy punkt
     *                                          beforePoint nie został wcześniej
     *                                          dodany do figury.
     * @throws WrongNumberOfDimensionsException wyjątek zgłaszany, gdy liczba
     *                                          wymiarów punktu beforePoint nie jest
     *                                          zgodna z liczbą wymiarów punktów
     *                                          dodanych wcześniej do kształtu.
     */

    @Override
    public Point removeAfter(Point afterPoint)
            throws NoSuchPointException, WrongNumberOfDimensionsException, WrongArgumentException {

        if (pointList.get(pointList.size() - 1).getPoint().equals(afterPoint)) {
            System.out.println("removeBefore - no such point");
            throw new NoSuchPointException(afterPoint);
        }

        int thisDimensions = 0;
        for (int i = 0; i < 100; i++) {
            try {
                afterPoint.getPosition(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                thisDimensions = i;
                break;
            }
        }

        if (thisDimensions != dimensions) {

            System.out.println("removeAfter - wrong number of dimensions");
            throw new WrongNumberOfDimensionsException(dimensions, thisDimensions);
        }

        boolean b = false;

        Point p = null;
        for (int i = pointList.size() - 1; i >= 0; i--) {
            if (pointList.get(i).getPoint().equals(afterPoint)) {
                if (i < pointList.size() - 1) {
                    p = pointList.get(i + 1).getPoint();
                    pointList.remove(i + 1);

                    b = true;
                    break;
                } else {
                    break;
                }
            }
        }

        if (!b) {
            System.out.println("removeAfter - wrong argument exception");
            throw new WrongArgumentException(afterPoint);
        }

        return p;
    }

    /**
     * Metoda zwraca aktualną listę wszystkich punktów.
     *
     * @return lista punktów
     */
    @Override
    public List<Point> get() {

        List<Point> l = new ArrayList<>();
        for (BetterPoint p : pointList) {
            l.add(p.getPoint());
        }
        return l;
    }

    /**
     * Metoda zwraca zbiór punktów czyli kolekcję punktów bez powtórzeń. Kolejność
     * punktów w tej kolekcji nie ma znaczenia. Powtórzenie punktu ma miejsce wtedy,
     * gdy P1.equals(P2)=true.
     *
     * @return kolekcja punktów bez powtórzeń.
     */
    @Override
    public Set<Point> getSetOfPoints() {

        Set<Point> l = new HashSet<>();
        for (BetterPoint p : pointList) {
            l.add(p.getPoint());
        }
        return l;

    }

    /**
     * Metoda zwraca obiekt typu Optional zawierający (o ile istnieje) punkt,
     * którego współrzędne przekazywane są na liście positions. Jeśli istnieje wiele
     * punktów o podanych wspołrzędnych zwracany jest punkt, który został dodany
     * jako ostatni. Metoda nigdy nie może zakończyć się zwróceniem null, jeśli
     * punktu o podanych współrzędnych nie ma, należy zwrócić pusty obiekt Optional.
     *
     * @param positions lista współrzędnych
     * @return obiekt Optional zawierający (o ile istnieje) punkt, o przekazanych za
     * pomocą positions współrzędnych, w przeciwnym wypadku pusty obiekt
     * Optional.
     * @throws WrongNumberOfDimensionsException wyjątek zgłaszany, gdy rozmiar listy
     *                                          jest niezgodny z liczbą wymiarów
     *                                          punktów należących do kształtu.
     */
    @Override
    public Optional<Point> getByPosition(List<Integer> positions) throws WrongNumberOfDimensionsException {


        if (positions.size() != dimensions) {
            System.out.println("getByPosition - wrong number of dimensions");
            throw new WrongNumberOfDimensionsException(dimensions, positions.size());
        } else {

            Optional<Point> o = Optional.empty();

            Point p = new Point(positions.size());
            for (int i = 0; i < positions.size(); i++) {
                p.setPosition(i, positions.get(i));
            }
            List<BetterPoint> points = new ArrayList<>();

            for (int i = 0;i < pointList.size(); i++) {

                if (pointList.get(i).getPoint().equals(p)) {

                    points.add(pointList.get(i));


                }
            }
            System.out.println(points);
            if (points.size() > 0) {
                BetterPoint latest = points.get(0);
                int i = latest.getCount();


                for (BetterPoint x : points) {

                    if (x.getCount() > i) {
                        i = x.getCount();
                        latest = x;
                    }
                }
                o = Optional.of(latest.getPoint());
            }


            return o;


        }
    }
}

