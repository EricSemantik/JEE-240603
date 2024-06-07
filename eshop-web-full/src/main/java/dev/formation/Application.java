package dev.formation;

import org.modelmapper.ModelMapper;

public class Application {
	private static Application instance = null;

	private final ModelMapper mapper;

	private Application() {
		this.mapper = new ModelMapper();
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public ModelMapper getMapper() {
		return mapper;
	}

}
