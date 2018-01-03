/**
 * 
 */
package exemplos.java8.optionals13;


import java.util.Optional;

public class MobileService {

  // mobile.getDisplayFeature().getScreenResolution().getWidth();
	
  public Integer getMobileScreenWidth(Optional<Mobile> mobile){
	return mobile.flatMap(Mobile::getDisplayFeatures)
		 .flatMap(DisplayFeatures::getResolution)
		 .map(ScreenResolution::getWidth)
		 .orElse(0);

  }

}
