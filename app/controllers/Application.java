package controllers;

import play.mvc.Controller;
import play.mvc.WebSocket;

public class Application extends Controller {

	/**
	 * EntryPoint
	 * @return
	 */
	public static WebSocket<String> index() {
		return new WebSocket<String>() {

			public void onReady(WebSocket.In<String> in,
					WebSocket.Out<String> out) {
				out.write("Hello!");
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);
						out.write(">>>" + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// Fin du websocket
				out.close();
			}

		};
	}

}
