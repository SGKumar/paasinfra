package com.infra.dsold;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.infra.dsold.MyCounter;

import org.junit.Test;

public class MyCounterManualTest {

  @Test
	public void testCounter() {
		MyCounter counter = new MyCounter();
		for (int i = 0; i < 500; i++) {
			counter.increment();
    }
		assertEquals(500, counter.getCount());
    assert (500 == counter.getCount()): "counter is rightly " + counter.getCount();
  }

  @Test
  public void testCounterWithConcurrency() throws InterruptedException {
      int numberOfThreads = 10;
      ExecutorService service = Executors.newFixedThreadPool(10);
      CountDownLatch latch = new CountDownLatch(numberOfThreads);
      MyCounter counter = new MyCounter();
      for (int i = 0; i < numberOfThreads; i++) {
          service.execute(() -> {
              counter.increment();
              latch.countDown();
          });
      }
      latch.await();
      assertEquals(numberOfThreads, counter.getCount());
  }

}
