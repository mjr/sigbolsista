package br.com.sigteam.sigbolsista.models;

import java.util.Arrays;
import java.util.List;

public class Role {
	
	public static final int ADMIN = 1;
	
	public static final int COLLEGER = 2;
	
	public static final int SECTOR_MANAGER = 3;
	
	public static final int UNIT_MANAGER = 4;
	
	public static List<Integer> allRoles(){
		return Arrays.asList(ADMIN, COLLEGER, SECTOR_MANAGER, UNIT_MANAGER);
	}
}
