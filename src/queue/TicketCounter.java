package queue;

import java.util.*;

/**
 * TicketCounter demonstrates the use of a queue for simulating a line of
 * customers.
 * 
 * @author Lewis and Chase
 * @version 4.0
 */
public class TicketCounter {
	private final static int PROCESS = 100;
	private final static int MAX_CASHIERS = 4;
	private final static int NUM_CUSTOMERS = 22;

	public static void main(String[] args) {
		Customer customer;
		Queue<Customer> customerQueue = new LinkedList<Customer>();
		int[] cashierTime = new int[MAX_CASHIERS];
		int totalTime, averageTime, departs, start;

		// run the simulation for various number of cashiers
		// for (int cashiers = 0; cashiers < MAX_CASHIERS; cashiers++)
		{
			// set each cashiers time to zero initially
			for (int count = 0; count < MAX_CASHIERS; count++)
				cashierTime[count] = 0;

			// load customer queue
			for (int count = 1; count <= NUM_CUSTOMERS; count++)
				customerQueue.add(new Customer(count * 10));

			totalTime = 0;

			// process all customers in the queue
			while (!(customerQueue.isEmpty())) {
				for (int count = 0; count < MAX_CASHIERS; count++) {
					if (!(customerQueue.isEmpty())) {
						customer = customerQueue.remove();
						if (customer.getArrivalTime() > cashierTime[count])
							start = customer.getArrivalTime();
						else
							start = cashierTime[count];
						departs = start + PROCESS;
						customer.setDepartureTime(departs);
						cashierTime[count] = departs;
						totalTime += customer.totalTime();
						System.out.println(customer.totalTime() + "--" + start + "-" + departs + ":"
								+ customer.getArrivalTime());
					}
				}
			}

			// output results for this simulation
			averageTime = totalTime / NUM_CUSTOMERS;
			System.out.println("Number of cashiers: " + (MAX_CASHIERS + 1));
			System.out.println("Average time: " + averageTime + "\n");

		}
	}

	private static class Customer {
		private int arrivalTime, departureTime;

		/**
		 * Creates a new customer with the specified arrival time.
		 * 
		 * @param arrives
		 *            the arrival time
		 */
		public Customer(int arrives) {
			arrivalTime = arrives;
			departureTime = 0;
		}

		/**
		 * Returns the arrival time of this customer.
		 * 
		 * @return the arrival time
		 */
		public int getArrivalTime() {
			return arrivalTime;
		}

		/**
		 * Sets the departure time for this customer.
		 * 
		 * @param departs
		 *            the departure time
		 **/
		public void setDepartureTime(int departs) {
			departureTime = departs;
		}

		/**
		 * Returns the departure time of this customer.
		 * 
		 * @return the departure time
		 */
		/*
		 * public int getDepartureTime() { return departureTime; }
		 */

		/**
		 * Computes and returns the total time spent by this customer.
		 * 
		 * @return the total customer time
		 */
		public int totalTime() {
			return departureTime - arrivalTime;
		}
	}

}
