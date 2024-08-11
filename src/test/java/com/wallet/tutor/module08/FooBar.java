package com.wallet.tutor.module08;

import com.wallet.tutor.module08.defaultInterfaces.Bar;
import com.wallet.tutor.module08.defaultInterfaces.Foo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FooBar implements Foo, Bar {
	
	@Override
	public void someMethod(){
		log.info("FooBar#someMethod");
	}

	public static void main(String [] args) {
		log.info("FooBar#main");
		FooBar fooBar = new FooBar();
		fooBar.someMethod();
		fooBar.someOtherMethod();
	}
}
