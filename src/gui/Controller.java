package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import BookingSystem.*;
import backend.SoldBuySeatException;
import backend.TicketManager;
import ordering.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import Model and View
public class Controller {
	private int index;
	private int result;
	private TicketManager aTicketManager;
	private generalBooking order;
	private Unbooking unbooking;
	private LookInto lookinto;
	private HomeView homeView;
	private BookView bookView;
	private BookView_1 bookView_1;
	private BookReturnView bookReturnView;
	private ChangeView changeView;
	private SearchView searchView;
	private SearchBookView searchBookView;
	private SearchReferNumView searchReferNumView;
	private SearchTimetableView searchTimetableView;
	private SearchDiscountView searchDiscountView;
	private OrderingMsg msg;
	private LoginView loginView;
	private MealView mealView;
	private OrderMealView orderMealView;
	private SearchMealView searchMealView;
	private CancelMealView cancelMealView;

	public Controller() {
		index = 1;
		aTicketManager = new TicketManager();
		order = new generalBooking();
		unbooking = new Unbooking();
		lookinto = new LookInto();

		homeView = new HomeView();
		homeView.addGoBookButtonListener(new GoBookButtonListener());
		homeView.addGoChangeButtonListener(new GoChangeButtonListener());
		homeView.addGoSearchButtonListener(new GoSearchButtonListener());
		homeView.addGoMealButtonListener(new GoMealButtonListener());

		bookView = new BookView();
		bookView.addSearchingButtonListener(new SearchingButtonListener());
		bookView.addBookingButtonListener(new BookingButtonListener());
		bookView.addHomeButtonListener(new GoHomeListener());

		bookView_1 = new BookView_1();
		bookView_1.addSearchingButtonListener_1(new SearchingButtonListener_1());
		bookView_1.addBookingButtonListener_1(new BookingButtonListener_1());
		bookView_1.addHomeButtonListener(new GoHomeListener());
		
		bookReturnView = new BookReturnView();
		bookReturnView.addSearchingButtonListener_2(new SearchingButtonListener_2());
		bookReturnView.addBookingButtonListener_2(new BookingButtonListener_2());
		bookReturnView.addHomeButtonListener(new GoHomeListener());

		changeView = new ChangeView();
		changeView.addEnterButtonListener(new EnterButtonListener_1());
		changeView.addHomeButtonListener(new GoHomeListener());

		searchView = new SearchView();
		searchView.addSearchBookListener(new GoSearchBookListener());
		searchView.addSearchReferNumListener(new GoSearchReferNumListener());
		searchView.addSearchTimetableListener(new GoSearchTimetableListener());
		searchView.addSearchDiscountListener(new GoSearchDiscountListener());
		searchView.addHomeButtonListener(new GoHomeListener());

		searchBookView = new SearchBookView();
		searchBookView.addSearchingButtonListener(new GoSearchButtonListener_1());
		searchBookView.addHomeButtonListener(new GoHomeListener());

		searchReferNumView = new SearchReferNumView();
		searchReferNumView.addSearchingButtonListener(new GoSearchButtonListener_2());
		searchReferNumView.addHomeButtonListener(new GoHomeListener());

		searchTimetableView = new SearchTimetableView();
		searchTimetableView.addSearchingButtonListener(new GoSearchButtonListener_3());
		searchTimetableView.addHomeButtonListener(new GoHomeListener());

		searchDiscountView = new SearchDiscountView();
		searchDiscountView.addSearchingButtonListener(new GoSearchButtonListener_4());
		searchDiscountView.addHomeButtonListener(new GoHomeListener());
		
		msg = new OrderingMsg();
		
		loginView = new LoginView();
		loginView.addSearchingButtonListener(new GoSearchButtonListener_5());
		loginView.addHomeButtonListener(new GoHomeListener());
		
		mealView = new MealView();
		mealView.addOrderMealListener(new GoOrderMealListener());
		mealView.addSearchMealListener(new GoOrderMealListener());
		mealView.addCancelMealListener(new GoOrderMealListener());
		
		
		
		orderMealView = new OrderMealView();
		
		searchMealView = new SearchMealView();
		
		cancelMealView = new CancelMealView();

		homeView.setVisible(true);
	}
	
