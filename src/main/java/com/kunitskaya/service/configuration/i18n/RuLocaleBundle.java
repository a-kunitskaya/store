package com.kunitskaya.service.configuration.i18n;

import java.util.ListResourceBundle;

public class RuLocaleBundle extends ListResourceBundle{
	public Object[][] getContents() {
		return contents;
	}

	private static final Object[][] contents = {
			{"username", "Логин"},
			{"password", "Пароль"},
			{"logInBtn", "ВОЙТИ"},
			{"registrationBtn", "РЕГИСТРАЦИЯ"},
			{"vendor", "ПРОДАВЕЦ"},
			{"customer", "ПОКУПАТЕЛЬ"},
			{"welcome", "СПАСИБО ЗА РЕГИСТРАЦИЮ!"},
			{"successRegistrationMsg", "Ваша регистрация прошла успешно. Поажлуйста, войдите."},
			{"role", "Роль"},
			{"addBtn", "ДОБАВИТЬ В КОРЗИНУ"},
			{"removeBtn", "УДАЛИТЬ ИЗ КОРЗИНЫ"},
			{"viewOrderBtn", "ПОСМОТРЕТЬ ЗАКАЗ"},
			{"viewOrderMsg", "Ваш заказ. Статуc заказа = 'СОЗДАН'"},
			{"checkoutBtn", "КУПИТЬ"},
			{"checkoutMsg", "Спасибо за заказ, мы доставим его в ближайшее время. Статус заказа = 'ГОТОВ'. Пожалуйста, нажмите 'ОТМЕНИТЬ' для отмены заказа"},
			{"cancelBtn", "ОТМЕНИТЬ"},
			{"cancelledOrderMsg", "Ваш заказ отменен. Пожалуйста, продолжите покупки, чтобы сроздать новый"}
	};
}
