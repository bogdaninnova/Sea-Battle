package controller;

import constants.Constants;
import model.ModelBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import view.ViewBean;

import javax.swing.*;

@SpringBootApplication
public class Controller {

	public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

	public static void main(String[] args) {

		//SpringApplication.run(Controller.class, args);




//		modelBeen = new ModelBeen();
//		viewBean = new ViewBean();

		ModelBean modelBeen = context.getBean("modelBean", ModelBean.class);
		modelBeen.initiate();

		ViewBean viewBean = context.getBean("viewBean", ViewBean.class);
		viewBean.initiate();
	}
}
