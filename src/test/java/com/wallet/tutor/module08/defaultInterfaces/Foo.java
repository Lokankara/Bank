package com.wallet.tutor.module08.defaultInterfaces;

public interface Foo {
	
	default void someMethod(){
		System.out.println(("Foo#someMethod"));
	}
	
	default void someOtherMethod(){
		System.out.println(("Foo#someOtherMethod"));
	}
}
