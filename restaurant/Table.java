    /*
        This class creates 10 tables of different capacity (from 2 to 6), adds them to arraylist "tables"
        and sorts them by number of free seats.

        The sorting is done as follows:
        each table has an int parameter "freeSeatsNumber" (number of free seats at the table)
        and boolean "alreadyHasPeople" (true if at least one group is already seating at that table,
        false otherwise).
        First, only unoccupied tables are sorted by seat number. After that, partially occupied ones
        are sorted (and partially occupied ones are always after unoccupied ones after sorting, so basically
        arraylist "tables" after sorting consists of 2 separate lists: first unoccupied tables, sorted by seats
        number, and after that partially occupied tables, sorted by free seats number).
        This way, we make sure that the following condition is met:
        "Groups may share tables, however if at the same time you have an empty table with the required number of
        chairs and enough empty chairs at a larger one, you must always seat your client(s) at an empty table and
        not any partly seated one, even if the empty table is bigger than the size of the group."

        Sorting is repeated each time some change happens: new group is created and seated, a small group
        that waits in line after larger one(s) jumps queue and is seated before larger one(s), a seated group
        finishes eating and leaves.

        Each table has a name that shows it's individual number and capacity, for example
        "table_06_4" is a table number 6 that seats 4 people.
     */

package restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Table  {
    int seatsNumber=0;
    int freeSeatsNumber=0;
    String name;
    boolean alreadyHasPeople=false;
    static ArrayList<Table> tables = new ArrayList<Table>();
    ArrayList <Group> sittingGroups = new ArrayList<>(6);

    public Table(int seatsNumber, String name){
        this.seatsNumber=seatsNumber;
        this.freeSeatsNumber=seatsNumber;
        this.name=name;
    }//close constructor


    @Override
    public String toString() {
        return name + "   Free seats "+freeSeatsNumber + "    " + Arrays.asList(sittingGroups);
    }


    public static void makeTables() {
        Table table_01_2 = new Table(2, "table_01_2");
        Table table_02_2 = new Table(2, "table_02_2");
        Table table_03_3 = new Table(3, "table_03_3");
        Table table_04_3 = new Table(3, "table_04_3");
        Table table_05_4 = new Table(4, "table_05_4");
        Table table_06_4 = new Table(4, "table_06_4");
        Table table_07_5 = new Table(5, "table_07_5");
        Table table_08_5 = new Table(5, "table_08_5");
        Table table_09_6 = new Table(6, "table_09_6");
        Table table_10_6 = new Table(6, "table_10_6");

        tables.add(table_01_2);
        tables.add(table_02_2);
        tables.add(table_03_3);
        tables.add(table_04_3);
        tables.add(table_05_4);
        tables.add(table_06_4);
        tables.add(table_07_5);
        tables.add(table_08_5);
        tables.add(table_09_6);
        tables.add(table_10_6);
    }//close makeTables

    static ArrayList<Table> sortTables(){
        Collections.sort(tables, new Comparator<Table>() {
            @Override
            public int compare(Table o1, Table o2) {
                return Comparator.comparing((Table t) -> t.alreadyHasPeople)
                        .thenComparing((Table t) -> t.freeSeatsNumber)
                        .compare(o1, o2);
            }
        });

        for (Table t : tables) {
            System.out.println(t);
        }//close for
        System.out.println("Currently wait in line to be seated: " + Arrays.asList(Group.groups));
        System.out.println();
        return tables;
    }//close sortTables

}//close class Table

