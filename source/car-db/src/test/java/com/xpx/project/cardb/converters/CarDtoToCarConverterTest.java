package com.xpx.project.cardb.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.entity.Car;


@RunWith(MockitoJUnitRunner.class)
public class CarDtoToCarConverterTest {

	@InjectMocks
	private CarDtoToCarConverter testee;
	
	@Test
	public void testConvert() {
		//given
		CarDto dto = new CarDto();
		dto.setId(123l);
		dto.setMake("make");
		dto.setModel("model");
		dto.setPrice(123.12);
		
		//when
		Car output = testee.convert(dto);
		
		//then
		assertThat(output.getId(), is(123l));
		assertThat(output.getMake(), is("make"));
		assertThat(output.getModel(), is("model"));
		assertThat(output.getPrice(), is(123.12));
		
	
	}

}
