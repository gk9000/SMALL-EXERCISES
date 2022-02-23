        /*
        Your restaurant has a set of tables of different sizes: each table can accommodate 2, 3, 4, 5 or 6 persons.
        Clients arrive alone or in groups, up to 6 persons. Clients within a given group must be seated together
        at one table, hence you can direct a group only to a table, which can accommodate them all. If there is
        no table with the required number of empty chairs, the group has to wait in the queue.

        Once seated, the group cannot change the table, i.e. you cannot move a group from one table to another
        to make room for new clients.

        Client groups must be served in the order of arrival with one exception: if there is enough room at a table
        for a smaller group arriving later, you can seat them before the larger group(s) in the queue. For example,
        if there is a six-person group waiting for a six-seat table and there is a two-person group queuing or
        arriving you can send them directly to a table with two empty chairs.

        Groups may share tables, however if at the same time you have an empty table with the required number of
        chairs and enough empty chairs at a larger one, you must always seat your client(s) at an empty table and
        not any partly seated one, even if the empty table is bigger than the size of the group.

        Of course the system assumes that any bigger group may get bored of seeing smaller groups arrive and get
        their tables ahead of them, and then decide to leave, which would mean that they abandon the queue without being served.
        */

package restaurant;

import java.util.*;
import java.util.function.Supplier;



public class Group implements Runnable {
    Random random = new Random();
    static ArrayList<Group> groups = new ArrayList<Group>() {};
    int size;
    int id = 0;
    static int i = 1;

    public Group(int size, int id) {
        this.size = size;
        this.id = id;
    }//close constructor
    public Group() {
    }//close constructor

    // "i" is used in manageGroups method to keep track of how many customers' groups are produced. I have limited
    // the number to 1000 as above it stack overflow happens on my computer. This method (rememberI)  is needed
    // to prevent "i" from being reset when manageGroups method is called from within itself
    static int rememberI() {
        i++;
        return i;
    }//close rememberI

    // This method makes sure group's id is not reset when manageGroups method is called from within itself
    int rememberId() {
        id++;
        return id;
    }//close rememberId

    // Providing 2 seconds break between creating customers' groups
    @Override
    public void run() {
        Runnable runnable = new Group();
        Thread thread = new Thread(runnable);
        try {
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//close catch
    }//close run

    @Override
    public String toString() {
        return "group of " + size + " (id " + id + ")";
    }


    // This method deals with all aspects of customers' groups - creates them and puts in line, decides when a group
    // still waiting in line makes decision not to wait any longer and leave, decides when a group eating finishes eating and leaves.
    // The actual process of seating groups at tables is managed by FindingTable class
    public void manageGroups() {

        // Supplier that creates new random groups of customers of 1 to 6 people. Each groups is given an individual id number
        // (method rememberId keeps track of those numbers and makes sure id is not reset when manageGroups method is called
        // from within itself)
        Supplier<Group> groupMaker = () -> {
            Group group = new Group(random.nextInt(6) + 1, rememberId());
            System.out.println("Created new " + group);
            return group;
        }; //close Supplier

        // If there are groups waiting in line to be seated, some of them may randomly decide to leave
        // and are removed from arraylist of waiting groups
        if (groups.size() > 0 && random.nextBoolean() && random.nextBoolean() && random.nextBoolean()) {
            int a = random.nextInt(groups.size());
            System.out.println("The group at index " + a + " (" + groups.get(a) + ")" + " decided not to wait for free table and has left the line");
            groups.remove(a);
        }//close if


        // After the first 10 groups are created and seated, some already seated groups randomly finish eating,
        // get up and leave. After each such event, tables are re-sorted and FindingTable class attempts to
        // seat groups waiting in line (if any)
        int a = random.nextInt(Table.tables.size());

        if (i > 10 && Table.tables.get(a).sittingGroups.size() > 0 && random.nextBoolean()) {
            int b = random.nextInt(Table.tables.get(a).sittingGroups.size());
            int temp = Table.tables.get(a).sittingGroups.get(b).size;
            Table.tables.get(a).sittingGroups.remove(b);
            System.out.println("The group with index " + b + " at table index " + a + " has finished eating and left");
            Table.tables.get(a).freeSeatsNumber = Table.tables.get(a).freeSeatsNumber + temp;
            if (Table.tables.get(a).freeSeatsNumber == Table.tables.get(a).seatsNumber) {
                Table.tables.get(a).alreadyHasPeople = false;
            }//close if
            Table.sortTables();
            FindingTable.findTable(Group.groups, Table.tables);
            manageGroups();
        } //close if

        // creating new groups (by using supplier) and adding them to the line, waiting for 2 seconds
        // (overridden "run" method), trying to seat waiting groups (by FindingTable class) and recursively
        // calling manageGroups method from within itself. The groups' number is limited to 1000.
        while (i <= 1000) {
            if (random.nextBoolean()) {
                groups.add(groupMaker.get());
                run();
                i=rememberI();
                FindingTable.findTable(Group.groups, Table.tables);
                manageGroups();
            }//close if
        } //close for

    } //close manageGroups


    public static void main(String[] args) {
        Table.makeTables();
        Group objectGroup = new Group();
        objectGroup.manageGroups();
    }//close main
}//close class


