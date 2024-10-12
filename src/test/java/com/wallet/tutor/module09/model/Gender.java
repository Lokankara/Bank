package com.wallet.tutor.module09.model;

public enum Gender {
	Female("f"), Male("m");
	private final String shortcut;
	
	Gender(String s) {
		shortcut = s;
	}
	
	public static Gender get(String s) {
		for (Gender g: Gender.values()) {
			if (g.shortcut.equals(s)) {
				return g;
			}
		}
		throw new RuntimeException("Wrong Gender "+s);
	}
}
