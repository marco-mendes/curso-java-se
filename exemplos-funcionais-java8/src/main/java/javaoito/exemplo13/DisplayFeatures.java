/**
 * 
 */
package javaoito.exemplo13;

/**
 * @author Marco Mendes
 * @since 2017
 */
import java.util.Optional;

public class DisplayFeatures {

	private String size; // Em polegadas
	private Optional<ScreenResolution> resolution;

	public DisplayFeatures(String size, Optional<ScreenResolution> resolution){
		this.size = size;
		this.resolution = resolution;
	}

	public String getSize() {
		return size;
	}
	public Optional<ScreenResolution> getResolution() {
		return resolution;
	}

}
