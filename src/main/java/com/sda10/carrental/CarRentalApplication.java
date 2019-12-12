package com.sda10.carrental;

import com.sda10.carrental.model.CarReturn;
import com.sda10.carrental.repository.CarReturnRepository;
import com.sda10.carrental.service.CarReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

/*	@Autowired
	private CarReturnRepository carReturnRepository;

	@Autowired
	private CarReturnService carReturnService;

	@Bean
	CommandLineRunner loadData() {
		return args -> {carReturnRepository.saveAll(createCarReturn());

			CarReturn newCarReturn = new CarReturn();
			newCarReturn.setDateOfReturn(LocalDate.of(2020, 11, 11));
			newCarReturn.setAdditionalPayment(44.0);
			newCarReturn.setComments("second car");
			carReturnService.createCarReturn(newCarReturn);

			List<CarReturn> carReturns = carReturnService.findAllCarReturn();
			System.out.println(carReturns);

			CarReturn carReturn = carReturnService.findCarReturnById(newCarReturn.getId());
			System.out.println(carReturn);

			CarReturn toUpdateCarReturn = new CarReturn();
			toUpdateCarReturn.setDateOfReturn(LocalDate.of(2010, 11, 11));
			toUpdateCarReturn.setAdditionalPayment(1.0);
			toUpdateCarReturn.setComments("updated car");
			carReturnService.updateCarReturn(newCarReturn.getId(), toUpdateCarReturn);

//			carReturnService.deleteCarReturn(newCarReturn.getId());

//			carReturnRepository.deleteAll();
		};
	}

	private List<CarReturn> createCarReturn() {
		CarReturn carReturn = new CarReturn();
		carReturn.setDateOfReturn(LocalDate.of(2019, 11, 11));
		carReturn.setAdditionalPayment(22.0);
		carReturn.setComments("first car");

		return Collections.singletonList(carReturn);
	}*/
}

