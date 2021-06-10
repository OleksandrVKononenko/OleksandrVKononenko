package ap.raif;

import static ap.raif.RaifUtil.sm;
import static ap.raif.RaifUtil.smt;

public class RaifHelper {

	public static void main(String[] args) {
		
		sm("Ok && RAIF !!!");

		smt("Результат запуска приложения",RaifDispatch.getInstance(RaifMap.getInstance(args).get()).dispatch());

	}

}
