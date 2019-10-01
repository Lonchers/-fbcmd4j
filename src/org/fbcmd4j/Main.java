package org.fbcmd4j;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fbcmd4j.utils.Utils;

import facebook4j.Facebook;

public class Main {
	static final Logger logger = LogManager.getLogger(Main.class);
	private static final String CONFIG_DIR = "config";
	private static final String CONFIG_FILE = "fbcmd4j.properties";

	public static void main(String[] args) {
		logger.info("Iniciando app");
		Facebook fb =  null;
		Properties props = null;

		try {
			props = Utils.loadConfigFile(CONFIG_DIR, CONFIG_FILE);
		} catch (IOException ex) {
			logger.error(ex);
		}
		
		int option = 1;
		try {
			Scanner scan = new Scanner(System.in);
			while(true) {
				fb = Utils.configFacebook(props);
				System.out.println("Cliente de Facebook en línea de comando por David Servín \n\n"
								+  "Opciones: \n"
								+  "(0) Configurar Cliente \n"
								+  "(1) NewsFeed \n"
								+  "(2) Wall \n"
								+  "(3) Publicar Estado \n"
								+  "(4) Publicar Link \n"
								+  "(5) Salir \n"
								+  "\nPor favor ingrese una opción:");
				try {
					option = scan.nextInt();
					scan.nextLine();
					switch (option) {
					case 0:
						Utils.configTokens(CONFIG_DIR, CONFIG_FILE, props, scan);
						props = Utils.loadConfigFile(CONFIG_DIR, CONFIG_FILE);
						break;
					case 1:
						System.out.println("Mostrar NewsFeed...");
						break;
					case 2:
						System.out.println("Mostrar Wall...");
						break;
					case 3:
						System.out.println("Escribe tu estado...");
						break;
					case 4:
						System.out.println("Ingresa el link...");
						break;
					case 5:
						System.out.println("Gracias por usar el cliente!");
						System.exit(0);
						break;
					default:
						break;
					}
				} catch (InputMismatchException ex) {
					System.out.println("Ocurrió un errror, favor de revisar log.");
					logger.error("Opción inválida. %s. \n", ex.getClass());
				} catch (Exception ex) {
					System.out.println("Ocurrió un errror, favor de revisar log.");
					logger.error(ex);
				}
				System.out.println();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}