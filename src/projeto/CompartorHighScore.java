package projeto;

import java.util.Comparator;

public class CompartorHighScore implements Comparator<HighScore>{

	@Override
	public int compare(HighScore H1, HighScore H2) {
		if (H1.victory() != H2.victory()) {
    		if (H1.victory())
    			return -1;
		}else {
    			return 1;
		}
    			if (H1.victory()) {
    		return H1.getturns() - H2.getturns();
    			}else {
    		return H2.getturns() - H1.getturns();
    			}
	}

	
	
}
