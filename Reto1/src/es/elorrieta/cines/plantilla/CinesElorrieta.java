package es.elorrieta.cines.plantilla;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Solucion del Reto 1 de Programacion "Cines Elorrieta" (2022-2023)
 * 
 * El codigo esta escrito para que sea facilmente comprensible por el alumno, no
 * para ser 100% eficiente, ni correcto ni completo. Por tanto, que todos los
 * metodos y atributos sean publicos y estaticos (entre otras decisiones) es
 * delibrerado.
 */
public class CinesElorrieta {

	public static Scanner teclado = new Scanner(System.in);

	// Horas del sabado y domingo en las que hay que proyectar
	public static final int NUMERO_HORAS_SABADO = 3;
	public static final int NUMERO_HORAS_DOMINGO = 6;
	public static int tiempoRestanteSabado = NUMERO_HORAS_SABADO;
	public static int tiempoRestanteDomingo = NUMERO_HORAS_DOMINGO;

	// Claves del usuario
	public static String usuarioDelPrograma = "admin";
	public static String claveDelPrograma = "12345";

	// Peliculas disponibles con sus generos y su duracion
	public static String peliculasString = "Drama, Memorias de Ana Frank, 2 - Drama, Su mejor historia, 1 - "
			+ "Comedia, Historias lamentables, 1 - Comedia, Kung Fu Yoga, 1 - Comedia, El milagro de P. Tinto, 2 - "
			+ "Terror, El monstruo, 2 - Terror, Ouija, 1 - Terror, Las brujas de Zugarramurdi, 2 - Ciencia, Arcadia, 2 - "
			+ "Ciencia, Los ultimos dias en Marte, 1 - Ciencia, El Septimo Hijo, 1";

	// Peliculas seleccionadas por el usuario
	public static String peliculasSeleccionadasSabado = new String("");
	public static String peliculasSeleccionadasDomingo = new String("");

	// Pinta el cartel de bienvenida
	public static void mostrarBienvenida() {
		try {
			System.out.println("--------------------------------");
			System.out.println("- Bienvenido a Cines Elorrieta -");
			System.out.println("--------------------------------");
			System.out.println(" ");
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			// No hace falta poner nada aqui
		}
	}

	// Pedimos el login hasta que lo acierte
	public static void pedirElLogin() {
		String usuarioIntroducido = null;
		String claveIntroducida = null;
		boolean claveCorrecta = false;
		do {
			System.out.print("Usuario: ");
			usuarioIntroducido = teclado.nextLine().trim();

			System.out.print("Clave: ");
			claveIntroducida = teclado.nextLine().trim();

			if ((usuarioDelPrograma.equalsIgnoreCase(usuarioIntroducido))
					&& (claveDelPrograma.equals(claveIntroducida))) {
				claveCorrecta = true;
			} else {
				System.out.println("Error!!! Usuario o Clave incorrectos");
			}

		} while (!claveCorrecta);
	}

	// Muestra las peliculas seleccionadas
	public static void mostrarPeliculasSeleccionadas() {
		System.out.print("Peliculas del Sabado: ");
		String[] peliculasSabado = obtenerPeliculas(peliculasSeleccionadasSabado);
		for (int i = 0; i < peliculasSabado.length; i++) {
			if ((peliculasSabado[i] != null) && (peliculasSabado[i].length() != 0)) {
				System.out.print(obtenerTituloPelicula(peliculasSabado[i]) + ", ");
			}
		}
		System.out.println(" ");
		System.out.print("Peliculas del Domingo: ");
		String[] peliculasDomingo = obtenerPeliculas(peliculasSeleccionadasDomingo);
		for (int i = 0; i < peliculasDomingo.length; i++) {
			if ((peliculasDomingo[i] != null) && (peliculasDomingo[i].length() != 0)) {
				System.out.print(obtenerTituloPelicula(peliculasDomingo[i]) + ", ");
			}
		}
	}

	// Muestra el tiempo restante
	public static void mostrarTiempoRestante() {
		System.out.println(" ");
		System.out.println("Tiempo Restante del Sabado: " + tiempoRestanteSabado);
		System.out.println("Tiempo Restante del Domingo: " + tiempoRestanteDomingo);
		System.out.println(" ");
	}

