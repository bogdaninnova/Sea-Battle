package controller;

import model.ModelBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import view.Frame;
import view.ViewBean;


@SpringBootApplication
public class Controller {

	public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

	public static void main(String[] args) {
		context.getBean("modelBean", ModelBean.class).initiate();
		context.getBean("viewBean", ViewBean.class).initiate();
	}
}
