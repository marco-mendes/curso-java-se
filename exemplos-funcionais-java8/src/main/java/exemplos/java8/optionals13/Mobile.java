package exemplos.java8.optionals13;

/**
 * @author Marco Mendes
 * @since 2017
 */
import java.util.Optional;

public class Mobile {

	private long id;
	private String brand;
	private String name;
	private Optional<DisplayFeatures> displayFeatures;

	public Mobile(long id, String brand, String name, 
			DisplayFeatures displayFeatures){
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.displayFeatures = Optional.ofNullable(displayFeatures);
	}

	public long getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getName() {
		return name;
	}

	public Optional<DisplayFeatures> getDisplayFeatures() {
		return displayFeatures;
	}

}
