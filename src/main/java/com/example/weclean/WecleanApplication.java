package com.example.weclean;

import com.example.weclean.service.VacuumCleanerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class WecleanApplication implements CommandLineRunner {
	@Value("${weclean.importfile}")
	private String importFile;

	@Autowired
	private VacuumCleanerService vacuumCleanerService;

	public static void main(String[] args) {
		SpringApplication.run(WecleanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createVacuumCleaners(importFile);
	}

	private void createVacuumCleaners(String fileToImport) throws IOException {
		VCFromFile.read(fileToImport).forEach(importedVC ->
				vacuumCleanerService.createVacuumCleaner(importedVC.getCode(),
						importedVC.getModel(),
						importedVC.getManufacturer(),
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
						importedVC.getNoiseLevel()));
	}

	private static class VCFromFile {
		private String code, model, manufacturer,
						price, construction, cleaningFeatures,
						dustCollectorType, volumeOfDustCollector, powerConsumption,
						powerSource, color, powerCordLength, weight, noiseLevel;

		static List<VCFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(FIELD, ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<VCFromFile>>() {});
		}
		protected VCFromFile(){}

		public String getCode() {
			return code;
		}

		public String getModel() {
			return model;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public Float getPrice() {
			return Float.parseFloat(price);
		}

		public String getConstruction() {
			return construction;
		}

		public String getCleaningFeatures() {
			return cleaningFeatures;
		}

		public String getDustCollectorType() {
			return dustCollectorType;
		}

		public Float getVolumeOfDustCollector() {
			return Float.parseFloat(volumeOfDustCollector);
		}

		public Integer getPowerConsumption() {
			return Integer.parseInt(powerConsumption);
		}

		public String getPowerSource() {
			return powerSource;
		}

		public String getColor() {
			return color;
		}

		public Float getPowerCordLength() {
			return Float.parseFloat(powerCordLength);
		}

		public Float getWeight() {
			return Float.parseFloat(weight);
		}

		public Float getNoiseLevel() {
			return Float.parseFloat(noiseLevel);
		}
	}
}
