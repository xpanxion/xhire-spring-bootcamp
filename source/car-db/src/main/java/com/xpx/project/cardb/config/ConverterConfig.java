package com.xpx.project.cardb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.xpx.project.cardb.converters.CarDtoToCarConverter;
import com.xpx.project.cardb.converters.CarToCarDtoConverter;
import com.xpx.project.cardb.converters.CustomerDtoToCustomerConverter;
import com.xpx.project.cardb.converters.CustomerToCustomerDtoConverter;
import com.xpx.project.cardb.converters.OrderDtoToOrderConverter;
import com.xpx.project.cardb.converters.OrderToOrderDtoConverter;

/**
 * The conversion config.
 */
@Configuration
public class ConverterConfig {

	/** The order converter. */
	@Autowired
	private OrderToOrderDtoConverter orderConverter;
	
	/** The car converter. */
	@Autowired
	private CarToCarDtoConverter carConverter;
	
	/** The customer converter. */
	@Autowired
	private CustomerToCustomerDtoConverter customerConverter;
	
	/** The car dto conveter. */
	@Autowired
	private CarDtoToCarConverter carDtoConveter;
	
	/** The customer dto converter. */
	@Autowired
	private CustomerDtoToCustomerConverter customerDtoConverter;
	
	/** The order dto converter. */
	@Autowired
	private OrderDtoToOrderConverter orderDtoConverter;
	
	/**
	 * Cars converter.
	 *
	 * @return the conversion service
	 */
	@Bean("carsConverter")
	public ConversionService carsConverter() {
		DefaultConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(carConverter);
		conversionService.addConverter(customerConverter);
		conversionService.addConverter(orderConverter);
		conversionService.addConverter(carDtoConveter);
		conversionService.addConverter(customerDtoConverter);
		conversionService.addConverter(orderDtoConverter);
		return conversionService;
	}
	
}
