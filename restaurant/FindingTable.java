      /*
      Method that iterates over arraylist that contains groups waiting in line
      and puts the first group at a proper table; after that the tables are re-sorted.
      The second part of the method allows smaller groups jump queue over larger groups
      if only small-capacity tables are available.
       */

package restaurant;

import java.util.ArrayList;
import java.util.Iterator;

class FindingTable {
    static void findTable(ArrayList<Group> groups, ArrayList<Table> tables){
        Iterator<Group> iterator = Group.groups.iterator();
        for (int i=0; i<Table.tables.size();i++) {
            if (iterator.hasNext() && Group.groups.get(0).size <= Table.tables.get(i).freeSeatsNumber) {
                Table.tables.get(i).sittingGroups.add(Group.groups.get(0));
                Table.tables.get(i).freeSeatsNumber = Table.tables.get(i).freeSeatsNumber - Group.groups.get(0).size;
                Table.tables.get(i).alreadyHasPeople = true;
                Group.groups.remove(0);
                Table.sortTables();
                findTable(Group.groups, Table.tables);
            } else {   //smaller group may jump queue over bigger one
                for(int j=1;j<Group.groups.size()-1;j++){
                    if(iterator.hasNext() && Group.groups.get(j).size <= Table.tables.get(i).freeSeatsNumber){
                        Table.tables.get(i).sittingGroups.add(Group.groups.get(j));
                        Table.tables.get(i).freeSeatsNumber = Table.tables.get(i).freeSeatsNumber - Group.groups.get(j).size;
                        Table.tables.get(i).alreadyHasPeople = true;
                        Group.groups.remove(j);

                        Table.sortTables();
                        findTable(Group.groups,Table.tables);
                    }//close if
                }//close for
            }//close if
        }//close for
    }//close findTable
}//close class
