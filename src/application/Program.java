package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {


		Department obj = new Department (1, "Book");

		Seller seller = new Seller(1, "Bruno", "bruno@carvalho.pt", new Date(),5000.00, obj);
		
		
		System.out.println(obj);
		System.out.println(seller);
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
	}

}