	// Pinta el menu inicial y permite escoger una opcion
	public static int mostrarMenuInicial() {
		int ret = -1;
		do {
			try {
				System.out.println("- Menu Inicial -");
				System.out.println("----------------");
				System.out.println("1. Drama");
				System.out.println("2. Comedia");
				System.out.println("3. Terror");
				System.out.println("4. Ciencia Ficcion");
				System.out.println("5. Ver Resumen");
				System.out.println("0. Salir");
				System.out.println(" ");
				System.out.print("Escoge una opcion: ");
				ret = teclado.nextInt();
				teclado.nextLine();
			} catch (Exception e) {
				System.out.println("Error!!! Opcion incorrecta");
				teclado.nextLine();
				ret = -1;
			}
		} while ((ret < 0) || (ret > 5));
		return ret;
	}

	// Pinta el menu inicial y permite escoger una opcion
	public static boolean estaDeAcuerdo() {
		boolean ret = false;
		System.out.println("Se perderan los datos guardados... ");
		System.out.print("Estas de acuerdo? [s, n]: ");
		String opcion = teclado.nextLine().trim().charAt(0) + "";
		ret = opcion.equalsIgnoreCase("S");
		return ret;
	}

	// Pinta el cartel de fin
	public static void mostrarFin() {
		try {
			System.out.println(" ");
			System.out.println("- Cambios confirmados -");
			System.out.println(" ");
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			// No hace falta poner nada aqui
		}
	}

	// Limpiamos...
	public static void resetear() {
		tiempoRestanteSabado = NUMERO_HORAS_SABADO;
		tiempoRestanteDomingo = NUMERO_HORAS_DOMINGO;
		peliculasSeleccionadasSabado = new String("");
		peliculasSeleccionadasDomingo = new String("");
		System.out.println("- Cambios eliminados -");
	}

	// Muestra las peliculas de un genero
	public static String mostrarPeliculas(String[] peliculas, String genero) {
		String ret = null;
		int opcionMenuPeliculas = -1;
		int contadorMenu = 1;
		do {
			contadorMenu = 1;
			try {
				System.out.println(" ");
				System.out.println("- Peliculas de " + genero + " - ");
				System.out.println("----------------------------");

				for (int i = 0; i < peliculas.length; i++) {
					if ((null != peliculas[i]) && (esDelGenero(peliculas[i], genero))) {
						System.out.println(contadorMenu + ". " + obtenerTituloPelicula(peliculas[i]) + " - "
								+ obtenerDuracionPelicula(peliculas[i]) + "h");
						contadorMenu++;
					}
				}
				System.out.println("0. Atras");
				System.out.println(" ");
				System.out.print("Escoge una opcion: ");
				opcionMenuPeliculas = teclado.nextInt();
				teclado.nextLine();
			} catch (Exception e) {
				System.out.println("Error!!! Opcion incorrecta");
				teclado.nextLine();
				opcionMenuPeliculas = -1;
			}
		} while ((opcionMenuPeliculas < 0) || (opcionMenuPeliculas >= contadorMenu));

		if (opcionMenuPeliculas != 0) {
			ret = peliculas[opcionMenuPeliculas - 1];
		}

		return ret;
	}

	// ---------------------------------------------------------------------------------------------------------------//

	public static String obtenerPelicula(String peliculasString, int i) {
		String[] parts = peliculasString.split("-");
		return parts[i].trim();
	}

	public static String[] obtenerPeliculas(String peliculasString) {
		String[] parts = peliculasString.split("-");
		return parts;
	}

	// Busca las peliculas de un genero y su duracion es inferior al tiempo
	// restante, las devuelve.
	public static String[] buscarPeliculas(String genero, int tiempoRestante) {
		String[] ret = new String[peliculasString.length()];
		String[] peliculas = obtenerPeliculas(peliculasString);

		int cont = 0;
		for (int i = 0; i < peliculas.length; i++) {
			int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculas[i]));

