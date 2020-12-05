package com.example.weclean;

import com.example.weclean.domain.Manufacturer;
import com.example.weclean.domain.enums.CleaningFeature;
import com.example.weclean.domain.enums.Construction;
import com.example.weclean.domain.enums.DustCollectorType;
import com.example.weclean.domain.enums.PowerSource;
import com.example.weclean.security.domain.Role;
import com.example.weclean.security.domain.User;
import com.example.weclean.security.service.UserService;
import com.example.weclean.service.ManufacturerService;
import com.example.weclean.service.VacuumCleanerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class WecleanApplication implements CommandLineRunner{
	@Value("${importfile}")
	private String importFile;
	@Value("${rootpassword}")
	private String rootPassword;

	@Autowired
	private VacuumCleanerService vacuumCleanerService;
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(WecleanApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		createVacuumCleaners(importFile);
		createUsers();
	}

	private void createUsers(){
		User root = new User();
		root.setPassword(rootPassword);
		root.setLogin("root");
		root.setRole(Role.ADMIN);
		userService.saveUser(root);

		User app = new User();
		app.setPassword(rootPassword);
		app.setLogin("application");
		app.setRole(Role.APPLICATION);
		userService.saveUser(app);
	}

	private void createVacuumCleaners(String fileToImport) throws IOException {
		VCFromFile.read(fileToImport).forEach(importedVC ->
				vacuumCleanerService.createVacuumCleaner(
						importedVC.getModel(),
						manufacturerService.createManufacturer(importedVC.getManufacturer()),
						importedVC.getPrice(),
						importedVC.getConstruction(),
						importedVC.getCleaningFeatures(),
						importedVC.getDustCollectorType(),
						importedVC.getVolumeOfDustCollector(),
						importedVC.getPowerConsumption(),
						importedVC.getPowerSource(),
						importedVC.getColor(),
						importedVC.getPowerCordLength(),
						importedVC.getWeight(),
						importedVC.getNoiseLevel(),
						importedVC.getDiscount()));
	}

	private static class VCFromFile {
		private String model, manufacturer,
						price, construction, powerSource, dustCollectorType, volumeOfDustCollector, powerConsumption,
						color, powerCordLength, weight, noiseLevel, discount;
		private Set<String> cleaningFeatures;

		static List<VCFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(FIELD, ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<VCFromFile>>() {});
		}
		protected VCFromFile(){}

		public String getModel() {
			return model;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public Double getPrice() {
			return Double.parseDouble(price);
		}
		public Construction getConstruction() {
			return Construction.valueOf(construction);
		}

		public Set<CleaningFeature> getCleaningFeatures() {
			return cleaningFeatures.stream().map(e-> CleaningFeature.valueOf(e)).collect(Collectors.toSet());
		}

		public DustCollectorType getDustCollectorType() {
			return DustCollectorType.valueOf(dustCollectorType);
		}

		public Double getVolumeOfDustCollector() {
			return Double.parseDouble(volumeOfDustCollector);
		}

		public Double getPowerConsumption() {
			return Double.parseDouble(powerConsumption);
		}

		public PowerSource getPowerSource() {
			return PowerSource.valueOf(powerSource);
		}

		public String getColor() {
			return color;
		}

		public Double getPowerCordLength() {
			return Double.parseDouble(powerCordLength);
		}

		public Double getWeight() {
			return Double.parseDouble(weight);
		}

		public Double getNoiseLevel() {
			return Double.parseDouble(noiseLevel);
		}

		public Double getDiscount() {
			return Double.parseDouble(discount);
		}
	}
}
