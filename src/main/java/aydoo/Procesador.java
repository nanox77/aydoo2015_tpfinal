package aydoo;

import java.util.concurrent.TimeUnit;

import aydoo.edu.tp.domain.processor.StatisticalProcessorDaemonStrategy;
import aydoo.edu.tp.domain.processor.StatisticalProcessorOnDemandStrategy;
import aydoo.edu.tp.domain.processor.StatisticalProcessorStrategy;

public class Procesador {

	public static void main(String[] args) throws Exception {
		String folder = args[0];

		StatisticalProcessorStrategy processorStrategy;

		if (args.length > 1 && args[1].equalsIgnoreCase("demonio")) {
			System.out.println("Modo demonio.");

			processorStrategy = new StatisticalProcessorDaemonStrategy();
			processorStrategy.processStatistics(folder);

		} else {
			System.out.println("Modo On-demand.");

			long startTime = System.currentTimeMillis();

			processorStrategy = new StatisticalProcessorOnDemandStrategy();
			processorStrategy.processStatistics(folder);

			long endTime = System.currentTimeMillis();
			String tiempoTotalDeProcesamiento = tiempoTotalDeProcesamiento(endTime - startTime);

			System.out.println(tiempoTotalDeProcesamiento);
		}
	}

	private static String tiempoTotalDeProcesamiento(long totalTime) {
		String result = String.format("Tiempo de procesamiento: %d.%d seconds",
				TimeUnit.MILLISECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime)),
				TimeUnit.MILLISECONDS.toMillis(totalTime));

		return result;
	}
}