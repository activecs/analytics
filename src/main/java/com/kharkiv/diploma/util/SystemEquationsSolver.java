package com.kharkiv.diploma.util;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Клас, що розв'язує системи рівнянь методом Ґаусса-Жордана
 * 
 */
@Component
@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SystemEquationsSolver {

	private double[][] coefficients; // Рядок - колонка

	public double[] solve(double[][] coefficients) {
		this.coefficients = coefficients;
		simplify();
		makeNullsInUpperLines();
		double[] result = new double[coefficients.length];
		// У результат переписую вільні члени після розв'язання
		for (int i = 0; i < result.length; i++) {
			result[i] = coefficients[i][coefficients[i].length - 1];
		}
		return result;
	}

	public void print() {
		for (int i = 0; i < coefficients.length; i++) {
			for (int j = 0; j < coefficients.length; j++) {
				System.out.printf("%-7f ", coefficients[i][j]);
			}
			System.out.println(" = "
					+ coefficients[i][coefficients[i].length - 1]);
		}

	}

	/**
	 * Спрощує та розв'язує систему. Використовується рекурсія
	 * 
	 * @return двовимірний масив коефіцієнтів розв'язаної системи
	 */
	private double[][] simplify() {
		int[] notNull = findFirstNotNull();
		makeFirstLineNotStartingWithNull(notNull[0], notNull[1]);
		// Всі елементи 1 рядка діляться на 1 елемент обраної колонки
		divideOn(0, coefficients[notNull[0]][notNull[1]]);
		// Отримання нулів у першій колонці
		makeNulls();
		SystemEquationsSolver es = new SystemEquationsSolver();
		es.coefficients = getTruncatedArray();
		if (es.coefficients.length > 1) { // Роблю так доки не залишається
			// жодного рівняння.
			// Рекурсивний виклик самої себе для обробки масиву
			// коефіцієнтів розміром n-1
			es.simplify();
		} else {
			es.coefficients[0][1] /= es.coefficients[0][0];
			es.coefficients[0][0] = 1;
		}
		this.rip(es);
		return this.coefficients;
	}

	/**
	 * Обирає першу зліва колонку, де є ненульове значення
	 * 
	 * @return координати цього числа (рядок-колонка)
	 */
	private int[] findFirstNotNull() {
		int[] Result = new int[2];
		Result[0] = 0;
		Result[1] = 0;
		find_column: for (Result[0] = 0; Result[0] < coefficients.length; Result[0]++) {
			for (Result[1] = 0; Result[1] < coefficients.length - 1; Result[1]++) {
				if ((coefficients[Result[1]][Result[0]]) != 0)
					break find_column;
			}
		}
		return Result;
	}

	/**
	 * Якщо перше число у 1 рядку 0, то міняю її місцями з тим, у якого перше
	 * число не нуль
	 * 
	 * @param i
	 *            Номер рядка з ненульовим елементом
	 * @param h
	 *            Номер стовпчика
	 */
	private void makeFirstLineNotStartingWithNull(int i, int h) {
		if (coefficients[0][0] == 0) {
			// Знаходжу рядок, що не починається з нуля
			int j;
			for (j = 1; j < coefficients.length; j++) {
				if ((coefficients[j][0]) != 0)
					break;
				else if (j == coefficients.length - 1) {
					// Немає ненульових коефіцієнтів у першій колонці
					// Метод не спрацює
					System.out
							.println("Помилка: У першій колонці нема ненульових елементів...");
					System.out.println("Коректна робота не гарантується");
					// System.exit(-1); //Можна б і завершити програму
				}
			}
			double[] t = new double[coefficients[0].length];
			// Запам'ятовую старі коефіцієнти першого рівняння
			t = coefficients[0];
			coefficients[0] = coefficients[j];
			coefficients[j] = t;
		}
	}

	/**
	 * 
	 * Ділить рядок з вказаним номером на вказане число
	 * 
	 * @param lineNumber
	 *            Який рядок поділити
	 * @param p
	 *            На що поділити
	 */
	private void divideOn(int lineNumber, double p) {
		for (int j = 0; j < coefficients[0].length; j++) {
			coefficients[lineNumber][j] /= p;
		}
	}

	/**
	 * Віднімаю від інших рівнянь перше рівняння, помножене на їх перші
	 * коефіцієнти
	 */
	private void makeNulls() {
		for (int k = 1; k < coefficients.length; k++) { // Для кожного рівн.,
			// поч з ІІ
			double t = coefficients[k][0]; // Перший елемент кожного рівняння
			for (int l = 0; l < coefficients[0].length; l++) {// Для кожного
				// коефіцієнта
				// рівняння
				coefficients[k][l] -= coefficients[0][l] * t;
			}
		}
	}

	/**
	 * Обрізає масив коефіцієнтів
	 * 
	 * @return масив коефіцієнтів із вирізаними першими рядком та стовпчиком
	 */
	private double[][] getTruncatedArray() {
		double[][] result = new double[coefficients.length - 1][coefficients[0].length - 1];
		for (int k = 1; k < coefficients.length; k++) {
			for (int l = 1; l < coefficients[0].length; l++) {
				result[k - 1][l - 1] = coefficients[k][l];
			}
		}
		return result;
	}

	/**
	 * Витягує у масив коефіцієнтів викликаючого екземпляра дані з масиву
	 * екземпляра-параметра. Мається на увазі поміщення коефіцієнтів параметра у
	 * нижній прямокутник nx(n-1)масиву викликаючого екземпляра
	 * 
	 * @param o
	 *            екземляр з даними
	 */
	private void rip(SystemEquationsSolver o) {
		for (int i = 0; i < o.coefficients.length; i++) {
			for (int j = 0; j < o.coefficients[i].length; j++) {
				// Рядок із врахуванням зміщення у коефіцієнтах викл.
				// екземплярів
				int line = this.coefficients.length - 1 - i;
				// Колонка із врахуванням зміщення у коефіцієнтах
				// викл.екземплярів
				int column = this.coefficients[line].length - 1 - j;
				// Номер рядка у коеф. параметра
				int parLine = o.coefficients.length - i - 1;
				// Номер колонки у коеф. параметра
				int parCol = o.coefficients[parLine].length - j - 1;
				// Переписую коефіцієнти із зміщенням
				this.coefficients[line][column] = o.coefficients[parLine][parCol];

			}
		}
	}

	/**
	 * Віднімає з верхніх рядків останні, помножені на відповідні коефіцієнти, з
	 * метою отримати нулі всюди, крім головної діагоналі та вільних членів.
	 */
	private void makeNullsInUpperLines() {
		int j = 0;
		for (int i = coefficients.length - 1; i >= 0; i--) { // Номер
			// активного
			// рівняння
			for (j = i - 1; j >= 0; j--) { // Номер рівняння, яке
				// перетворюється
				// Номер останнього коефіцієнта (вільного члена) у рядку
				int t = coefficients[i].length - 1;
				// Проводжу операцію над вільним членом
				coefficients[j][t] -= coefficients[j][i] * coefficients[i][t];
				// Коефіцієнт над одиницею перетворюється в нуль
				coefficients[j][i] = 0;
			}
		}
	}
}
