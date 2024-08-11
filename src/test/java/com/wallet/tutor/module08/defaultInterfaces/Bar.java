package com.wallet.tutor.module08.defaultInterfaces;

public interface Bar {
	
	default void someMethod(){
		System.out.println(("Bar#someMethod"));
	}
}
