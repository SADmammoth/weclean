package com.example.weclean;

import com.example.weclean.domain.enums.CleaningFeatures;
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
				vacuumCleanerService.createVacuumCleaner(
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
						importedVC.getNoiseLevel(),
						importedVC.getDiscount()));
	}

	private static class VCFromFile {
		private String model, manufacturer,
						price, construction,
						dustCollectorType, volumeOfDustCollector, powerConsumption,
						powerSource, color, powerCordLength, weight, noiseLevel,discount;
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
		public String getConstruction() {
			return construction;
		}

		public Set<CleaningFeatures> getCleaningFeatures() {
			return cleaningFeatures.stream().map(e->CleaningFeatures.valueOf(e)).collect(Collectors.toSet());
		}

		public String getDustCollectorType() {
			return dustCollectorType;
		}

		public Double getVolumeOfDustCollector() {
			return Double.parseDouble(volumeOfDustCollector);
		}

		public Double getPowerConsumption() {
			return Double.parseDouble(powerConsumption);
		}

		public String getPowerSource() {
			return powerSource;
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
