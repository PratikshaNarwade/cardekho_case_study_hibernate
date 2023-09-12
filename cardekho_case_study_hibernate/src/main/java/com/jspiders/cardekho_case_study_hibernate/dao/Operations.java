package com.jspiders.cardekho_case_study_hibernate.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.cardekho_case_study_hibernate.dto.Car;
import com.jspiders.cardekho_case_study_hibernate.dto.Menu;

public class Operations {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	private static Query query;
	private static int rowCount;
	Scanner sc = new Scanner(System.in);
	
	//ADD CAR DETAILS
	public void addCarDetails() {
		try {
			openConnection();
			entityTransaction.begin();
			
			System.out.println("How Many Car Details You Want To add?");
			int val = sc.nextInt();
			
			query = entityManager.createQuery("SELECT car FROM Car car");
			rowCount = query.getResultList().size();
			if(rowCount>0) {
				System.out.println("Already Present Car ID's in App : ");
				query = entityManager.createQuery("SELECT car FROM Car car");
				@SuppressWarnings("unchecked")
				List<Car> list = query.getResultList();
				System.out.print("[  ");
				for(Car var : list) {
					System.out.print(var.getCarID() + "  ");
				}
				System.out.println("]");
			}

			for(int i = 0; i<val; i++) {
				Car car = new Car();
				System.out.println("Enter Car Id : ");
				car.setCarID(sc.nextInt());
				System.out.println("Enter Car Name : ");
				car.setName(sc.next());
				System.out.println("Enter Car Model : ");
				car.setModel(sc.next());
				System.out.println("Enter Car Brand : ");
				car.setBrand(sc.next());
				System.out.println("Enter Car Fuel Type : ");
				car.setFuelType(sc.next());
				System.out.println("Enter Car Price : ");
				car.setPrice(sc.nextDouble());
				entityManager.persist(car);
			}
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	
	public void searchCarDetails() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("Search Car By \n1.Name\n2.Brand\n3.Fuel Type\n4.Go Back To Main Menu");
			System.out.println("Enter Your Choice : ");
			int ch = sc.nextInt();
			
			switch (ch) {
			case 1:
				query = entityManager.createQuery("SELECT car FROM Car car");
				rowCount = query.getResultList().size();
				//System.out.println(rowCount);
				if (rowCount == 0) {
					System.out.println("No Car Details Available!");
				} else {
					System.out.println("Enter Car Name : ");
					String name = sc.next();
					query = entityManager.createQuery("SELECT car FROM Car car WHERE car.name = :task");
					query.setParameter("task", name);
					@SuppressWarnings("unchecked")
					List<Car> cars = query.getResultList();
					for (Car carlist : cars) {
						System.out.println(carlist);
					}
				}
				entityTransaction.commit();
				break;
			
			case 2:
				query = entityManager.createQuery("SELECT car FROM Car car");
				rowCount = query.getResultList().size();
				//System.out.println(rowCount);
				if (rowCount == 0) {
					System.out.println("No Car Details Available!");
				} else {
					System.out.println("Enter Car Brand : ");
					String brand = sc.next();
					query = entityManager.createQuery("SELECT car FROM Car car WHERE car.brand = :task");
					query.setParameter("task", brand);
					@SuppressWarnings("unchecked")
					List<Car> cars = query.getResultList();
					for (Car car : cars) {
						System.out.println(car);
					}
				}
				entityTransaction.commit();
				break;
				
			case 3:
				query = entityManager.createQuery("SELECT car FROM Car car");
				rowCount = query.getResultList().size();
				//System.out.println(rowCount);
				if (rowCount == 0) {
					System.out.println("No Car Details Available!");
				} else {
					System.out.println("Enter Car Fuel Type : ");
					String fuel = sc.next();
					query = entityManager.createQuery("SELECT car FROM Car car WHERE car.fuelType = :task");
					query.setParameter("task", fuel);
					@SuppressWarnings("unchecked")
					List<Car> cars = query.getResultList();
					for (Car car : cars) {
						System.out.println(car);
					}
				}
				entityTransaction.commit();
				break;
				
			case 4:
				Menu.MenuOptions();
				break;
				
			default:
				System.out.println("Invalid Input!!Try Again");
				break;
			}	
			//entityTransaction.commit();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	
	public void getCarDetails() {
		try {
			query = entityManager.createQuery("SELECT car FROM Car car");
			rowCount = query.getResultList().size();
			//System.out.println(rowCount);
			if (rowCount == 0) {
				System.out.println("No Car Details Available!");
			} else {
				query = entityManager.createQuery("SELECT car FROM Car car");
				System.out.println("Total No of Available Car : " + rowCount);
				@SuppressWarnings("unchecked")
				List<Car> cars = query.getResultList();
				for (Car car : cars) {
					System.out.println(car);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//UPDATE CAR DETAILS
		public void updateCarDetails() {
			try {
				openConnection();
				entityTransaction.begin();
				getCarDetails();
				System.out.println("\nUpdate Car Details By \n1.Name\n2.Brand\n3.Fuel Type\n4.Go Back To Main Menu");
				System.out.println("Enter Your Choice : ");
				int ch = sc.nextInt();
				
				switch(ch) {
				case 1:
					query = entityManager.createQuery("SELECT car FROM Car car");
					rowCount = query.getResultList().size();
					//System.out.println(rowCount);
					if (rowCount == 0) {
						System.out.println("No Car Details Available!");
					} else {
						System.out.println("Enter Car ID :");
						int id = sc.nextInt();
						System.out.println("Enter New Car Name :");
						String name = sc.next();	
						query = entityManager.createQuery("UPDATE Car SET name = :task1 WHERE id = :task2 ");
						query.setParameter("task1", name);
						query.setParameter("task2", id);
						int result = query.executeUpdate();
						entityTransaction.commit();
						System.out.println("Query OK, " + result + " rows(s) affected");
					}
					break;
				case 2:
					query = entityManager.createQuery("SELECT car FROM Car car");
					rowCount = query.getResultList().size();
					//System.out.println(rowCount);
					if (rowCount == 0) {
						System.out.println("No Car Details Available!");
					} else {
						System.out.println("Enter Car ID :");
						int id = sc.nextInt();
						System.out.println("Enter New Car Brand :");
						String brand = sc.next();	
						query = entityManager.createQuery("UPDATE Car SET brand = :task1 WHERE id = :task2 ");
						query.setParameter("task1", brand);
						query.setParameter("task2", id);
						int result = query.executeUpdate();
						entityTransaction.commit();
						System.out.println("Query OK, " + result + " rows(s) affected");
					}
					break;
					
				case 3:
					query = entityManager.createQuery("SELECT car FROM Car car");
					rowCount = query.getResultList().size();
					//System.out.println(rowCount);
					if (rowCount == 0) {
						System.out.println("No Car Details Available!");
					} else {
						System.out.println("Enter Car ID :");
						int id = sc.nextInt();
						System.out.println("Enter New Fuel Type :");
						String fuelType = sc.next();	
						query = entityManager.createQuery("UPDATE Car SET fuelType = :task1 WHERE id = :task2 ");
						query.setParameter("task1", fuelType);
						query.setParameter("task2", id);
						int result = query.executeUpdate();
						entityTransaction.commit();
						System.out.println("Query OK, " + result + " rows(s) affected");
					}
					break;
					
				case 4:
					Menu.MenuOptions();
					break;
				
				default:
					System.out.println("Invalid Input!!Try Again");
					break;
				}
		    } catch (Exception e){
		    	e.printStackTrace();
		    } finally {
		    	closeConnection();
		    }
		}	
		
		//REMOVE CAR DETAILS
	    public void removeCarDetails() {
	    	try {
	    		openConnection();
	    		entityTransaction.begin();
	    		query = entityManager.createQuery("SELECT car FROM Car car");
				rowCount = query.getResultList().size();
				//System.out.println(rowCount);
				if (rowCount == 0) {
					System.out.println("No Car Details Available!");
				} else {
					System.out.println("Enter Car ID :");
		        	int id = sc.nextInt();
		        	query = entityManager.createQuery("DELETE FROM Car WHERE id = :task1");
		        	query.setParameter("task1", id);
		        	int result = query.executeUpdate();
		        	entityTransaction.commit();
		        	System.out.println("Query OK, " + result + " rows(s) affected");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConnection();
			}
	    }
	
	private static void openConnection() {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("unique");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			closeConnection();
		} 
	}
	
	private static void closeConnection() {
		try {
			if(entityManagerFactory != null) {
				entityManagerFactory.close();
			}
			if(entityManager != null) {
				entityManager.close();
			}
			if(entityTransaction != null) {
				if (entityTransaction.isActive()) {
					entityTransaction.rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