			if ((esDelGenero(peliculas[i], genero) && (duracionPelicula <= tiempoRestante))) {
				ret[cont] = peliculas[i];
				cont++;
			}
		}
		return ret;
	}

	// Dice si una pelicula es de un genero o no
	public static boolean esDelGenero(String pelicula, String genero) {
		boolean ret = false;
		if ((pelicula != null) && (genero != null) && (pelicula.indexOf(genero) != -1)) {
			ret = true;
		}
		return ret;
	}

	public static boolean yaHayPeliculasDelGeneroElSabado(String genero) {
		boolean ret = false;
		String[] peliculasSabado = obtenerPeliculas(peliculasSeleccionadasSabado);
		for (int i = 0; i < peliculasSabado.length; i++) {
			String pelicula = peliculasSabado[i];
			if ((null != pelicula) && (esDelGenero(pelicula, genero))) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public static boolean yaHayPeliculasDelGeneroElDomingo(String genero) {
		boolean ret = false;
		String[] peliculasDomingo = obtenerPeliculas(peliculasSeleccionadasDomingo);
		for (int i = 0; i < peliculasDomingo.length; i++) {
			String pelicula = peliculasDomingo[i];
			if ((null != pelicula) && (esDelGenero(pelicula, genero))) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public static boolean hayHuecoParaPeliculasElSabado() {
		boolean ret = false;
		String[] peliculas = obtenerPeliculas(peliculasString);
		for (int i = 0; i < peliculas.length; i++) {
			String pelicula = peliculas[i];
			if ((null != pelicula) 
					&& (Integer.parseInt((obtenerDuracionPelicula(pelicula))) <= tiempoRestanteSabado)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public static boolean hayHuecoParaPeliculasElDomingo() {
		boolean ret = false;
		String[] peliculas = obtenerPeliculas(peliculasString);
		for (int i = 0; i < peliculas.length; i++) {
			String pelicula = peliculas[i];
			if ((null != pelicula)
					&& (Integer.parseInt((obtenerDuracionPelicula(pelicula))) <= tiempoRestanteDomingo)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public static String obtenerGeneroPelicula(String pelicula) {
		String[] parts = pelicula.split(",");
		return parts[0].trim();
	}

	public static String obtenerTituloPelicula(String pelicula) {
		String[] parts = pelicula.split(",");
		return parts[1].trim();
	}

	public static String obtenerDuracionPelicula(String pelicula) {
		String[] parts = pelicula.split(",");
		return parts[2].trim();
	}

	// ---------------------------------------------------------------------------------------------------------------//
	// El Main de la clase

	// Main de la clase
	public static void main(String[] args) {

		int seleccionMenuInicial = 0;
		boolean deAcuerdo = false;
		do {
			mostrarBienvenida();
			pedirElLogin();
			System.out.println(" ");

			// Bucle principal - Se sale con seleccionMenuInicial == 0
			do {

				mostrarPeliculasSeleccionadas();
				mostrarTiempoRestante();

				seleccionMenuInicial = mostrarMenuInicial();

				String peliculaSeleccionada = null;
				String[] peliculas;
				switch (seleccionMenuInicial) {
				case 1:
					if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
						System.out.println("Espacio libre para peliculas el sabado");
						peliculas = buscarPeliculas("Drama", tiempoRestanteSabado);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Drama");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Sabado
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElSabado("Drama")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Drama asignadas al Sabado");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al sabado");
								tiempoRestanteSabado = tiempoRestanteSabado - duracionPelicula;
								if (peliculasSeleccionadasSabado.length() == 0)
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + peliculaSeleccionada;
								else
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + " - "
											+ peliculaSeleccionada;
							}
						}
					} else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
						System.out.println("No hay espacio para peliculas el sabado");
						peliculas = buscarPeliculas("Drama", tiempoRestanteDomingo);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Drama");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Domingo
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElDomingo("Drama")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Drama asignadas al Domingo");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al domingo");
								tiempoRestanteDomingo = tiempoRestanteDomingo - duracionPelicula;
								if (peliculasSeleccionadasDomingo.length() == 0)
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo
											+ peliculaSeleccionada;
								else
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo + " - "
											+ peliculaSeleccionada;
							}
						}
					} else {
						System.out.println("No hay espacio para peliculas ni el sabado ni el domingo");
					}

					break;
				case 2:
					if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
						System.out.println("Espacio libre para peliculas el sabado");
						peliculas = buscarPeliculas("Comedia", tiempoRestanteSabado);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Comedia");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Sabado
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElSabado("Comedia")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Comedia asignadas al Sabado");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al sabado");
								tiempoRestanteSabado = tiempoRestanteSabado - duracionPelicula;
								if (peliculasSeleccionadasSabado.length() == 0)
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + peliculaSeleccionada;
								else
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + " - "
											+ peliculaSeleccionada;
							}
						}
					} else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
						System.out.println("No hay espacio para peliculas el sabado");
						peliculas = buscarPeliculas("Drama", tiempoRestanteDomingo);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Comedia");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Domingo
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElDomingo("Comedia")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Comedia asignadas al Domingo");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al domingo");
								tiempoRestanteDomingo = tiempoRestanteDomingo - duracionPelicula;
								if (peliculasSeleccionadasDomingo.length() == 0)
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo
											+ peliculaSeleccionada;
								else
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo + " - "
											+ peliculaSeleccionada;
							}
						}
					} else {
						System.out.println("No hay espacio para peliculas ni el sabado ni el domingo");
					}

					break;
				case 3:
					if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
						System.out.println("Espacio libre para peliculas el sabado");
						peliculas = buscarPeliculas("Terror", tiempoRestanteSabado);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Terror");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Sabado
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElSabado("Terror")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Terror asignadas al Sabado");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al sabado");
								tiempoRestanteSabado = tiempoRestanteSabado - duracionPelicula;
								if (peliculasSeleccionadasSabado.length() == 0)
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + peliculaSeleccionada;
								else
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + " - "
											+ peliculaSeleccionada;
							}
						}
					} else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
						System.out.println("No hay espacio para peliculas el sabado");
						peliculas = buscarPeliculas("Terror", tiempoRestanteDomingo);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Terror");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Domingo
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElDomingo("Terror")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Terror asignadas al Domingo");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al domingo");
								tiempoRestanteDomingo = tiempoRestanteDomingo - duracionPelicula;
								if (peliculasSeleccionadasDomingo.length() == 0)
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo
											+ peliculaSeleccionada;
								else
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo + " - "
											+ peliculaSeleccionada;
							}
						}
					} else {
						System.out.println("No hay espacio para peliculas ni el sabado ni el domingo");
					}

					break;
				case 4:
					if ((tiempoRestanteSabado >= 0) && (hayHuecoParaPeliculasElSabado())) {
						System.out.println("Espacio libre para peliculas el sabado");
						peliculas = buscarPeliculas("Ciencia", tiempoRestanteSabado);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Ciencia");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Sabado
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElSabado("Ciencia")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Ciencia asignadas al Sabado");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al sabado");
								tiempoRestanteSabado = tiempoRestanteSabado - duracionPelicula;
								if (peliculasSeleccionadasSabado.length() == 0)
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + peliculaSeleccionada;
								else
									peliculasSeleccionadasSabado = peliculasSeleccionadasSabado + " - "
											+ peliculaSeleccionada;
							}
						}
					} else if ((tiempoRestanteDomingo >= 0) && (hayHuecoParaPeliculasElDomingo())) {
						System.out.println("No hay espacio para peliculas el sabado");
						peliculas = buscarPeliculas("Ciencia", tiempoRestanteDomingo);
						peliculaSeleccionada = mostrarPeliculas(peliculas, "Ciencia");

						// Pueden haber seleccionado 0 -> Volver...
						if (null != peliculaSeleccionada) {

							// Tenemos que añadir la pelicula al Domingo
							int duracionPelicula = Integer.parseInt(obtenerDuracionPelicula(peliculaSeleccionada));

							// ¿Hay ya peliculas del mismo genero?
							if (yaHayPeliculasDelGeneroElDomingo("Ciencia")) {
								// No podemos añadirla...
								System.out.println("Ya hay peliculas de Ciencia asignadas al Domingo");
							} else {
								// Si podemos añadirla...
								System.out.println("La pelicula se asigna al domingo");
								tiempoRestanteDomingo = tiempoRestanteDomingo - duracionPelicula;
								if (peliculasSeleccionadasDomingo.length() == 0)
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo
											+ peliculaSeleccionada;
								else
									peliculasSeleccionadasDomingo = peliculasSeleccionadasDomingo + " - "
											+ peliculaSeleccionada;
							}
						}
					} else {
						System.out.println("No hay espacio para peliculas ni el sabado ni el domingo");
					}

					break;
				case 5:
					/**
					 * --------- - FALTA - ---------
					 */
					if (estaDeAcuerdo()) {
						mostrarFin();
					} else {
						resetear();
					}
					break;
				case 0:
					System.out.println("Adios!!");
					break;
				default:
					System.out.println("Este mensaje no deberia mostrarse");
				}

			} while (seleccionMenuInicial != 0);

		} while ((seleccionMenuInicial != 0) && (!deAcuerdo));
		teclado.close();
	}
}