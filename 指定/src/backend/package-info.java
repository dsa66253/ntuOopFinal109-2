
/**
 * 
 * 
 * Version 1.4<br>
 * Modified at 0624.<br>
 * Coomments: add MealManagerSingle.java to implement design pattern-Singleton<br><br>

 * 
 * This is the backend package helping you read and write our database.<br><br>
 * 
 * How to install?<br>
 * Directly put the BOTH backend directory and org directory under the ../PROJECTNAME/src .<br><br>
 * 
 * 
 * How to import?<br>
 * Add "import backend.*" at the top of your code, and then you can use those classes.<br><br>
 * 
 * How to use?<br>
 * Each class represent the each json files provided by TA according to the class name and json file name.<br>
 * And those json files are put under the ../backend/file .<br><br>
 * 
 * Warning: The directory, ../backend/file , is our database, so if you overwrite any file. The package might go wrong.<br>
 * For the security of purpose, except for TicketManager and Ticket which can write or create a json file, the other classes can ONLY read the file.<br>
 * This is to say, we only provide getter method for them (the other classes).<br><br>
 * 
 * 
 * Version 1.3.0<br>
 * Modified at 0624.<br>
 * Coomments: add meal.json MealManager.java Order.java<br><br>
 * 
 * Version 1.2.0<br>
 * Modified at 0612.<br>
 * Coomments: add seat directory, and we can operate each XXXX-XXXX-seat.json respectively.<br>
 * By doing this, you need to input trainNo, and StationId as parameter to constructor. <br><br>
 * 
 * Version 1.1.2<br>
 * Modified at 0606.<br>
 * Coomments: Show all of the examples to get each value for each class in main()<br><br>
 * 
 * Update log.<br>
 * Version 1.1.1.<br>
 * Modified at 0602.<br>
 * Coomments: There is a change in Ticket Class. I change the way to get "ticketsCount" into "ticketsCountA" and "ticketsCountB" in booking.json<br><br>
 * @author kobemary
 */

package backend;