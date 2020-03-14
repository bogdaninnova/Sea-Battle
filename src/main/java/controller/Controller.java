package controller;

import model.Model;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import view.View;

@SpringBootApplication
public class Controller {

	private static View view;
	private static Model model;
		
	public static void main(String[] some) {
		model = new Model();
		view = new View();
	}
	
	public static View getView() {
		return view;
	}
	
	public static Model getModel() {
		return model;
	}
}
