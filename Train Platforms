//This program calculates min number of platforms that a train station must have in order
//to be able to accommodate all trains from a given schedule. The schedule is set by creating "Train" objects in main method.
// The program will properly deal with trains that arrive before midnight and depart after,
// but is not designed to deal with situations when a train spends at the station more than 24 hours.


import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;

public class TrainPlatforms {

    static ArrayList<Train> trains = new ArrayList<>();

    private int calculateNumberOfPlatforms(ArrayList<Train> trains){
        int permCounter=0; //this counter shows max number of tracks needed

        //define start and end of 24-hours period
        LocalTime startTime=LocalTime.of(0, 0);
        LocalTime endTime=LocalTime.of(23, 59);

        //set up test time moment, moving that moment forward by one minute with each iteration
        FIRST: for(LocalTime i = startTime; i.compareTo(endTime)<0; i=i.plusMinutes(1)) {

            int tempCounter=0;//this counter shows max number of tracks needed at each iteration

            //runs each train through test time
            SECOND: for (Train train : trains) {

               THIRD: if((train.getArrival()).isBefore(train.getDeparture())) {
                    tempCounter = calculateTempCounter(i, train, tempCounter);
                } else {
                //FORTH block deals with trains that arrive before midnight and depart after midnight
                   FORTH:
                   if (i.isAfter(LocalTime.of(0, 0)) && i.isBefore(train.getDeparture())) {
                       tempCounter++;
                   } else if (i.isBefore(LocalTime.of(23, 59)) && i.isAfter(train.getArrival())) {
                       tempCounter++;
                   }//close FORTH
               }//close THIRD

                if (tempCounter > permCounter) {
                    permCounter = tempCounter;
                }//close if
            }//close SECOND
        }//close FIRST
       return permCounter;
    }//close calculatePlatforms

    private int calculateTempCounter(LocalTime i, Train train, int tempCounter){
        if (i.isAfter(train.getArrival()) && i.isBefore(train.getDeparture())) {
            tempCounter++;
        }//close if
        return tempCounter;
    }//close calculateTempCounter

    public static void main(String[] args) throws DateTimeException{

        //create trains with their times of arrival at station and departure from station, place them in collection
            try {
                Train tr1 = new Train(LocalTime.of(23, 0), LocalTime.of(1, 0));
                Train tr2 = new Train(LocalTime.of(23, 0), LocalTime.of(23, 5));
                Train tr3 = new Train(LocalTime.of(0, 25), LocalTime.of(0, 45));
                Train tr4 = new Train(LocalTime.of(23, 0), LocalTime.of(0, 18));
                Train tr5 = new Train(LocalTime.of(0, 45), LocalTime.of(2, 0));
                Train tr6 = new Train(LocalTime.of(22, 45), LocalTime.of(23, 50));
                Train tr7 = new Train(LocalTime.of(15, 45), LocalTime.of(16, 50));
                Train tr8 = new Train(LocalTime.of(16, 0), LocalTime.of(16, 10));


            TrainPlatforms tP = new TrainPlatforms();

            System.out.println(tP.calculateNumberOfPlatforms(trains));

                   } catch (DateTimeException e){
                        System.out.println("Hours should be within 0-23 and minutes within 0-59 range");
                   }//close catch
    }//close main
}//close TrainPlatforms

class Train {
    private LocalTime arrival;
    private LocalTime departure;
    public Train (LocalTime arrival, LocalTime departure){
        this.arrival=arrival;
        this.departure=departure;
        TrainPlatforms.trains.add(this);
    }//close constructor

    public LocalTime getArrival(){
        return arrival;
    }//close getArrival
    public LocalTime getDeparture(){
        return departure;
    }//close getDeparture
   }//close Train