	//創建class定義傳入各介面方法的物件

	// GoHomeView
	public class GoHomeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			homeView.setVisible(true);
			bookView.setVisible(false);
			bookView.clear();
			bookView_1.setVisible(false);
			bookView_1.clear();
			bookReturnView.setVisible(false);
			bookReturnView.clear();
			changeView.setVisible(false);
			changeView.clear();
			searchView.setVisible(false);
			searchBookView.setVisible(false);
			searchBookView.clear();
			searchBookView.setVisible(false);
			searchBookView.clear();
			searchReferNumView.setVisible(false);
			searchReferNumView.clear();
			searchTimetableView.setVisible(false);
			searchTimetableView.clear();
			searchDiscountView.setVisible(false);
			searchDiscountView.clear();
			loginView.setVisible(false);
			loginView.clear();
		}
	}

	// HomeView Begins
	public class GoBookButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			result = JOptionPane.showConfirmDialog(bookView, "是否訂購回程票", "", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.NO_OPTION) {
				bookView.setVisible(true);
				homeView.setVisible(false);
			}
			if (result == JOptionPane.YES_OPTION) {
				bookView_1.setVisible(true);
				homeView.setVisible(false);
			}
		}
	}

	public class GoChangeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			changeView.setVisible(true);
			homeView.setVisible(false);
		}
	}

	public class GoSearchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchView.setVisible(true);
			homeView.setVisible(false);
		}
	}

	public class GoMealButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			loginView.setVisible(true);
			homeView.setVisible(false);
		}
	}
	// HomeView Ends

	// BookView Begins
	public class SearchingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setCode(index);
			aTicketManager = new TicketManager();
			try {
				order.setRoundTrip("0");
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			try {
				order.setUid(bookView.getuidIn());
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			order.setStartStationID(bookView.getStationFromID());
			order.setEndStationID(bookView.getStationToID());
			order.setTicketType(bookView.getStandardOrBusiness());
			order.setPreference(bookView.getSeatPrefer());
			try {
				order.setGeneralTicket(String.valueOf(bookView.getNumberOfTicket()));
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			try {
				order.setUniversityTicket(String.valueOf(bookView.getNumberOfDiscount()));
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			String searchChoice = bookView.getOrderType();
			if (searchChoice.equals("0")) {
				order.setDate(bookView.getTimeIn());
				try {
					order.findValidTrainNo(bookView.getToTime());
				} catch (SoldBuySeatException e2) {
					e2.printStackTrace();
				}
				try {
					bookView.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else if (searchChoice.equals("1")) {
				order.setDate(bookView.getTimeIn());
				order.setListOfValidTrainNo2(bookView.getTrainNumber());
				try {
					bookView.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else
				System.out.println("Invalid input!");
		}
	}

	public class BookingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setValidIndex(bookView.getOrderIndex());
			try {
				order.sellTicket();
			} catch (SoldBuySeatException e1) {
				e1.printStackTrace();
			}
			try {
				order.paymentDeadlineCalculator();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			order.totalPriceCalculator();
			aTicketManager.addTicket(order.getaTicket());
			aTicketManager.save("booking.json");
			String code = aTicketManager.getTicketObj(index + 1).getCode();
			String uid = aTicketManager.getTicketObj(index + 1).getUid();
			String date_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "date");
			String type_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsType");
			String generalTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountA");
			String uniTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountB");
			String start_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "start");
			String end_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "end");
			String seat_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "seats");
			String price = aTicketManager.getTicketObj(index + 1).getPayment();
			String deadline = aTicketManager.getTicketObj(index + 1).getPayDeadLine();
			String information = "Ticket code: " + code + "\nUser ID: " + uid + "\nDate: " + date_0 + "\nTicket type: "
					+ type_0 + "\nNumber of general tickets: " + generalTicket_0
					+ "\nNumber of university discount tickets: " + uniTicket_0 + "\nStart station: " + start_0
					+ "\nEnd station: " + end_0 + "\nSeat: " + seat_0 + "\nTotal price: " + price
					+ "\nThe payment deadline: " + deadline;
			JOptionPane.showMessageDialog(bookView, information);
			order = new generalBooking();
			index = index + 1;
		}
	}
	//BookView Ends

	//BookView_1 Begins
	public class SearchingButtonListener_1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setCode(index);
			aTicketManager = new TicketManager();
			try {
				order.setRoundTrip("1");
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			order.set_toOrFrom(0);
			try {
				order.setUid(bookView_1.getuidIn());
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			order.setStartStationID(bookView_1.getStationFromID());
			order.setEndStationID(bookView_1.getStationToID());
			order.setTicketType(bookView_1.getStandardOrBusiness());
			order.setPreference(bookView_1.getSeatPrefer());
			try {
				order.setGeneralTicket(String.valueOf(bookView_1.getNumberOfTicket()));
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			try {
				order.setUniversityTicket(String.valueOf(bookView_1.getNumberOfDiscount()));
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			String searchChoice = bookView_1.getOrderType();
			if (searchChoice.equals("0")) {
				order.setDate(bookView_1.getTimeIn());
				try {
					order.findValidTrainNo(bookView_1.getToTime());
				} catch (SoldBuySeatException e2) {
					e2.printStackTrace();
				}
				try {
					bookView_1.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else if (searchChoice.equals("1")) {
				order.setDate(bookView_1.getTimeIn());
				order.setListOfValidTrainNo2(bookView_1.getTrainNumber());
				try {
					bookView_1.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else
				System.out.println("Invalid input!");
		}
	}
	
	public class BookingButtonListener_1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setValidIndex(bookView_1.getOrderIndex());
			try {
				order.sellTicket();
			} catch (SoldBuySeatException e1) {
				e1.printStackTrace();
			}
			 order.clearListOfValidTrainNo();
			 order.set_toOrFrom(1);
			 
			 bookView_1.setVisible(false);
			 bookReturnView.setVisible(true);
		}
	}
	
	public class SearchingButtonListener_2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setStartStationID(bookView_1.getStationToID());
			order.setEndStationID(bookView_1.getStationFromID());
			order.setDate(bookReturnView.getTime1In());
			String searchChoice = bookView_1.getOrderType();
			if (searchChoice.equals("0")) {
				try {
					order.findValidTrainNo(bookReturnView.getFromTime());
				} catch (SoldBuySeatException e2) {
					e2.printStackTrace();
				}
				try {
					bookReturnView.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else if (searchChoice.equals("1")) {
				order.setListOfValidTrainNo2(bookReturnView.getTrainNumber());
				try {
					bookReturnView.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else
				System.out.println("Invalid input!");
		}
	}
	//BookView_1 Ends
	
	//BookReturnView Begins
	public class BookingButtonListener_2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setValidIndex(bookReturnView.getOrderIndex());
			try {
				order.sellTicket();
			} catch (SoldBuySeatException e1) {
				e1.printStackTrace();
			}
			try {
				order.paymentDeadlineCalculator();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
            order.totalPriceCalculator();
            aTicketManager.addTicket(order.getaTicket());
            aTicketManager.save("booking.json");
            String code = aTicketManager.getTicketObj(index + 1).getCode();
            String uid = aTicketManager.getTicketObj(index + 1).getUid();
            String date_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "date");
            String type_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsType");
            String generalTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountA");
            String uniTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountB");
            String start_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "start");
            String end_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "end");
            String seat_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "seats");
            String price = aTicketManager.getTicketObj(index + 1).getPayment();
            String deadline = aTicketManager.getTicketObj(index + 1).getPayDeadLine();
            String date_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "date");
            String type_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "ticketsType");
            String generalTicket_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "ticketsCountA");
            String uniTicket_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "ticketsCountB");
            String start_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "start");
            String end_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "end");
            String seat_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "seats");
            String information = "Ticket code: " + code + "\nUser ID: " + uid + "\nDeparture ticket details: \n" + "Date: " + date_0 + "\nTicket type: "
                     + type_0 + "\nNumber of general tickets: " + generalTicket_0 + "\nNumber of university discount tickets: " + uniTicket_0 + "\nStart station: " + start_0
                     + "\nEnd station: " + end_0 + "\nSeat: " + seat_0 + "\nReturn ticket details: \n" + "Date: " + date_1 + "\nTicket type: "
                    + type_1 + "\nNumber of general tickets: " + generalTicket_1 + "\nNumber of university discount tickets: " + uniTicket_1 + "\nStart station: " + start_1
                    + "\nEnd station: " + end_1 + "\nSeat: " + seat_1 + "\nTotal price: " + price + "\nThe payment deadline: " + deadline;
            JOptionPane.showMessageDialog(bookReturnView, information);
            order = new generalBooking();
            index = index + 1;
		}
	}
	// BookReturnView Ends

	// ChangeView Begins
	public class EnterButtonListener_1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			unbooking = new Unbooking();
			unbooking.Unbooking(changeView.getReferNumber(), changeView.getuidIn());
			JOptionPane.showMessageDialog(changeView, "已取消訂票");
			index = index -1;
			unbooking = new Unbooking();
		}
	}
	// ChangeView Ends

	// SearchView Begins
	public class GoSearchBookListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchBookView.setVisible(true);
			searchView.setVisible(false);
		}
	}

	public class GoSearchReferNumListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchReferNumView.setVisible(true);
			searchView.setVisible(false);
		}
	}

	public class GoSearchTimetableListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchTimetableView.setVisible(true);
			searchView.setVisible(false);
		}
	}

	public class GoSearchDiscountListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchDiscountView.setVisible(true);
			searchView.setVisible(false);
		}
	}
	// SearchView ends

	// SearchBookView Begins
	public class GoSearchButtonListener_1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lookinto = new LookInto();
			JOptionPane.showMessageDialog(searchBookView, "BookingHistory: " + lookinto.BookingHistory(searchBookView.getReferNumber(), searchBookView.getuidIn()));
		}
	}
	// SearchBookView Ends

	// SearchReferNumView Begins
	public class GoSearchButtonListener_2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lookinto = new LookInto();
			JOptionPane.showMessageDialog(searchReferNumView, "BookingCode: " + lookinto.BookingCode(searchReferNumView.getuidIn(), searchReferNumView.getStationFromID(), searchReferNumView.getStationToID(), searchReferNumView.getDateIn(), null));
		}
	}
	// SearchReferNumView Ends

	// SearchTimetableView Begins
	public class GoSearchButtonListener_3 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lookinto = new LookInto();
			searchTimetableView.clear_1();
			try {
				searchTimetableView.output(lookinto.SameDayTrains(searchTimetableView.getDateIn()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	}
	// SearchTimetableView Ends

	// SearchDiscountView Begins
	public class GoSearchButtonListener_4 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lookinto = new LookInto();
			searchDiscountView.clear_1();
			try {
				searchDiscountView.output(lookinto.DiscountTrains(searchDiscountView.getStationFromID(),
						searchDiscountView.getStationToID(), searchDiscountView.getDateIn(), "1"));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	}
	// SearchDiscountView Ends
	
	// MealView Begins
	public class GoSearchButtonListener_5 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	public class GoOrderMealListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//searchBookView.setVisible(true);
			mealView.setVisible(false);
		}
	}

	public class GoSearchMealListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//searchReferNumView.setVisible(true);
			mealView.setVisible(false);
		}
	}

	public class CancelMealListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//searchTimetableView.setVisible(true);
			mealView.setVisible(false);
		}
	}
	// MealView Ends
}