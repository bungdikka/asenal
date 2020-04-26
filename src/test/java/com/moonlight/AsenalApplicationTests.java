package com.moonlight;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.moonlight.util.CurrentTimeMillisScheduled;
import com.moonlight.util.IdGenerator;

@SpringBootTest
class AsenalApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void CurrentTimeMillisScheduledTest() {
//		long tb = System.currentTimeMillis();
//		for (int i = 0; i < 1000000000; i++) {
//			System.currentTimeMillis();
//		}
//		long te = System.currentTimeMillis();
//		System.out.println("cost:"+(te-tb));
//		
//		long tb1 = System.currentTimeMillis();
//		for (int i = 0; i < 1000000000; i++) {
//			CurrentTimeMillisScheduled.getInstance().now();
//		}
//		long te1 = System.currentTimeMillis();
//		System.out.println("cost:"+(te1-tb1));
	}
	
	@Test
	void idGeneratorTest() throws Exception {
		long tb = System.currentTimeMillis();
		List<Long> ids = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			Long id = IdGenerator.getInstance().nextId();
			System.out.println(id);
			if(ids.contains(id)) {
				throw new Exception();
			}
			if(id!=0L) {
				ids.add(id);
			}
		}
		long te = System.currentTimeMillis();
		System.out.println("cost:"+(te-tb));
		
//		Integer maxAutoIncrement = 0;
//		for (int i = 0; i < 4; i++) {
//			Double d = 9*(Math.pow(10, i));
//			maxAutoIncrement += d.intValue();
//		}
//		System.out.println(maxAutoIncrement);
	}

}
