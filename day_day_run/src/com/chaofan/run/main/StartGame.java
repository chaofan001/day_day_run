package com.chaofan.run.main;

import com.chaofan.run.view.EndView;
import com.chaofan.run.view.LoadingView;
import com.chaofan.run.view.LoginView;
import com.chaofan.run.view.MainView;
import com.chaofan.run.view.PlayView;

public class StartGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LoginView().loginShow();
		//new MainView().mainShow();
		//new PlayView().playShow();
		//new LoadingView().run();
	}

}
